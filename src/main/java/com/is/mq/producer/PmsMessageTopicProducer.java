package com.is.mq.producer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.base.security.SpringSecurityUtils;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.ext.UserDetail;
import com.is.mq.listener.msg.util.MessageUtil;
import com.is.pms.msginteract.entity.MsgMessageInfo;
import com.is.utils.StringUtils;

/**
 * 
 * <p>文件名称: PmsMessageTopicProducer.java</p>
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
public class PmsMessageTopicProducer
{

    private JmsTemplate pmsMessageJmsTemplate;

    private Destination topicDestination;

    /**
     * 发送pub sub消息
     * @param receiverId 消息接收者ID
     * @param theme 消息主题（title标题）
     * @param msgObj 消息内容
     * @param senderId 消息发送者ID
     */
    public void sendTopic(final String receiverId, final String theme, final Object msgObj, final String senderId)
    {
        MsgMessageInfo msgMessageInfo = new MsgMessageInfo();
        if (StringUtils.isEmpty(senderId))
        {
            UserDetail sessionUser = (UserDetail) SpringSecurityUtils.getCurrentUser();
            if (sessionUser == null)
            {
                return;
            }
            msgMessageInfo.setSender(sessionUser.getUserId());
        }
        else
        {
            msgMessageInfo.setSender(senderId);
        }
        msgMessageInfo.setSendername();
        msgMessageInfo.setReceiver(receiverId);
        msgMessageInfo.setReceivername();
        msgMessageInfo.setTheme(theme);

        GgkzUserInfo user = new GgkzUserInfo();
        user.setUserId(receiverId);
        sendTopic(user, msgObj, msgMessageInfo);
    }

    /**
     * 发送pub sub消息
     * @param userId 消息接收者Id(可以为空)
     * @param msgObj 消息内容(一般是Sring)
     * @param property 消息属性(目前只支持MsgMessageInfo对象可以为空null)
     */
    public void sendTopic(final String userId, final Object msgObj, final Object property)
    {
        GgkzUserInfo user = new GgkzUserInfo();
        user.setUserId(userId);
        sendTopic(user, msgObj, property);
    }

    /**
     * 发送pub sub消息
     * @param user 消息接收者(可以为空)
     * @param msgObj 消息内容(一般是Sring)
     * @param property 消息属性(目前只支持MsgMessageInfo对象可以为空null)
     */
    public void sendTopic(final GgkzUserInfo user, final Object msgObj, final Object property)
    {
        String userId = user.getUserId();
        if (userId != null && !"".equals(userId.trim()))
        {
            user.setUserId(userId.toUpperCase());
            sendMessage(user, this.topicDestination, msgObj, property);
            user.setUserId(userId.toLowerCase());
            sendMessage(user, this.topicDestination, msgObj, property);
        }
        else
        {
            sendMessage(user, this.topicDestination, msgObj, property);
        }
    }

    /**
     * 使用jmsTemplate的send/MessageCreator()发送Map类型的消息并在Message中附加属性用于消息过滤.
     */
    private void sendMessage(final GgkzUserInfo user, Destination destination, final Object msgObj, final Object property)
    {
        this.pmsMessageJmsTemplate.send(destination, new MessageCreator()
        {
            @Override
            public Message createMessage(Session session) throws JMSException
            {
                Message message = null;
                if (msgObj == null)
                {
                    TextMessage msg = session.createTextMessage("你有新消息！");
                    message = msg;
                }
                else if (msgObj instanceof String)
                {
                    TextMessage msg = session.createTextMessage(msgObj.toString());
                    message = msg;
                }
                else if (msgObj instanceof Map)
                {
                    MapMessage msg = session.createMapMessage();

                    @SuppressWarnings("unchecked")
                    Map<Object, Object> map = (Map<Object, Object>) msgObj;
                    Iterator<Object> iterator = map.keySet().iterator();
                    while (iterator.hasNext())
                    {
                        Object key = iterator.next();
                        Object val = map.get(key);
                        if (val == null)
                        {
                            msg.setObject(key.toString(), val);
                        }
                        else if (val instanceof String)
                        {
                            msg.setString(key.toString(), val.toString());
                        }
                        else if (val instanceof Boolean)
                        {
                            msg.setBoolean(key.toString(), (Boolean) val);
                        }
                        else if (val instanceof Byte)
                        {
                            msg.setByte(key.toString(), (Byte) val);
                        }
                        else if (val instanceof Byte[])
                        {
                            msg.setBytes(key.toString(), (byte[]) val);
                        }
                        else if (val instanceof Character)
                        {
                            msg.setChar(key.toString(), (Character) val);
                        }
                        else if (val instanceof Double)
                        {
                            msg.setDouble(key.toString(), (Double) val);
                        }
                        else if (val instanceof Float)
                        {
                            msg.setFloat(key.toString(), (Float) val);
                        }
                        else if (val instanceof Integer)
                        {
                            msg.setInt(key.toString(), (Integer) val);
                        }
                        else if (val instanceof Long)
                        {
                            msg.setLong(key.toString(), (Long) val);
                        }
                        else if (val instanceof Short)
                        {
                            msg.setShort(key.toString(), (Short) val);
                        }
                        else
                        {
                            msg.setObject(key.toString(), (Short) val);
                        }
                    }
                    message = msg;
                }
                if (user != null && user.getUserId() != null && !"".equals(user.getUserId().trim()))
                {
                    message.setStringProperty("receiver", user.getUserId());
                }
                else
                {
                    message.setStringProperty("receiver", MessageUtil.publickMsg);
                }
                if (property != null && property instanceof MsgMessageInfo)
                {
                    MsgMessageInfo msgMessageInfo = (MsgMessageInfo) property;
                    String receivername = msgMessageInfo.getReceivername();
                    String sendername = msgMessageInfo.getSendername();
                    String id = msgMessageInfo.getId();
                    String theme = msgMessageInfo.getTheme();

                    message.setStringProperty("receivername", receivername == null ? "" : receivername);
                    message.setStringProperty("sendername", sendername == null ? "" : sendername);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    message.setStringProperty("sendtime", sdf.format(new Date()));
                    message.setStringProperty("id", id == null ? "" : id);
                    message.setStringProperty("theme", theme == null ? "" : theme);
                }
                return message;
            }
        });
    }

    public JmsTemplate getPmsMessageJmsTemplate()
    {
        return pmsMessageJmsTemplate;
    }

    public void setPmsMessageJmsTemplate(JmsTemplate pmsMessageJmsTemplate)
    {
        this.pmsMessageJmsTemplate = pmsMessageJmsTemplate;
    }

    public Destination getTopicDestination()
    {
        return topicDestination;
    }

    public void setTopicDestination(Destination topicDestination)
    {
        this.topicDestination = topicDestination;
    }

}
