package com.is.mq.subscribe;

import java.util.List;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TopicSubscriber;

import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;

import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.service.GgkzUserInfoServiceImpl;
import com.is.mq.listener.msg.PmsMobileMsgTopicListener;
import com.is.mq.listener.msg.PmsMsgTopicListener;
import com.is.mq.listener.msg.util.MessageUtil;

public class SubscribeConsumerService
{
    private static Logger LOGGER = LoggerFactory.getLogger(SubscribeConsumerService.class);

    private SubscribeConsumerService(CachingConnectionFactory cachingConnectionFactory, GgkzUserInfoServiceImpl ggkzUserInfoServiceImpl,
            ActiveMQTopic topicDestination)
    {
        List<GgkzUserInfo> ggkzUserInfoList = ggkzUserInfoServiceImpl.selectUsers();
        if (ggkzUserInfoList != null)
        {
            Connection connection;
            try
            {
                connection = cachingConnectionFactory.createConnection();
                for (GgkzUserInfo userInfo : ggkzUserInfoList)
                {
                    try
                    {
                        String userId = userInfo.getUserId();
                        if (userId != null && !"".equals(userId.trim()))
                        {
                            userId = userId.trim();

                            StringBuffer sbf = new StringBuffer();
                            sbf.append("receiver IN ('").append(userId.toUpperCase()).append("','").append(MessageUtil.publickMsg).append("'");
                            if (userInfo.getPost() != null)
                            {
                                sbf.append(",'POST").append(userInfo.getPost()).append("'");
                            }
                            sbf.append(")");
                            Session websession = connection.createSession(true, Session.SESSION_TRANSACTED);
                            TopicSubscriber webtopicSubscriber = websession.createDurableSubscriber(topicDestination, userId.toUpperCase(),
                                    sbf.toString(), false);
                            webtopicSubscriber.setMessageListener(new PmsMsgTopicListener(websession, userInfo));
                            websession.close();

                            sbf.delete(0, sbf.length());
                            sbf.append("receiver IN ('").append(userId.toLowerCase()).append("','").append(MessageUtil.publickMsg).append("'");
                            if (userInfo.getPost() != null)
                            {
                                sbf.append(",'post").append(userInfo.getPost()).append("'");
                            }
                            sbf.append(")");
                            Session mobilesession = connection.createSession(true, Session.SESSION_TRANSACTED);
                            TopicSubscriber mobiletopicSubscriber = mobilesession.createDurableSubscriber(topicDestination, userId.toLowerCase(),
                                    sbf.toString(), false);
                            mobiletopicSubscriber.setMessageListener(new PmsMobileMsgTopicListener(mobilesession, userInfo));
                            mobilesession.close();
                        }
                    }
                    catch (JMSException e)
                    {
                        if (LOGGER.isErrorEnabled())
                        {
                            LOGGER.error("PmsMessageTopicConsumer异常：{}", e);
                        }
                    }
                }
            }
            catch (JMSException e1)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("SubscribeConsumerService异常：{}", e1);
                }
            }

        }
    }

}
