package com.is.pms.msginteract.io.msg;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.is.mq.util.MessageTool;

/**
 * <p>文件名称: PmsRecevieMessage.java</p>
 * <p>文件描述: 本类描述</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年9月28日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
public class PmsRecevieMessage implements Serializable
{
    private static final long   serialVersionUID = 1L;

    private static final Logger LOGGER           = LoggerFactory.getLogger(PmsRecevieMessage.class);

    private String              data             = "";

    private String              action           = "";

    private String              sessionId        = "";

    private String              userId           = "";

    public PmsRecevieMessage()
    {

    }

    public PmsRecevieMessage(String content)
    {
        JSONObject jsonobj = JSONObject.fromObject(content);
        try
        {
            this.action = jsonobj.get("action") == null ? null : URLDecoder.decode(jsonobj.get("action").toString(), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("Message异常：{}", e);
            }
        }
        if ("handle".equals(this.action))
        {
            JSONObject jsonData = jsonobj.getJSONObject("data");
            try
            {
                this.sessionId = jsonData.get(MessageTool.JSESSIONID) == null ? null : URLDecoder.decode(jsonData.get(MessageTool.JSESSIONID)
                        .toString(), "UTF-8");
            }
            catch (UnsupportedEncodingException e)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("PmsRecevieMessage异常：{}", e);
                }
            }
            try
            {
                this.userId = jsonData.get(MessageTool.USERID) == null ? null : URLDecoder.decode(jsonData.get(MessageTool.USERID).toString(), "UTF-8");
            }
            catch (UnsupportedEncodingException e)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("PmsRecevieMessage异常：{}", e);
                }
            }
        }
        try
        {
            this.data = jsonobj.get("data") == null ? null : URLDecoder.decode(jsonobj.get("data").toString(), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("Message异常：{}", e);
            }
        }
    }

    @SuppressWarnings("static-access")
    public void transContent(String content)
    {
        JSONObject jsonobj = new JSONObject();
        jsonobj.fromObject(content);
        this.data = jsonobj.get("data") == null ? null : jsonobj.get("data").toString();
        try
        {
            this.action = jsonobj.get("action") == null ? null : URLDecoder.decode(jsonobj.get("action").toString(), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("Message异常：{}", e);
            }
        }
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

}
