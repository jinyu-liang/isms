package com.base.dict;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractRequiredListTag;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * @ClassName: DataConvert
 * @Description:
 * @author life
 * 
 */
@SuppressWarnings("serial")
public class DataConvertTag extends AbstractRequiredListTag {
	protected String defaultValue;
	protected String withCode;
	protected String separator;

	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new DataConvert(stack, req, res);
	}

	protected void populateParams() {
		super.populateParams();

		DataConvert dataConvert = ((DataConvert) component);
		dataConvert.setDefault(defaultValue);
		dataConvert.setWithCode(withCode);
		dataConvert.setSeparator(separator);
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
