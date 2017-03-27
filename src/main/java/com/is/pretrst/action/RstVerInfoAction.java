package com.is.pretrst.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.mybatis.BaseStruts2Action;
import com.base.security.SpringSecurityUtils;
import com.is.ggkz.entity.ext.UserDetail;
import com.is.pretrst.entity.RstVerInfo;
import com.is.pretrst.entity.query.RstVerInfoQuery;
import com.is.pretrst.service.RstVerInfoServiceImpl;

/**
 * 
 * @ClassName: RstVerInfoAction
 * @Description: RstVerInfo表对应的Action类
 * @author 
 * @date 2013-10-26 12:44:17 *
 */
@Namespace("/rst")
public class RstVerInfoAction extends BaseStruts2Action {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RstVerInfoAction.class);

	private RstVerInfo entity;

	private RstVerInfoQuery queryEntity;
	private RstVerInfoServiceImpl rstVerInfoServiceImpl;

	public RstVerInfoAction() {
		super();
		if (entity == null) {
			entity = new RstVerInfo();
		}
		if (queryEntity == null) {
			queryEntity = new RstVerInfoQuery();
		}
	}

	/**
	 * 获取版本列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toVerConf() throws Exception {
		page = rstVerInfoServiceImpl.selectAll();
		return "RstVerInfo/verConf";
	}
	
	/**
	 * 获取最新版本
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getNewVerInfo() throws Exception {
		entity = rstVerInfoServiceImpl.getMaxVerCode();
		return "RstVerInfo/uploadView";
	}

	/**
	 * 版本停用启用功能
	 * 
	 * @return
	 * @throws Exception
	 */

	public String verStartStop() throws Exception {
		String startStopFlag = rstVerInfoServiceImpl.verStartStop(
				entity.getVerCode(), entity.getStatusCd());
		if (startStopFlag.equals("1")) {
			setMessage("操作成功!");
		} else {
			setInfoMessage("不能停用，应至少有一个版本启用!");
		}
		return JSON_DATA;
	}

	public String verConf() throws Exception {
		UserDetail user = SpringSecurityUtils.getCurrentUser();

		String verFlag = rstVerInfoServiceImpl.getVerFlag(entity.getVerCode());
		if (verFlag.equals("1")) {
			setMessage("您输入的版本号已经存在!");
		} else {
			String i = rstVerInfoServiceImpl.verConf(entity, user);
			if (i.equals("0")) {
				setMessage("版本维护成功!");
			} else {
				setMessage("修改失败!");

			}
		}
		return JSON_DATA;
	}

	public String toEdit() throws Exception {
		return "";
	}

	public String edit() throws Exception {
		return "";
	}

	public String delete() throws Exception {
		return "";
	}

	public String view() throws Exception {
		return "";
	}

	public String toList() throws Exception {
		return "RstVerInfo/list";
	}

	public String list() throws Exception {
		page = rstVerInfoServiceImpl.selectAll();
		return "RstVerInfo/list";
		// return "RstVerInfo/verConf";
	}

	/**
	 * 校验版本号是否重复
	 * 
	 * @return 1：重复 0：i重复
	 * @throws IOException
	 */
	public String isExist() throws IOException {
		ServletActionContext.getResponse().getWriter()
				.print(rstVerInfoServiceImpl.getVerFlag(entity.getVerCode()));// 有重复的;
		return null;
	}

	public RstVerInfo getEntity() {
		return entity;
	}

	public void setEntity(RstVerInfo entity) {
		this.entity = entity;
	}

	public RstVerInfoQuery getQueryEntity() {
		return queryEntity;
	}

	public void setQueryEntity(RstVerInfoQuery queryEntity) {
		this.queryEntity = queryEntity;
	}

	public RstVerInfoServiceImpl getRstVerInfoServiceImpl() {
		return rstVerInfoServiceImpl;
	}

	public void setRstVerInfoServiceImpl(
			RstVerInfoServiceImpl rstVerInfoServiceImpl) {
		this.rstVerInfoServiceImpl = rstVerInfoServiceImpl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.base.mybatis.BaseStruts2Action#getWarnMessage()
	 */
	@Override
	public String getWarnMessage() {
		// TODO Auto-generated method stub
		return super.getWarnMessage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.base.mybatis.BaseStruts2Action#getMessage()
	 */
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.base.mybatis.BaseStruts2Action#getInfoMessage()
	 */
	@Override
	public String getInfoMessage() {
		// TODO Auto-generated method stub
		return super.getInfoMessage();
	}

}
