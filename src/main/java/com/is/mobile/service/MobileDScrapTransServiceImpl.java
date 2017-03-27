package com.is.mobile.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.ggkz.dao.GgkzUserInfoDaoImpl;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.mobile.util.WsUtil;
import com.is.mq.producer.PmsMessageTopicProducer;
import com.is.pretrst.dao.DScrapTransDaoImpl;
import com.is.pretrst.dao.DScrapTransImageDaoImpl;
import com.is.pretrst.entity.DScrapTrans;
import com.is.pretrst.entity.DScrapTransImage;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.query.DScrapTransQuery;
import com.is.pretrst.entity.query.MWorkshopQuery;
import com.is.pretrst.service.MWorkshopServiceImpl;
import com.is.utils.PublicDict;
import com.is.utils.StringUtils;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 * 
 * <p>
 * 文件名称: MobileDScrapTransServiceImpl.java
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
public class MobileDScrapTransServiceImpl {
	@Autowired
	private DScrapTransDaoImpl dscrapTransDaoImpl;
	@Autowired
	private DScrapTransImageDaoImpl dScrapTransImageDaoImpl;
	@Autowired
	private GgkzUserInfoDaoImpl ggkzUserInfoDaoImpl;
	@Autowired
	private MWorkshopServiceImpl mWorkshopServiceImpl;

