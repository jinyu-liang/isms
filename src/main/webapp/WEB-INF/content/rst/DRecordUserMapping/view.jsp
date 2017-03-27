<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>dRecordUserMapping查看</title>
	</head>
	<body>
			<div class="page">
				<s:property value="message" escapeHtml="false"/>
				<div class="pageContent">
						<s:form method="post" action="dRecordUserMapping!toEdit" cssClass="pageForm" onsubmit="return navTabSearch(this);">
							<div>
								
								<s:hidden name="entity.mappingId" value="%{entity.mappingId}" />
							
									
									<table class="list" width="100%">
											<tr>
													<td><label>映射Id</label></td>	
													<td><span>
																<s:property value="entity.mappingId" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>流水号</label></td>	
													<td><span>
																<s:property value="entity.recordId" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>记录种类</label></td>	
													<td><span>
																<s:property value="entity.recordType" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>对应用户CD</label></td>	
													<td><span>
																<s:property value="entity.mappingUserCd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>权限区分</label></td>	
													<td><span>
																<s:property value="entity.levelFlg" /></br>
														
																
														
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
											<tr>
													<td><label>删除区分</label></td>	
													<td><span>
																<s:property value="entity.deleteCd" /></br>
														
																
														
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
