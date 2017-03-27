<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>dExItem查看</title>
	</head>
	<body>
			<div class="page">
				<s:property value="message" escapeHtml="false"/>
				<div class="pageContent">
						<s:form method="post" action="dExItem!toEdit" cssClass="pageForm" onsubmit="return navTabSearch(this);">
							<div>
								
								<s:hidden name="entity.itemId" value="%{entity.itemId}" />
							
									
									<table class="list" width="100%">
											<tr>
													<td><label>发货计划货物Id</label></td>	
													<td><span>
																<s:property value="entity.itemId" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>工程ID</label></td>	
													<td><span>
																<s:property value="entity.projectId" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>设备名称</label></td>	
													<td><span>
																<s:property value="entity.itemNm" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>金额</label></td>	
													<td><span>
																<s:property value="entity.amount" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>备注</label></td>	
													<td><span>
																<s:property value="entity.memo" /></br>
														
																
														
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
																<button type="button" onclick="$.pdialog.closeCurrent();">关闭</button>
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
