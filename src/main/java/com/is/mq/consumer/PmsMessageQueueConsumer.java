package com.is.mq.consumer;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import com.is.mq.listener.msg.util.MessageUtil;

/**
 * 
 * <p>文件名称: PmsMessageListener.java</p>
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
public class PmsMessageQueueConsumer
{

    private static Logger LOGGER = LoggerFactory.getLogger(PmsMessageQueueConsumer.class);

    private JmsTemplate   pmsMessageJmsTemplate;

    private Destination   queueDestination;

    public void receiveMessage(final String userId, IoSession ioSession)
    {
        Message message = null;
        if (userId != null && !"".equals(userId.trim()))
        {
            String selector = "receiver='" + userId + "'";
            while ((message = pmsMessageJmsTemplate.receiveSelected(queueDestination, selector)) != null)
            {
                try
                {
                    //                    if (message.getJMSRedelivered())
                    //                    {
                    //                        continue;
                    //                    }
                    String receiver = message.getStringProperty("receiver");
                    if (receiver != null && !"".equals(receiver))
                    {
                        if (!receiver.equals(userId))
                        {
                            continue;
                        }
                    }
                    MessageUtil.socketTrans(ioSession, message);
                }
                catch (JMSException e)
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
