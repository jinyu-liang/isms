package com.is.pretrst.service;

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

import com.base.mybatis.Page;
import com.is.ggkz.dao.GgkzUserInfoDaoImpl;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.mobile.util.WsUtil;
import com.is.mq.producer.PmsMessageTopicProducer;
import com.is.pretrst.dao.DScrapTransImageDaoImpl;
import com.is.pretrst.dao.MTransItemDaoImpl;
import com.is.pretrst.dao.MWorkshopDaoImpl;
import com.is.pretrst.dao.MWorkshopItemDaoImpl;
import com.is.pretrst.dao.MWorkshopTransDaoImpl;
import com.is.pretrst.entity.DScrapTransImage;
import com.is.pretrst.entity.MTransItem;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.MWorkshopItem;
import com.is.pretrst.entity.MWorkshopTrans;
import com.is.pretrst.entity.query.DScrapTransImageQuery;
import com.is.pretrst.entity.query.MTransItemQuery;
import com.is.pretrst.entity.query.MWorkshopItemQuery;
import com.is.pretrst.entity.query.MWorkshopQuery;
import com.is.pretrst.entity.query.MWorkshopTransQuery;
import com.is.utils.PublicDict;
import com.is.utils.StringUtils;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 * 
 * @ClassName: mWorkshopTransServiceImpl
 * @Description: mWorkshopTrans表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:55 *
 */
@Component
@Transactional
public class MWorkshopTransServiceImpl {
	private static Logger logger = LoggerFactory
			.getLogger(MWorkshopTrans.class);
	@Autowired
	private MWorkshopTransDaoImpl mWorkshopTransDaoImpl;
	@Autowired
	private MWorkshopItemDaoImpl mWorkshopItemDaoImpl;
	@Autowired
	private MWorkshopServiceImpl mWorkshopServiceImpl;
	@Autowired
	private GgkzUserInfoDaoImpl ggkzUserInfoDaoImpl;
	@Autowired
	private MTransItemDaoImpl mTransItemDaoImpl;
	@Autowired
	private DScrapTransImageDaoImpl dScrapTransImageDaoImpl;
	@Autowired
	private MWorkshopDaoImpl mWorkshopDaoImpl;

	@Autowired
	private PmsMessageTopicProducer pmsMessageTopicProducer;
	@Autowired
	private DScrapTransImageServiceImpl dScrapTransImageServiceImpl;

	/**
	 * 分页查询 剩料列表
	 * 
	 * @param queryEntity
	 * @return page
	 */
	@Transactional(readOnly = true)
	public Page pageQuery(MWorkshopTransQuery queryEntity) {
		return mWorkshopTransDaoImpl.pageQuery("MWorkshopTrans.selectByPage",
				queryEntity);
	}

