package com.is.pms.msginteract.io.msg;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PmsWriteMessage implements Serializable
{
    private static final long   serialVersionUID = 1L;

    private static final Logger LOGGER           = LoggerFactory.getLogger(PmsWriteMessage.class);

    private String              result           = "";

    private String              data             = "";

    public PmsWriteMessage()
    {

    }

    public PmsWriteMessage(String result, String data)
    {
        try
        {
            this.result = result == null ? "" : URLEncoder.encode(result, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("PmsWriteMessage异常：{}", e);
            }
        }
        try
        {
            this.data = data == null ? "" : URLEncoder.encode(data, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("PmsWriteMessage异常：{}", e);
            }
        }
    }

    public String transToJson()
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("result", result);
        map.put("data", data);
        JSONObject jsonobj = new JSONObject();
        jsonobj.accumulateAll(map);
        return jsonobj.toString();
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data == null ? "" : data;
        //        try
        //        {
        //            this.data = data == null ? "" : URLEncoder.encode(data, "UTF-8");
        //        }
        //        catch (UnsupportedEncodingException e)
        //        {
        //            if (LOGGER.isErrorEnabled())
        //            {
        //                LOGGER.error("setData异常：{}", e);
        //            }
        //        }
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result == null ? "" : result;
        //        try
        //        {
        //            this.result = result == null ? "" : URLEncoder.encode(result, "UTF-8");
        //        }
        //        catch (UnsupportedEncodingException e)
        //        {
        //            if (LOGGER.isErrorEnabled())
        //            {
        //                LOGGER.error("setResult异常：{}", e);
        //            }
        //        }
    }

}
