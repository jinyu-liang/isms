package com.is.mq.listener.msg.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.is.mq.util.MessageTool;
import com.is.pms.msginteract.io.msg.PmsWriteMessage;

public class MessageUtil
{
    private static Logger      LOGGER     = LoggerFactory.getLogger(MessageUtil.class);

    public static final String publickMsg = "PUBLIC";

    private MessageUtil()
    {

    }

    @SuppressWarnings("rawtypes")
    public static int socketTrans(IoSession ioSession, Message message)
    {
        int rs = 0;
        try
        {
            if (ioSession != null && ioSession.isConnected() && !ioSession.containsAttribute(MessageTool.CLIENTCLOSED))
            {
                String theme = message.getStringProperty("theme");
                IoBuffer ioBuffer = IoBuffer.allocate(2048);
                ioBuffer.setAutoExpand(true);
                if (message instanceof BytesMessage)
                {
                    BytesMessage msg = (BytesMessage) message;
                    byte[] bt = new byte[2048];
                    int rst = 0;
                    while ((rst = msg.readBytes(bt)) != -1)
                    {
                        ioBuffer.put(bt, 0, rst);
                    }
                }
                else if (message instanceof TextMessage)
                {
                    TextMessage msg = (TextMessage) message;
                    ioBuffer.put(URLEncoder.encode(msg.getText(), "UTF-8").getBytes());
                }
                else if (message instanceof MapMessage)
                {
                    MapMessage msg = (MapMessage) message;
                    JSONObject jsonobj = new JSONObject();
                    Enumeration enumeration = msg.getMapNames();
                    Map<String, Object> map = new HashMap<String, Object>();
                    while (enumeration.hasMoreElements())
                    {
                        String key = enumeration.nextElement().toString();
                        map.put(key, msg.getObject(key));
                    }
                    jsonobj.accumulateAll(map);
                    ioBuffer.put(URLEncoder.encode(jsonobj.toString(), "UTF-8").getBytes());
                }
                else if (message instanceof ObjectMessage)
                {
                    ObjectMessage msg = (ObjectMessage) message;
                    ioBuffer.put(URLEncoder.encode(msg.getObject().toString(), "UTF-8").getBytes());
                }
                else if (message instanceof StreamMessage)
                {
                    StreamMessage msg = (StreamMessage) message;
                    byte[] bt = new byte[20480];
                    int rst = 0;
                    while ((rst = msg.readBytes(bt)) != -1)
                    {
                        ioBuffer.put(bt, 0, rst);
                    }
                }
                Map<String, String> msgdata = new HashMap<String, String>();
                msgdata.put("theme", theme == null ? "" : theme);
                msgdata.put("msg", ioBuffer.position() == 0 ? "" : new String(ioBuffer.array(), 0, ioBuffer.position()));
                msgdata.put("uri", "");

                List<Map<String, String>> msgData = new ArrayList<Map<String, String>>();
                msgData.add(msgdata);
                JSONArray jsonarray = new JSONArray();
                jsonarray.addAll(msgData);

                PmsWriteMessage pmsWriteMessage = new PmsWriteMessage();
                pmsWriteMessage.setResult("data");
                pmsWriteMessage.setData(jsonarray.toString());

                try
                {
                    if (!ioSession.containsAttribute(MessageTool.CLIENTCLOSED))
                    {
                        ioSession.write(pmsWriteMessage);
                        rs = 1;
                    }
                }
                catch (Exception e)
                {
                    if (LOGGER.isErrorEnabled())
                    {
                        LOGGER.error("socketTrans异常：{}", e);
                    }
                }
                if (LOGGER.isDebugEnabled())
                {
                    LOGGER.debug(pmsWriteMessage.transToJson());
                }
            }
        }
        catch (Exception e)
        {
        }
        return rs;
    }

    public static int socketTrans(IoSession ioSession, String msg)
    {
        int rs = 0;
        if (ioSession != null && ioSession.isConnected() && !ioSession.containsAttribute(MessageTool.CLIENTCLOSED))
        {
            PmsWriteMessage pmsWriteMessage = new PmsWriteMessage();
            pmsWriteMessage.setResult("data");
            pmsWriteMessage.setData(msg);

            try
            {
                if (!ioSession.containsAttribute(MessageTool.CLIENTCLOSED))
                {
                    ioSession.write(pmsWriteMessage);
                    rs = 1;
                }
            }
            catch (Exception e)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("socketTrans异常：{}", e);
                }
            }
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug(pmsWriteMessage.transToJson());
            }
        }
        return rs;
    }

    @SuppressWarnings("rawtypes")
    public static String messageTrans(Message message) throws JMSException, IOException
    {
        IoBuffer ioBuffer = IoBuffer.allocate(2048);
        ioBuffer.setAutoExpand(true);
        if (message instanceof BytesMessage)
        {
            BytesMessage msg = (BytesMessage) message;
            byte[] bt = new byte[2048];
            int rst = 0;
            while ((rst = msg.readBytes(bt)) != -1)
            {
                ioBuffer.put(bt, 0, rst);
            }
        }
        else if (message instanceof TextMessage)
        {
            TextMessage msg = (TextMessage) message;
            return msg.getText();
        }
        else if (message instanceof MapMessage)
        {
            MapMessage msg = (MapMessage) message;
            JSONObject jsonobj = new JSONObject();
            Enumeration enumeration = msg.getMapNames();
            Map<String, Object> map = new HashMap<String, Object>();
            while (enumeration.hasMoreElements())
            {
                String key = enumeration.nextElement().toString();
                map.put(key, msg.getObject(key));
            }
            jsonobj.accumulateAll(map);
            ioBuffer.put(jsonobj.toString().getBytes("UTF-8"));
        }
        else if (message instanceof ObjectMessage)
        {
            ObjectMessage msg = (ObjectMessage) message;
            ioBuffer.put(msg.getObject().toString().getBytes("UTF-8"));
        }
        else if (message instanceof StreamMessage)
        {
            StreamMessage msg = (StreamMessage) message;
            byte[] bt = new byte[20480];
            int rst = 0;
            while ((rst = msg.readBytes(bt)) != -1)
            {
                ioBuffer.put(bt, 0, rst);
            }
        }

        return new String(ioBuffer.array(), 0, ioBuffer.position());
    }
}
