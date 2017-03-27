<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>工地转移设备明细查看</title>
	</head>
	<body>
			<div class="page">
				<s:property value="message" escapeHtml="false"/>
				<div class="pageContent">
						<s:form method="post" action="mTransItem!toEdit" cssClass="pageForm" onsubmit="return pageSubmit(this);">
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
													<td><label>运输Id</label></td>	
													<td><span>
																<s:property value="entity.transId" /></br>
														
																
														
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
													<td><label>分类代码</label></td>	
													<td><span>
																<s:property value="entity.categoryCd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>材质代码</label></td>	
													<td><span>
																<s:property value="entity.materialCd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>型号</label></td>	
													<td><span>
																<s:property value="entity.modelNo" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>总数</label></td>	
													<td><span>
																<s:property value="entity.totalAmount" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>单位</label></td>	
													<td><span>
																<s:property value="entity.unit" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>使用位置</label></td>	
													<td><span>
																<s:property value="entity.usePlace" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>材质名称</label></td>	
													<td><span>
																<s:property value="entity.materialNm" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>分类名称</label></td>	
													<td><span>
																<s:property value="entity.categoryNm" /></br>
														
																
														
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
