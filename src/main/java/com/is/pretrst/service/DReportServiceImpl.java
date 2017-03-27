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
import com.is.pretrst.dao.DReportDaoImpl;
import com.is.pretrst.entity.DReport;
import com.is.pretrst.entity.query.DReportImageQuery;
import com.is.pretrst.entity.query.DReportQuery;
import com.is.utils.PublicDict;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 * 
 * @ClassName: DReportServiceImpl
 * @Description: DReport表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:43 *
 */
@Component
@Transactional
public class DReportServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(DReport.class);
	@Autowired
	private DReportDaoImpl dReportDaoImpl;
	@Autowired
	private DReportImageServiceImpl dReportImageServiceImpl;
	@Autowired
	private PmsMessageTopicProducer pmsMessageTopicProducer;

	public Page selectReportPage(DReportQuery queryEntity) {
		return dReportDaoImpl.selectReportPage(queryEntity);
	}

	/**
	 * 新增保存方法
	 * 
	 * @param entity
	 */
	public void saveDReport(DReport entity) {
		String newRportId = KeyGen.getCommonKeyGen(PublicDict.DReport_id);
		UserDetail user = SpringSecurityUtils.getCurrentUser();
		entity.setReportId(newRportId);
		entity.setReportUserCd(user.getUserId());
		entity.setReportTm(new Date());
		int i = dReportDaoImpl.insert(entity);
		if (i > 0) {

			// 添加日志
			OperLogUtil.insertOperLog(entity.getReportId(),
					PublicDict.MODEL_REPORT, "采购管理",
					PublicDict.OPER_LOG_EVENT_ADD, "添加", "添加采购信息", "添加成功",
					"添加采购信息", "d_report");
		}
	}

	/**
	 * 修改方法
	 * 
	 * @param entity
	 */
	public void updateDReport(DReport entity) {
		int i = dReportDaoImpl.updateByEntity(entity);
		if (i > 0) {
			// 添加日志
			OperLogUtil.insertOperLog(entity.getReportId(),
					PublicDict.MODEL_REPORT, "采购管理",
					PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "更新采购信息", "更新成功",
					"更新采购信息", "d_report");
		}
	}

	/**
	 * 获取对象查询方法
	 * 
	 * @param reportId
	 * @return
	 */
	public DReport getDreport(DReport entity) {
		return dReportDaoImpl.selectOneByEntity(entity);
	}

	public DReportDaoImpl getdReportDaoImpl() {
		return dReportDaoImpl;
	}

	@Autowired
	public void setdReportDaoImpl(DReportDaoImpl dReportDaoImpl) {
		this.dReportDaoImpl = dReportDaoImpl;
	}

	/**
	 * 采购申请审核方法
	 * 
	 * @param entity
	 */
	public String verfied(DReport entity) {
		UserDetail user = SpringSecurityUtils.getCurrentUser();
		entity.setVerifiedHeadTm(new Date());
		entity.setVerifiedUserCd(user.getUserId());
		if (entity.getVerifiedHeadStatus().equals("Yes")) {
			entity.setStatusCd("1");
			entity.setDealResult("0");
		} else {
			entity.setStatusCd("0");
		}
		int i = dReportDaoImpl.updateByEntity(entity);
		if (i > 0) {
			// 添加日志
			OperLogUtil.insertOperLog(entity.getReportId(),
					PublicDict.MODEL_REPORT, "采购管理",
					PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "更新采购信息", "更新成功",
					"更新采购信息", "d_report");

			if (entity.getVerifiedHeadStatus().equals("Yes")) {
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(
						entity.getReportUserCd(),"【" + entity.getVerifiedUserName() + "】采购审批同意通知",
						"【" + entity.getVerifiedUserName() + "】已经审批同意采购申请,您从【" + entity.getProjName() + "】发起的采购申请【"
								+ entity.getTitle() + "】",entity.getVerifiedUserCd());
			} else {
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(
						entity.getReportUserCd(),"【" + entity.getVerifiedUserName() + "】采购审批同意通知",
						"【" + entity.getVerifiedUserName() + "】不同意采购申请,您从【" + entity.getProjName() + "】发起的采购申请【"
								+ entity.getTitle() + "】,问题为【"
								+ entity.getVerifiedHeadMemo() + "】",entity.getVerifiedUserCd());

			}

			return "1";
		} else {
			return "0";
		}
	}

	/**
	 * 发票处理审核方法
	 * 
	 * @param entity
	 */
	public String billDeal(DReport entity) {
		UserDetail user = SpringSecurityUtils.getCurrentUser();
		entity.setProcessTm(new Date());
		entity.setProcessUserCd(user.getUserId());
		entity.setProcessUserName(user.getUsername());
		if (entity.getDealResult().equals("Yes")) {
			entity.setStatusCd("3");
		} else {
			entity.setStatusCd("1");
		}
		int i = dReportDaoImpl.updateByEntity(entity);
		if (i > 0) {
			// 添加日志
			OperLogUtil.insertOperLog(entity.getReportId(),
					PublicDict.MODEL_REPORT, "采购管理",
					PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "更新采购信息", "更新成功",
					"更新采购信息", "d_report");

			if (entity.getDealResult().equals("Yes")) {
				// logger.debug("采购发起人【{}】，审批人【{}】",entity.getReportUserCd(),entity.getVerifiedUserCd());
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(
						entity.getReportUserCd(),"采购申请财务处理通知",
						"【" + entity.getProcessUserName() + "】对采购申请：【"
								+ entity.getTitle() + "】财务处理完毕,【"
								+ entity.getReportUserName() + "】从【"
								+ entity.getProjName() + "】发起的采购申请【"
								+ entity.getTitle() + "】已经处理完毕", entity.getProcessUserCd());
				pmsMessageTopicProducer.sendTopic(
						entity.getVerifiedUserCd(),"采购申请财务处理通知",
							"【" + entity.getProcessUserName() + "】对采购申请：【"
							+ entity.getTitle() + "】财务处理完毕,【"
							+ entity.getReportUserName() + "】从【"
							+ entity.getProjName() + "】发起的采购申请【"
							+ entity.getTitle() + "】已经处理完毕", entity.getProcessUserCd());
			} else {
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(
						entity.getReportUserCd(),"采购申请财务处理通知",
						"【" + entity.getProcessUserName() + "】对采购进行财务处理时发现有问题,【" + entity.getReportUserName() + "】从【"
								+ entity.getProjName() + "】发起的采购申请【"
								+ entity.getTitle() + "】财务处理发现问题，问题为【"
								+ entity.getComment() + "】", entity.getProcessUserCd());
				pmsMessageTopicProducer.sendTopic(
						entity.getVerifiedUserCd(),"采购申请财务处理通知",
						"【" + entity.getProcessUserName() + "】对采购进行财务处理时发现有问题,【" + entity.getReportUserName() + "】从【"
								+ entity.getProjName() + "】发起的采购申请【"
								+ entity.getTitle() + "】财务处理发现问题，问题为【"
								+ entity.getComment() + "】", entity.getProcessUserCd());

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
	public int deleteByEntity(DReport entity){
	    int i=0;
	    DReportImageQuery image = new DReportImageQuery();
	    image.setReportId(entity.getReportId());
	    i += dReportImageServiceImpl.deleteByEntity(image);//删除数据库采购图片
	    i += dReportDaoImpl.deleteByEntity(entity);
	   return i;
	}
	
}
