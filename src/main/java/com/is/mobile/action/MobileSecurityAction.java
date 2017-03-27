package com.is.mobile.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.mybatis.BaseStruts2Action;

@Namespace("/mobile")
public class MobileSecurityAction extends BaseStruts2Action
{
    private static final long   serialVersionUID = 1L;

    private static final Logger LOGGER           = LoggerFactory.getLogger(MobileSecurityAction.class);

    private String              username;

    private String              userpwd;

    private String              mobile_sn;

    public void login()
    {

        Map<String, List<String>> secrityMap = new HashMap<String, List<String>>();

        List<String> sendMetralRightList = new ArrayList<String>();

        sendMetralRightList.add("add");
        sendMetralRightList.add("delete");
        sendMetralRightList.add("update");
        sendMetralRightList.add("select");

        secrityMap.put("sendMetral", sendMetralRightList);

        List<String> progressRightList = new ArrayList<String>();

        progressRightList.add("add");
        progressRightList.add("delete");
        progressRightList.add("update");
        progressRightList.add("select");

        secrityMap.put("progress", progressRightList);
        List<String> requestList = new ArrayList<String>();

        requestList.add(username);
        requestList.add(userpwd);
        requestList.add(mobile_sn);

        secrityMap.put("requestData", requestList);

        JSONObject jsonobj = new JSONObject();
        /*//json配置
        JsonConfig jsonConfig = new JsonConfig();
        //json日期格式化
        DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
        //注册值转换器
        jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
        //排除字段集合
        String[] excludes = {"sellOrderCode"};
        //注册排除字段
        jsonConfig.setExcludes(excludes);

        //加载集合
        jsonobj.accumulateAll(secrityMap, jsonConfig);*/
        //加载集合
        jsonobj.accumulateAll(secrityMap);

        PrintWriter out = null;
        try
        {
            out = ServletActionContext.getResponse().getWriter();
            out.write(jsonobj.toString());
            out.flush();
        }
        catch (IOException e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("login异常：{}", e);
            }
        }
        finally
        {
            if (out != null)
            {
                out.close();
            }
        }
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUserpwd()
    {
        return userpwd;
    }

    public void setUserpwd(String userpwd)
    {
        this.userpwd = userpwd;
    }

    public String getMobile_sn()
    {
        return mobile_sn;
    }

    public void setMobile_sn(String mobile_sn)
    {
        this.mobile_sn = mobile_sn;
    }

}
