<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>操作日志查看</title>
	</head>
	<body>
			<div class="page">
				<s:property value="message" escapeHtml="false"/>
				<div class="pageContent">
						<s:form method="post" action="sys-oper-log!toEdit" cssClass="pageForm" onsubmit="return pageSubmit(this);">
							<div>
								
								<s:hidden name="entity.logId" value="%{entity.logId}" />
							
									
									<table class="list" width="100%">
											<tr>
													<td><label>日志id</label></td>	
													<td><span>
																<s:property value="entity.logId" /></br>
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>业务Id</label></td>	
													<td><span>
																<s:property value="entity.busiId" /></br>
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>模块名称</label></td>	
													<td><span>
																<s:property value="entity.modelName" /></br>
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>操作功能名称</label></td>	
													<td><span>
																<s:property value="entity.operFuncName" /></br>
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>操作结果</label></td>	
													<td><span>
																<s:property value="entity.operResult" /></br>
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>操作时间</label></td>	
													<td><span>
																<s:date name="entity.operateTime" format="yyyy-MM-dd HH:mm:ss" /></br>
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
													<td><label>操作人Ip</label></td>	
													<td><span>
																<s:property value="entity.operIp" /></br>
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>备注</label></td>	
													<td><span>
																<s:textarea cols="50" name="entity.note" readonly="true"/></br>
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
