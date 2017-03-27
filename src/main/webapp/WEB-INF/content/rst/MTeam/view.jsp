<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>施工队查看</title>
	</head>
	<body>
			<div class="page">
				<s:property value="message" escapeHtml="false"/>
				<div class="pageContent">
						<s:form method="post" action="mTeam!toEdit" cssClass="pageForm" onsubmit="return pageSubmit(this);">
							<div>
								
								<s:hidden name="entity.teamCd" value="%{entity.teamCd}" />
							
									
									<table class="list" width="100%">
											<tr>
													<td>施工队编号</td>	
													<td><span>
																<s:property value="entity.teamCd" />
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td>施工队名称</td>	
													<td><span>
																<s:property value="entity.teamNm" />
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td>施工队总人数</td>	
													<td><span>
																<s:property value="entity.workerCount" />
														
																
														
														</span>
												 </td>
											</tr>	
                                            <tr>
                                                    <td>工地中心名称</td>  
                                                    <td><span>
                                                                <s:property value="entity.wsNm" />
                                                        
                                                                
                                                        
                                                        </span>
                                                 </td>
                                            </tr>   
											<tr>
													<td>操作用户名称</td>	
													<td><span>
																<s:property value="entity.operUserName" />
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td>操作时间</td>	
													<td><span>
																<s:date name="entity.operTime" format="yyyy-MM-dd HH:mm:ss" />
														
																
														
														</span>
												 </td>
											</tr>	
										</table>
										<div class="formBar">
												<ul>
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
