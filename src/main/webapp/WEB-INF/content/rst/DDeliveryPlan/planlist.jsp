<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发料管理</title>
</head>
<body>
    <div class="unitBox" id="plan_main_plan" style="height: 60%;width: 100%;"><s:action name="d-delivery-plan!list" executeResult="true" ignoreContextParams="false" flush="true" namespace="/rst"></s:action></div>
    <div class="unitBox" id="plan_child_plan" style="height: 40%;width: 100%;"><s:action name="d-invoice!list" executeResult="true" ignoreContextParams="false" flush="true" namespace="/rst"></s:action></div>
</body>
</html>