package com.is.mobile.action;

import java.io.File;
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
import com.is.mobile.service.MobileDScrapTransServiceImpl;
import com.is.pretrst.entity.DScrapTrans;
import com.is.utils.json.DateJsonValueProcessor;

@Namespace("/mobile")
public class MobileScrapTransAction extends BaseStruts2Action
{
    private static final long           serialVersionUID = 1L;

    private static final Logger         LOGGER           = LoggerFactory.getLogger(MobileScrapTransAction.class);

    private String                      userId;																	//用户cd
    private String 						transId;																//剩料运输主键Id
    private String                      sessionId;
	private String             			fromWsCd;																/* 发货仓库编码 */
    private String             			sender;																	/* 发货人 */
    private String             			toWsCd;																	/* 收货工作中心编码 */
    private String             			receiver;																/* 接收人 */
	private String             			toCompanyNm;																/* 运输公司名称 */
    private String             			driver;																	/* 司机 */
    private String 						status;
    private String             			weight;																	/* 重量 */
    private String            			memo;																	/* 备注 */
    private List<File> 					userfile;
    
    @Autowired
    private MobileDScrapTransServiceImpl mobileDScrapTransServiceImpl;

    /**
     * 获取剩料列表
     */
    public void getScrapTransList()
    {
        String flag = "0";
        List<DScrapTrans> rstList = new ArrayList<DScrapTrans>();
        try
        {
            rstList = mobileDScrapTransServiceImpl.getScrapTransList(userId);
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
     * 剩料发货接口
     * @param userId 	起单用户Id
     * @param fromWsCd 	工作中心编码
     * @param sender  	发货人
     * @param toWsCd  	收货单位
     * @param receiver  收货人
     * @param tCompanyNm运输公司名称
     * @param driver	司机
     * @param weight	重量
     * @param memo		备注
     * @param userfile  附件上传
     * @return
     */
    public void scrapTransUpLoad()
    {
        String flag = "0";
        List<DScrapTrans> rstList = new ArrayList<DScrapTrans>();
        try
        {
        	LOGGER.debug("传入的参数【{}】【{}】",fromWsCd,toWsCd);
            flag = mobileDScrapTransServiceImpl.scrapTransUpLoad(transId,userId,fromWsCd,sender,toWsCd,receiver,toCompanyNm,driver,weight,memo,userfile);
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
     * 剩料收货接口
     * @param transID	剩料运输主键Id
     * @param status    No_有问题，Yes_确认收货 
     * @param memo		备注
     * @return
     */
    public void scrapTransReceive()
    {
        String flag = "0";
        List<DScrapTrans> rstList = new ArrayList<DScrapTrans>();
        try
        {
        	LOGGER.debug("传入的参数为1==============【{}】【{}】",transId,status);
        	LOGGER.debug("传入的参数为2==============【{}】【{}】",transId,memo);
            flag = mobileDScrapTransServiceImpl.scrapTransReceive(transId,status,memo);
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
    
    public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId.replaceAll("\r\n", "");
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status.replaceAll("\r\n", "");
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
     * 查询 收货单位与根据请求的用户id得到 所在工作中心
     * @return
     */
    public void getWorkShop(){
        String flag = "0";
        List<String> workShopList = new ArrayList<String>();//所在工作中心
        Map<String,String> shopNameAndUserNameMap = new HashMap<String,String>();//仓库和负责人
        try
        {
            workShopList=mobileDScrapTransServiceImpl.getMworkShop(userId);
            shopNameAndUserNameMap=mobileDScrapTransServiceImpl.getReceiptMap();
        }
        catch (Exception e1)
        {
            flag = "1";
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getSendMetralApproval异常：{}", e1);
            }
        }
        Map<String, Object> secrityMap = new HashMap<String, Object>();
        secrityMap.put("flag", flag);
        secrityMap.put("workShops", workShopList);
        secrityMap.put("receiptShops", shopNameAndUserNameMap);
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
    
    //getter setter
    public String getUserId()
    {
        return userId;
    }


    public void setUserId(String userId)
    {
        this.userId = userId.replaceAll("\r\n", "");
    }


    public String getSessionId()
    {
        return sessionId;
    }


    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }
    public String getFromWsCd() {
		return fromWsCd;
	}


	public void setFromWsCd(String fromWsCd) {
		this.fromWsCd = fromWsCd.replaceAll("\r\n", "");
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender.replaceAll("\r\n", "");
	}


	public String getToWsCd() {
		return toWsCd;
	}


	public void setToWsCd(String toWsCd) {
		this.toWsCd = toWsCd.replaceAll("\r\n", "");
	}


	public String getReceiver() {
		return receiver;
	}


	public void setReceiver(String receiver) {
		this.receiver = receiver.replaceAll("\r\n", "");
	}




	public String getDriver() {
		return driver;
	}


	public void setDriver(String driver) {
		this.driver = driver.replaceAll("\r\n", "");
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight.replaceAll("\r\n", "");
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo.replaceAll("\r\n", "");
	}


	public MobileDScrapTransServiceImpl getMobileDScrapTransServiceImpl() {
		return mobileDScrapTransServiceImpl;
	}


	public void setMobileDScrapTransServiceImpl(
			MobileDScrapTransServiceImpl mobileDScrapTransServiceImpl) {
		this.mobileDScrapTransServiceImpl = mobileDScrapTransServiceImpl;
	}
    public String getToCompanyNm() {
		return toCompanyNm;
	}

	public void setToCompanyNm(String toCompanyNm) {
		this.toCompanyNm = toCompanyNm.replaceAll("\r\n", "");
	}

	public void setUserfile(List<File> userfile) {
		this.userfile = userfile;
	}

	public List<File> getUserfile() {
		return userfile;
	}

}