	@Autowired
	private PmsMessageTopicProducer pmsMessageTopicProducer;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MobileDScrapTransServiceImpl.class);

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public void setdScrapTransImageDaoImpl(
			DScrapTransImageDaoImpl dScrapTransImageDaoImpl) {
		this.dScrapTransImageDaoImpl = dScrapTransImageDaoImpl;
	}

	String statusCd = "";

	/**
	 * 剩料发货接口
	 * 
	 * @param userId
	 *            起单用户Id
	 * @param fromWsCd
	 *            工作中心编码
	 * @param sender
	 *            发货人
	 * @param toWsCd
	 *            收货单位名称
	 * @param receiver
	 *            收货人
	 * @param tCompanyNm运输公司名称
	 * @param driver
	 *            司机
	 * @param weight
	 *            重量
	 * @param memo
	 *            备注
	 * @param userfile
	 *            附件上传
	 * @return
	 */
	public String scrapTransUpLoad(String transId, String userId,
			String fromWsCd, String sender, String toWsCd, String receiver,
			String tCompanyNm, String driver, String weight, String memo,
			List<File> userfile) {
		String logFlag = transId;
		int i = 0;
		String receiverId = getUserIdByName(receiver);
		if (StringUtils.isEmpty(receiverId)) {
			LOGGER.debug("您填写的收货人为[{}]", receiverId);
		}
		DScrapTrans dScrapTrans = new DScrapTrans();
		if (StringUtils.isEmpty(transId)) {

			transId = KeyGen.getCommonKeyGen(PublicDict.Trans_id);
			dScrapTrans.setTransId(transId);
			dScrapTrans.setTransUserCd(userId);
			dScrapTrans.setTransUserName(getUserNameByAndroid(userId));
			dScrapTrans.setFromWsCd(fromWsCd);
			dScrapTrans.setSender(sender);
			dScrapTrans.setToWsCd(toWsCd);// 根据传过来的收货工地名称查询工地代码
			dScrapTrans.setReceiver(receiver.trim());
			dScrapTrans.setDriver(driver);
			dScrapTrans.setReceiverId(receiverId);
			dScrapTrans.setMemo(memo);
			dScrapTrans.setTCompanyNm(tCompanyNm);
			if (StringUtils.isNotEmpty(weight)) {
				dScrapTrans.setWeight(Float.parseFloat(weight));
			}
			dScrapTrans.setStatusCd("0");// 0已发货，No有问题，Yes已收货
			dScrapTrans.setDeliveryTm(new Date());
			i += dscrapTransDaoImpl.insert(dScrapTrans);
		} else {
			DScrapTransQuery dScrapTransQuery = new DScrapTransQuery();
			dScrapTransQuery.setTransId(transId);
			dScrapTrans = dscrapTransDaoImpl
					.selectOneByEntity(dScrapTransQuery);
			dScrapTrans.setMemo(memo);
			dScrapTrans.setArrivalTm(new Date());
			dScrapTrans.setStatusCd("0");// 0已发货，No有问题，Yes已收货
			i += dscrapTransDaoImpl.updateByEntity(dScrapTrans);
		}
		// 图片处理方法
		if (userfile != null && userfile.size() > 0) {
			i += imageDeal(transId, userId, userfile);
		}
		if (i != 0) {// 成功
			// 添加日志
			if (StringUtils.isEmpty(logFlag)) {
				OperLogUtil.insertMobileOperLog(transId, userId,
						PublicDict.MODEL_SCRAPTRANS, "剩料管理",
						PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "手机端添加剩料发货",
						"更新成功", "手机端添加剩料发货", "d_scrap_trans");

			} else {
				OperLogUtil.insertMobileOperLog(transId, "",
						PublicDict.MODEL_SCRAPTRANS, "剩料管理",
						PublicDict.OPER_LOG_EVENT_UPDATE, "更新",
						"手机端修改重新发货修改提交", "更新成功", "手机端修改重新发货修改提交",
						"d_scrap_trans");
			}
			// 发消息接口
			pmsMessageTopicProducer.sendTopic(
					dScrapTrans.getReceiverId(),
					"【" + dScrapTrans.getReceiver() + "】剩料发货通知",
					"【" + dScrapTrans.getReceiver() + "】已经发起从【"
							+ dScrapTrans.getFromWsCd() + "】发往【"
							+ dScrapTrans.getToWsCd() + "】的剩料发货",
					dScrapTrans.getTransUserCd());
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
			return userIndo.getName();
		} else {
			return userId;
		}

	}

	/**
	 * 剩料收货
	 * 
	 * @param transID
	 *            剩料运输主键Id
	 * @param memo
	 *            备注
	 * @return
	 */
	public String scrapTransReceive(String transId, String status, String memo) {
		DScrapTransQuery dScrapTransQuery = new DScrapTransQuery();
		dScrapTransQuery.setTransId(transId);
		DScrapTrans dScrapTrans = dscrapTransDaoImpl
				.selectOneByEntity(dScrapTransQuery);
		dScrapTrans.setMemo(memo);
		dScrapTrans.setStatusCd(status);
		dScrapTrans.setArrivalTm(new Date());
		int i = 0;
		i += dscrapTransDaoImpl.updateByEntity(dScrapTrans);
		if (i != 0) {// 成功
			// 添加日志
			OperLogUtil.insertMobileOperLog(transId, "",
					PublicDict.MODEL_SCRAPTRANS, "剩料管理",
					PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "手机端确认收货", "更新成功",
					"手机端更新剩料信息", "d_scrap_trans");

			if (status.equals("Yes")) {
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(dScrapTrans.getTransUserCd(), "【" + dScrapTrans.getReceiver() + "】同意收货通知", "您从【"
						+ dScrapTrans.getFromWsCd() + "】发往【"
						+ dScrapTrans.getToWsCd() + "】的剩料发货", dScrapTrans.getReceiverId());
			} else {
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(dScrapTrans.getTransUserCd(), "【" + dScrapTrans.getReceiver() + "】收货时发现有问题",  "您从【"
						+ dScrapTrans.getFromWsCd() + "】发往【"
						+ dScrapTrans.getToWsCd() + "】的剩料发货", dScrapTrans.getReceiverId());
			}

			return "0";
		} else {
			return "1";
		}
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
	 * 获取收货仓库
	 * 
	 * @return
	 */
	public Map<String, String> getReceiptMap() {
		MWorkshop shop = new MWorkshop();
		shop.setTypeCd("3");
		Map<String, String> shopNameAndUserName = new HashMap<String, String>();
		List<MWorkshop> receiptList = mWorkshopServiceImpl.selectByEntity(shop);
		GgkzUserInfo user = null;
		for (MWorkshop workshop : receiptList) {
			user = new GgkzUserInfo();
			if (StringUtils.isNotEmpty(workshop.getManagerUserId())) {
				user.setUserId(workshop.getManagerUserId());
				user = ggkzUserInfoDaoImpl.selectOneByEntity(user);// 根据仓库管理人员
																	// id查询管理人员name
				if (user != null && StringUtils.isNotEmpty(user.getName())) {
					shopNameAndUserName.put(workshop.getWsNm(), user.getName());// 把仓库名称与仓库负责人名称存入map
				} else {// 如果仓库负责人为空
					shopNameAndUserName.put(workshop.getWsNm(), "");
				}
			}
		}
		return shopNameAndUserName;
	}

	/**
	 * 获取剩料列表数据
	 * 
	 * @param userId
	 * @return
	 */
	public List<DScrapTrans> getScrapTransList(String userId) {
		if (StringUtils.isEmpty(userId)) {
			return null;
		}
		List<DScrapTrans> reportList = new ArrayList<DScrapTrans>();
		reportList.addAll(this.getPurchByUserId(userId));
		return reportList;
	}

	/**
	 * 根据当前用户id和状态码得到待审批数据列表
	 * 
	 * @param userId
	 * @param statusCd
	 * @return
	 */
	public List<DScrapTrans> getPurchByUserId(String userId) {
		List<DScrapTrans> planList = new ArrayList<DScrapTrans>();
		String post = WsUtil.getPostByUserId(userId);// 当前用户的职务代码
		if ("38".equals(post)) {// 职务为区域部长
			planList = verifiedSiteHead(userId);
		} else if ("1".equals(post) || "3".equals(post)||"11".equals(post)||"2".equals(post)||"6".equals(post)) {// 职务为1总经理或3部长11信息主管，2部门领导,6董事长
			planList = verifiedAll(statusCd);// 查询所有
		} else {// 项目经理和其他职务只查询自己相关的数据
			planList = verifiedSiteLevel(userId);
		}
		return planList;
	}

	/**
	 * 项目经理级别的剩料表，查询跟自己相关的数据
	 * 
	 * @param userId
	 * @param statusCd
	 * @return
	 */
	public List<DScrapTrans> verifiedSiteLevel(String userId) {
		List<DScrapTrans> levelList = new ArrayList<DScrapTrans>();// 中间list
		levelList = new ArrayList<DScrapTrans>();
		DScrapTransQuery entity = new DScrapTransQuery();
		entity.setReceiverId(userId);
		// entity.setVerifiedUserCd(userId);//收货人为当前用户
		levelList = dscrapTransDaoImpl.selectByEntity(entity);
		entity.setReceiverId(null);
		entity.setTransUserCd(userId);// 报告者为当前用户
		levelList.addAll(dscrapTransDaoImpl.selectByEntity(entity));
		return levelList;
	}

	/**
	 * 总经理与部长的剩料表，查询所有的数据
	 * 
	 * @param userId
	 * @param statusCd
	 * @return
	 */
	public List<DScrapTrans> verifiedAll(String statusCd) {
		List<DScrapTrans> allList = new ArrayList<DScrapTrans>();
		DScrapTrans entity = new DScrapTrans();
		entity.setStatusCd(statusCd);
		allList = dscrapTransDaoImpl.selectByEntity(entity);
		return allList;
	}

	/**
	 * 区域部长的剩料表，查询跟自己相关的数据和下属项目经理的数据
	 * 
	 * @param userId
	 * @param statusCd
	 * @return
	 */
	public List<DScrapTrans> verifiedSiteHead(String userId) {
		List<DScrapTrans> siteHeadList = new ArrayList<DScrapTrans>();
		DScrapTrans entity = new DScrapTrans();
		entity.setStatusCd(statusCd);
		siteHeadList.addAll(this.verifiedSiteLevel(userId));// 查询跟自己相关的数据
		List<String> strList = WsUtil.getSiteUserIdByEareaheadUserId(userId);// 查询区域部长所有下属项目经理的id
		for (String siteId : strList) {
			siteHeadList.addAll(this.verifiedSiteLevel(siteId));// 查询区域部长所有下属项目经理的待审批
		}
		return siteHeadList;
	}

	public void setDscrapTransDaoImpl(DScrapTransDaoImpl dscrapTransDaoImpl) {
		this.dscrapTransDaoImpl = dscrapTransDaoImpl;
	}

	public void setmWorkshopServiceImpl(
			MWorkshopServiceImpl mWorkshopServiceImpl) {
		this.mWorkshopServiceImpl = mWorkshopServiceImpl;
	}

	/**
	 * 图片处理方法
	 * 
	 * @param reportId
	 * @param userId
	 * @param userfile
	 * @return
	 */

	public int imageDeal(String transId, String userId, List<File> userfile) {
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
					DScrapTransImage image = new DScrapTransImage();
					image.setTransId(transId);
					filename = "/upload/" + filename;
					image.setFileName(filename);
					image.setCheckUserCd(userId);
					image.setCheckTm(new Date());
					image.setStatusCd("0");
					image.setPhotoId(photoId);// 主键
					j += dScrapTransImageDaoImpl.insert(image);
					// if(j>0){
					// // 添加日志
					// OperLogUtil.insertMobileOperLog(transId,
					// userId,PublicDict.MODEL_SCRAPTRANS,
					// "剩料管理", PublicDict.OPER_LOG_EVENT_ADD, "添加", "手机端添加剩料图片",
					// "添加成功",
					// "手机端添加剩料图片", "d_scrap_trans_image");
					// }
				}

			}
		} catch (Exception e) {
		}
		return j;
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
		GgkzUserInfo info = ggkzUserInfoDaoImpl.selectOneByEntity(entity);
		if (info != null) {
			return info.getUserId();
		}
		return name;
	}

}
