package com.is.ggkz.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.base.mybatis.Page;
import com.is.ggkz.entity.GgkzRoleAuth;
import com.is.ggkz.entity.query.GgkzRoleAuthQuery;
import com.is.ggkz.service.GgkzRoleAuthServiceImpl;

/**
 * 
 * @ClassName: GgkzRoleAuthAction
 * @Description: GgkzRoleAuth表对应的Action类
 * @author 
 * @date 2013-02-27 14:19:29 *
 */
// 定义URL映射对应/readroom/readroom-worker.action
@Namespace("/ggkz")
public class GgkzRoleAuthAction extends BaseStruts2Action {
	private static final long serialVersionUID = 1L;
	
	private String  authId;//权限id
	private String roleId;//角色id
	//private GgkzRoleAuthQuery query;
	@Autowired
	private GgkzRoleAuthServiceImpl ggkzRoleAuthServiceImpl;
	//页面属性
	private GgkzRoleAuth  entity;
	private List<GgkzRoleAuth>  ggkzRoleAuthList;
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GgkzRoleAuthAction.class);

	public GgkzRoleAuthAction() {
		super();
	}

	 
	public String list() throws Exception {
		page = ggkzRoleAuthServiceImpl.pageQuery(new GgkzRoleAuthQuery());
		return "ggkz-role-auth/list";
	}

	 
	public String input() throws Exception {
		return "ggkz-role-auth/input";
	}

	 
	public String save() throws Exception {
		ggkzRoleAuthServiceImpl.insertGgkzRoleAuth(entity);
		list();
		return  "ggkz-role-auth/list";
	}

	 /**
	  * 删除单个对象的方法
	  * @return 请求的页面路径
	  * @throws Exception
	  */
	public String delete() throws Exception {
		if(entity == null){
			entity = new GgkzRoleAuth();
		}
		entity.setAuthId(authId);
		entity.setRoleId(roleId);
		ggkzRoleAuthServiceImpl.deleteGgkzRoleAuth(entity);
		list();//调用查询方法
		return "ggkz-role-auth/list";//返回列表页
	}
	 
	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public void setGgkzRoleAuthServiceImpl(GgkzRoleAuthServiceImpl ggkzRoleAuthServiceImpl) {
		this.ggkzRoleAuthServiceImpl = ggkzRoleAuthServiceImpl;
	}

	public GgkzRoleAuth getEntity() {
		return entity;
	}

	public void setEntity(GgkzRoleAuth entity) {
		this.entity = entity;
	}

	public List<GgkzRoleAuth> getGgkzRoleAuthList() {
		return ggkzRoleAuthList;
	}

	public void setGgkzRoleAuthList(List<GgkzRoleAuth> ggkzRoleAuthList) {
		this.ggkzRoleAuthList = ggkzRoleAuthList;
	}
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
