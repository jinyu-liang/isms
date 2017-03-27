package com.is.mq.consumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import com.base.dict.UserUtil;
import com.is.mq.listener.msg.util.MessageUtil;
import com.is.utils.xmpp.XmppUtil;

/**
 * 
 * <p>文件名称: PmsMsgMobileQueueConsumer.java</p>
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
public class PmsMsgMobileQueueConsumer
{

    private static Logger LOGGER = LoggerFactory.getLogger(PmsMsgMobileQueueConsumer.class);

    private JmsTemplate   pmsMessageJmsTemplate;

    private Destination   queueDestination;

    public void receiveMessage(final String userId)
    {
        if (userId == null || "".equals(userId.trim()))
        {
            return;
        }
        String selector = "receiver='" + userId.toLowerCase() + "'";
        Message message = null;
        while ((message = pmsMessageJmsTemplate.receiveSelected(queueDestination, selector)) != null)
        {
            try
            {
//                if (message.getJMSRedelivered())
//                {
//                    continue;
//                }
                String receiver = message.getStringProperty("receiver");
                if (receiver != null && !"".equals(receiver))
                {
                    if (!receiver.equals(userId))
                    {
                        continue;
                    }
                }
                Map<String, String> dataMap = new HashMap<String, String>();
                String receivername = message.getStringProperty("receivername");
                if (receivername == null || "".equals(receivername.trim()))
                {
                    receivername = UserUtil.getUserNameById(userId.toUpperCase());
                }
                dataMap.put("receivername", receivername);
                dataMap.put("sendername", message.getStringProperty("sendername"));
                dataMap.put("sendtime", message.getStringProperty("sendtime"));
                dataMap.put("id", message.getStringProperty("id"));

                JSONObject jsonobj = new JSONObject();
                jsonobj.accumulateAll(dataMap);

                XmppUtil.sendNotifcationToUser(userId, message.getStringProperty("theme"), MessageUtil.messageTrans(message), jsonobj.toString());
            }
            catch (JMSException e)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("receiveMessage异常：{}", e);
                }
            }
            catch (IOException e)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("receiveMessage异常：{}", e);
                }
            }
            try
            {
                Thread.sleep(3000);
            }
            catch (InterruptedException e)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("receiveMessage异常：{}", e);
                }
            }
        }
    }

    public JmsTemplate getPmsMessageJmsTemplate()
    {
        return pmsMessageJmsTemplate;
    }

    public void setPmsMessageJmsTemplate(JmsTemplate pmsMessageJmsTemplate)
    {
        this.pmsMessageJmsTemplate = pmsMessageJmsTemplate;
    }

    public Destination getQueueDestination()
    {
        return queueDestination;
    }

    public void setQueueDestination(Destination queueDestination)
    {
        this.queueDestination = queueDestination;
    }

}
