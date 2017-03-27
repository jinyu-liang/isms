package com.is.mq.consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TopicSubscriber;

import net.sf.json.JSONArray;

import org.androidpn.server.xmpp.session.ClientSession;
import org.androidpn.server.xmpp.session.SessionManager;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springside.modules.utils.spring.SpringContextHolder;

import com.base.dict.UserUtil;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.mq.listener.msg.PmsMobileMsgTopicListener;
import com.is.mq.listener.msg.util.MessageUtil;
import com.is.utils.xmpp.XmppUtil;

/**
 * 
 * <p>文件名称: PmsMsgMobileTopicConsumer.java</p>
 * <p>文件描述: 本类描述</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年9月26日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
public class PmsMsgMobileTopicConsumer
{

    private static Logger LOGGER        = LoggerFactory.getLogger(PmsMsgMobileTopicConsumer.class);

    private Session       mobilesession = null;

    public PmsMsgMobileTopicConsumer()
    {
        CachingConnectionFactory cachingConnectionFactory = SpringContextHolder.getBean("cachingConnectionFactory");
        try
        {
            Connection connection = cachingConnectionFactory.createConnection();
            mobilesession = connection.createSession(true, Session.SESSION_TRANSACTED);
        }
        catch (JMSException e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("PmsMsgMobileTopicConsumer异常：{}", e);
            }
        }
    }

    public void receiveMessage(final GgkzUserInfo user)
    {
        if (user == null || user.getUserId() == null || "".equals(user.getUserId().trim()))
        {
            return;
        }

        ActiveMQTopic topicDestination = SpringContextHolder.getBean("topicDestination");
        String userId = user.getUserId().toLowerCase();
        try
        {
            ClientSession clientSession = SessionManager.getInstance().getSession(userId);
            //            IoSession ioSession = null;
            if (clientSession == null)
            {
                mobilesession.close();
                return;
            }
            else
            {
                org.androidpn.server.xmpp.net.Connection xmppconnection = clientSession.getConnection();
                if (xmppconnection == null)
                {
                    mobilesession.close();
                    return;
                }
                //                ioSession = xmppconnection.getIoSession();
                //                if (ioSession == null || !ioSession.isConnected())
                //                {
                //                    mobilesession.close();
                //                    return;
                //                }
            }
            StringBuffer sbf = new StringBuffer();
            sbf.append("receiver IN ('").append(userId).append("','").append(MessageUtil.publickMsg).append("'");
            if (user.getPost() != null)
            {
                sbf.append(",'post").append(user.getPost()).append("'");
            }
            sbf.append(")");
            TopicSubscriber mobiletopicSubscriber = mobilesession.createDurableSubscriber(topicDestination, userId, sbf.toString(), false);
            Message message = null;
            //            ioSession.setAttribute(MessageTool.RECEIVEMSG);
            List<Map<String, String>> msgData = new ArrayList<Map<String, String>>();
            while ((message = mobiletopicSubscriber.receiveNoWait()) != null)
            {
                try
                {
                    //                    if (message.getJMSRedelivered())
                    //                    {
                    //                        continue;
                    //                    }
                    //                    synchronized (ioSession)
                    //                    {
                    //                    if (ioSession.containsAttribute(MessageTool.CLIENTCLOSED))
                    //                    {
                    //                        ioSession.removeAttribute(MessageTool.RECEIVEMSG);
                    //                        return;
                    //                        //                            continue;
                    //                    }
                    Map<String, String> dataMap = new HashMap<String, String>();
                    String receivername = message.getStringProperty("receivername");
                    if (receivername == null || "".equals(receivername.trim()) || ";".equals(receivername.trim()))
                    {
                        receivername = UserUtil.getUserNameById(userId.toUpperCase());
                    }
                    dataMap.put("receivername", receivername);
                    dataMap.put("sendername", message.getStringProperty("sendername"));
                    dataMap.put("sendtime", message.getStringProperty("sendtime"));
                    dataMap.put("id", message.getStringProperty("id"));
                    dataMap.put("msg", MessageUtil.messageTrans(message));
                    dataMap.put("theme", message.getStringProperty("theme"));

                    msgData.add(dataMap);
                    //                    }
                }
                catch (JMSException e)
                {
                    if (LOGGER.isErrorEnabled())
                    {
                        LOGGER.error("receiveMessage异常：{}", e);
                    }
                    //                    if (ioSession.containsAttribute(MessageTool.CLIENTCLOSED))
                    //                    {
                    //                        ioSession.removeAttribute(MessageTool.RECEIVEMSG);
                    //                        return;
                    //                        //                        continue;
                    //                    }
                }
                catch (IOException e)
                {
                    if (LOGGER.isErrorEnabled())
                    {
                        LOGGER.error("receiveMessage异常：{}", e);
                    }
                    //                    if (ioSession.containsAttribute(MessageTool.CLIENTCLOSED))
                    //                    {
                    //                        ioSession.removeAttribute(MessageTool.RECEIVEMSG);
                    //                        return;
                    //                        //                        continue;
                    //                    }
                }
                //                try
                //                {
                //                    Thread.sleep(3000);
                //                }
                //                catch (InterruptedException e)
                //                {
                //                    if (LOGGER.isErrorEnabled())
                //                    {
                //                        LOGGER.error("receiveMessage异常：{}", e);
                //                    }
                //                }
                //                if (ioSession.containsAttribute(MessageTool.CLIENTCLOSED))
                //                {
                //                    ioSession.removeAttribute(MessageTool.RECEIVEMSG);
                //                    return;
                //                    //                    continue;
                //                }
            }
            //            if (ioSession.containsAttribute(MessageTool.CLIENTCLOSED))
            //            {
            //                ioSession.removeAttribute(MessageTool.RECEIVEMSG);
            //                return;
            //            }
            if (!msgData.isEmpty())
            {
                JSONArray jsonarray = new JSONArray();
                jsonarray.addAll(msgData);
                int rst = 0;
                if (msgData.size() == 1 && "destroy_mobile".equals(msgData.get(0).get("theme")))
                {
                    rst = XmppUtil.sendNotifcationToUser(userId, "destroy_mobile", "destroy_mobile", "");
                }
                else
                {
                    rst = XmppUtil.sendNotifcationToUser(userId, "新条消息(" + msgData.size() + ")条", jsonarray.toString(), "");
                }

                if (rst == 1)
                {
                    //                    message.acknowledge();
                }
                else
                {
                    //                    if (ioSession.containsAttribute(MessageTool.CLIENTCLOSED))
                    //                    {
                    //                        ioSession.removeAttribute(MessageTool.RECEIVEMSG);
                    //                        return;
                    //                        //                                continue;
                    //                    }
                }
            }
            if (mobilesession.getAcknowledgeMode() == Session.SESSION_TRANSACTED)
            {
                mobilesession.commit();
                if (LOGGER.isInfoEnabled())
                {
                    LOGGER.info(userId);
                }
            }
            mobiletopicSubscriber.setMessageListener(new PmsMobileMsgTopicListener(mobilesession, user));
        }
        catch (JMSException e)
        {
            if (!user.isCheckJMSException())
            {
                user.setCheckJMSException(true);
                ClientSession thatclientSession = SessionManager.getInstance().getSession(user.getUserId().toLowerCase());
                if (thatclientSession != null)
                {
                    javax.jms.Session jmsSession = thatclientSession.getJmsSession();
                    if (jmsSession != null)
                    {
                        try
                        {
                            jmsSession.close();
                            thatclientSession.setJmsSession(mobilesession);
                        }
                        catch (JMSException e1)
                        {
                            // TODO Auto-generated catch block
                            //                            if (LOGGER.isErrorEnabled())
                            //                            {
                            //                                LOGGER.error("receiveMessage异常：{}", e1);
                            //                            }
                        }
                    }
                    try
                    {
                        mobilesession.unsubscribe(userId);
                    }
                    catch (JMSException e2)
                    {
                        //                        if (LOGGER.isErrorEnabled())
                        //                        {
                        //                            LOGGER.error("receiveMessage异常：{}", e2);
                        //                        }
                    }
                    receiveMessage(user);
                }
            }
        }
    }

    public Session getMobilesession()
    {
        return mobilesession;
    }

}
