package com.is.mobile.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.mobile.service.MobileWorkShopServiceImpl;
import com.is.pretrst.entity.ExPersonManager;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.RstVerInfo;
import com.is.pretrst.service.RstVerInfoServiceImpl;
import com.is.utils.json.DateJsonValueProcessor;

@Namespace("/mobile")
public class MobileVerUpdateAction extends BaseStruts2Action
{
    private static final long           serialVersionUID = 1L;

    private static final Logger         LOGGER           = LoggerFactory.getLogger(MobileSendMetralAction.class);

    private String                      userId;
    private String                      sessionId;
    private String 						verCode;
    private String  					flag;
    @Autowired
    private RstVerInfoServiceImpl rstVerInfoServiceImpl;
    /**安卓版本更新接口
     * @param verCode 传入当前安卓手机端版本号
     * 
     * return 返回 flag,data
     * flag:
     * 1_版本有效，提示更新，如果不更新仍可以运行，是旧的版本
     * 0_提示版本无效，系统不能登录，需要更新版本后方可使用系统
     * Y_已经是最新版本，无需更新
     * N_当前客户端的版本号在服务器没有登记
     * data：最新版本信息
     */
    public void getNewVerInfo()
    {
        RstVerInfo verInfo = new RstVerInfo();
        try
        {	//获取最新的版本信息
            verInfo = rstVerInfoServiceImpl.getMaxVerCode();
            flag=rstVerInfoServiceImpl.getVerValidFlag(verCode);
        }
        catch (Exception e1)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getSendMetralApproval异常：{}", e1);
            }
        }
        this.jsonUtil( verInfo,flag);
    }
    
    
    /**
     * 本类中共用的封装json字符串并返回给前台
     * @param rstList
     * @param flag
     */
    public void jsonUtil(RstVerInfo verInfo,String flag){
        Map<String, Object> secrityMap = new HashMap<String, Object>();
        secrityMap.put("data", verInfo);
        secrityMap.put("flag", flag);
        JSONObject jsonobj = new JSONObject();
        //json配置
        JsonConfig jsonConfig = new JsonConfig();
        //json日期格式化
        DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
        //注册值转换器
        jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
        //排除字段集合
        String[] excludes = {"callbacks", "pageNumber", "pageSize", "deleteCd", "dbType", "distinct"};
        //注册排除字段
        jsonConfig.setExcludes(excludes);

        //加载集合
        jsonobj.accumulateAll(secrityMap, jsonConfig);

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


	/**
     * 
     * @return
     */
    public void ApprovalDispose(){
        
        
    }
    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }


    /**
     * @return Returns the rstVerInfoServiceImpl.
     */
    public RstVerInfoServiceImpl getRstVerInfoServiceImpl()
    {
        return rstVerInfoServiceImpl;
    }


    /**
     * @param rstVerInfoServiceImpl The rstVerInfoServiceImpl to set.
     */
    @Autowired
    public void setRstVerInfoServiceImpl(RstVerInfoServiceImpl rstVerInfoServiceImpl)
    {
        this.rstVerInfoServiceImpl = rstVerInfoServiceImpl;
    }


	public String getVerCode() {
		return verCode;
	}


	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}


}
