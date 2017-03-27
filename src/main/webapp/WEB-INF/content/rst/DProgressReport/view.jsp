<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>dProgressReport查看</title>
	</head>
	<body>
			<div class="page">
				<s:property value="message" escapeHtml="false"/>
				<div class="pageContent">
						<s:form method="post" action="dProgressReport!toEdit" cssClass="pageForm" onsubmit="return navTabSearch(this);">
							<div>
								
								<s:hidden name="entity.reportId" value="%{entity.reportId}" />
							
									
									<table class="list" width="100%">
											<tr>
													<td><label>报告Id</label></td>	
													<td><span>
																<s:property value="entity.reportId" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>工程中心编码</label></td>	
													<td><span>
																<s:property value="entity.wsCd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>进度报告日</label></td>	
													<td><span>
																<s:date name="entity.reportDt" format="yyyy-MM-dd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>用户编码</label></td>	
													<td><span>
																<s:property value="entity.userCd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>创建时间</label></td>	
													<td><span>
																<s:date name="entity.createTm" format="yyyy-MM-dd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>修改时间</label></td>	
													<td><span>
																<s:date name="entity.updateTm" format="yyyy-MM-dd" /></br>
														
																
														
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
