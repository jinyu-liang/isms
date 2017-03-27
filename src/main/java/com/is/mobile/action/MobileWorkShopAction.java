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
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.MWorkshopExt;
import com.is.pretrst.service.DContractImageServiceImpl;
import com.is.utils.json.DateJsonValueProcessor;

@Namespace("/mobile")
public class MobileWorkShopAction extends BaseStruts2Action
{
    private static final long           serialVersionUID = 1L;

    private static final Logger         LOGGER           = LoggerFactory.getLogger(MobileSendMetralAction.class);

    private String                      userId;
    private String 						wscd;
    private String                      sessionId;
    @Autowired
    private MobileWorkShopServiceImpl mobileWorkShopServiceImpl;
    @Autowired
    private DContractImageServiceImpl dContractImageServiceImpl;
    /**
     * 获取合同列表
     */
    public void getWorkShopList()
    {
        String flag = "0";
        List<MWorkshopExt> rstList = new ArrayList<MWorkshopExt>();
        try
        {
        	LOGGER.debug("传入的参数【{}】",userId);
            rstList = mobileWorkShopServiceImpl.getWorkShopList(userId);
        }
        catch (Exception e1)
        {
            flag = "1";
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getWorkShopList异常：{}", e1);
            }
        }
        this.jsonUtil( rstList,flag);
    }
    
    /**
     * 获取合同图片
     */
    public void getWorkShopImg()
    {
        String flag = "0";
        List<String> rstList = new ArrayList<String>();
        try
        {
        	LOGGER.debug("传入的参数【{}】",wscd);
        	MWorkshop entity=new MWorkshop();
        	entity.setWsCd(wscd);
            rstList = mobileWorkShopServiceImpl.getWorkShopImg(entity);
        }
        catch (Exception e1)
        {
            flag = "1";
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getSendMetralApproval异常：{}", e1);
            }
        }
        this.jsonUtil( rstList,flag);
    }
    
    
    /**
     * 本类中共用的封装json字符串并返回给前台
     * @param rstList
     * @param flag
     */
    public void jsonUtil(List<?> rstList,String flag){
        Map<String, Object> secrityMap = new HashMap<String, Object>();
        secrityMap.put("flag", flag);
        secrityMap.put("data", rstList);
        secrityMap.put("sessionId", sessionId);
        JSONObject jsonobj = new JSONObject();

        //json配置
        JsonConfig jsonConfig = new JsonConfig();
        //json日期格式化
        DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
        //注册值转换器
        jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
        //排除字段集合
        String[] excludes = {"callbacks", "pageNumber", "pageSize", "deleteCd", "dbType", "distinct"};
//        
//        private List<MWorkshopItem> itemList;
//
//    	
//    	private List<DContractImage>  contractImage;
//    	
//    	private DExProject project;
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

		public void setMobileWorkShopServiceImpl(
				MobileWorkShopServiceImpl mobileWorkShopServiceImpl) {
			this.mobileWorkShopServiceImpl = mobileWorkShopServiceImpl;
		}

		public MobileWorkShopServiceImpl getMobileWorkShopServiceImpl() {
			return mobileWorkShopServiceImpl;
		}

		public String getWscd() {
			return wscd;
		}

		public void setWscd(String wscd) {
			this.wscd = wscd;
		}

		public DContractImageServiceImpl getdContractImageServiceImpl() {
			return dContractImageServiceImpl;
		}

		public void setdContractImageServiceImpl(
				DContractImageServiceImpl dContractImageServiceImpl) {
			this.dContractImageServiceImpl = dContractImageServiceImpl;
		}

}
