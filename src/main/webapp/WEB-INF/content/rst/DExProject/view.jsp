<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>dexproject查看</title>
	</head>
	<body>
			<div class="page">
				<s:property value="message" escapeHtml="false"/>
				<div class="pageContent">
						<s:form method="post" action="dExProject!toEdit" cssClass="pageForm" onsubmit="return navTabSearch(this);">
							<div>
								
								<s:hidden name="entity.projectId" value="%{entity.projectId}" />
							
									
									<table class="list" width="100%">
											<tr>
													<td><label>工程ID</label></td>	
													<td><span>
																<s:property value="entity.projectId" /></br>
														
																
														
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
													<td><label>工程名称</label></td>	
													<td><span>
																<s:property value="entity.projectNm" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>重量</label></td>	
													<td><span>
																<s:property value="entity.weight" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>合同要求进驻日期</label></td>	
													<td><span>
																<s:date name="entity.contractStartDate" format="yyyy-MM-dd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>起始日期</label></td>	
													<td><span>
																<s:date name="entity.startDate" format="yyyy-MM-dd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>合同其他要求</label></td>	
													<td><span>
																<s:property value="entity.contractOtherReq" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>最新报告日</label></td>	
													<td><span>
																<s:date name="entity.lastReportDt" format="yyyy-MM-dd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>最新报告ID</label></td>	
													<td><span>
																<s:property value="entity.lastReportId" /></br>
														
																
														
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
