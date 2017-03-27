<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>添加工地人员信息表</title>
	</head>
	<body>
			<s:property value="message" escapeHtml="false"/>
			  
			<div class="page">
					<div class="pageContent">
							<s:form method="post" action="ex-person-info!create" callback="closeAndRefresh(this)" cssClass="pageForm" onsubmit="return validateCallback(this,$.pdialog.dialogAjaxDone);">
							<s:hidden name="entity.teamCd" value="%{entity.teamCd}"></s:hidden>
									<div class="pageFormContent col1" layoutH="56">
											<s:include value="_edit.jsp" />
									</div>				
								  <div class="formBar">
											<ul>
												<li>
													<div class="buttonActive">
														<div class="buttonContent">
															<button type="submit">确定</button>
														</div>
													</div>
												</li>
												<li>
													<div class="buttonActive">
														<div class="buttonContent">
															<button type="button" onclick="closeCurrentPageContainer(this);">取消</button>
														</div>
													</div>
												</li>
											</ul>
									</div>
							</s:form>
					</div>
			</div>		
				<script type="text/javascript">
				function closeAndRefresh(obj){
					var callbackId = '{callbackId}';
					closeCurrentPageContainer(obj);
					if(callbackId != ''){
						refreshPageContainer(callbackId);	
					}
			}
		</script>
	</body>
</html>
