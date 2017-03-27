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
package org.androidpn.server.xmpp.session;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.JMSException;

import org.androidpn.server.xmpp.XmppServer;
import org.androidpn.server.xmpp.net.Connection;
import org.androidpn.server.xmpp.net.ConnectionCloseListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmpp.packet.JID;

/** 
 * This class manages the sessions connected to the server.
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class SessionManager
{

    private static final Log           log                   = LogFactory.getLog(SessionManager.class);

    private static final String        RESOURCE_NAME         = "AndroidpnClient";

    private static SessionManager      instance;

    private String                     serverName;

    private Map<String, ClientSession> preAuthSessions       = new ConcurrentHashMap<String, ClientSession>();

    private Map<String, ClientSession> clientSessions        = new ConcurrentHashMap<String, ClientSession>();

    private final AtomicInteger        connectionsCounter    = new AtomicInteger(0);

    private ClientSessionListener      clientSessionListener = new ClientSessionListener();

    private SessionManager()
    {
        serverName = XmppServer.getInstance().getServerName();

//        new CleanClientSessionTask().start();
    }

    /**
     * Returns the singleton instance of SessionManager.
     * 
     * @return the instance
     */
    public static SessionManager getInstance()
    {
        if (instance == null)
        {
            synchronized (SessionManager.class)
            {
                if (instance == null)
                {
                    instance = new SessionManager();
                }
            }
        }
        return instance;
    }

    /**
     * Creates a new ClientSession and returns it.
     *  
     * @param conn the connection
     * @return a newly created session
     */
    public ClientSession createClientSession(Connection conn)
    {
        if (serverName == null)
        {
            throw new IllegalStateException("Server not initialized");
        }

        Random random = new Random();
        String streamId = Integer.toHexString(random.nextInt());

        ClientSession session = new ClientSession(serverName, conn, streamId);
        conn.init(session);
        conn.registerCloseListener(clientSessionListener);

        // Add to pre-authenticated sessions
        synchronized (preAuthSessions)
        {
            preAuthSessions.put(session.getAddress().getResource(), session);
        }

        // Increment the counter of user sessions
        connectionsCounter.incrementAndGet();

        log.debug("ClientSession created.");
        return session;
    }

    /**
     * Adds a new session that has been authenticated. 
     *  
     * @param session the session
     */
    public void addSession(ClientSession session)
    {
        synchronized (preAuthSessions)
        {
            preAuthSessions.remove(session.getStreamID().toString());
        }
        synchronized (clientSessions)
        {
            clientSessions.put(session.getAddress().toString(), session);
        }
    }

    /**
     * Returns the session associated with the username.
     * 
     * @param username the username of the client address
     * @return the session associated with the username
     */
    public ClientSession getSession(String username)
    {
        // return getSession(new JID(username, serverName, null, true));
        return getSession(new JID(username, serverName, RESOURCE_NAME, true));
    }

    /**
     * Returns the session associated with the JID.
     * 
     * @param from the client address
     * @return the session associated with the JID
     */
    public ClientSession getSession(JID from)
    {
        if (from == null || serverName == null || !serverName.equals(from.getDomain()))
        {
            return null;
        }
        // Check pre-authenticated sessions
        if (from.getResource() != null)
        {
            synchronized (preAuthSessions)
            {
                ClientSession session = preAuthSessions.get(from.getResource());
                if (session != null)
                {
                    return session;
                }
            }
        }
        if (from.getResource() == null || from.getNode() == null)
        {
            return null;
        }
        return clientSessions.get(from.toString());
    }

    /**
     * Returns a list that contains all authenticated client sessions.
     * 
     * @return a list that contains all client sessions
     */
    public Collection<ClientSession> getSessions()
    {
        return clientSessions.values();
    }

    /**
     * Removes a client session.
     * 
     * @param session the session to be removed
     * @return true if the session was successfully removed 
     */
    public boolean removeSession(ClientSession session)
    {
        if (session == null || serverName == null)
        {
            return false;
        }
        JID fullJID = session.getAddress();

        boolean clientRemoved = false;

        // Remove the session from list
        synchronized (clientSessions)
        {
            ClientSession clientSession = clientSessions.remove(fullJID.toString());
            clientRemoved = clientSession != null;

            if (clientRemoved)
            {
                javax.jms.Session jmsSession = null;
                if (clientRemoved)
                {
                    jmsSession = clientSession.getJmsSession();
                }
                if (jmsSession != null)
                {
                    clientSession.setJmsSession(null);
                    try
                    {
                        jmsSession.close();
                    }
                    catch (JMSException e)
                    {
                        if (log.isErrorEnabled())
                        {
                            log.debug("removeSession异常：{}", e);
                        }
                    }
                }
            }
        }
        boolean preAuthRemoved = false;
        synchronized (preAuthSessions)
        {
            ClientSession preAuthclientSession = preAuthSessions.remove(fullJID.getResource());
            preAuthRemoved = preAuthclientSession != null;
            // Decrement the counter of user sessions
            if (preAuthRemoved)
            {
                javax.jms.Session jmsSession = null;
                if (preAuthRemoved)
                {
                    jmsSession = preAuthclientSession.getJmsSession();
                }
                if (jmsSession != null)
                {
                    preAuthclientSession.setJmsSession(null);
                    try
                    {
                        jmsSession.close();
                    }
                    catch (JMSException e)
                    {
                        if (log.isErrorEnabled())
                        {
                            log.debug("removeSession异常：{}", e);
                        }
                    }
                }

            }
        }
        if (clientRemoved || preAuthRemoved)
        {
            connectionsCounter.decrementAndGet();
            return true;
        }
        return false;
    }

    /**
     * Closes the all sessions. 
     */
    public void closeAllSessions()
    {
        try
        {
            // Send the close stream header to all connections
            Set<ClientSession> sessions = new HashSet<ClientSession>();
            synchronized (preAuthSessions)
            {
                sessions.addAll(preAuthSessions.values());
                for (ClientSession session : sessions)
                {
                    try
                    {
                        session.getConnection().systemShutdown();
                    }
                    catch (Throwable t)
                    {
                    }
                }
            }
            synchronized (clientSessions)
            {
                sessions.clear();
                sessions.addAll(clientSessions.values());
                for (ClientSession session : sessions)
                {
                    try
                    {
                        session.getConnection().systemShutdown();
                    }
                    catch (Throwable t)
                    {
                    }
                }
            }

        }
        catch (Exception e)
        {
        }
    }

    /**
     * A listner to handle a session that has been closed.
     */
    private class ClientSessionListener implements ConnectionCloseListener
    {

        public void onConnectionClose(Object handback)
        {
            try
            {
                ClientSession session = (ClientSession) handback;
                if (removeSession(session))
                {
                }
            }
            catch (Exception e)
            {
                log.debug("Could not close socket", e);
            }
        }
    }

    private class CleanClientSessionTask extends Thread
    {

        @Override
        public void run()
        {
            try
            {
                while (!isInterrupted())
                {
                    Thread.sleep(180 * 1000L);
                    synchronized (preAuthSessions)
                    {
                        if (!preAuthSessions.isEmpty())
                        {
                            Set<String> Keys = preAuthSessions.keySet();
                            long now = System.currentTimeMillis();
                            for (String key : Keys)
                            {
                                ClientSession item = preAuthSessions.get(key);
                                if ((now - item.getLastUseTime()) / 1000 > 300)
                                {
                                    preAuthSessions.remove(key);
                                }
                            }
                        }
                    }
                    synchronized (clientSessions)
                    {
                        if (!clientSessions.isEmpty())
                        {
                            Set<String> Keys = clientSessions.keySet();
                            long now = System.currentTimeMillis();
                            for (String key : Keys)
                            {
                                ClientSession item = clientSessions.get(key);
                                if ((now - item.getLastUseTime()) / 1000 > 300)
                                {
                                    clientSessions.remove(key);
                                }
                            }
                        }
                    }
                }
            }
            catch (final InterruptedException e)
            {

            }
        }

    }
}
