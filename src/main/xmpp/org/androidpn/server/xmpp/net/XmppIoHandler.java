/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.androidpn.server.xmpp.net;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.androidpn.server.xmpp.session.ClientSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.dom4j.io.XMPPPacketReader;
import org.jivesoftware.openfire.net.MXParser;
import org.jivesoftware.openfire.nio.XMLLightweightParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.is.sys.properties.SystemPropertiesUtil;

/** 
 * This class is to create new sessions, destroy sessions and deliver
 * received XML stanzas to the StanzaHandler.
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class XmppIoHandler implements IoHandler
{

    private static final Log                      LOGGER         = LogFactory.getLog(XmppIoHandler.class);

    public static final String                    XML_PARSER     = "XML_PARSER";

    private static final String                   CONNECTION     = "CONNECTION";

    private static final String                   STANZA_HANDLER = "STANZA_HANDLER";

    private String                                serverName;

    private static Map<Integer, XMPPPacketReader> parsers        = new ConcurrentHashMap<Integer, XMPPPacketReader>();

    private static XmlPullParserFactory           factory        = null;

    static
    {
        try
        {
            factory = XmlPullParserFactory.newInstance(MXParser.class.getName(), null);
            factory.setNamespaceAware(true);
        }
        catch (XmlPullParserException e)
        {
            LOGGER.debug("Error creating a parser factory", e);
        }
    }

    /**
     * Constructor. Set the server name from server instance. 
     */
    protected XmppIoHandler()
    {
        serverName = SystemPropertiesUtil.getStringPropertie("xmpp.domain", "127.0.0.1").toLowerCase();
    }

    /**
     * Invoked from an I/O processor thread when a new connection has been created.
     */
    @Override
    public void sessionCreated(IoSession session) throws Exception
    {
        LOGGER.debug("sessionCreated()...");
    }

    /**
     * Invoked when a connection has been opened.
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception
    {
        LOGGER.debug("sessionOpened()...");
        LOGGER.debug("remoteAddress=" + session.getRemoteAddress());
        // Create a new XML parser
        XMLLightweightParser parser = new XMLLightweightParser("UTF-8");
        session.setAttribute(XML_PARSER, parser);
        // Create a new connection
        Connection connection = new Connection(session);
        session.setAttribute(CONNECTION, connection);
        session.setAttribute(STANZA_HANDLER, new StanzaHandler(serverName, connection));
    }

    /**
     * Invoked when a connection is closed.
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception
    {
        LOGGER.debug("sessionClosed()...");
        //        Object colsederObj = session.getAttribute(MessageTool.COLSEDER);
        //        if (colsederObj == null)
        //        {//客户端关闭
        //            session.setAttribute(MessageTool.CLIENTCLOSED);
        //            while (session.containsAttribute(MessageTool.RECEIVEMSG))
        //            {
        //                try
        //                {
        //                    Thread.sleep(10);
        //                }
        //                catch (Exception e)
        //                {
        //                            if (LOGGER.isDebugEnabled())
        //                            {
        //                                LOGGER.debug("sessionClosed异常：{}", e);
        //                            }
        //                }
        //            }
        StanzaHandler stanzaHandler = (StanzaHandler) session.getAttribute(STANZA_HANDLER);
        if (stanzaHandler != null)
        {
            ClientSession clientSession = (ClientSession) stanzaHandler.getSession();
            if (clientSession != null)
            {
                javax.jms.Session jmsSession = clientSession.getJmsSession();
                if (jmsSession != null)
                {
                    try
                    {
                        jmsSession.close();
                    }
                    catch (Exception e)
                    {
                        if (LOGGER.isDebugEnabled())
                        {
                            LOGGER.debug("sessionClosed异常：{}", e);
                        }
                    }
                    clientSession.setJmsSession(null);
                }
            }
        }
        Connection connection = (Connection) session.getAttribute(CONNECTION);
        connection.close();
        //        }
        //        else
        //        {//服务端关闭
        //        }

    }

    /**
     * Invoked with the related IdleStatus when a connection becomes idle.
     */
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception
    {
        LOGGER.debug("sessionIdle()...");
        Connection connection = (Connection) session.getAttribute(CONNECTION);
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("Closing connection that has been idle: " + connection);
        }
        connection.close();
    }

    /**
     * Invoked when any exception is thrown.
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception
    {
        LOGGER.debug("exceptionCaught()...");
        LOGGER.debug(cause);
        session.close(true);
    }

    /**
     * Invoked when a message is received.
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception
    {
        LOGGER.debug("messageReceived()...");
        LOGGER.debug("RCVD: " + message);

        // Get the stanza handler
        StanzaHandler handler = (StanzaHandler) session.getAttribute(STANZA_HANDLER);

        // Get the XMPP packet parser
        int hashCode = Thread.currentThread().hashCode();
        XMPPPacketReader parser = parsers.get(hashCode);
        if (parser == null)
        {
            parser = new XMPPPacketReader();
            parser.setXPPFactory(factory);
            parsers.put(hashCode, parser);
        }

        // The stanza handler processes the message
        try
        {
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug(message);
            }
            handler.process((String) message, parser);
        }
        catch (Exception e)
        {
            LOGGER.debug("Closing connection due to error while processing message: " + message, e);
            Connection connection = (Connection) session.getAttribute(CONNECTION);
            connection.close();
        }
    }

    /**
     * Invoked when a message written by IoSession.write(Object) is sent out.
     */
    @Override
    public void messageSent(IoSession session, Object message) throws Exception
    {
        LOGGER.debug("messageSent()...");
    }

}