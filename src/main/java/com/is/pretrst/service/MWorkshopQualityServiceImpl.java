package com.is.pretrst.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.base.security.SpringSecurityUtils;
import com.is.ggkz.entity.ext.UserDetail;
import com.is.mq.producer.PmsMessageTopicProducer;
import com.is.pretrst.dao.MWorkshopQualityDaoImpl;
import com.is.pretrst.dao.MWorkshopQualityDaoImpl;
import com.is.pretrst.entity.MWorkshopQuality;
import com.is.pretrst.entity.query.DQualityImageQuery;
import com.is.pretrst.entity.query.MWorkshopQualityQuery;
import com.is.utils.PublicDict;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 * 
 * @ClassName: MWorkshopQualityServiceImpl
 * @Description: MWorkshopQuality表对应的服务类
 * @author 
 * @date
 */
@Component
@Transactional
public class MWorkshopQualityServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(MWorkshopQuality.class);
	@Autowired
	private MWorkshopQualityDaoImpl dqualityDaoImpl;
	@Autowired
	private DQualityImageServiceImpl dqualityImageServiceImpl;
	@Autowired
	private PmsMessageTopicProducer pmsMessageTopicProducer;

	public Page selectReportPage(MWorkshopQualityQuery queryEntity) {
		return dqualityDaoImpl.selectReportPage(queryEntity);
	}

	/**
	 * 新增保存方法
	 * 
	 * @param entity
	 */
	public void saveDQuality(MWorkshopQuality entity) {
		String newRportId = KeyGen.getCommonKeyGen(PublicDict.DReport_id);
//		UserDetail user = SpringSecurityUtils.getCurrentUser();
//		entity.setReportId(newRportId);
//		entity.setReportUserCd(user.getUserId());
//		entity.setReportTm(new Date());
//		int i = dqualityDaoImpl.insert(entity);
//		if (i > 0) {
//
//			// 添加日志
//			OperLogUtil.insertOperLog(entity.getReportId(),
//					PublicDict.MODEL_REPORT, "采购管理",
//					PublicDict.OPER_LOG_EVENT_ADD, "添加", "添加采购信息", "添加成功",
//					"添加采购信息", "d_report");
//		}
	}

	/**
	 * 修改方法
	 * 
	 * @param entity
	 */
	public void updateDQuality(MWorkshopQuality entity) {
//		int i = dqualityDaoImpl.updateByEntity(entity);
//		if (i > 0) {
//			// 添加日志
//			OperLogUtil.insertOperLog(entity.getReportId(),
//					PublicDict.MODEL_REPORT, "采购管理",
//					PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "更新采购信息", "更新成功",
//					"更新采购信息", "d_report");
//		}
	}

	/**
	 * 获取对象查询方法
	 * 
	 * @param reportId
	 * @return
	 */
	public MWorkshopQuality getDQuality(MWorkshopQuality entity) {
		return dqualityDaoImpl.selectOneByEntity(entity);
	}

	public MWorkshopQualityDaoImpl getDQualityDaoImpl() {
		return dqualityDaoImpl;
	}

	@Autowired
	public void setDQualityDaoImpl(MWorkshopQualityDaoImpl DQualityDaoImpl) {
		this.dqualityDaoImpl = DQualityDaoImpl;
	}

	/**
	 * 采购申请审核方法
	 * 
	 * @param entity
	 */
	public String verfied(MWorkshopQuality entity) {
//		UserDetail user = SpringSecurityUtils.getCurrentUser();
//		entity.setVerifiedHeadTm(new Date());
//		entity.setVerifiedUserCd(user.getUserId());
//		if (entity.getVerifiedHeadStatus().equals("Yes")) {
//			entity.setStatusCd("1");
//			entity.setDealResult("0");
//		} else {
//			entity.setStatusCd("0");
//		}
//		int i = dqualityDaoImpl.updateByEntity(entity);
//		if (i > 0) {
//			// 添加日志
//			OperLogUtil.insertOperLog(entity.getReportId(),
//					PublicDict.MODEL_REPORT, "采购管理",
//					PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "更新采购信息", "更新成功",
//					"更新采购信息", "d_report");
//
//			if (entity.getVerifiedHeadStatus().equals("Yes")) {
//				// 发消息接口
//				pmsMessageTopicProducer.sendTopic(
//						entity.getReportUserCd(),"【" + entity.getVerifiedUserName() + "】采购审批同意通知",
//						"【" + entity.getVerifiedUserName() + "】已经审批同意采购申请,您从【" + entity.getProjName() + "】发起的采购申请【"
//								+ entity.getTitle() + "】",entity.getVerifiedUserCd());
//			} else {
//				// 发消息接口
//				pmsMessageTopicProducer.sendTopic(
//						entity.getReportUserCd(),"【" + entity.getVerifiedUserName() + "】采购审批同意通知",
//						"【" + entity.getVerifiedUserName() + "】不同意采购申请,您从【" + entity.getProjName() + "】发起的采购申请【"
//								+ entity.getTitle() + "】,问题为【"
//								+ entity.getVerifiedHeadMemo() + "】",entity.getVerifiedUserCd());
//
//			}
//
//			return "1";
//		} else {
			return "0";
//		}
	}

	/**
	 * 发票处理审核方法
	 * 
	 * @param entity
	 */
	public String billDeal(MWorkshopQuality entity) {
		UserDetail user = SpringSecurityUtils.getCurrentUser();
		

		if (entity.getDealType().equals("zq")) {
			
			if("1".equals(entity.getFqstatus())){
				entity.setFqstatus("2");
			}else if("2".equals(entity.getFqstatus())){
				entity.setFqstatus("3");
			}
			
		}else if(entity.getDealType().equals("fq")){
			entity.setFqstatus("4");
		}else if(entity.getDealType().equals("jl")){
			entity.setFqstatus("5");
		}
		
		entity.setZgfinishuserid(user.getUserId());
		entity.setZgfinishusername(user.getUsername());
		System.out.println("-------entity------------"+entity);
		System.out.println("-------entity------------"+entity.getZgfinishuserid());
		System.out.println("-------entity------------"+entity.getZgfinishusername());
		int i = dqualityDaoImpl.updateByEntity(entity);
		if (i > 0) {
			// 添加日志
			OperLogUtil.insertOperLog(entity.getPid(),
					PublicDict.MODEL_WORKSHOP_QUALITY, "质量管理",
					PublicDict.MODEL_WORKSHOP_QUALITY, "更新", "更新整改方信息", "更新成功",
					"更新整改方信息", "m_workshop_quality");

			if (entity.getDealType().equals("zq")) {
				// logger.debug("采购发起人【{}】，审批人【{}】",entity.getReportUserCd(),entity.getVerifiedUserCd());
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(
						entity.getPid(),"整改接收通知",
						"【】已经处理完毕", entity.getPid());
				 
			} else {
				

			}

			return "1";
		} else {
			return "0";
		}
	}
	/**
	 * 删除采购信息
	 * @param entity
	 * @return
	 */
	public int deleteByEntity(MWorkshopQuality entity){
	    int i=0;
//	    DQualityImageQuery image = new DQualityImageQuery();
//	    image.setReportId(entity.getReportId());
//	    i += dqualityImageServiceImpl.deleteByEntity(image);//删除数据库采购图片
//	    i += dqualityDaoImpl.deleteByEntity(entity);
	   return i;
	}
	
}
