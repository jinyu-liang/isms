package com.is.mq.consumer;

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

import org.apache.activemq.command.ActiveMQTopic;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springside.modules.utils.spring.SpringContextHolder;

import com.is.ggkz.entity.GgkzUserInfo;
import com.is.mq.listener.msg.PmsMsgTopicListener;
import com.is.mq.listener.msg.util.MessageUtil;
import com.is.mq.util.MessageTool;
import com.is.session.context.IsSessionContext;

/**
 * 
 * <p>
 * 文件名称: PmsMessageTopicConsumer.java
 * </p>
 * <p>
 * 文件描述: 本类描述
 * </p>
 * <p>
 * 版权所有: 版权所有(C)2013-2020
 * </p>
 * <p>
 * 公 司: IS软件事业部
 * </p>
 * <p>
 * 内容摘要:
 * </p>
 * <p>
 * 其他说明:
 * </p>
 * <p>
 * 完成日期：2014年9月26日
 * </p>
 * <p>
 * 修改记录0：无
 * </p>
 * 
 * @version 1.0
 * @author 
 */
public class PmsMessageTopicConsumer {

	private static Logger LOGGER = LoggerFactory
			.getLogger(PmsMessageTopicConsumer.class);

	private Session websession = null;

	public PmsMessageTopicConsumer() {
		CachingConnectionFactory cachingConnectionFactory = SpringContextHolder
				.getBean("cachingConnectionFactory");
		try {
			Connection connection = cachingConnectionFactory.createConnection();
			websession = connection.createSession(true,
					Session.SESSION_TRANSACTED);
		} catch (JMSException e) {
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("PmsMessageTopicConsumer异常：{}", e);
			}
		}
	}

	public void receiveMessage(final GgkzUserInfo user, IoSession ioSession) {
		if (user == null || user.getUserId() == null
				|| "".equals(user.getUserId().trim())) {
			return;
		}
		ActiveMQTopic topicDestination = SpringContextHolder
				.getBean("topicDestination");
		try {
			String userId = user.getUserId().toUpperCase();
			StringBuffer sbf = new StringBuffer();
			sbf.append("receiver IN ('").append(userId).append("','")
					.append(MessageUtil.publickMsg).append("'");
			if (user.getPost() != null) {
				sbf.append(",'POST").append(user.getPost()).append("'");
			}
			sbf.append(")");
			TopicSubscriber webtopicSubscriber = websession
					.createDurableSubscriber(topicDestination, userId,
							sbf.toString(), false);
			List<Map<String, String>> msgData = new ArrayList<Map<String, String>>();
			Message message = null;
			ioSession.setAttribute(MessageTool.RECEIVEMSG);
			while ((message = webtopicSubscriber.receiveNoWait()) != null) {
				try {
					// if (message.getJMSRedelivered())
					// {
					// continue;
					// }
					// synchronized (ioSession)
					// {
					if (ioSession.containsAttribute(MessageTool.CLIENTCLOSED)) {
						ioSession.removeAttribute(MessageTool.RECEIVEMSG);
						return;
						// continue;
					}
					Map<String, String> dataMap = new HashMap<String, String>();
					dataMap.put("msg", MessageUtil.messageTrans(message));
					dataMap.put("theme", message.getStringProperty("theme"));
					msgData.add(dataMap);
					// }
				} catch (JMSException e) {
					if (LOGGER.isErrorEnabled()) {
						LOGGER.error("receiveMessage异常：{}", e);
					}
					if (ioSession.containsAttribute(MessageTool.CLIENTCLOSED)) {
						ioSession.removeAttribute(MessageTool.RECEIVEMSG);
						return;
						// continue;
					}
				} catch (Exception e) {
					if (LOGGER.isErrorEnabled()) {
						LOGGER.error("receiveMessage异常：{}", e);
					}
					if (ioSession.containsAttribute(MessageTool.CLIENTCLOSED)) {
						ioSession.removeAttribute(MessageTool.RECEIVEMSG);
						return;
						// continue;
					}
				}
				// try
				// {
				// Thread.sleep(5000);
				// }
				// catch (InterruptedException e)
				// {
				// if (LOGGER.isErrorEnabled())
				// {
				// LOGGER.error("receiveMessage异常：{}", e);
				// }
				// }
				if (ioSession.containsAttribute(MessageTool.CLIENTCLOSED)) {
					ioSession.removeAttribute(MessageTool.RECEIVEMSG);
					return;
					// continue;
				}
			}
			if (ioSession.containsAttribute(MessageTool.CLIENTCLOSED)) {
				ioSession.removeAttribute(MessageTool.RECEIVEMSG);
				return;
			}
			if (!msgData.isEmpty()) {
				JSONArray jsonarray = new JSONArray();
				jsonarray.addAll(msgData);
				int rst = MessageUtil.socketTrans(ioSession,
						jsonarray.toString());
				if (rst == 1) {
					// message.acknowledge();
				} else {
					if (ioSession.containsAttribute(MessageTool.CLIENTCLOSED)) {
						ioSession.removeAttribute(MessageTool.RECEIVEMSG);
						return;
						// continue;
					}
				}
			}
			if (websession.getAcknowledgeMode() == Session.SESSION_TRANSACTED) {
				websession.commit();
				if (LOGGER.isInfoEnabled()) {
					LOGGER.info(userId);
				}
			}
			webtopicSubscriber.setMessageListener(new PmsMsgTopicListener(
					websession, user));
			ioSession.removeAttribute(MessageTool.RECEIVEMSG);
		} catch (JMSException e) {
			IsSessionContext.getInstance().DelSession(user.getUserId(),
					"checkSubscrib", false);
			if(!ioSession.containsAttribute("receivetimes"))
			{
				ioSession.setAttribute("receivetimes", "1");
				receiveMessage(user, ioSession);
			}
		}
	}

	public Session getWebsession() {
		return websession;
	}

}
