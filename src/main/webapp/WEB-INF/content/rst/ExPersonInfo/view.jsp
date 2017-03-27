<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>工地人员信息表查看</title>
	</head>
	<body>
			<div class="page">
				<s:property value="message" escapeHtml="false"/>
				<div class="pageContent">
						<s:form method="post" action="exPersonInfo!toEdit" cssClass="pageForm" onsubmit="return pageSubmit(this);">
							<div>
								
								<s:hidden name="entity.empId" value="%{entity.empId}" />
							
									
									<table class="list" width="100%">
											<tr>
													<td><label>人员Id</label></td>	
													<td><span>
																<s:property value="entity.empId" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>报告Id</label></td>	
													<td><span>
																<s:property value="entity.reportId" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>姓名</label></td>	
													<td><span>
																<s:property value="entity.name" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>身份证号</label></td>	
													<td><span>
																<s:property value="entity.identyCardCode" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>联系电话</label></td>	
													<td><span>
																<s:property value="entity.telephone" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>工作类型</label></td>	
													<td><span>
																<s:property value="entity.workType" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>工作类型名称</label></td>	
													<td><span>
																<s:property value="entity.workTypeName" /></br>
														
																
														
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
													<td><label>工程中心编码</label></td>	
													<td><span>
																<s:property value="entity.wsCd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>工地中心名称</label></td>	
													<td><span>
																<s:property value="entity.wsNm" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>更新日期</label></td>	
													<td><span>
																<s:date name="entity.updateDate" format="yyyy-MM-dd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>标志</label></td>	
													<td><span>
																<s:property value="entity.flag" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>施工队名</label></td>	
													<td><span>
																<s:property value="entity.termName" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>施工队编号</label></td>	
													<td><span>
																<s:property value="entity.termId" /></br>
														
																
														
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
