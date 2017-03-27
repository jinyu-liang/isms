package com.is.mobile.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.ggkz.dao.GgkzUserInfoDaoImpl;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.pretrst.entity.MTransItem;
import com.is.pretrst.entity.MWorkshopTrans;
import com.is.pretrst.service.MWorkshopServiceImpl;
import com.is.pretrst.service.MWorkshopTransServiceImpl;
import com.is.utils.StringUtils;
import com.is.utils.json.DateJsonValueProcessor;

@Namespace("/mobile")
public class MobileWorkshopTransAction extends BaseStruts2Action {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MobileSendMetralAction.class);
	private String             			fromWsCd;																/* 发货仓库编码 */
    private String             			sender;																	/* 发货人 */
    private String             			toWsCd;																	/* 收货工作中心编码 */
    private String             			receiver;																/* 接收人 */
	private String             			toCompanyNm;																/* 运输公司名称 */
    private String             			driver;																	/* 司机 */
    private String 						status;
    private String             			weight;																	/* 重量 */
	private String 	          			userId;
	private String           			sessionId;
	private String           			transId;           			          			          				// 工地转移Id
	private String           			comment;           			          			          				// 处理意见/备注
	private String         	itemList;           			          		          				// 工地转移明细
	private List<File>          		userfile;           			          			          			// 图片附件上传
	@Autowired
	private MWorkshopTransServiceImpl mWorkshopTransServiceImpl;
	@Autowired
	private MWorkshopServiceImpl mWorkshopServiceImpl;
	@Autowired
	private GgkzUserInfoDaoImpl ggkzUserInfoDaoImpl;
	/**
	 * 工地转移已上传列表
	 */
	public void getMworkshopTransList() {
		String flag = "0";
		List<MWorkshopTrans> rstList = new ArrayList<MWorkshopTrans>();
		try {
			rstList = mWorkshopTransServiceImpl.getMworkshopTransList(userId);
		} catch (Exception e1) {
			flag = "1";
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("getSendMetralApproval异常：{}", e1);
			}
		}
		this.jsonUtil(rstList, flag);
	}
	/**根据工地编码获取当前工地的剩料数据列表
	 * 
	 * @param fromWsCd
	 * @return
	 */
	public void getWorkItemList() {
		String flag = "0";
		List<MTransItem> rstList = new ArrayList<MTransItem>();// 所在工作剩料明细
		try {
			rstList = mWorkshopTransServiceImpl.getTransItemListByWscd(fromWsCd,transId);
		} catch (Exception e1) {
			flag = "1";
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("getSendMetralApproval异常：{}", e1);
			}
		}
		this.jsonUtil(rstList, flag);
	}

	/**
	 * 获取 自己负责的工作单位 收货单位与根据请求的用户id得到 所在工作中心
	 * 
	 * @return
	 */
	public void getWorkShop() {
		String flag = "0";
		List<String> workShopList = new ArrayList<String>();// 所在工作中心
		Map<String, String> shopNameAndUserNameMap = new HashMap<String, String>();// 仓库和负责人
		try {
			workShopList = mWorkshopTransServiceImpl.getMworkShop(userId);

			shopNameAndUserNameMap = mWorkshopTransServiceImpl.getReceiptMap();
		} catch (Exception e1) {
			flag = "1";
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("getSendMetralApproval异常：{}", e1);
			}
		}
		Map<String, Object> secrityMap = new HashMap<String, Object>();
		secrityMap.put("flag", flag);
		secrityMap.put("workShops", workShopList);
		secrityMap.put("receiptShops", shopNameAndUserNameMap);
		secrityMap.put("sessionId", sessionId);
		JSONObject jsonobj = new JSONObject();

		// json配置
		JsonConfig jsonConfig = new JsonConfig();
		// json日期格式化
		DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
		// 注册值转换器
		jsonConfig.registerJsonValueProcessor(Date.class,
				dateJsonValueProcessor);
		// 排除字段集合
		String[] excludes = { "callbacks", "pageNumber", "pageSize",
				"deleteCd", "dbType", "distinct" };
		// 注册排除字段
		jsonConfig.setExcludes(excludes);

		// 加载集合
		jsonobj.accumulateAll(secrityMap, jsonConfig);

		PrintWriter out = null;
		try {
			out = ServletActionContext.getResponse().getWriter();
			out.write(jsonobj.toString());
			out.flush();
		} catch (IOException e) {
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("login异常：{}", e);
			}
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 工地转移安卓客户端上传接口
	 *	@param 	sender发货人 
	 *	@param 	receiver接收人
	 * @param	toCompanyNm运输公司名称
	 * @param	driver司机
	 * @param	weight重量
	 * @param	transId 运输Id
	 * @param	fromWsCd 原工地
	 * @param	toWsCd目标工地
	 * @param	comment 内容
	 * @param 	userId	        报告人
	 * @param   itemList 设备明细list
	 * @param 	userfile 图片流
	 * @return String 0:成功 ;1：失败
	 * @throws IOException
	 */
	public void workTransUpload() throws IOException {
		String flag = "0";
		MWorkshopTrans entity=new MWorkshopTrans();
		entity.setSender(sender); /* 发货人 */
		entity.setReceiver(receiver); /* 接收人 */
		entity.settCompanyNm(toCompanyNm); /* 运输公司名称 */
		entity.setDriver(driver); /* 司机 */
		if(StringUtils.isNotEmpty(weight)){
			entity.setWeight(Float.parseFloat(weight));
		}
		entity.setTransId(transId);
		entity.setFromWsCd(fromWsCd);
		entity.setToWsCd(toWsCd);
		entity.setMemo(comment);
		entity.setTransUserCd(userId);
		entity.setTransUserName(getUserNameById(userId));
		itemList = itemList.substring(itemList.indexOf("["),itemList.indexOf("]")+1);
		JSONArray jsonArray = JSONArray.fromObject(itemList);
		List<MTransItem> list = JSONArray.toList(jsonArray, MTransItem.class);   
		flag = mWorkshopTransServiceImpl.workTransUpload(entity,list,userfile);
		this.jsonUtil(null, flag);
	}
    /**
     * 根据姓名获取userId
     * @param name
     * @return
     */
    public String getUserNameById(String userId){
    	GgkzUserInfoQuery entity=new GgkzUserInfoQuery();
    	entity.setUserId(userId);
    	 GgkzUserInfo info=ggkzUserInfoDaoImpl.selectOneByEntity(entity);
    	 if(info!=null){
    		 return info.getName();
    	 }
    	 return userId;
    }


	/**
	 * 工地转移接收操作
	 * 
	 * @param transId
	 *            业务编号
	 * @param userId
	 *            处理人Id
	 * @param status
	 *            处理结果
	 * @param comment
	 *            处理意见
	 * @return
	 * @throws IOException
	 */
	public void workTransDeal() throws IOException {
		String flag = "0";
		flag = mWorkshopTransServiceImpl.MWorkTransReceive(transId, userId, status,
				comment);
		this.jsonUtil(null, flag);
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



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status.replaceAll("\r\n", "");
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment.replaceAll("\r\n", "");
	}

	/**
	 * 
	 * @return
	 */
	public void ApprovalDispose() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId.replaceAll("\r\n", "");
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setUserfile(List<File> userfile) {
		this.userfile = userfile;
	}

	public List<File> getUserfile() {
		return userfile;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId.replaceAll("\r\n", "");
	}

	public String getFromWsCd() {
		return fromWsCd;
	}

	public void setFromWsCd(String fromWsCd) {
		this.fromWsCd = fromWsCd.replaceAll("\r\n", "");
	}

	public String getToWsCd() {
		return toWsCd;
	}

	public void setToWsCd(String toWsCd) {
		this.toWsCd = toWsCd.replaceAll("\r\n", "");
	}

	

	public String getItemList() {
		return itemList;
	}
	public void setItemList(String itemList) {
		this.itemList = itemList;
	}
	public MWorkshopTransServiceImpl getmWorkshopTransServiceImpl() {
		return mWorkshopTransServiceImpl;
	}

	public void setmWorkshopTransServiceImpl(
			MWorkshopTransServiceImpl mWorkshopTransServiceImpl) {
		this.mWorkshopTransServiceImpl = mWorkshopTransServiceImpl;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getToCompanyNm() {
		return toCompanyNm;
	}
	public void setToCompanyNm(String toCompanyNm) {
		this.toCompanyNm = toCompanyNm;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public MWorkshopServiceImpl getmWorkshopServiceImpl() {
		return mWorkshopServiceImpl;
	}
	public void setmWorkshopServiceImpl(MWorkshopServiceImpl mWorkshopServiceImpl) {
		this.mWorkshopServiceImpl = mWorkshopServiceImpl;
	}
	public GgkzUserInfoDaoImpl getGgkzUserInfoDaoImpl() {
		return ggkzUserInfoDaoImpl;
	}
	public void setGgkzUserInfoDaoImpl(GgkzUserInfoDaoImpl ggkzUserInfoDaoImpl) {
		this.ggkzUserInfoDaoImpl = ggkzUserInfoDaoImpl;
	}

}
