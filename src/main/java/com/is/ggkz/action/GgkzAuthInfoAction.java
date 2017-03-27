package com.is.ggkz.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.ggkz.entity.GgkzAuthInfo;
import com.is.ggkz.entity.query.GgkzAuthInfoQuery;
import com.is.ggkz.service.GgkzAuthInfoServiceImpl;

/**
 * 
 * @ClassName: GgkzAuthInfoAction
 */
@Namespace("/ggkz")
public class GgkzAuthInfoAction extends BaseStruts2Action {
	private static final long serialVersionUID = 1L;
  
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GgkzAuthInfoAction.class);
	@Autowired
	public GgkzAuthInfoServiceImpl ggkzAuthInfoServiceImpl;

	// -- 页面属性 --//
	private GgkzAuthInfo entity;

	private GgkzAuthInfoQuery queryObject;

	public GgkzAuthInfo getEntity() {
		return entity;
	}

	public void setEntity(GgkzAuthInfo entity) {
		this.entity = entity;
	}

	public GgkzAuthInfoQuery getQueryObject() {
		return queryObject;
	}

	public String getAuthId() {
		return authId;
	}

	private String authId;
	public GgkzAuthInfoAction() {
		super();
	}

	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String input() throws Exception {
		return "ggkz-auth-info/add";
	}

	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	public String save() throws Exception {
		ggkzAuthInfoServiceImpl.saveAuth(entity);
		entity = ggkzAuthInfoServiceImpl.getAuthInfo(entity.getAuthId());
		return "ggkz-auth-info/view";
	}
	
	public String edit() throws Exception {
		ggkzAuthInfoServiceImpl.updateAuth(entity);
		entity = ggkzAuthInfoServiceImpl.getAuthInfo(entity.getAuthId());
		return "ggkz-auth-info/view";
	}
	
	public String toView() throws Exception {
		if(entity == null){
			entity = new GgkzAuthInfo();
		}
		entity = ggkzAuthInfoServiceImpl.getAuthInfo(entity.getAuthId());
		return "ggkz-auth-info/view";
	}
	
	public String toEdit() throws Exception {
		entity = ggkzAuthInfoServiceImpl.getAuthInfo(entity.getAuthId());
		return "ggkz-auth-info/edit";
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public void setGgkzAuthInfoServiceImpl(
			GgkzAuthInfoServiceImpl ggkzAuthInfoServiceImpl) {
		this.ggkzAuthInfoServiceImpl = ggkzAuthInfoServiceImpl;
	}

	public void setQueryObject(GgkzAuthInfoQuery queryObject) {
		this.queryObject = queryObject;
	}
	public static void main(String[] args) {
		
		String str = "{'data':[{'categoryCd':'32','categoryNm':'','itemId':'11','materialCd':'3','materialNm':'2吧','memo':'','modelNo':'','totalAmount':33,'transId':'','unit':'','usePlace':'','wsCd':'15'},{'categoryCd':'12','categoryNm':'','itemId':'22','materialCd':'','materialNm':'螺丝刀','memo':'','modelNo':'','totalAmount':9,'transId':'','unit':'','usePlace':'','wsCd':'15'},{'categoryCd':'12','categoryNm':'','itemId':'33','materialCd':'3','materialNm':'避孕套','memo':'','modelNo':'','totalAmount':12,'transId':'','unit':'','usePlace':'','wsCd':'15'}],'flag':'0'}";
		str = str.substring(str.indexOf("["),str.indexOf("]")+1);
	}
}
