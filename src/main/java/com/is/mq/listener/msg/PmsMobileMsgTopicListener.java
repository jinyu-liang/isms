package com.is.mq.listener.msg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.dict.UserUtil;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.mq.listener.msg.util.MessageUtil;
import com.is.utils.xmpp.XmppUtil;

/**
 * 
 * <p>文件名称: PmsMobileMsgTopicListener.java</p>
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
public class PmsMobileMsgTopicListener implements MessageListener
{

    private static Logger LOGGER = LoggerFactory.getLogger(PmsMobileMsgTopicListener.class);

    private Session       session;

    private GgkzUserInfo  user;

    public PmsMobileMsgTopicListener(Session session, GgkzUserInfo user)
    {
        this.session = session;
        this.user = user;
    }

    @Override
    public void onMessage(Message message)
    {
        //        synchronized (PmsMobileMsgTopicListener.class)
        //        {
        try
        {
            //                String userId = user.getUserId().toLowerCase();
            int rst = 0;
            if ("destroy_mobile".equals(message.getStringProperty("theme")))
            {
                rst = XmppUtil.sendNotifcationToUser(user.getUserId().toLowerCase(), "destroy_mobile", "destroy_mobile", "");
            }
            else
            {
                Map<String, String> dataMap = new HashMap<String, String>();
                String receivername = message.getStringProperty("receivername");
                if (receivername == null || "".equals(receivername.trim()) || ";".equals(receivername.trim()))
                {
                    receivername = UserUtil.getUserNameById(user.getUserId().toUpperCase());
                }

                dataMap.put("receivername", receivername);
                dataMap.put("sendername", message.getStringProperty("sendername"));
                dataMap.put("sendtime", message.getStringProperty("sendtime"));
                dataMap.put("id", message.getStringProperty("id"));
                dataMap.put("msg", MessageUtil.messageTrans(message));
                dataMap.put("theme", message.getStringProperty("theme"));

                List<Map<String, String>> msgData = new ArrayList<Map<String, String>>();
                msgData.add(dataMap);
                JSONArray jsonarray = new JSONArray();
                jsonarray.addAll(msgData);

                rst = XmppUtil.sendNotifcationToUser(user.getUserId().toLowerCase(), "新条消息(1)条", jsonarray.toString(), "");
            }
            if (rst == 1)
            {
                //                    message.acknowledge();
                if (session.getAcknowledgeMode() == Session.SESSION_TRANSACTED)
                {
                    session.commit();
                    if (LOGGER.isInfoEnabled())
                    {
                        LOGGER.info(user.getUserId().toLowerCase());
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
