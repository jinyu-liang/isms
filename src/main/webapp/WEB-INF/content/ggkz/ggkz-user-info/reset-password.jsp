<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pt" uri="/is-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>修改密码</title>
	</head>
	<body>
		<div class="page unitBox" >
			<s:property value="message" escapeHtml="false" />
			<div class="pageContent">
				<s:form method="post" action="ggkz/ggkz-user-info!resetPwd.action"  cssClass="pageForm" onsubmit="return validateCallback(this,closeAndRefresh);" id="%{_}">
                    <div class="pageFormContent col1" layoutH="56">
                    <div>
                        <label for="userName">用户名</label>
                        <span>
                            <s:textfield value="%{entity.name}" readonly="readonly" disabled="true"/>
                        </span>
                    </div>
						<div>
							<label for="newPassword">请输入新密码</label>
							<span>
								<s:password name="entity.newPassword" id="newPassword" cssClass="required alphanumeric" minlength="6" maxlength="20"></s:password>
						  	</span>
						</div>
						<div>
							<label for="newPasswordAgain">再次输入新密码</label>
							<span>
								<s:password name="entity.name" id="newPasswordAgain" cssClass="required" equalto="#newPassword"></s:password>
						  	</span>
						</div>
					</div>
					 <div class="formBar">
							<ul>
								<li>
									<div class="buttonActive">
										<div class="buttonContent">
											<s:submit type="button" value="确定" />
											<s:hidden name="entity.userId" value="%{entity.userId}"></s:hidden>
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
		<script type="text/javascript">
		function closeAndRefresh(json) {
			if (json.statusCode == DWZ.statusCode.error) {
				if (json.message && alertMsg)
					alertMsg.error(json.message);
			} else if (json.statusCode == DWZ.statusCode.timeout) {
				if (alertMsg)
					alertMsg.error(json.message || DWZ.msg("sessionTimout"), {
						okCall : DWZ.loadLogin
					});
				else
					DWZ.loadLogin();
			} else {
				if (json.warnMessage && alertMsg) {
					alertMsg.error(json.warnMessage);
				} else if (json.infoMessage && alertMsg) {
					alertMsg.info(json.infoMessage);
				} else if (json.message && alertMsg) {
					alertMsg.correct(json.message);
					/* navTabSearch(this);*/
					$.pdialog.closeCurrent(); 
				}
			}
		}
		</script>
	</body>
</html>
