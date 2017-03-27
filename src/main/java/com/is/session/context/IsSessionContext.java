package com.is.session.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import net.sf.json.JSONArray;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.is.ggkz.entity.GgkzUserInfo;
import com.is.mq.listener.msg.util.MessageUtil;
import com.is.mq.util.MessageTool;
import com.is.pms.msginteract.io.msg.PmsWriteMessage;
import com.is.pms.msginteract.security.CheckSessionId;

/**
 * 
 * <p>文件名称: IsSessionContext.java</p>
 * <p>文件描述: 消息互动 flex socket 通信 session 管理器</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年9月26日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
public class IsSessionContext
{
    private static final Logger           LOGGER                 = LoggerFactory.getLogger(IsSessionContext.class);

    private static IsSessionContext isSessionContext = null;

    private Map<String, SessionObject>    sessionMap             = null;

    private IsSessionContext()
    {
        sessionMap = new HashMap<String, SessionObject>();
    }

    public static IsSessionContext getInstance()
    {
        if (isSessionContext == null)
        {
            synchronized (IsSessionContext.class)
            {
                if (isSessionContext == null)
                {
                    isSessionContext = new IsSessionContext();
                }
            }
        }
        return isSessionContext;
    }

    public void AddSession(String sessionid, SessionObject sessionObject)
    {
        //        synchronized (sessionMap)
        //        {
        if (!sessionMap.containsKey(sessionid))
        {
            if (sessionMap.containsValue(sessionObject))
            {
                try
                {
                    String thatSessionId = sessionObject.getThatSessionId();
                    if (thatSessionId != null)
                    {
                        SessionObject thatsessionObject = sessionMap.get(thatSessionId);
                        if (thatsessionObject != null)
                        {
                            IoSession ioSession = thatsessionObject.getIoSession();
                            if (ioSession != null && ioSession.isConnected())
                            {
                                PmsWriteMessage pmsWriteMessage = new PmsWriteMessage();
                                pmsWriteMessage.setResult("RemoteLogin");
                                pmsWriteMessage.setData("RemoteLogin");
                                ioSession.write(pmsWriteMessage);
                                ioSession.close(false);
                            }
                            Session jmsSession = thatsessionObject.getJmssession();
                            if (jmsSession != null)
                            {
                                try
                                {
                                    jmsSession.close();
                                }
                                catch (JMSException e)
                                {
                                    if (LOGGER.isErrorEnabled())
                                    {
                                        LOGGER.error("DelSession异常：{}", e);
                                    }
                                }
                            }
                        }
                        sessionMap.remove(thatSessionId);
                    }
                }
                catch (Exception e)
                {
                    LOGGER.error("{}", e);
                }
            }
            sessionMap.put(sessionid, sessionObject);
        }
        //        }
    }

    public void DelSession(String sessionid, String rstData, boolean serv)
    {
        //        synchronized (sessionMap)
        //        {
        try
        {
            if (sessionMap.containsKey(sessionid))
            {
                if (serv)
                {
                    SessionObject sessionObject = sessionMap.get(sessionid);
                    IoSession ioSession = sessionObject.getIoSession();
                    if (ioSession != null && ioSession.isConnected())
                    {
                        ioSession.setAttribute(MessageTool.COLSEDER, "SERVER");
                        PmsWriteMessage pmsWriteMessage = new PmsWriteMessage();
                        pmsWriteMessage.setResult(rstData);
                        pmsWriteMessage.setData(rstData);
                        ioSession.write(pmsWriteMessage);
                        ioSession.close(false);
                    }
                }
                SessionObject sessionObject = sessionMap.get(sessionid);
                Session jmsSession = sessionObject.getJmssession();
                if (jmsSession != null)
                {
                    try
                    {
                        jmsSession.close();
                    }
                    catch (JMSException e)
                    {
                        if (LOGGER.isErrorEnabled())
                        {
                            LOGGER.error("DelSession异常：{}", e);
                        }
                    }
                }
                sessionMap.remove(sessionid);
            }
            else
            {
                SessionObject sessionObject = new SessionObject();
                sessionObject.setUserId(sessionid);

                if (sessionMap.containsValue(sessionObject))
                {
                    String thatSessionId = sessionObject.getThatSessionId();
                    if (thatSessionId != null)
                    {
                        SessionObject thatsessionObject = sessionMap.get(thatSessionId);
                        if (thatsessionObject != null)
                        {
                            if (serv)
                            {
                                IoSession ioSession = thatsessionObject.getIoSession();
                                if (ioSession != null && ioSession.isConnected())
                                {
                                    PmsWriteMessage pmsWriteMessage = new PmsWriteMessage();
                                    pmsWriteMessage.setResult("RemoteLogin");
                                    pmsWriteMessage.setData("RemoteLogin");
                                    ioSession.write(pmsWriteMessage);
                                    ioSession.close(false);
                                }
                            }
                            Session jmsSession = thatsessionObject.getJmssession();
                            if (jmsSession != null)
                            {
                                try
                                {
                                    jmsSession.close();
                                }
                                catch (JMSException e)
                                {
                                    if (LOGGER.isErrorEnabled())
                                    {
                                        LOGGER.error("DelSession异常：{}", e);
                                    }
                                }
                            }
                        }
                        sessionMap.remove(thatSessionId);
                    }
                }
            }
        }
        catch (Exception e)
        {
            LOGGER.error("{}", e);
        }
        //        }
    }

    public void DelSession(IoSession ioSession, String rstData, boolean serv)
    {
        //        synchronized (sessionMap)
        //        {
        SessionObject that = new SessionObject();
        that.setIoSession(ioSession);
        if (!sessionMap.containsValue(ioSession))
        {
            String thatSessionId = that.getSessionId();
            if (thatSessionId != null)
            {
                try
                {
                    if (serv)
                    {//服务端关闭发送关闭原因
                        if (ioSession != null && ioSession.isConnected())
                        {
                            ioSession.setAttribute(MessageTool.COLSEDER, "SERVER");
                            PmsWriteMessage pmsWriteMessage = new PmsWriteMessage();
                            pmsWriteMessage.setResult(rstData);
                            pmsWriteMessage.setData(rstData);
                            ioSession.write(pmsWriteMessage);
                            ioSession.close(false);
                        }
                    }
                    SessionObject sessionObject = sessionMap.get(thatSessionId);
                    Session jmsSession = sessionObject.getJmssession();
                    if (jmsSession != null)
                    {
                        try
                        {
                            jmsSession.close();
                        }
                        catch (JMSException e)
                        {
                            if (LOGGER.isErrorEnabled())
                            {
                                LOGGER.error("DelSession异常：{}", e);
                            }
                        }
                    }
                    sessionMap.remove(thatSessionId);
                }
                catch (Exception e)
                {
                    LOGGER.error("{}", e);
                }
            }
        }
        //        }
    }

    public int transeIoSession(String sessionid, Object data)
    {
        int rst = 0;
        //        synchronized (sessionMap)
        //        {
        if (sessionMap.containsKey(sessionid))
        {
            try
            {
                IoSession ioSession = sessionMap.get(sessionid).getIoSession();
                if (ioSession != null && CheckSessionId.checker(sessionid))
                {
                    if (data instanceof Message)
                    {
                        rst = MessageUtil.socketTrans(ioSession, (Message) data);
                    }
                }
                else
                {
                    DelSession(sessionid, "SessionOutTime", true);
                }
            }
            catch (Exception e)
            {
                LOGGER.error("{}", e);
            }
        }
        //        }
        return rst;
    }

    public int transeIoSession(GgkzUserInfo user, Message message)
    {
        int rst = 0;
        //        synchronized (sessionMap)
        //        {
        try
        {
            List<Map<String, String>> msgData = new ArrayList<Map<String, String>>();
            Map<String, String> dataMap = new HashMap<String, String>();
            dataMap.put("msg", MessageUtil.messageTrans(message));
            dataMap.put("theme", message.getStringProperty("theme"));
            msgData.add(dataMap);
            JSONArray jsonarray = new JSONArray();
            jsonarray.addAll(msgData);

            if (MessageUtil.publickMsg.equals(user.getUserId()))
            {
                Set<String> keys = sessionMap.keySet();
                for (String key : keys)
                {
                    SessionObject item = sessionMap.get(key);
                    if (item != null)
                    {
                        IoSession ioSession = item.getIoSession();
                        if (ioSession != null && CheckSessionId.checker(key))
                        {
                            rst = MessageUtil.socketTrans(ioSession, jsonarray.toString());
                        }
                        else
                        {
                            DelSession(key, "SessionOutTime", true);
                        }
                    }
                }
            }
            else
            {
                SessionObject sessionObject = new SessionObject();
                sessionObject.setUserId(user.getUserId());
                if (sessionMap.containsValue(sessionObject))
                {
                    String thatSessionid = sessionObject.getThatSessionId();
                    IoSession ioSession = sessionMap.get(thatSessionid).getIoSession();
                    if (ioSession != null && CheckSessionId.checker(thatSessionid))
                    {
                        rst = MessageUtil.socketTrans(ioSession, jsonarray.toString());
                    }
                    else
                    {
                        DelSession(thatSessionid, "SessionOutTime", true);
                    }
                }
            }
        }
        catch (Exception e)
        {
            LOGGER.error("{}", e);
        }
        //        }
        return rst;
    }

    public void setIoSession(String sessionid, IoSession ioSession, Session jmssession)
    {
        //        synchronized (sessionMap)
        //        {
        try
        {
            if (sessionMap.containsKey(sessionid))
            {
                SessionObject sessionObject = sessionMap.get(sessionid);
                sessionObject.setIoSession(ioSession);
                Session thatjmsSession = sessionObject.getJmssession();
                if (thatjmsSession != null)
                {
                    try
                    {
                        thatjmsSession.close();
                    }
                    catch (JMSException e)
                    {
                        if (LOGGER.isErrorEnabled())
                        {
                            LOGGER.error("setIoSession异常：{}", e);
                        }
                    }
                }
                sessionObject.setJmssession(jmssession);
                sessionMap.put(sessionid, sessionObject);
            }
            else
            {
                SessionObject sessionObject = new SessionObject();

                sessionObject.setSessionId(sessionid);
                Object userId = ioSession.getAttribute(MessageTool.USERID);
                if (userId != null)
                {
                    sessionObject.setUserId(userId.toString());
                }
                sessionObject.setIoSession(ioSession);
                sessionObject.setJmssession(jmssession);
                sessionMap.put(sessionid, sessionObject);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("{}", e);
        }
        //        }
    }
}
