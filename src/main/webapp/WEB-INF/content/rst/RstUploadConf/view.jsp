<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>上传大小控制表查看</title>
	</head>
	<body>
			<div class="page">
				<s:property value="message" escapeHtml="false"/>
				<div class="pageContent">
						<s:form method="post" action="rstUploadConf!toEdit" cssClass="pageForm" onsubmit="return pageSubmit(this);">
							<div>
								
								<s:hidden name="entity.fileType" value="%{entity.fileType}" />
							
									
									<table class="list" width="100%">
											<tr>
													<td><label>文件类型</label></td>	
													<td><span>
																<s:property value="entity.fileType" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>文件大小设置</label></td>	
													<td><span>
																<s:property value="entity.sizeConf" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>状态</label></td>	
													<td><span>
																<s:property value="entity.statusCd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>操作用户Id</label></td>	
													<td><span>
																<s:property value="entity.operUserId" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>操作用户名称</label></td>	
													<td><span>
																<s:property value="entity.operUserName" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>操作时间</label></td>	
													<td><span>
																<s:date name="entity.operTime" format="yyyy-MM-dd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
										</table>
										<div class="formBar">
												<ul>
													<li>
														<div class="buttonActive">
															<div class="buttonContent">
																<s:submit type="button" value="修改"/>
															</div>
														</div>
													</li>
													<li>
														<div class="button">
															<div class="buttonContent">
																<button type="button" onclick="closeCurrentPageContainer(this);">取消</button>
															</div>
														</div>
													</li>
												</ul>
										</div>
											
							  	</div>
							</s:form>
					</div>		
			</div>
	</body>
</html>
