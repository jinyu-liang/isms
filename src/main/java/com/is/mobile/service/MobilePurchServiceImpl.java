package com.is.mobile.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.ggkz.dao.GgkzUserInfoDaoImpl;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.ggkz.service.GgkzUserInfoServiceImpl;
import com.is.mobile.util.WsUtil;
import com.is.mq.producer.PmsMessageTopicProducer;
import com.is.pretrst.dao.DReportDaoImpl;
import com.is.pretrst.dao.DReportImageDaoImpl;
import com.is.pretrst.dao.MWorkshopDaoImpl;
import com.is.pretrst.entity.DReport;
import com.is.pretrst.entity.DReportImage;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.query.DReportQuery;
import com.is.pretrst.entity.query.MWorkshopQuery;
import com.is.pretrst.service.DReportServiceImpl;
import com.is.pretrst.service.MWorkshopServiceImpl;
import com.is.utils.PublicDict;
import com.is.utils.StringUtils;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 * 
 * <p>
 * 文件名称: MobileSendMetralServiceImpl.java
 * </p>
 * <p>
 * 文件描述: 手机登录验证服务实现
 * </p>
 * <p>
 * 版权所有: 版权所有(C)2013-2020
 * </p>
 * <p>
 * 公 司: IS软件事业部
 * </p>
 * <p>
 * 内容摘要:
 * <p>
 * 其他说明:只要能够请求到本方法，即有采购的的权限
 * </p>
 * <p>
 * 完成日期：2014年9月14日
 * </p>
 * <p>
 * 修改记录0：无
 * </p>
 * 
 * @version 1.0
 * @author 
 */
@Component
@Transactional
public class MobilePurchServiceImpl {
	@Autowired
	private DReportServiceImpl dReportServiceImpl;
	@Autowired
	private DReportDaoImpl dReportDaoImpl;
	@Autowired
	private MWorkshopServiceImpl mWorkshopServiceImpl;
	@Autowired
	private MWorkshopDaoImpl mWorkshopDaoImpl;

	@Autowired
	private PmsMessageTopicProducer pmsMessageTopicProducer;

