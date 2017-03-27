package com.is.pretrst.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.ggkz.dao.GgkzUserInfoDaoImpl;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.mq.producer.PmsMessageTopicProducer;
import com.is.pretrst.dao.DContractImageDaoImpl;
import com.is.pretrst.dao.DExItemDaoImpl;
import com.is.pretrst.dao.DExProjectDaoImpl;
import com.is.pretrst.dao.MWorkshopDaoImpl;
import com.is.pretrst.entity.DContractImage;
import com.is.pretrst.entity.DExItem;
import com.is.pretrst.entity.DExProject;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.query.MWorkshopQuery;
import com.is.sys.dao.SysAttachAuthDaoImpl;
import com.is.utils.PublicDict;
import com.is.utils.StringUtils;
import com.is.utils.date.DateUtil;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 * 
 * @ClassName: MWorkshopServiceImpl
 * @Description: MWorkshop表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:59 *
 */
@Component
@Transactional
public class MWorkshopServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(MWorkshop.class);

	@Autowired
	private MWorkshopDaoImpl mWorkshopDaoImpl;
	@Autowired
	private DExProjectServiceImpl dExProjectServiceImpl;

	public SysAttachAuthDaoImpl getAttachAuthDaoImpl() {
		return attachAuthDaoImpl;
	}

	public void setAttachAuthDaoImpl(SysAttachAuthDaoImpl attachAuthDaoImpl) {
		this.attachAuthDaoImpl = attachAuthDaoImpl;
	}

	@Autowired
	private DExProjectDaoImpl dExProjectDaoImpl;
	@Autowired
	private SysAttachAuthDaoImpl attachAuthDaoImpl;
	@Autowired
	private DContractImageDaoImpl contractImageDaoImpl;
	@Autowired
	private DExItemDaoImpl dExItemDaoImpl;
	@Autowired
	private PmsMessageTopicProducer pmsMessageTopicProducer;
	@Autowired
	private GgkzUserInfoDaoImpl ggkzUserInfoDaoImpl;
	
	
	

	public DExProjectDaoImpl getdExProjectDaoImpl() {
		return dExProjectDaoImpl;
	}

	public void setdExProjectDaoImpl(DExProjectDaoImpl dExProjectDaoImpl) {
		this.dExProjectDaoImpl = dExProjectDaoImpl;
	}

	public MWorkshopDaoImpl getmWorkshopDaoImpl() {
		return mWorkshopDaoImpl;
	}

	public void setmWorkshopDaoImpl(MWorkshopDaoImpl mWorkshopDaoImpl) {
		this.mWorkshopDaoImpl = mWorkshopDaoImpl;
	}

	public DExProjectServiceImpl getdExProjectServiceImpl() {
		return dExProjectServiceImpl;
	}

	public void setdExProjectServiceImpl(
			DExProjectServiceImpl dExProjectServiceImpl) {
		this.dExProjectServiceImpl = dExProjectServiceImpl;
	}

	/**
	 * 查询所有工地列表
	 * 
	 * @return
	 */
	public List<MWorkshop> selectAll() {
		return mWorkshopDaoImpl.selectAll();
	}

	/**
	 * 根据工地名称查询负责人名称
	 * 
	 * @param entity
	 * @return
	 */
	public String getShopMangeUserName(String wsNm) {
		return mWorkshopDaoImpl.getShopMangeUserName(wsNm);
	}

	/**
	 * 根据工地名称查询工地wscd
	 * 
	 * @param entity
	 * @return
	 */
	public String getShopWsCd(String wsNm) {
		return mWorkshopDaoImpl.getShopWsCd(wsNm);
	}

	/**
	 * 根据工地名称查询负责人名称
	 * 
	 * @param entity
	 * @return
	 */
	public List<MWorkshop> getShopMangeUserId(String wsId) {
		MWorkshopQuery work = new MWorkshopQuery();
		work.setManagerUserId(wsId);
		return mWorkshopDaoImpl.selectByEntity(work);
	}

	/**
	 * 根据条件查询工地
	 */
	public List<MWorkshop> selectByEntity(MWorkshop entity) {
		return mWorkshopDaoImpl.selectByEntity(entity);
	}
	/**
	 * 查询一个工地对象
	 */
	public MWorkshop selectOneByEntity(MWorkshop entity) {
	    return mWorkshopDaoImpl.selectOneByEntity(entity);
	}

	public Page selectMWorkShopPage(MWorkshopQuery queryEntity) {
		return mWorkshopDaoImpl
				.pageQuery("MWorkshop.selectByPage", queryEntity);
	}

	public MWorkshop getOneWorkshop(MWorkshop entity) {
		return mWorkshopDaoImpl.selectOneByEntity(entity);
	}

	/**
	 * 工地处理方法
	 * 
	 * @param projectEntity
	 */
	public void workDeal(DExProject projectEntity, List<DExItem> itemList) {
	    int i =0;
		String projectId = projectEntity.getProjectId();
		if (StringUtils.isEmpty(projectId)) {
			projectId = KeyGen.getCommonKeyGen(PublicDict.project_Id);
			projectEntity.setProjectId(projectId);
			i = dExProjectDaoImpl.insert(projectEntity);
			if(i>0){
			 // 添加日志
                OperLogUtil.insertOperLog(projectEntity.getProjectId(), PublicDict.MODEL_WORKSHOP,
                        "工地管理", PublicDict.OPER_LOG_EVENT_ADD, "添加", "添加工地管理信息", "添加成功",
                        "添加工地管理信息", "ex_person_manager"); 
			}
		} else {
		    i = dExProjectDaoImpl.updateByEntity(projectEntity);
            if(i>0){
                // 添加日志
                OperLogUtil.insertOperLog(projectEntity.getProjectId(), PublicDict.MODEL_WORKSHOP,
                        "工地管理", PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "更新工地管理信息", "更新成功",
                        "更新工地管理信息", "m_workshop"); 
            }
		}
		dexItemDeal(projectId, itemList);

	}

	/**
	 * 先删除再插入
	 * 
	 * @param projectId
	 * @param itemList
	 */
	private void dexItemDeal(String projectId, List<DExItem> itemList) {
		// 删除原有数据
		DExItem dExItem = new DExItem();
		dExItem.setProjectId(projectId);
		int result = dExItemDaoImpl.deleteByEntity(dExItem);
		if(result>0){
		 // 添加日志
            OperLogUtil.insertOperLog(projectId, PublicDict.MODEL_WORKSHOP,
                    "工地管理", PublicDict.OPER_LOG_EVENT_ADD, "添加", "添加工地管理信息", "添加成功",
                    "添加工地管理信息", "m_workshop");
		}
		// 新增传入的列表
		int addResult =0;
		if (itemList != null && itemList.size() > 0) {
			for (int i = 0; i < itemList.size(); i++) {
				dExItem = new DExItem();
				dExItem.setProjectId(projectId);
				dExItem.setItemId(KeyGen.getCommonKeyGen("item"));
				dExItem.setMemo(itemList.get(i).getMemo());
				dExItem.setAmount(itemList.get(i).getAmount());
				dExItem.setItemNm(itemList.get(i).getItemNm());
				addResult +=dExItemDaoImpl.insert(dExItem);
			}
			if(addResult>0){
			 // 添加日志
	            OperLogUtil.insertOperLog(projectId, PublicDict.MODEL_WORKSHOP,
	                    "工地管理", PublicDict.OPER_LOG_EVENT_ADD, "添加", "添加工地管理信息", "添加成功",
	                    "添加工地管理信息", "m_workshop"); 
			}
		}
	}

	/**
	 * 工地保存
	 * 
	 * @param workshop
	 */
	public void workSave(MWorkshop workshop) {
		String wsCd = KeyGen.getCommonKeyGen(PublicDict.project_Id);
		workshop.setWsCd(wsCd);
		int i = mWorkshopDaoImpl.insert(workshop);
		if(i>0){
		 // 添加日志
            OperLogUtil.insertOperLog(workshop.getWsCd(), PublicDict.MODEL_WORKSHOP,
                    "工地管理", PublicDict.OPER_LOG_EVENT_ADD, "添加", "添加工地管理信息", "添加成功",
                    "添加工地管理信息", "m_workshop"); 
			// 发消息接口
			pmsMessageTopicProducer.sendTopic(
					workshop.getManagerUserId(),
					"新增了工地【"+workshop.getWsNm()+"】",
					"新增了工地【"+workshop.getWsNm()+"】,负责人为【"+getUserNameByAndroid(workshop.getManagerUserId())+"】","");
//			// 发消息接口
//			pmsMessageTopicProducer.sendTopic(
//					"post1",
//					"新增了工地【"+workshop.getWsNm()+"】",
//					"新增了工地【"+workshop.getWsNm()+"】,负责人为【"+getUserNameByAndroid(workshop.getManagerUserId())+"】","");
//			// 发消息接口
//			pmsMessageTopicProducer.sendTopic(
//					"post3",
//					"新增了工地【"+workshop.getWsNm()+"】",
//					"新增了工地【"+workshop.getWsNm()+"】,负责人为【"+getUserNameByAndroid(workshop.getManagerUserId())+"】","");
		}
		// 附件处理
		attatchDeal(wsCd, workshop.getFilenames());
	}
    /**
     * 根据userId获取userName
     * @param userId
     * @return
     */
	public String getUserNameByAndroid(String userId) {
		GgkzUserInfo entity=new GgkzUserInfo();
		entity.setUserId(userId);
		GgkzUserInfo userIndo = ggkzUserInfoDaoImpl.selectOneByEntity(entity);
		if(userIndo!=null){
			return userIndo.getName();
		}else{
			return userId;
		}
	
	}
	/**
	 * 工地更新
	 * 
	 * @param workshop
	 * @return
	 */
	public String workUpdate(MWorkshop workshop) {
		int i = 0;
		i += mWorkshopDaoImpl.updateByEntity(workshop);
		attatchDeal(workshop.getWsCd(), workshop.getFilenames());
		if (i != 0) {// 成功
		    // 添加日志
            OperLogUtil.insertOperLog(workshop.getWsCd(), PublicDict.MODEL_WORKSHOP,
                    "工地管理", PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "更新工地管理信息", "更新成功",
                    "更新工地管理信息", "m_workshop"); 
            
			// 发消息接口
			pmsMessageTopicProducer.sendTopic(
					workshop.getManagerUserId(),
					"修改了工地【"+workshop.getWsNm()+"】",
					"修改了工地【"+workshop.getWsNm()+"】,负责人为【"+getUserNameByAndroid(workshop.getManagerUserId())+"】","");
//			// 发消息接口
//			pmsMessageTopicProducer.sendTopic(
//					"post1",
//					"修改了工地【"+workshop.getWsNm()+"】",
//					"修改了工地【"+workshop.getWsNm()+"】,负责人为【"+getUserNameByAndroid(workshop.getManagerUserId())+"】","");
//			// 发消息接口
//			pmsMessageTopicProducer.sendTopic(
//					"post3",
//					"修改了工地【"+workshop.getWsNm()+"】",
//					"修改了工地【"+workshop.getWsNm()+"】,负责人为【"+getUserNameByAndroid(workshop.getManagerUserId())+"】","");
			return "0";
		} else {
			return "1";
		}
	}

	/**
	 * 附件处理操作
	 * 
	 * @param busiId
	 * @param fileIds
	 */
	public void attatchDeal(String busiId, String fileIds) {
		// web端附件上传业务处理
		if (StringUtils.isNotEmpty(fileIds)) {
			DContractImage entity = new DContractImage();
			entity.setWsCd(busiId);
			int result = contractImageDaoImpl.deleteByEntity(entity);
			if(result>0){
			 // 添加日志
	            OperLogUtil.insertOperLog(busiId, PublicDict.MODEL_WORKSHOP,
	                    "工地管理", PublicDict.OPER_LOG_EVENT_ADD, "添加", "添加工地合同图片", "添加成功",
	                    "添加工地合同图片", "d_contract_image");
			}
			int addResult = 0;
			String[] fileIdss = fileIds.split(";");// 将所有文件Id区分开来
			for (int i = 0; i < fileIdss.length; i++) {
				String sysFileName = fileIdss[i];
				DContractImage sysAttachAuth = new DContractImage();
				sysAttachAuth.setPhotoId(KeyGen.getCommonKeyGen("conc"));
				sysAttachAuth.setWsCd(busiId);
				sysAttachAuth.setFilename("upload/pub/"
						+ DateUtil.getCurDateByyyyymmdd() + "/" + sysFileName);
				addResult =+contractImageDaoImpl.insert(sysAttachAuth);
			}
			if(addResult>0){
			 // 添加日志
                OperLogUtil.insertOperLog(busiId, PublicDict.MODEL_WORKSHOP,
                        "工地管理", PublicDict.OPER_LOG_EVENT_ADD, "添加", "添加工地合同图片", "添加成功",
                        "添加工地合同图片", "d_contract_image");
			}
		}
	}

	/**
	 * 
	 * @param queryEntity
	 * @return 1_删除成功,0_删除失败
	 */
	public String delete(MWorkshopQuery queryEntity) {
		String wsCd = queryEntity.getWsCd();
		MWorkshop workshop=mWorkshopDaoImpl.selectOneByEntity(queryEntity);
		DExProject project = new DExProject();
		project.setWsCd(wsCd);
		DExProject project1 = dExProjectServiceImpl.selectOneByEntity(project);
		if (project1 == null) {
			int i = mWorkshopDaoImpl.deleteByEntity(queryEntity);
			if(i>0){
			 // 添加日志
                OperLogUtil.insertOperLog(queryEntity.getWsCd(), PublicDict.MODEL_WORKSHOP,
                        "工地管理", PublicDict.OPER_LOG_EVENT_DEL, "删除", "删除工地管理信息", "删除成功",
                        "删除工地管理信息", "d_contract_image");
                
    			// 发消息接口
    			pmsMessageTopicProducer.sendTopic(
    					workshop.getManagerUserId(),
    					"删除了工地【"+workshop.getWsNm()+"】",
    					"删除了工地【"+workshop.getWsNm()+"】,原负责人为【"+workshop.getManagerUserId()+"】","");
//    			// 发消息接口
//    			pmsMessageTopicProducer.sendTopic(
//    					"post1",
//    					"删除了工地【"+workshop.getWsNm()+"】",
//    					"删除了工地【"+workshop.getWsNm()+"】,原负责人为【"+workshop.getManagerUserId()+"】","");
//    			// 发消息接口
//    			pmsMessageTopicProducer.sendTopic(
//    					"post3",
//    					"删除了工地【"+workshop.getWsNm()+"】",
//    					"删除了工地【"+workshop.getWsNm()+"】,原负责人为【"+workshop.getManagerUserId()+"】",""); 
			}
			return "1";
		} else {
			return "0";
		}
	}

	public void setContractImageDaoImpl(
			DContractImageDaoImpl contractImageDaoImpl) {
		this.contractImageDaoImpl = contractImageDaoImpl;
	}

	public DContractImageDaoImpl getContractImageDaoImpl() {
		return contractImageDaoImpl;
	}

	public DExItemDaoImpl getdExItemDaoImpl() {
		return dExItemDaoImpl;
	}

	public void setdExItemDaoImpl(DExItemDaoImpl dExItemDaoImpl) {
		this.dExItemDaoImpl = dExItemDaoImpl;
	}

}
