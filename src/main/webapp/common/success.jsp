<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>operate-prompt|操作提示</title>
</head>
<body>
	<s:if test="infoMessage == null || infoMessage == ''">
		<div id="system_msg" class="info">
			操作成功!
		</div>
	</s:if>
	<s:else>
		<s:property value="infoMessage" escapeHtml="false"/>
	</s:else>
</body>
</html>
