<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>dExProgress查看</title>
	</head>
	<body>
			<div class="page">
				<s:property value="message" escapeHtml="false"/>
				<div class="pageContent">
						<s:form method="post" action="dExProgress!toEdit" cssClass="pageForm" onsubmit="return navTabSearch(this);">
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
													<td><label>工程ID</label></td>	
													<td><span>
																<s:property value="entity.projectId" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>进度状况</label></td>	
													<td><span>
																<s:property value="entity.progressStatus" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>现场反馈问题</label></td>	
													<td><span>
																<s:property value="entity.fbWorkshop" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>厂内发货问题</label></td>	
													<td><span>
																<s:property value="entity.fbDelivery" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>质量问题反馈</label></td>	
													<td><span>
																<s:property value="entity.fbQuality" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>安全问题反馈</label></td>	
													<td><span>
																<s:property value="entity.fbSecurity" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>文明施工问题</label></td>	
													<td><span>
																<s:property value="entity.fbManner" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>主材情况</label></td>	
													<td><span>
																<s:property value="entity.fbMmaterial" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>配件情况</label></td>	
													<td><span>
																<s:property value="entity.fbSmaterial" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>设备情况</label></td>	
													<td><span>
																<s:property value="entity.fbEquipment" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>计划定额费用</label></td>	
													<td><span>
																<s:property value="entity.totalCost" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>实际费用</label></td>	
													<td><span>
																<s:property value="entity.currentCost" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>总费用</label></td>	
													<td><span>
																<s:property value="entity.totalExpense" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>易耗费用已发额</label></td>	
													<td><span>
																<s:property value="entity.currentExpense" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>施工队长</label></td>	
													<td><span>
																<s:property value="entity.teamLeader" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>主要焊工</label></td>	
													<td><span>
																<s:property value="entity.welder" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>主要铆工</label></td>	
													<td><span>
																<s:property value="entity.riveter" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>施工总人数</label></td>	
													<td><span>
																<s:property value="entity.workerCount" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>分管副部长</label></td>	
													<td><span>
																<s:property value="entity.viceManager" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>现场带队人员</label></td>	
													<td><span>
																<s:property value="entity.wsLeader" /></br>
														
																
														
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
