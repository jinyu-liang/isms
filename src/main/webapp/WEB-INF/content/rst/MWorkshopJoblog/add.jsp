<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>新增工作日志</title>
	</head>
	<body>
		<div class="page">
			<s:property value="message" escapeHtml="false"/>
			<div class="pageContent">
				<s:form method="post" action="m-workshop-joblog!add.action" cssClass="pageForm" onsubmit="return validateCallback(this,$.pdialog.closeCurrentRefresh);">
					<div class="pageFormContent col1" layoutH="56">
						<s:include value="_edit.jsp" />
					</div>	
					<div class="formBar">
						<ul>
							<li>
								<div class="buttonActive">
									<div class="buttonContent">
										<s:submit type="button" value="确定" />
									</div>
								</div>
							</li>
							<li>
								<div class="button">
									<div class="buttonContent">
										<button type="button" onclick="$.pdialog.closeCurrent();">关闭</button>
									</div>
								</div>
							</li>
						</ul>
					</div>			
				</s:form>
			</div>
		</div>
	</body>
</html>
