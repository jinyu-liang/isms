package com.is.pretrst.service;

import java.text.ParseException;
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
import com.is.pretrst.dao.MWorkshopContactDaoImpl;
import com.is.pretrst.entity.DExProject;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.MWorkshopContact;
import com.is.pretrst.entity.MWorkshopJoblog;
import com.is.pretrst.entity.query.MWorkshopContactQuery;
import com.is.pretrst.entity.query.MWorkshopJoblogQuery;
import com.is.pretrst.entity.query.MWorkshopQuery;
import com.is.utils.PublicDict;
import com.is.utils.date.DateUtil;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 * 
 * @ClassName: MWorkshopContactServiceImpl
 * @Description: MWorkshopContact表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:43 *
 */
@Component
@Transactional
public class MWorkshopContactServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(MWorkshopContact.class);
	@Autowired
	private MWorkshopContactDaoImpl MWorkshopContactDaoImpl;
	@Autowired
	private PmsMessageTopicProducer pmsMessageTopicProducer;

	/**
	 * 分布查詢
	 * 
	 * @param query
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageQuery(MWorkshopContactQuery query) {
		return MWorkshopContactDaoImpl.pageQuery("MWorkshopContact.selectByPage", query);
	}
	
    /**
     * 根据查询条件条件查询单个用户信息
     * 
     * @return
     */
    @Transactional(readOnly = true)
    public MWorkshopContact selectOneByEntity(MWorkshopContact entity)
    {
        return MWorkshopContactDaoImpl.selectOneByEntity(entity);
    }

	/**
	 * 新增保存方法
	 * 
	 * @param entity
	 * @throws ParseException 
	 */
	public void saveMWorkshopContact(MWorkshopContact entity) throws ParseException {
		String id = KeyGen.getCommonKeyGen(PublicDict.M_WORKSHOP_CONTACT);
		
		entity.setID(id);
		entity.setAddtime(DateUtil.getCurDate());
		int i = MWorkshopContactDaoImpl.insert(entity);
		if (i > 0) {

			// 添加日志
			OperLogUtil.insertOperLog(id,
					PublicDict.M_WORKSHOP_CONTACT, "工作联系单管理",
					PublicDict.OPER_LOG_EVENT_ADD, "添加", "添加工作联系单信息", "添加成功",
					"添加工作联系单信息", "m_workshop_contact");
		}
	}

	/**
	 * 修改方法
	 * 
	 * @param entity
	 */
	public void updateMWorkshopContact(MWorkshopContact entity) {
		int i = MWorkshopContactDaoImpl.updateByEntity(entity);
		if (i > 0) {
			// 添加日志
			OperLogUtil.insertOperLog(entity.getID(),
					PublicDict.MODEL_REPORT, "工作联系单管理",
					PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "更新工作联系单信息", "更新成功",
					"更新工作联系单信息", "m_workshop_contact");
		}
	}

	/**
	 * 获取对象查询方法
	 * 
	 * @param reportId
	 * @return
	 */
	public MWorkshopContact getMWorkshopContact(MWorkshopContact entity) {
		return MWorkshopContactDaoImpl.selectOneByEntity(entity);
	}

	public MWorkshopContactDaoImpl getMWorkshopContactDaoImpl() {
		return MWorkshopContactDaoImpl;
	}

	@Autowired
	public void setMWorkshopContactDaoImpl(MWorkshopContactDaoImpl MWorkshopContactDaoImpl) {
		this.MWorkshopContactDaoImpl = MWorkshopContactDaoImpl;
	}

	/**
	 * 工作联系单申请审核方法
	 * 
	 * @param entity
	 */
	public String verfied(MWorkshopContact entity) {
//		UserDetail user = SpringSecurityUtils.getCurrentUser();
//		entity.setVerifiedHeadTm(new Date());
//		entity.setVerifiedUserCd(user.getUserId());
//		if (entity.getVerifiedHeadStatus().equals("Yes")) {
//			entity.setStatusCd("1");
//			entity.setDealResult("0");
//		} else {
//			entity.setStatusCd("0");
//		}
//		int i = MWorkshopContactDaoImpl.updateByEntity(entity);
//		if (i > 0) {
//			// 添加日志
//			OperLogUtil.insertOperLog(entity.getReportId(),
//					PublicDict.MODEL_REPORT, "工作联系单管理",
//					PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "更新工作联系单信息", "更新成功",
//					"更新工作联系单信息", "m_workshop_contact");
//
//			if (entity.getVerifiedHeadStatus().equals("Yes")) {
//				// 发消息接口
//				pmsMessageTopicProducer.sendTopic(
//						entity.getReportUserCd(),"【" + entity.getVerifiedUserName() + "】工作联系单审批同意通知",
//						"【" + entity.getVerifiedUserName() + "】已经审批同意工作联系单申请,您从【" + entity.getProjName() + "】发起的工作联系单申请【"
//								+ entity.getTitle() + "】",entity.getVerifiedUserCd());
//			} else {
//				// 发消息接口
//				pmsMessageTopicProducer.sendTopic(
//						entity.getReportUserCd(),"【" + entity.getVerifiedUserName() + "】工作联系单审批同意通知",
//						"【" + entity.getVerifiedUserName() + "】不同意工作联系单申请,您从【" + entity.getProjName() + "】发起的工作联系单申请【"
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
	 * 删除工作联系单信息
	 * @param entity
	 * @return
	 */
	public int deleteByEntity(MWorkshopContact entity){
	    int i=0;
//	    MWorkshopContactImageQuery image = new MWorkshopContactImageQuery();
//	    image.setReportId(entity.getReportId());
//	    i += MWorkshopContactImageServiceImpl.deleteByEntity(image);//删除数据库工作联系单图片
//	    i += MWorkshopContactDaoImpl.deleteByEntity(entity);
	   return i;
	}
	
	/**
	 * 
	 * @param queryEntity
	 * @return 1_删除成功,0_删除失败
	 */
	public String delete(MWorkshopContact entity) {
		String ID = entity.getID();
		UserDetail user = SpringSecurityUtils.getCurrentUser();
		MWorkshopContact workshop=MWorkshopContactDaoImpl.selectOneByEntity(entity);
		String status = workshop.getStatus();
		if(!user.getUserId().equals(workshop.getFquserid())){
			return "2";
		}
		if("0".equals(status)){
			int i = MWorkshopContactDaoImpl.deleteByEntity(entity);
			if(i>0){
				 // 添加日志
              OperLogUtil.insertOperLog(ID, PublicDict.M_WORKSHOP_CONTACT,
                      "工作联系单", PublicDict.OPER_LOG_EVENT_DEL, "删除", "删除工作联系单信息", "删除成功",
                      "删除工作联系单信息", "m_workshop_contact");
              
  			// 发消息接口
  			pmsMessageTopicProducer.sendTopic(
  					workshop.getFquserid(),
  					"删除了工作联系单【"+workshop.getWs_nm()+"】",
  					"删除了工作联系单【"+workshop.getWs_nm()+"】,原负责人为【"+workshop.getFqusername()+"】","");
				return "1";
			}else{
				return "0";
			}
		}else{
			return "0";
		}
		
	}
}
