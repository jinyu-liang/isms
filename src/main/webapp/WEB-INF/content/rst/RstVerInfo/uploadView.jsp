<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<s:property value="message" escapeHtml="false" />
<s:hidden name="entity.verId" value="%{entity.verId}" />
<a href="<%=basePath%>/<s:property value='entity.fileName'/>"><font
	color="red">下载最新版本</font>
</a>
