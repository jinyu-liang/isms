<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>dInvoiceImage查看</title>
	</head>
	<body>
			<div class="page">
				<s:property value="message" escapeHtml="false"/>
				<div class="pageContent">
						<s:form method="post" action="dInvoiceImage!toEdit" cssClass="pageForm" onsubmit="return navTabSearch(this);">
							<div>
								
								<s:hidden name="entity.photoId" value="%{entity.photoId}" />
							
									
									<table class="list" width="100%">
											<tr>
													<td><label>照片Id</label></td>	
													<td><span>
																<s:property value="entity.photoId" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>发货单ID</label></td>	
													<td><span>
																<s:property value="entity.invoiceId" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>文件名</label></td>	
													<td><span>
																<s:property value="entity.fileName" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>经纬位置</label></td>	
													<td><span>
																<s:property value="entity.posLongitude" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>维度位置</label></td>	
													<td><span>
																<s:property value="entity.posLatitude" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>位置高度</label></td>	
													<td><span>
																<s:property value="entity.posHeight" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>拍摄用户编码</label></td>	
													<td><span>
																<s:property value="entity.photoUserCd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>拍照时间</label></td>	
													<td><span>
																<s:property value="entity.photoTm" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>上传时间</label></td>	
													<td><span>
																<s:property value="entity.uploadTm" /></br>
														
																
														
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
											<tr>
													<td><label>验货时间</label></td>	
													<td><span>
																<s:property value="entity.checkTm" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>验货用户编码</label></td>	
													<td><span>
																<s:property value="entity.checkUserCd" /></br>
														
																
														
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
