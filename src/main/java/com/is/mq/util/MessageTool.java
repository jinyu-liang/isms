package com.is.mq.util;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TopicSubscriber;

import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springside.modules.utils.spring.SpringContextHolder;

import com.is.ggkz.entity.GgkzUserInfo;
import com.is.mq.listener.msg.PmsMobileMsgTopicListener;
import com.is.mq.listener.msg.PmsMsgTopicListener;
import com.is.mq.listener.msg.util.MessageUtil;

public class MessageTool
{

    private static Logger      LOGGER       = LoggerFactory.getLogger(MessageTool.class);

    public static final String RECEIVEMSG   = "RECEIVEMSG";

    public static final String CLIENTCLOSED = "CLIENTCLOSED";

    public static final String JSESSIONID   = "JSESSIONID";

    public static final String USERID       = "USERID";

    public static final String COLSEDER     = "COLSEDER";

    /**
     * 订阅消息
     * @param userId 用户ID
     * @param post 用户职位(必须以post开始) 
     */
    public static void subscribConsumer(String userId, String post)
    {
        GgkzUserInfo ggkzUserInfo = new GgkzUserInfo();

        ggkzUserInfo.setUserId(userId);
        ggkzUserInfo.setPost(post);

        subscribConsumer(ggkzUserInfo);
    }

    /**
     * 订阅消息
     * @param userInfo 用户对象 (post属性必须post开始)
     */
    public static void subscribConsumer(GgkzUserInfo userInfo)
    {
        String userId = userInfo.getUserId();
        if (userId != null && !"".equals(userId.trim()))
        {
            CachingConnectionFactory cachingConnectionFactory = SpringContextHolder.getBean("cachingConnectionFactory");
            ActiveMQTopic topicDestination = SpringContextHolder.getBean("topicDestination");
            if (cachingConnectionFactory != null && topicDestination != null)
            {
                try
                {
                    Connection connection = cachingConnectionFactory.createConnection();
                    if (connection != null)
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
                        LOGGER.error("subscribConsumer异常：{}", e);
                    }
                }
            }
        }
    }

    /**
     * 注销消息订阅
     * @param userId 用户ID
     */
    public static void unsubscribConsumer(String userId)
    {
        GgkzUserInfo ggkzUserInfo = new GgkzUserInfo();

        ggkzUserInfo.setUserId(userId);
        unsubscribConsumer(ggkzUserInfo);
    }

    /**
     * 注销消息订阅
     * @param userInfo 用户对象
     */
    public static void unsubscribConsumer(GgkzUserInfo userInfo)
    {
        String userId = userInfo.getUserId();
        if (userId != null && !"".equals(userId))
        {
            CachingConnectionFactory cachingConnectionFactory = SpringContextHolder.getBean("cachingConnectionFactory");
            try
            {
                Connection connection = cachingConnectionFactory.createConnection();
                Session session = connection.createSession(true, Session.SESSION_TRANSACTED);

                session.unsubscribe(userId.toUpperCase());
                session.unsubscribe(userId.toLowerCase());
                session.close();
            }
            catch (JMSException e)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("unsubscribe异常：{}", e);
                }
            }
        }
    }
}
