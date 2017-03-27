package com.base.iterceptor;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;

public class Ret {
	public static void ret301(String message) {
		String ajaxErr = "{\"statusCode\":\"301\"}";
		try {
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.write(ajaxErr);
			out.close();
			out.flush();
		} catch (IOException e) {
		}
	}
}
