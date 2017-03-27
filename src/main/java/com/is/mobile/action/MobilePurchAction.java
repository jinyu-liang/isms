package com.is.mobile.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.mobile.service.MobilePurchServiceImpl;
import com.is.pretrst.entity.DReport;
import com.is.utils.json.DateJsonValueProcessor;

@Namespace("/mobile")
public class MobilePurchAction extends BaseStruts2Action {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MobileSendMetralAction.class);
	private String userId; // 用户cd
	private String sessionId;
	private String reportId; // 采购Id
	private String projectCode; // 工地中心代码
	private String projName; // 工地中心
	private String title; // 标题
	private String unitPrice; // 单价
	private String amount; // 金额
	private String number; // 数量
	private String status; // 审批状态
	private String comment; // 审批意见
	private String verifiedUserCd; // 审批人userCd
	@Autowired
	private MobilePurchServiceImpl mobilePurchServiceImpl;
	private List<File> userfile;

	/**
	 * 采购待审批
	 */
	public void getPurchNeedVouch() {
		String flag = "0";
		List<DReport> rstList = new ArrayList<DReport>();
		try {
			rstList = mobilePurchServiceImpl.getListDreport(userId, "0");
		} catch (Exception e1) {
			flag = "1";
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("getSendMetralApproval异常：{}", e1);
			}
		}
		this.jsonUtil(rstList, flag);
	}

	/**
	 * 采购已审批
	 */
	public void getPurchHaveVouch() {
		String flag = "0";
		List<DReport> rstList = new ArrayList<DReport>();
		try {
			rstList = mobilePurchServiceImpl.getListDreport(userId, "1");
		} catch (Exception e1) {
			flag = "1";
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("getSendMetralApproval异常：{}", e1);
			}
		}
		this.jsonUtil(rstList, flag);
	}

	/**
	 * 发票待审批
	 */
	public void getBillNeedVouch() {
		String flag = "0";
		List<DReport> rstList = new ArrayList<DReport>();
		try {
			LOGGER.debug("userId[{}]",userId);
			rstList = mobilePurchServiceImpl.getListDreport(userId, "2");
		} catch (Exception e1) {
			flag = "1";
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("getSendMetralApproval异常：{}", e1);
			}
		}
		this.jsonUtil(rstList, flag);
	}
	

	/**
	 * 发票已审批
	 */
	public void getBillHaveVouch() {
		String flag = "0";
		List<DReport> rstList = new ArrayList<DReport>();
		try {
			rstList = mobilePurchServiceImpl.getListDreport(userId, "3");
		} catch (Exception e1) {
			flag = "1";
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("getSendMetralApproval异常：{}", e1);
			}
		}
		this.jsonUtil(rstList, flag);
	}



	/**
	 * 采购申请发起流程接口 title 标题 projectCode 项目代码 projName 项目名称 unitPrice 单价 amount 金额
	 * number 数量 memo 备注 reportUserCd 报告人编码 verifiedUserCd 审核人编码
	 * 
	 * @return String 0:成功 ;1：失败
	 */
	public void purchApply() throws IOException {
		String flag = "0";
		flag = mobilePurchServiceImpl.purchApply(reportId,title, projectCode, projName,
				unitPrice, amount, number, comment, userId, verifiedUserCd);
		this.jsonUtil(null, flag);
	}

	/**
	 * 上传实物图片和发票操作
	 * 发票处理后有问题修改备注操作
	 * @param reportId
	 * @param userId
	 * @param inStreamList
	 * @return
	 * @throws IOException
	 */
	public void uploadPurchImage() throws IOException {
		
		  String flag = "0"; LOGGER.debug("开始调用uploadPurchImage()");
		 flag = mobilePurchServiceImpl.uploadPurchImage(reportId, userId,comment,userfile); 
		 this.jsonUtil(null, flag);
	}
	/**
	 * 采购审批操作
	 * 
	 * @param reportId
	 * @param status
	 * @param comment
	 * @return
	 * @throws IOException
	 */
	public void purchVouch() throws IOException {
		String flag = "0";
		flag = mobilePurchServiceImpl.purchVouch(reportId, status, comment);
		this.jsonUtil(null, flag);
	}

	/**
	 * 发票处理操作
	 * 
	 * @param reportId
	 * @param status
	 * @param comment
	 * @return
	 * @throws IOException
	 */
	public void billDeal() throws IOException {
		String flag = "0";
		flag = mobilePurchServiceImpl.billDeal(reportId, userId, status,
				comment);
		this.jsonUtil(null, flag);
	}

	/**
	 * 获取工地中心list和部长总经理数据
	 * 
	 */
	public void getWorkAndVerfieder() {

		String flag = "0";
		List<String> workList = new ArrayList<String>();
		List<String> userList = new ArrayList<String>();
		try {
			workList = mobilePurchServiceImpl.getMworkShop(userId);
			userList = mobilePurchServiceImpl.getGgkzUserInfoList(userId);
		} catch (Exception e1) {
			flag = "1";
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("getSendMetralApproval异常：{}", e1);
			}
		}
		Map<String, Object> secrityMap = new HashMap<String, Object>();
		secrityMap.put("flag", flag);
		secrityMap.put("workdata", workList);
		secrityMap.put("userdata", userList);
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
	 * 本类中共用的封装json字符串并返回给前台
	 * 
	 * @param rstList
	 * @param flag
	 */
	public void jsonUtil(List<?> rstList, String flag) {
		Map<String, Object> secrityMap = new HashMap<String, Object>();
		secrityMap.put("flag", flag);
		secrityMap.put("data", rstList);
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
//	/**
//	 * 发票处理后有问题修改备注操作
//	 * 
//	 * @param reportId
//	 * @param comment
//	 * @return
//	 * @throws IOException
//	 */
//	public void problemUpdateComment() throws IOException {
//		String flag = "0";
//		flag = mobilePurchServiceImpl.problemUpdateComment(reportId,userId, comment,userfile);
//		this.jsonUtil(null, flag);
//	}
	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId.replaceAll("\r\n", "");
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode.replaceAll("\r\n", "");
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName.replaceAll("\r\n", "");
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title.replaceAll("\r\n", "");
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice.replaceAll("\r\n", "");
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number.replaceAll("\r\n", "");
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
		this.sessionId = sessionId.replaceAll("\r\n", "");
	}

	public void setVerifiedUserCd(String verifiedUserCd) {
		this.verifiedUserCd = verifiedUserCd.replaceAll("\r\n", "");
	}

	public String getVerifiedUserCd() {
		return verifiedUserCd;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount.replaceAll("\r\n", "");
	}

	public List<File> getUserfile() {
		return userfile;
	}

	public void setUserfile(List<File> userfile) {
		this.userfile = userfile;
	}

}
