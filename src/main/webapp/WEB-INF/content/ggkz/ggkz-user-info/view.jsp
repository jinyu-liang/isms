<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pt" uri="/is-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>查看用户信息</title>
	</head>
	<body>
		<div class="page">
			<s:property value="message" escapeHtml="false"/>
			<div class="pageContent">
				<s:form method="post" action="ggkz-user-info!toEdit.action" cssClass="pageForm" onsubmit="return navTabSearch(this);">
					
					<table class="list" width="100%">
						<tr>
							<td>姓名</td>
							<td><span><s:property value="entity.name" /></span></td>
						</tr>
						<tr>
							<td>工号</td>
							<td><span><s:property value="entity.workCode" /></span></td>
						</tr>
						<tr>
							<td>用户名</td>
							<td><span><s:property value="entity.loginName" /></span></td>
						</tr>
						<tr>
							<td>角色</td>
							<td>
								<span>
									<s:iterator value="entity.ggzkRoleBeans" id="role">
										<s:property value="roleName"/>
									</s:iterator>
								</span>
							</td>
						</tr>
						<tr>
							<td>性别</td>
							<td><span><pt:dictshow name="entity.sex" listValue="dictName" listKey="dictCode" list="sexList"></pt:dictshow></span></td>
						</tr>
						<tr>
							<td>出生日期</td>
							<td><span><s:date name="entity.birthDate" format="yyyy-MM-dd"/></span></td>
						</tr>
						<tr>
							<td>证件类型</td>
							<td><span><pt:dictshow name="entity.certificateType" listValue="dictName" listKey="dictCode" list="certificateTypeList"></pt:dictshow></span></td>
						</tr>
						<tr>
							<td>证件号码</td>
							<td><span><s:property value="entity.certificateCode"/></span></td>
						</tr>
						<tr>
							<td>现居住地</td>
							<td><span><s:property value="entity.nowResideAddress"/></span></td>
						</tr>
						<tr>
							<td>邮编</td>
							<td><span><s:property value="entity.postcode"/></span></td>
						</tr>
						<tr>
							<td>当前状态</td>
							<td><span>
								<pt:dictshow name="entity.userState" listValue="dictName" listKey="dictCode" list="userStateList"></pt:dictshow>
								</span></td>
						</tr>
					</table>
					<div class="formBar">
						<ul>
							<li>
								<div class="buttonActive">
									<div class="buttonContent">
										<button type="button" onclick="resetPassword(this);">重置密码</button>
									</div>
								</div>
							</li>
							<li>
								<div class="buttonActive">
									<div class="buttonContent">
										<s:if test="%{entity.userState==\"Y\"}">
											<button type="button" onclick="unableUser(this);">禁用</button>
										</s:if>
										<s:else>
											<button type="button" onclick="enableUser(this);">启用</button>
										</s:else>
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
		<s:url id="resetPasswordUrl" namespace="/ggkz" action="ggkz-user-info" method="resetPassword">
			<s:param name="entity.userId" value="%{entity.userId}"></s:param>
		</s:url>
		<s:url id="unableUserUrl" namespace="/ggkz" action="ggkz-user-info" method="unableUser">
			<s:param name="entity.userId" value="%{entity.userId}"></s:param>
		</s:url>
		<s:url id="enableUserUrl" namespace="/ggkz" action="ggkz-user-info" method="enableUser">
			<s:param name="entity.userId" value="%{entity.userId}"></s:param>
		</s:url>
		<script type="text/javascript">
			var callbackId = '${callbackId}';
			function resetPassword(obj){
				// 重置密码,就是清空用户自己创建的密码
				var url = '${resetPasswordUrl}';
				$.ajax({
		            type: "POST",
		            url: url,
		            dataType:"json",
		            success: function(data){
		                var result = data.jsondata;
		                if(result == '1'){
		                	alertMsg.correct("重置成功"); 
		                	closeCurrentPageContainer(obj);
			            }else{
			            	alertMsg.warn("重置失败");
				        }
		            },
		            error: function(data){
		            	alertMsg.error("网络繁忙，请稍后再试");
		            }
		        });
		        return false;
				// 关闭页面
			}
			// 禁用
			function unableUser(obj){
				var url = '${unableUserUrl}';
				$.ajax({
		            type: "POST",
		            url: url,
		            dataType:"json",
		            success: function(data){
		                var result = data.jsondata;
		                if(result == '1'){
		                	alertMsg.correct("禁用成功"); 
		                	closeCurrentPageContainer(obj);
		                	refreshPageContainer(callbackId);
			            }else{
			            	alertMsg.warn("禁用失败");
				        }
		            },
		            error: function(data){
		            	alertMsg.error("网络繁忙，请稍后再试");
		            }
		        });
		        return false;
			}
			// 启用
			function enableUser(obj){
				var url = '${enableUserUrl}';
				$.ajax({
		            type: "POST",
		            url: url,
		            dataType:"json",
		            success: function(data){
		                var result = data.jsondata;
		                if(result == '1'){
		                	alertMsg.correct("启用成功"); 
		                	closeCurrentPageContainer(obj);
		                	refreshPageContainer(callbackId);
			            }else{
			            	alertMsg.warn("启用失败");
				        }
		            },
		            error: function(data){
		            	alertMsg.error("网络繁忙，请稍后再试");
		            }
		        });
		        return false;
			}
		</script>
	</body>
</html>