	/**
	 * 查找一个 剩料对象
	 * 
	 * @param entity
	 * @return DDeliveryPlan
	 */
	public MWorkshopTrans selectOneByEntity(MWorkshopTrans entity) {
		entity = mWorkshopTransDaoImpl.selectOneByEntity(entity);
		return entity;
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
	 * 工地转移发货接口
	 * 
	 * @param entity
	 * @param itemList
	 * @param userfile
	 * @return
	 */
	public String workTransUpload(MWorkshopTrans entity,
			List<MTransItem> itemList, List<File> userfile) {
		logger.debug("传入的设备明细为【{}】", itemList);
		int j = 0;
		String transId = entity.getTransId();
		if(entity.getFromWsCd().equals(entity.getToWsCd())){
			return "1";
		}
		// 新增
		if (StringUtils.isEmpty(transId)) {
			transId = KeyGen.getCommonKeyGen(PublicDict.Trans_id);
			entity.setStatusCd("0");
			entity.setDeliveryTm(new Date());
			entity.setTransId(transId);
			j += mWorkshopTransDaoImpl.insert(entity);
			logger.debug("新增【{}】", j);
		} else {// 修改
			entity.setStatusCd("0");
			entity.setDeliveryTm(new Date());
			j += mWorkshopTransDaoImpl.updateByEntity(entity);
			logger.debug("修改【{}】", j);
		}
		// 明细新增/修改
		if (itemList != null && itemList.size() > 0) {
			// 删除上次的明细,再插入新的数据
			MTransItemQuery query = new MTransItemQuery();
			query.setTransId(transId);
			j += mTransItemDaoImpl.deleteByEntity(query);
			logger.debug("删除明细【{}】", j);
			for (int i = 0; i < itemList.size(); i++) {
				MTransItem item = new MTransItem();
				String itemId = KeyGen.getCommonKeyGen(PublicDict.Item_id);
				item = itemList.get(i);
				item.setItemId(itemId);
				item.setTransId(transId);
				item.setWsCd(entity.getToWsCd());
				j += mTransItemDaoImpl.insert(item);
				/*
				 * 此处代码在收货的时候处理 // 处理工地剩料明细的数据，目标工地新增，原工地减少 j +=
				 * workItemDeal("from", transId, entity.getFromWsCd().trim(),
				 * item.getMaterialNm().trim(), item.getTotalAmount(),
				 * item.getModelNo().trim());// 原工地 j += workItemDeal("to",
				 * transId, entity.getToWsCd().trim(),
				 * item.getMaterialNm().trim(), item.getTotalAmount(),
				 * item.getModelNo().trim());// 目标工地 logger.debug("新增明细【{}】",j);
				 */
			}
		}
		// 图片处理方法
		if (userfile != null && userfile.size() > 0) {
			logger.debug("图片处理【{}】");
			j += imageDeal(transId, entity.getTransUserCd(), userfile);
		}
		if (j != 0) {
			// 发消息接口
			pmsMessageTopicProducer.sendTopic(
					getUserIdByName(entity.getReceiver()),
					"【" + entity.getTransUserName() + "】已发起工地转移,请【"
							+ entity.getReceiver() + "】收货",
					"从【" + entity.getFromWsCd() + "】发往【" + entity.getToWsCd()
							+ "】",entity.getTransUserCd());
			// 添加日志
			OperLogUtil.insertOperLog(entity.getTransId(),
					PublicDict.MODEL_GDZY, "工地转移发货",
					PublicDict.OPER_LOG_EVENT_UPDATE, "工地转移发货发车", "工地转移发货操作",
					"操作成功", "工地转移发货", "d_scrap_trans");
			return "0";

		} else {
			return "1";
		}
	}

	/**
	 * 根据姓名获取userId
	 * 
	 * @param name
	 * @return
	 */
	public String getUserIdByName(String userName) {
		GgkzUserInfoQuery entity = new GgkzUserInfoQuery();
		entity.setName(userName);
		GgkzUserInfo info = ggkzUserInfoDaoImpl.selectOneByEntity(entity);
		if (info != null) {
			return info.getUserId();
		}
		return userName;
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
	 * 工地转移收货处理
	 * 
	 * @param transId
	 * @param userId
	 * @param status
	 * @param comment
	 * @return
	 */
	public String MWorkTransReceive(String transId, String userId,
			String status, String comment) {
		MWorkshopTrans entity = new MWorkshopTrans();
		entity.setTransId(transId);
		entity = mWorkshopTransDaoImpl.selectOneByEntity(entity);
		entity.setStatusCd(status);
		entity.setDealComment(comment);
		entity.setArrivalTm(new Date());
		int i = mWorkshopTransDaoImpl.updateByEntity(entity);
		if (status.equals("Yes")) {// 收货同意处理原工地和目标工地
			List<MTransItem> itemList = entity.getItemList();
			if (itemList != null && itemList.size() > 0) {
				for (int j = 0; j < itemList.size(); j++) {
					MTransItem item = new MTransItem();
					item = itemList.get(j);
					// 处理工地剩料明细的数据，目标工地新增，原工地减少
					i += workItemDeal("from", entity.getFromWsCd().trim(), item
							.getCategoryNm().trim(), item.getTotalAmount(),
							item.getModelNo());// 原工地
					i += workItemDeal("to", entity.getToWsCd().trim(), item
							.getCategoryNm().trim(), item.getTotalAmount(),
							item.getModelNo());// 目标工地
					logger.debug("新增明细【{}】", i);
				}
			}
		}

		if (i > 0) {
			if (status.equals("Yes")) {
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(
						entity.getTransUserCd(),
						"【" + entity.getReceiver() + "】已经同意收货",
						"您从【" + entity.getFromWsCd() + "】发往【"
								+ entity.getToWsCd() + "】的工地转移发货",getUserIdByName(entity.getReceiver()));
			} else {
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(
						entity.getTransUserCd(),
						"【" + entity.getReceiver() + "】收货时发现有问题",
						"您从【" + entity.getFromWsCd() + "】发往【"
								+ entity.getToWsCd() + "】的工地转移发货，问题为【"
								+ entity.getDealComment() + "】",getUserIdByName(entity.getReceiver()));

			}

			// 添加日志
			OperLogUtil.insertOperLog(entity.getTransId(),
					PublicDict.MODEL_GDZY, "工地转移",
					PublicDict.OPER_LOG_EVENT_UPDATE, "工地转移收货", "工地转移收货",
					"操作成功", "工地转移收货", "d_scrap_trans");
			return "0";
		}
		return "1";
	}

	/**
	 * 工地明细处理方法： 原工地剩料明细减少，目标工地剩料明细增加
	 * 
	 * @param flag
	 *            from_原工地减少，to_新工地，增加
	 * @param wsNm
	 *            工地中心名称
	 * @param materialNm
	 *            设备名称，如果名称不一样，则为新设备， 例:同为螺丝，如填了螺丝钉，之前填写了螺丝，那么螺丝和螺丝钉在系统里就是两种设备
	 * @param totalAmount
	 *            数量
	 * @param modelNo
	 *            型号
	 */
	public int workItemDeal(String flag, String wsNm, String categoryNm,
			Integer totalAmount, String modelNo) {
		int i = 0;
		logger.debug("工地明细处理方法： 原工地剩料明细减少，目标工地剩料明细增加：工地代码【{}】；剩料名称【{}】", wsNm,
				categoryNm);
		logger.debug("转移数量【{}】", totalAmount);
		MWorkshopItemQuery itemQuery = new MWorkshopItemQuery();
		itemQuery.setWsCd(wsNm);
		itemQuery.setCategoryNm(categoryNm.trim());
		itemQuery.setModelNo(modelNo.trim());
		MWorkshopItem item = mWorkshopItemDaoImpl.selectOneByEntity(itemQuery);
		if (item != null) {
			if (flag.equals("from")) {// 原工地减少
				int remain = item.getRemainNumber() - totalAmount;
				item.setRemainNumber(remain);
			} else {// 新工地增加
				int remain = item.getRemainNumber() + totalAmount;
				int total=item.getTotalAmount()+totalAmount;
				item.setTotalAmount(total);
				item.setRemainNumber(remain);
			}
			mWorkshopItemDaoImpl.updateByEntity(item);
		} else {// 如果没有数据，说明是新料，新增
			item = new MWorkshopItem();
			String itemId = KeyGen.getCommonKeyGen(PublicDict.Item_id);
			item.setItemId(itemId);
			item.setWsCd(wsNm);
			item.setCategoryNm(categoryNm);
			item.setTotalAmount(totalAmount);
			item.setRemainNumber(totalAmount);
			item.setModelNo(modelNo);
			i += mWorkshopItemDaoImpl.insert(item);
		}

		return i;
	}

	/**
	 * 获取收货仓库
	 * 
	 * @return
	 */
	public Map<String, String> getReceiptMap() {
		MWorkshop shop = new MWorkshop();
		// shop.setTypeCd("3");
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
	 * 根据条件更新对象
	 * 
	 * @param entity
	 * @return
	 */
	public int updateByEntity(MWorkshopTrans entity) {
		int i = mWorkshopTransDaoImpl.updateByEntity(entity);
		if (i > 0) {
			// 添加日志
			OperLogUtil.insertOperLog(entity.getTransId(),
					PublicDict.MODEL_SCRAPTRANS, "剩料管理",
					PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "更新剩料信息", "更新成功",
					"更新剩料信息", "d_scrap_trans");
		}
		return i;
	}

	/**
	 * 根据userId，获取工地目前的剩料
	 * 
	 * @param userId
	 * @return
	 */
	public List<MWorkshopItem> getMWorkshopItemList(String wsCd) {
		MWorkshopItemQuery shop = new MWorkshopItemQuery();
		shop.setWsCd(wsCd);
		return mWorkshopItemDaoImpl.selectByEntity(shop);
	}

	/**
	 * 根据当前用户id得到数据列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<MWorkshopTrans> getMworkshopTransList(String userId) {
		List<MWorkshopTrans> transList = new ArrayList<MWorkshopTrans>();
		String post = WsUtil.getPostByUserId(userId);// 当前用户的职务代码
		if ("38".equals(post)) {// 职务为区域部长
			transList = verifiedSiteHead(userId);
			// 职务为1总经理3部长11信息主管2部门领导,6董事长
		} else if ("1".equals(post) || "3".equals(post)||"11".equals(post)||"2".equals(post)||"6".equals(post)) {
			transList = verifiedAll();// 查询所有
		} else {// 项目经理和其他职务只查询自己相关的数据
			transList = verifiedSiteLevel(userId);
		}
		return transList;
	}

	/**
	 * 根据工地编码获取当前工地的剩料数据列表
	 * 
	 * @param fromWsCd
	 * @return
	 */
	public List<MTransItem> getTransItemListByWscd(String fromWsCd,
			String transId) {
		MTransItemQuery item = new MTransItemQuery();
		if (StringUtils.isEmpty(transId)) {
			item.setWsCd(fromWsCd);
			return mTransItemDaoImpl.getTransItemListByWscd(item);
		} else {
			item.setTransId(transId);
			return mTransItemDaoImpl.selectByEntity(item);
		}

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
	 * 区域部长，查询跟自己相关的数据和下属项目经理的数据
	 * 
	 * @param userId
	 * @return
	 */
	public List<MWorkshopTrans> verifiedSiteHead(String userId) {
		List<MWorkshopTrans> siteHeadList = new ArrayList<MWorkshopTrans>();
		siteHeadList.addAll(this.verifiedSiteLevel(userId));// 查询跟自己相关的数据
		List<String> strList = WsUtil.getSiteUserIdByEareaheadUserId(userId);// 查询区域部长所有下属项目经理的id
		for (String siteId : strList) {
			siteHeadList.addAll(this.verifiedSiteLevel(siteId));// 查询区域部长所有下属项目经理的
		}
		return siteHeadList;
	}

	/**
	 * 总经理与部长的表，查询所有的数据
	 * 
	 * @return
	 */
	public List<MWorkshopTrans> verifiedAll() {
		List<MWorkshopTrans> allList = new ArrayList<MWorkshopTrans>();
		MWorkshopTrans entity = new MWorkshopTrans();
		allList = mWorkshopTransDaoImpl.selectByEntity(entity);
		return allList;
	}
	/**
	 * 根据姓名获取userId
	 * 
	 * @param name
	 * @return
	 */
	public String getUserNameById(String userId) {
		GgkzUserInfoQuery entity = new GgkzUserInfoQuery();
		entity.setUserId(userId);
		GgkzUserInfo info = ggkzUserInfoDaoImpl.selectOneByEntity(entity);
		if (info != null) {
			return info.getName();
		}
		return userId;
	}
	/**
	 * 项目经理级别，查询跟自己相关的数据
	 * 
	 * @param userId
	 * @param statusCd
	 * @return
	 */
	public List<MWorkshopTrans> verifiedSiteLevel(String userId) {
		List<MWorkshopTrans> levelList = new ArrayList<MWorkshopTrans>();// 中间list
		levelList = new ArrayList<MWorkshopTrans>();
		MWorkshopTransQuery entity = new MWorkshopTransQuery();
		entity.setTransUserCd(userId);// 报告者为当前用户
		levelList.addAll(mWorkshopTransDaoImpl.selectByEntity(entity));
		entity.setTransUserCd(null);// 报告者为当前用户
		entity.setReceiver(getUserNameById(userId));// 接收者为当前用户
		levelList.addAll(mWorkshopTransDaoImpl.selectByEntity(entity));
		return levelList;
	}

	public void setMWorkshopTransDaoImpl(
			MWorkshopTransDaoImpl mWorkshopTransDaoImpl) {
		this.mWorkshopTransDaoImpl = mWorkshopTransDaoImpl;
	}
	
	/**
	 * 删除工地转移的信息与工地转移的图片
	 * @param entity
	 * @return
	 */
	public int deleteByEntity(MWorkshopTrans entity){
	    int i = 0;
	    i = this.mWorkshopTransDaoImpl.deleteByEntity(entity);//删除工地转移的信息
	    DScrapTransImageQuery image = new DScrapTransImageQuery();
        image.setTransId(entity.getTransId());
	    i+=dScrapTransImageServiceImpl.deleteByEntity(image);//删除工地转移的图片信息与系统 目录下的图片
	    return i;
	    
	}
}
