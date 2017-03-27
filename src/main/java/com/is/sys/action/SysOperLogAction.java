package com.is.sys.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.dict.SysDictUtil;
import com.base.mybatis.BaseStruts2Action;
import com.is.sys.entity.SysDict;
import com.is.sys.entity.SysOperLog;
import com.is.sys.entity.query.SysOperLogQuery;
import com.is.sys.service.SysOperLogServiceImpl;

/**
 * 
 * @ClassName: SysOperLogAction
 * @Description: SysOperLog表对应的Action类
 * @author 
 * @date 2013-02-27 14:20:26 *
 */
@Namespace("/sys")
public class SysOperLogAction extends BaseStruts2Action {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SysOperLogAction.class);

	public SysOperLogAction() {
		super();
		if(entity == null){
			entity =  new SysOperLog();
		}
		if(queryEntity == null){
			queryEntity = new SysOperLogQuery();
		}
	}

	private SysOperLogServiceImpl sysOperLogServiceImpl;
	// -- 页面属性 --//
	private SysOperLog entity;

	private SysOperLogQuery queryEntity;
	
	/**模块编号*/
	private List<SysDict> modelTypeList;

	public List<SysDict> getModelTypeList() {
		return modelTypeList;
	}

	public void setModelTypeList(List<SysDict> modelTypeList) {
		this.modelTypeList = modelTypeList;
	}

	/**
	 * 初始化字典
	 */
	private void dictInit() {
		modelTypeList = SysDictUtil.getDictByType("model");
	}
	public SysOperLog getEntity() {
		return entity;
	}

	public void setEntity(SysOperLog entity) {
		this.entity = entity;
	}

	public SysOperLogQuery getQueryEntity() {
		return queryEntity;
	}

	public void setQueryEntity(SysOperLogQuery queryEntity) {
		this.queryEntity = queryEntity;
	}

	@Autowired
	public void setSysOperLogServiceImpl(
			SysOperLogServiceImpl sysOperLogServiceImpl) {
		this.sysOperLogServiceImpl = sysOperLogServiceImpl;
	}

	public String list() throws Exception {
		dictInit();
		page = sysOperLogServiceImpl.pageQuery(queryEntity);
		return "SysOperLog/list";
	}

	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public String view() throws Exception {
		dictInit();
		entity = sysOperLogServiceImpl.selectOneByEntity(entity);
		return "SysOperLog/view";
	}

}
