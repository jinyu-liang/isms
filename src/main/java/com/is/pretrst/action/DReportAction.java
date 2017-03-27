package com.is.pretrst.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.base.security.SpringSecurityUtils;
import com.is.ggkz.entity.ext.UserDetail;
import com.is.pretrst.entity.DReport;
import com.is.pretrst.entity.query.DReportQuery;
import com.is.pretrst.service.DReportServiceImpl;

/**
 * 
 * @ClassName: DReportAction
 * @Description: DReport表对应的Action类
 * @author 
 * @date 2013-09-11 17:24:26 *
 */
@Namespace("/rst")
public class DReportAction extends BaseStruts2Action {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DReportAction.class);

	private DReportServiceImpl dReportServiceImpl;

	private DReportQuery queryEntity;

	private DReport entity;

	public DReportAction() {
		super();
		if (entity == null) {
			entity = new DReport();
		}
		if (queryEntity == null) {
			queryEntity = new DReportQuery();
		}
	}

	/**
	 * 采购列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		page = dReportServiceImpl.selectReportPage(queryEntity);
		return "DReport/list";
	}

	/**
	 * 转向用户输入页面
	 */
	public String toCreate() throws Exception {
		return "DReport/add";
	}

	/**
	 * 转向用户输入页面
	 */
	public String toEdit() throws Exception {
		entity = dReportServiceImpl.getDreport(entity);
		return "DReport/edit";
	}

	/**
	 * 打开查看页面
	 */
	public String view() throws Exception {
		entity = dReportServiceImpl.getDreport(queryEntity);
		return "DReport/view";
	}
	/**
	 * 打开查看页面
	 */
	public String imgView() throws Exception {
		entity = dReportServiceImpl.getDreport(queryEntity);
		return "DReport/imgView";
	}
	/**
	 * 保存页面
	 */
	public String create() throws Exception {
		dReportServiceImpl.saveDReport(entity);
		return SUCCESS;
	}

	/**
	 * 审核页面
	 */
	public String toVerfied() throws Exception {
		entity = dReportServiceImpl.getDreport(queryEntity);
		return "DReport/verified";
	}

	/**
	 * 发票处理页面
	 */
	public String toDeal() throws Exception {
		entity = dReportServiceImpl.getDreport(queryEntity);
		return "DReport/deal";
	}

	/**
	 * 发票处理审核保存页面
	 */
	public String billDeal() throws Exception {
		DReport dreport = dReportServiceImpl.getDreport(queryEntity);
		//采购审核人
		dreport.setDealResult(entity.getDealResult());
		dreport.setComment(entity.getComment());
		String flag=dReportServiceImpl.billDeal(dreport);
		if(flag.equals("1")){
			setMessage("操作成功!");	
		}else{
			setInfoMessage("操作失败!");
		}
		return JSON_DATA;

	}

	/**
	 * 审核保存页面
	 */
	public String verified() throws Exception {
		DReport dreport = dReportServiceImpl.getDreport(queryEntity);
		LOGGER.debug("打印==【{}】+++【{}】", entity, dreport.getVerifiedHeadStatus());

		String verfieder = dreport.getVerifiedUserCd();
		UserDetail user = SpringSecurityUtils.getCurrentUser();
		LOGGER.debug("========【{}】=======【{}】", verfieder, user.getUserId());
		dreport.setVerifiedHeadMemo(entity.getVerifiedHeadMemo());
		dreport.setVerifiedHeadStatus(entity.getVerifiedHeadStatus());
		String flag=dReportServiceImpl.verfied(dreport);
		if(flag.equals("1")){
			setMessage("操作成功!");	
		}else{
			setInfoMessage("操作失败");
		}
		return JSON_DATA;
	}

	/**
	 * 删除采购信息
	 * @return
	 */
	public String deleteReport(){
	    if(dReportServiceImpl.deleteByEntity(entity)!=0){
	        setMessage("删除成功");
	    }else{
	        setInfoMessage("删除失败");
	    }
	    return JSON_DATA;
	}
	@Override
	public String getWarnMessage() {
		// TODO Auto-generated method stub
		return super.getWarnMessage();
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

	@Override
	public String getInfoMessage() {
		// TODO Auto-generated method stub
		return super.getInfoMessage();
	}
	public DReportServiceImpl getdReportServiceImpl() {
		return dReportServiceImpl;
	}

	@Autowired
	public void setdReportServiceImpl(DReportServiceImpl dReportServiceImpl) {
		this.dReportServiceImpl = dReportServiceImpl;
	}

	public DReportQuery getQueryEntity() {
		return queryEntity;
	}

	public void setQueryEntity(DReportQuery queryEntity) {
		this.queryEntity = queryEntity;
	}

	public DReport getEntity() {
		return entity;
	}

	public void setEntity(DReport entity) {
		this.entity = entity;
	}
}
