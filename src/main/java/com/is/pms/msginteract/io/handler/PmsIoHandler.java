package com.is.pms.msginteract.io.handler;

import javax.jms.Session;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.utils.spring.SpringContextHolder;

import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.ggkz.service.GgkzUserInfoServiceImpl;
import com.is.mq.consumer.PmsMessageTopicConsumer;
import com.is.mq.util.MessageTool;
import com.is.pms.msginteract.io.msg.PmsRecevieMessage;
import com.is.pms.msginteract.io.msg.PmsWriteMessage;
import com.is.session.context.IsSessionContext;

public class PmsIoHandler extends IoHandlerAdapter
{
    private static Logger          LOGGER                 = LoggerFactory.getLogger(PmsIoHandler.class);

    private IsSessionContext isSessionContext = IsSessionContext.getInstance();

    @Override
    public void sessionOpened(IoSession session) throws Exception
    {
        super.sessionOpened(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception
    {
        super.sessionClosed(session);
        Object colsederObj = session.getAttribute(MessageTool.COLSEDER);
        if (colsederObj == null)
        {//客户端关闭
            session.setAttribute(MessageTool.CLIENTCLOSED);
            while (session.containsAttribute(MessageTool.RECEIVEMSG))
            {
                try
                {
                    Thread.sleep(10);
                }
                catch (Exception e)
                {
                    if (LOGGER.isErrorEnabled())
                    {
                        LOGGER.error("sessionClosed异常：{}", e);
                    }
                }
            }
            Object sessionIdObj = session.getAttribute(MessageTool.JSESSIONID);
            if (sessionIdObj == null)
            {
                isSessionContext.DelSession(session, "ClientRefresh", false);
            }
            else
            {
                isSessionContext.DelSession(sessionIdObj.toString(), "ClientRefresh", false);
            }
        }
        else
        {//服务端关闭
        }
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception
    {
        super.sessionCreated(session);
    }

    @Override
    public void messageReceived(IoSession ioSession, Object message) throws Exception
    {
        PmsRecevieMessage msg = (PmsRecevieMessage) message;

        String action = msg.getAction();
        if (action == null || "".equals(action.trim()))
        {
            super.messageReceived(ioSession, message);
        }
        else if ("handle".equals(action))
        {
            ioSession.setAttribute(MessageTool.JSESSIONID, msg.getSessionId());
            ioSession.setAttribute(MessageTool.USERID, msg.getUserId());

            PmsWriteMessage writeMsg = new PmsWriteMessage();
            writeMsg.setResult("connectsuccess");
            writeMsg.setData("Connect to Server Successful");

            ioSession.write(writeMsg);
            super.messageReceived(ioSession, message);

            new Thread(new ReceiveMsg(msg, ioSession)).start();
        }
    }

    private class ReceiveMsg implements Runnable
    {
        private PmsRecevieMessage pmsRecevieMessage;

        private IoSession         ioSession;

        ReceiveMsg(PmsRecevieMessage pmsRecevieMessage, IoSession ioSession)
        {
            this.pmsRecevieMessage = pmsRecevieMessage;
            this.ioSession = ioSession;
        }

        @Override
        public void run()
        {
            GgkzUserInfoServiceImpl ggkzUserInfoServiceImpl = SpringContextHolder.getBean("ggkzUserInfoServiceImpl");
            if (ggkzUserInfoServiceImpl != null)
            {
                GgkzUserInfoQuery ggkzUserInfoQuery = new GgkzUserInfoQuery();
                ggkzUserInfoQuery.setUserId(pmsRecevieMessage.getUserId());
                GgkzUserInfo ggkzUserInfo = ggkzUserInfoServiceImpl.selectOneByEntity(ggkzUserInfoQuery);
                isSessionContext.DelSession(ggkzUserInfo.getUserId(), "checkSubscrib", false);

                PmsMessageTopicConsumer pmsMessageTopicConsumer = new PmsMessageTopicConsumer();
                Session jmssession = pmsMessageTopicConsumer.getWebsession();
                isSessionContext.setIoSession(pmsRecevieMessage.getSessionId(), ioSession, jmssession);
                pmsMessageTopicConsumer.receiveMessage(ggkzUserInfo, ioSession);
            }
        }
    }
}