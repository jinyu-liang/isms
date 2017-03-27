<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>dInvoice查看</title>
	</head>
	<body>
			<div class="page">
				<s:property value="message" escapeHtml="false"/>
				<div class="pageContent">
						<s:form method="post" action="dInvoice!toEdit" cssClass="pageForm" onsubmit="return navTabSearch(this);">
							<div>
								
								<s:hidden name="entity.invoiceId" value="%{entity.invoiceId}" />
							
									
									<table class="list" width="100%">
											<tr>
													<td><label>发货单ID</label></td>	
													<td><span>
																<s:property value="entity.invoiceId" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>发货计划ID</label></td>	
													<td><span>
																<s:property value="entity.planId" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>销售订单号</label></td>	
													<td><span>
																<s:property value="entity.sellOrderCode" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>计划表标题</label></td>	
													<td><span>
																<s:property value="entity.title" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>运输公司名称</label></td>	
													<td><span>
																<s:property value="entity.tcompanyNm" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>运输公司电话</label></td>	
													<td><span>
																<s:property value="entity.tcompanyTel" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>司机</label></td>	
													<td><span>
																<s:property value="entity.driver" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>车号</label></td>	
													<td><span>
																<s:property value="entity.truckNum" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>司机电话</label></td>	
													<td><span>
																<s:property value="entity.driverTel" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>发货仓库编码</label></td>	
													<td><span>
																<s:property value="entity.fromWsCd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>收货工作中心编码</label></td>	
													<td><span>
																<s:property value="entity.toWsCd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>审核人名</label></td>	
													<td><span>
																<s:property value="entity.verifiedBy" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>起单用户ID</label></td>	
													<td><span>
																<s:property value="entity.invoiceUserId" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>发料请求时间</label></td>	
													<td><span>
																<s:property value="entity.approvalReqTm" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>发货批准时间</label></td>	
													<td><span>
																<s:property value="entity.approvalTm" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>发货批准用户</label></td>	
													<td><span>
																<s:property value="entity.approvalUserCd" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>发货时间</label></td>	
													<td><span>
																<s:property value="entity.deliveryTm" /></br>
														
																
														
														</span>
												 </td>
											</tr>	
											<tr>
													<td><label>到货时间</label></td>	
													<td><span>
																<s:property value="entity.arrivalTm" /></br>
														
																
														
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
													<td><label>最后更新时间</label></td>	
													<td><span>
																<s:property value="entity.updageTm" /></br>
														
																
														
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
