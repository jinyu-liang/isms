package com.is.pretrst.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.base.security.SpringSecurityUtils;
import com.is.ggkz.entity.ext.UserDetail;
import com.is.mq.producer.PmsMessageTopicProducer;
import com.is.pretrst.dao.ExPersonInfoDaoImpl;
import com.is.pretrst.dao.ExPersonManagerDaoImpl;
import com.is.pretrst.entity.ExPersonInfo;
import com.is.pretrst.entity.ExPersonManager;
import com.is.pretrst.entity.query.ExPersonInfoQuery;
import com.is.pretrst.entity.query.ExPersonManagerQuery;
import com.is.utils.PublicDict;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 *
 * @ClassName: ExPersonManagerServiceImpl
 * @Description: ExPersonManager表对应的服务类
 * @author 
 * @date 2013-09-11 17:24:50 *
 */
@Component
@Transactional 
public class ExPersonManagerServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(ExPersonManager.class);
	private ExPersonManagerDaoImpl exPersonManagerDaoImpl ;
	
	@Autowired
	private PmsMessageTopicProducer pmsMessageTopicProducer;
	@Autowired
	private ExPersonInfoDaoImpl exPersonInfoDaoImpl;
	

	public void savePersonManager(ExPersonManager entity) {
		exPersonManagerDaoImpl.insert(entity);
		// 添加日志
        OperLogUtil.insertOperLog(entity.getReportId(), PublicDict.MODEL_PERSON,
                "外线人员管理", PublicDict.OPER_LOG_EVENT_ADD, "添加", "添加外线人员信息", "添加成功",
                "添加外线人员信息", "ex_person_manager"); 
	}
	public void delete(ExPersonManager entity) {
		exPersonManagerDaoImpl.deleteByEntity(entity);
	}
	/**
	 * 外线处理
	 * @param entity
	 */
	public void deal(ExPersonManager entity) {
		UserDetail user = SpringSecurityUtils.getCurrentUser();
		entity.setProcessTm(new Date());
		entity.setProcessUserCd(user.getUserId());
		entity.setProcessUserName(user.getUsername());
		int i = exPersonManagerDaoImpl.updateByEntity(entity);
		//外线人员
		i+=personListDeal(entity);
		if(i>0){
		    // 添加日志
		    OperLogUtil.insertOperLog(entity.getReportId(), PublicDict.MODEL_PERSON,
		            "外线人员管理", PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "更新外线人员信息", "更新成功",
		            "更新外线人员信息", "ex_person_manager"); 
		    
		    
			if (entity.getStatusCd().equals("Yes")) {
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(
						entity.getReportUserCd(),
						"【" + user.getUsername() + "】已经处理您的外线人员报告",
						"您从【" + entity.getWorkCenterId() + "】发起的【"
								+ entity.getTitle() + "】",entity.getProcessUserCd());
			} else {
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(
						entity.getReportUserCd(),
						"【" + user.getUsername() + "】处理时发现有问题",
						"您从【" + entity.getWorkCenterId() + "】发起的【"
								+ entity.getTitle() + "】有问题，问题为【"
								+ entity.getDealComment() + "】",entity.getProcessUserCd());
			}
		    
		}
	}
	/**
	 * 管理员处理外线人员明细列表
	 * @param entity
	 */
	private int personListDeal(ExPersonManager entity) {
		ExPersonInfo  info=new ExPersonInfo();
		info.setReportId(entity.getReportId());
		info.setFlag("1");
		int i=0;
		if(entity.getStatusCd().equals("Yes")){
			 i = exPersonInfoDaoImpl.perInfoDeal(info);	
		}
		return i;
	}

	@Autowired
	public void setExPersonManagerDaoImpl(ExPersonManagerDaoImpl exPersonManagerDaoImpl) {
		this.exPersonManagerDaoImpl = exPersonManagerDaoImpl;
	}
	public ExPersonManagerDaoImpl getExPersonManagerDaoImpl() {
		return exPersonManagerDaoImpl;
	}
	public ExPersonManager getPersonManager(ExPersonManager entity) {
		return exPersonManagerDaoImpl.selectOneByEntity(entity);
	}
	
	public Page selectPersonManagerPage(ExPersonManagerQuery queryEntity) {
		return exPersonManagerDaoImpl.selectExpersonManagerPage(queryEntity);
	}
	public ExPersonInfoDaoImpl getExPersonInfoDaoImpl() {
		return exPersonInfoDaoImpl;
	}
	public void setExPersonInfoDaoImpl(ExPersonInfoDaoImpl exPersonInfoDaoImpl) {
		this.exPersonInfoDaoImpl = exPersonInfoDaoImpl;
	}


}
