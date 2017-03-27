package com.base.userTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * @ClassName: DataConvert
 * @Description:
 * @author life
 * 
 */
@SuppressWarnings("serial")
public class UserNameConvertTag extends AbstractUITag {
	/** 用户id */
	protected String userId;

	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new UserNameConvert(stack, req, res);
	}

	protected void populateParams() {
		super.populateParams();
		UserNameConvert username = (UserNameConvert)component;
		username.setUserId(userId);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
