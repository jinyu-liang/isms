package com.is.mq.listener.msg;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;

import com.is.ggkz.entity.GgkzUserInfo;
import com.is.mq.listener.msg.util.MessageUtil;
import com.is.session.context.IsSessionContext;
import com.is.utils.xmpp.XmppUtil;

/**
 * 
 * <p>文件名称: PmsMessageQueueListener.java</p>
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
@SuppressWarnings("rawtypes")
public class PmsMessageQueueListener implements SessionAwareMessageListener
{

    private static Logger          LOGGER                 = LoggerFactory.getLogger(PmsMessageQueueListener.class);

    private IsSessionContext isSessionContext = IsSessionContext.getInstance();

    @Override
    public void onMessage(Message message, Session session) throws JMSException
    {
        //        synchronized (PmsMessageQueueListener.class)
        //        {
        try
        {
            String receiver = message.getStringProperty("receiver");
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug(receiver);
            }
            GgkzUserInfo ggkzUserInfo = new GgkzUserInfo();
            ggkzUserInfo.setUserId(receiver);

            Map<String, String> dataMap = new HashMap<String, String>();
            dataMap.put("receivername", message.getStringProperty("receivername"));
            dataMap.put("sendername", message.getStringProperty("sendername"));
            dataMap.put("sendtime", message.getStringProperty("sendtime"));
            dataMap.put("id", message.getStringProperty("id"));

            String mobiletheme = message.getStringProperty("theme");
            String msgbody = MessageUtil.messageTrans(message);

            JSONObject jsonobj = new JSONObject();
            jsonobj.accumulateAll(dataMap);

            if (MessageUtil.publickMsg.equals(receiver))
            {
                XmppUtil.sendBroadcast(mobiletheme, msgbody, jsonobj.toString());
            }
            else
            {
                XmppUtil.sendNotifcationToUser(receiver, mobiletheme, msgbody, jsonobj.toString());
            }

            int rst = isSessionContext.transeIoSession(ggkzUserInfo, message);
            if (rst == 1)
            {

                //                    message.acknowledge();
                if (session.getAcknowledgeMode() == Session.SESSION_TRANSACTED)
                {
                    session.commit();
                    if (LOGGER.isInfoEnabled())
                    {
                        LOGGER.info(receiver);
                    }
                }
            }
            //                else
            //                {
            //                    message.setJMSRedelivered(false);
            //                }
        }
        catch (Exception e)
        {
            LOGGER.error("处理消息时发生异常.", e);
        }
    }
    //    }
}