	// 采购申请图片
	@Autowired
	private DReportImageDaoImpl dReportImageDaoImpl;
	@Autowired
	private GgkzUserInfoServiceImpl ggkzUserInfoServiceImpl;
	@Autowired
	private GgkzUserInfoDaoImpl ggkzUserInfoDaoImpl;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MobilePurchServiceImpl.class);

	public GgkzUserInfoDaoImpl getGgkzUserInfoDaoImpl() {
		return ggkzUserInfoDaoImpl;
	}

	public void setGgkzUserInfoDaoImpl(GgkzUserInfoDaoImpl ggkzUserInfoDaoImpl) {
		this.ggkzUserInfoDaoImpl = ggkzUserInfoDaoImpl;
	}

	public GgkzUserInfoServiceImpl getGgkzUserInfoServiceImpl() {
		return ggkzUserInfoServiceImpl;
	}

	public void setGgkzUserInfoServiceImpl(
			GgkzUserInfoServiceImpl ggkzUserInfoServiceImpl) {
		this.ggkzUserInfoServiceImpl = ggkzUserInfoServiceImpl;
	}

	String statusCd = "";

	/**
	 * 采购申请发起流程接口 title 标题 projectCode 项目代码 projName 项目名称 unitPrice 单价 amount 金额
	 * number 数量 memo 备注 reportUserCd 报告人编码 verifiedUserCd 审核人编码
	 * 
	 * @return String 0:成功 ;1：失败
	 */
	public String purchApply(String reportId, String title, String projectCode,
			String projectName, String unitPrice, String amount, String number,
			String memo, String reportUserCd, String verifiedUserCd)
			throws IOException {
		String flagId = reportId;
		String reportUserName = getUserNameByAndroid(reportUserCd);
		// String verifiedUserName = getUserNameByAndroid(verifiedUserCd);
		int i = 0;
		DReport report = new DReport();
		if (StringUtils.isEmpty(reportId)) {

			verifiedUserCd = getUserIdByName(verifiedUserCd);
			reportId = KeyGen.getCommonKeyGen(PublicDict.DReport_id);
			report.setReportId(reportId);
			report.setTitle(title);
			report.setProjectCode(getWorkIdByName(projectName));
			report.setProjName(projectName);
			report.setUnitPrice(Double.parseDouble(unitPrice));
			report.setAmount(Double.parseDouble(amount));
			report.setNumber(Integer.parseInt(number));
			report.setMemo(memo);
			report.setReportTm(new Date());
			report.setReportUserCd(reportUserCd);
			report.setReportUserName(reportUserName);
			report.setVerifiedUserCd(verifiedUserCd);
			report.setVerifiedUserName(getUserNameByAndroid(verifiedUserCd));
			report.setVerifiedHeadStatus("0");
			report.setStatusCd("0");
			i += dReportDaoImpl.insert(report);

		} else {
			report = new DReport();
			report.setReportId(reportId);
			report = dReportServiceImpl.getDreport(report);
			report.setMemo(memo);
			report.setReportTm(new Date());
			report.setVerifiedHeadStatus("0");
			report.setStatusCd("0");
			i += dReportDaoImpl.updateByEntity(report);
			// // 发消息接口
			// pmsMessageTopicProducer.sendTopic(report.getVerifiedUserCd(), "【"
			// + report.getReportUserName() + "】已经发起从【" + report.getProjName()
			// + "】工地发起采购申请,请审批", "【" + report.getReportUserName() + "】已经发起从【"
			// + report.getProjName()+ "】采购申请：【" +report.getTitle() +
			// "】",report.getReportUserCd());
		}
		if (i != 0) {// 成功
			// 添加日志
			if (StringUtils.isEmpty(flagId)) {
				OperLogUtil.insertMobileOperLog(reportId, reportUserCd,
						PublicDict.MODEL_REPORT, "采购管理",
						PublicDict.OPER_LOG_EVENT_ADD, "添加", "手机端添加采购信息",
						"添加成功", "手机端添加采购信息", "d_report");
			} else {
				OperLogUtil.insertMobileOperLog(reportId, reportUserCd,
						PublicDict.MODEL_REPORT, "采购管理",
						PublicDict.OPER_LOG_EVENT_ADD, "重新申请", "手机端采购信息重新申请",
						"操作成功", "手机端采购信息重新申请", "d_report");
			}
			// 发消息接口
			pmsMessageTopicProducer
					.sendTopic(
							report.getVerifiedUserCd(),
							"【" + report.getReportUserName() + "】已经发起从【"
									+ report.getProjName() + "】工地发起采购申请,请审批",
							"【" + report.getReportUserName() + "】已经发起从【"
									+ report.getProjName() + "】采购申请：【"
									+ report.getTitle() + "】",
							report.getReportUserCd());

			return "0";
		} else {
			return "1";
		}
	}

	/**
	 * 根据userId获取userName
	 * 
	 * @param userId
	 * @return
	 */
	public String getUserNameByAndroid(String userId) {
		GgkzUserInfo entity = new GgkzUserInfo();
		entity.setUserId(userId);
		GgkzUserInfo userIndo = ggkzUserInfoDaoImpl.selectOneByEntity(entity);
		if (userIndo != null) {
			LOGGER.debug("sdfkdsjjjjj=[{}]", userIndo.getName());
			return userIndo.getName();
		} else {
			return userId;
		}

	}

	/**
	 * 上传实物图片和发票操作
	 * 
	 * @param reportId
	 * @param userId
	 * @param inStreamList
	 * @return
	 * @throws IOException
	 */
	public String uploadPurchImage(String reportId, String userId,
			String comment, List<File> userfile) throws IOException {
		DReportQuery query = new DReportQuery();
		query.setReportId(reportId);
		DReport report = dReportDaoImpl.selectOneByEntity(query);
		report.setStatusCd("2");
		report.setDealResult("0");
		report.setComment(comment);
		int j = 0;
		j += dReportDaoImpl.updateByEntity(report);
		// 图片处理方法
		j += imageDeal(reportId, userId, userfile);
		if (j != 0) {// 成功
			// 添加日志
			if (StringUtils.isEmpty(report.getDealResult())) {
				OperLogUtil.insertMobileOperLog(reportId, userId,
						PublicDict.MODEL_REPORT, "采购管理",
						PublicDict.OPER_LOG_EVENT_ADD, "添加", "手机端上传采购实物和发票图片",
						"添加成功", "手机端上传采购实物和发票图片", "d_report_image");
			} else {
				OperLogUtil.insertMobileOperLog(reportId, "",
						PublicDict.MODEL_REPORT, "采购管理",
						PublicDict.OPER_LOG_EVENT_UPDATE, "更新",
						"手机端更新审批处理发票问题", "更新成功", "手机端更新审批处理发票问题", "d_report");
			}

			if (StringUtils.isNotEmpty(report.getProcessUserCd())) {
				pmsMessageTopicProducer.sendTopic(report.getProcessUserCd(),
						"上传实物图片和发票上传提醒通知", "【" + report.getReportUserName()
								+ "】发起的采购【" + report.getTitle()
								+ "】已经上传了实物图片和发票,请做财务处理审批",
						report.getReportUserCd());
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(report.getVerifiedUserCd(),
						"上传实物图片和发票上传提醒通知", "【" + report.getReportUserName()
								+ "】发起的采购【" + report.getTitle()
								+ "】已经上传了实物图片和发票,请做财务处理审批",
						report.getReportUserCd());
			} else {
				// 发消息接口
				//财务总监
				pmsMessageTopicProducer.sendTopic(
						"post23",
						"上传实物图片和发票上传提醒通知",
						"【" + report.getReportUserName() + "】发起的采购【"
								+ report.getTitle() + "】已经上传了实物图片和发票,请做财务处理审批",
						report.getReportUserCd());
				//财务部长
				pmsMessageTopicProducer.sendTopic(
						"post24",
						"上传实物图片和发票上传提醒通知",
						"【" + report.getReportUserName() + "】发起的采购【"
								+ report.getTitle() + "】已经上传了实物图片和发票,请做财务处理审批",
						report.getReportUserCd());
				//部长
				pmsMessageTopicProducer.sendTopic(
						"post3",
						"上传实物图片和发票上传提醒通知",
						"【" + report.getReportUserName() + "】发起的采购【"
								+ report.getTitle() + "】已经上传了实物图片和发票,请做财务处理审批",
						report.getReportUserCd());
			}
			return "0";
		} else {
			return "1";
		}
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
	public String purchVouch(String reportId, String status, String comment) {
		int i = 0;
		DReport dreport = new DReport();
		dreport.setReportId(reportId);
		dreport = dReportServiceImpl.getDreport(dreport);
		dreport.setVerifiedHeadMemo(comment);
		dreport.setVerifiedHeadTm(new Date());
		dreport.setVerifiedHeadStatus(status);
		if (status.equals("No")) {
		} else {
			dreport.setStatusCd("1");// 已审批
			dreport.setDealResult("0");
		}

		i += dReportDaoImpl.updateByEntity(dreport);
		if (i != 0) {// 成功
			// 添加日志
			if (status.equals("No")) {
				OperLogUtil.insertMobileOperLog(reportId, "",
						PublicDict.MODEL_REPORT, "采购管理",
						PublicDict.OPER_LOG_EVENT_UPDATE, "更新",
						"手机端审批采购申请，有问题打回", "打回处理", "手机端审批采购申请，有问题打回",
						"d_report");
			} else {
				OperLogUtil.insertMobileOperLog(reportId, "",
						PublicDict.MODEL_REPORT, "采购管理",
						PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "手机端审批同意采购申请",
						"更新成功", "手机端审批同意采购申请", "d_report");
			}
			if (status.equals("Yes")) {
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(
						dreport.getReportUserCd(),
						"【" + dreport.getVerifiedUserName() + "】已经审批同意采购申请",
						"您从【" + dreport.getProjName() + "】发起的采购申请【"
								+ dreport.getTitle() + "】",
						dreport.getVerifiedUserCd());
			} else {
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(
						dreport.getReportUserCd(),
						"【" + dreport.getVerifiedUserName() + "】不同意采购申请",
						"您从【" + dreport.getProjName() + "】发起的采购申请【"
								+ dreport.getTitle() + "】，问题为【"
								+ dreport.getVerifiedHeadMemo() + "】",
						dreport.getVerifiedUserCd());

			}

			return "0";
		} else {
			return "1";
		}
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
	public String billDeal(String reportId, String userId, String dealResult,
			String comment) {
		int i = 0;
		DReport dreport = new DReport();
		dreport.setReportId(reportId);
		dreport = dReportServiceImpl.getDreport(dreport);
		dreport.setComment(comment);
		dreport.setDealResult(dealResult);
		dreport.setProcessTm(new Date());
		dreport.setProcessUserCd(userId);
		dreport.setProcessUserName(getUserNameByAndroid(userId));
		if (dealResult.equals("No")) {// 有问题，将流程打回到已审批，上传实物图片和发票
			dreport.setStatusCd("1");// 到已审批，重新上传实物图片和发票
		} else {
			dreport.setStatusCd("3");// 已审批
		}

		i += dReportDaoImpl.updateByEntity(dreport);
		if (i != 0) {// 成功
			// 添加日志
			if (dealResult.equals("No")) {
				OperLogUtil.insertMobileOperLog(reportId, userId,
						PublicDict.MODEL_REPORT, "采购管理",
						PublicDict.OPER_LOG_EVENT_UPDATE, "更新",
						"手机端审批发票申请,有问题打回", "审核打回", "手机端审批发票申请,有问题打回",
						"d_report");
			} else {
				OperLogUtil.insertMobileOperLog(reportId, userId,
						PublicDict.MODEL_REPORT, "采购管理",
						PublicDict.OPER_LOG_EVENT_UPDATE, "更新",
						"手机端审批发票申请，审核通过", "审核通过", "手机端审批发票申请，审核通过", "d_report");
			}

			if (dealResult.equals("Yes")) {
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(
						dreport.getReportUserCd(),
						"【" + dreport.getProcessUserName() + "】对采购申请：【"
								+ dreport.getTitle() + "】财务处理完毕",
						"【" + dreport.getReportUserName() + "】从【"
								+ dreport.getProjName() + "】发起的采购申请【"
								+ dreport.getTitle() + "】已经处理完毕",
						dreport.getProcessUserCd());
				pmsMessageTopicProducer.sendTopic(
						dreport.getVerifiedUserCd(),
						"【" + dreport.getProcessUserName() + "】对采购申请：【"
								+ dreport.getTitle() + "】财务处理完毕",
						"【" + dreport.getReportUserName() + "】从【"
								+ dreport.getProjName() + "】发起的采购申请【"
								+ dreport.getTitle() + "】已经处理完毕",
						dreport.getProcessUserCd());
			} else {
				// 发消息接口
				pmsMessageTopicProducer
						.sendTopic(dreport.getReportUserCd(),
								"【" + dreport.getProcessUserName()
										+ "】对采购进行财务处理时发现有问题",
								"【" + dreport.getReportUserName() + "】从【"
										+ dreport.getProjName() + "】发起的采购申请【"
										+ dreport.getTitle() + "】财务处理发现问题，问题为【"
										+ dreport.getComment() + "】",
								dreport.getProcessUserCd());
				pmsMessageTopicProducer
						.sendTopic(dreport.getVerifiedUserCd(),
								"【" + dreport.getProcessUserName()
										+ "】对采购进行财务处理时发现有问题",
								"【" + dreport.getReportUserName() + "】从【"
										+ dreport.getProjName() + "】发起的采购申请【"
										+ dreport.getTitle() + "】财务处理发现问题，问题为【"
										+ dreport.getComment() + "】",
								dreport.getProcessUserCd());

			}

			return "0";
		} else {
			return "1";
		}
	}

	/**
	 * 图片处理方法
	 * 
	 * @param reportId
	 * @param userId
	 * @param userfile
	 * @return
	 */

	public int imageDeal(String reportId, String userId, List<File> userfile) {
		int j = 0;
		try {
			if (userfile != null && userfile.size() > 0) {
				for (int i = 0; i < userfile.size(); i++) {

					String photoId = KeyGen
							.getCommonKeyGen(PublicDict.USERPHOTO);
					String rootPath = Thread.currentThread()
							.getContextClassLoader().getResource("/").getPath();
					rootPath = rootPath.substring(0, rootPath.length() - 16)
							+ "upload/";
					String filename = photoId + ".jpg";
					FileInputStream in = new FileInputStream(userfile.get(i));
					FileOutputStream out = new FileOutputStream(rootPath
							+ filename);
					byte[] b = new byte[10240];
					int len = 0;
					while ((len = in.read(b)) > 0) {
						out.write(b, 0, len);
					}
					in.close();
					out.close();
					DReportImage image = new DReportImage();
					image.setReportId(reportId);
					filename = "/upload/" + filename;
					image.setFileName(filename);
					image.setPhotoUserCd(userId);
					image.setUploadTm(new Date());
					image.setStatusCd("0");
					image.setPhotoId(photoId);// 主键
					j += dReportImageDaoImpl.insert(image);
					// if(j>0){
					// // 添加日志
					// OperLogUtil.insertMobileOperLog(reportId,
					// userId,PublicDict.MODEL_PERSON,
					// "外线人员管理", PublicDict.OPER_LOG_EVENT_ADD, "添加",
					// "手机端添加外线人员图片", "添加成功",
					// "手机端添加外线人员图片", "d_report_image");
					// }
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return j;
	}

	/**
	 * 采购审批列表
	 * 
	 * @param username
	 * @return List<DReport>
	 */
	public List<DReport> getListDreport(String userId, String statusCd)
			throws Exception {
		List<DReport> reportList = new ArrayList<DReport>();
		// statusCd = "0";//状态为待审批
		reportList.addAll(this.getPurchByUserId(userId, statusCd));
		return reportList;
	}

	/**
	 * 获取可以选择的工地中心数据list
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<String> getMworkShop(String userId) throws Exception {
		List<String> shopNames = new ArrayList<String>();
		MWorkshopQuery work = new MWorkshopQuery();
		work.setManagerUserId(userId);
		List<MWorkshop> workList = new ArrayList<MWorkshop>();
		workList = mWorkshopServiceImpl.getShopMangeUserId(userId);
		for (MWorkshop shop : workList) {
			shopNames.add(shop.getWsNm());
		}
		return shopNames;
	}

	/**
	 * 获取总经理和部长的对象list
	 * 
	 * @return
	 */
	public List<String> getGgkzUserInfoList(String userId) {
		List<String> userNames = new ArrayList<String>();
		List<GgkzUserInfo> userList = ggkzUserInfoServiceImpl
				.getDepartOrManager(userId);
		for (GgkzUserInfo user : userList) {
			userNames.add(user.getName());
		}
		return userNames;
	}

	/**
	 * 根据姓名获取userId
	 * 
	 * @param name
	 * @return
	 */
	public String getUserIdByName(String name) {
		GgkzUserInfoQuery entity = new GgkzUserInfoQuery();
		entity.setName(name);
		return ggkzUserInfoDaoImpl.selectOneByEntity(entity).getUserId();
	}

	/**
	 * 根据工地中心name获取代码
	 * 
	 * @param name
	 * @return
	 */
	public String getWorkIdByName(String name) {
		MWorkshopQuery entity = new MWorkshopQuery();
		entity.setWsNm(name);
		return mWorkshopDaoImpl.selectOneByEntity(entity).getWsCd();
	}

	/**
	 * 采购已审批列表
	 * 
	 * @param username
	 * @return List<DReport>
	 */
	public List<DReport> getVouchedDreport(String userId) throws Exception {
		List<DReport> reportList = new ArrayList<DReport>();
		statusCd = "0";// 状态为待审批
		reportList.addAll(this.getPurchByUserId(userId, statusCd));
		return reportList;
	}

	/**
	 * 根据当前用户id和状态码得到待审批数据列表
	 * 
	 * @param userId
	 * @param statusCd
	 * @return
	 */
	public List<DReport> getPurchByUserId(String userId, String statusCd) {
		List<DReport> planList = new ArrayList<DReport>();
		LOGGER.debug("userId[{}]", userId);
		String post = WsUtil.getPostByUserId(userId);// 当前用户的职务代码

		if ("38".equals(post)) {// 职务为区域部长
			planList = verifiedSiteHead(userId, statusCd);
		} else if ("1".equals(post) || "3".equals(post)||"11".equals(post)) {// 职务为总经理或部长
			planList = verifiedAll(statusCd);// 查询所有
		} else {// 项目经理和其他职务只查询自己相关的数据
			planList = verifiedSiteLevel(userId, statusCd);
		}
		return planList;
	}

	/**
	 * 项目经理级别的采购表，查询跟自己相关的数据
	 * 
	 * @param userId
	 * @param statusCd
	 * @return
	 */
	public List<DReport> verifiedSiteLevel(String userId, String statusCd) {
		List<DReport> levelList = new ArrayList<DReport>();// 中间list
		levelList = new ArrayList<DReport>();
		DReportQuery entity = new DReportQuery();
		entity.setStatusCd(statusCd);
		entity.setVerifiedUserCd(userId);// 审批人为当前用户
		levelList = dReportDaoImpl.selectByEntity(entity);
		entity.setVerifiedUserCd(null);
		entity.setReportUserCd(userId);// 报告者为当前用户
		levelList.addAll(dReportDaoImpl.selectByEntity(entity));
		return levelList;
	}

	/**
	 * 区域部长的采购表，查询跟自己相关的数据和下属项目经理的数据
	 * 
	 * @param userId
	 * @param statusCd
	 * @return
	 */
	public List<DReport> verifiedSiteHead(String userId, String statusCd) {
		List<DReport> siteHeadList = new ArrayList<DReport>();
		DReport entity = new DReport();
		entity.setStatusCd(statusCd);
		siteHeadList.addAll(this.verifiedSiteLevel(userId, statusCd));// 查询跟自己相关的数据
		List<String> strList = WsUtil.getSiteUserIdByEareaheadUserId(userId);// 查询区域部长所有下属项目经理的id
		for (String siteId : strList) {
			siteHeadList.addAll(this.verifiedSiteLevel(siteId, statusCd));// 查询区域部长所有下属项目经理的待审批
		}
		return siteHeadList;
	}

	/**
	 * 总经理与部长的采购表，查询所有的数据
	 * 
	 * @param userId
	 * @param statusCd
	 * @return
	 */
	public List<DReport> verifiedAll(String statusCd) {
		List<DReport> allList = new ArrayList<DReport>();
		DReport entity = new DReport();
		entity.setStatusCd(statusCd);
		allList = dReportDaoImpl.selectByEntity(entity);
		return allList;
	}

	public MWorkshopServiceImpl getmWorkshopServiceImpl() {
		return mWorkshopServiceImpl;
	}

	public void setmWorkshopServiceImpl(
			MWorkshopServiceImpl mWorkshopServiceImpl) {
		this.mWorkshopServiceImpl = mWorkshopServiceImpl;
	}

	public DReportImageDaoImpl getdReportImageDaoImpl() {
		return dReportImageDaoImpl;
	}

	public void setdReportImageDaoImpl(DReportImageDaoImpl dReportImageDaoImpl) {
		this.dReportImageDaoImpl = dReportImageDaoImpl;
	}

	public void setmWorkshopDaoImpl(MWorkshopDaoImpl mWorkshopDaoImpl) {
		this.mWorkshopDaoImpl = mWorkshopDaoImpl;
	}

	public MWorkshopDaoImpl getmWorkshopDaoImpl() {
		return mWorkshopDaoImpl;
	}

	public void setdReportDaoImpl(DReportDaoImpl dReportDaoImpl) {
		this.dReportDaoImpl = dReportDaoImpl;
	}

	public DReportDaoImpl getdReportDaoImpl() {
		return dReportDaoImpl;
	}

	public void setdReportServiceImpl(DReportServiceImpl dReportServiceImpl) {
		this.dReportServiceImpl = dReportServiceImpl;
	}

	public DReportServiceImpl getdReportServiceImpl() {
		return dReportServiceImpl;
	}

}
