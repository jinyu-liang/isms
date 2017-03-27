<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>RFID资源管理</title>
	<meta name="decorator" content="main"/>
</head>

<body>
<div id="query"></div>
<div id="tools"></div>
<div id="table">
	<form action="ggkz-role-auth!save.action" method="post" id="form1">
			角色id:<s:textfield name="entity.roleId"  id="entity_roleId"/></br >
			权限id:<s:textfield name="entity.authId"  id="entity_authId"/></br>
			操作时间:<s:textfield name="entity.operTime"  id="entity_operTime"/></br >
			操作用户id:<s:textfield name="entity.operUserId"  id="entity_operUserId"/></br >
			<input type="submit" name="submit" value="提交"/>
			<input type="button" name="goback" onclick="javascript:history.back(-1)"  value="关闭"/>
	</form>
</div>
<div id="page"></div>
</body>
</html>
