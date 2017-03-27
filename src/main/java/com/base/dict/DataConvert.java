package com.base.dict;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.ListUIBean;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * @ClassName: DataConvert
 * @Description:
 * @author life
 * 
 */
public class DataConvert extends ListUIBean {
	public static final String TEMPLATE = "dataConvert";

	protected String defaultValue;
	protected String withCode;
	protected String separator;

	public DataConvert(ValueStack stack, HttpServletRequest request,
			HttpServletResponse response) {
		super(stack, request, response);
	}

	protected String getDefaultTemplate() {
		return TEMPLATE;
	}

	public void evaluateExtraParams() {
		super.evaluateExtraParams();
		super.setTheme("is");
		if (defaultValue != null) {
			addParameter("defaultValue", defaultValue);
		}
		if (withCode != null) {
			addParameter("withCode", withCode);
		}
		if (separator != null) {
			addParameter("separator", separator);
		}
	}

	public void setDefault(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setWithCode(String withCode) {
		this.withCode = withCode;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

}
