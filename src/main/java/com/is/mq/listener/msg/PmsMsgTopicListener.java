package com.is.mq.listener.msg;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.is.ggkz.entity.GgkzUserInfo;
import com.is.session.context.IsSessionContext;

/**
 * 
 * <p>文件名称: PmsMsgTopicListener.java</p>
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
public class PmsMsgTopicListener implements MessageListener
{

    private static Logger          LOGGER                 = LoggerFactory.getLogger(PmsMsgTopicListener.class);

    private IsSessionContext isSessionContext = IsSessionContext.getInstance();

    private Session                session;

    private GgkzUserInfo           user;

    public PmsMsgTopicListener(Session session, GgkzUserInfo user)
    {
        this.session = session;
        this.user = user;
    }

    @Override
    public void onMessage(Message message)
    {
        //        synchronized (PmsMsgTopicListener.class)
        //        {
        try
        {
            //                String userId = user.getUserId().toUpperCase();
            int rst = isSessionContext.transeIoSession(user, message);
            if (rst == 1)
            {
                //                    message.acknowledge();
                if (session.getAcknowledgeMode() == Session.SESSION_TRANSACTED)
                {
                    session.commit();
                    if (LOGGER.isInfoEnabled())
                    {
                        LOGGER.info(user.getUserId());
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
