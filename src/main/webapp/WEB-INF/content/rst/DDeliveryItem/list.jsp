<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>dDeliveryItem列表</title>
    </head>
    <body>
		<s:property value="message" escapeHtml="false"/>
		<s:form method="post" action="d-delivery-item!list" cssStyle="height:100%;width:100%;" onsubmit="return navTabSearch(this);" id="%{_}">
				<div class="page" style="height:100%;width:100%;">
						<div class="layout">
								<div region="north" height="95">
										<div class="pageHeader">
												<div class="searchBar">
														<%-- <ul class="searchContent">
																				<li>
																						
																						
																						<label for="entity_itemId">发货计划货物Id</label>
																							<s:textfield id="queryEntity_itemId" name="queryEntity.itemId" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_planId">发货计划ID</label>
																							<s:textfield id="queryEntity_planId" name="queryEntity.planId" maxLength="32"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_itemNo">序号</label>
																							<s:textfield id="queryEntity_itemNo" name="queryEntity.itemNo" maxLength="8"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_classCd">货物类别代码-备用</label>
																							<s:textfield id="queryEntity_classCd" name="queryEntity.classCd" maxLength="4"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_classNm">货物类别名称</label>
																							<s:textfield id="queryEntity_classNm" name="queryEntity.classNm" maxLength="50"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_categoryCd">分类代码</label>
																							<s:textfield id="queryEntity_categoryCd" name="queryEntity.categoryCd" maxLength="8"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_categoryNm">分类名称</label>
																							<s:textfield id="queryEntity_categoryNm" name="queryEntity.categoryNm" maxLength="80"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_materialCd">材质代码</label>
																							<s:textfield id="queryEntity_materialCd" name="queryEntity.materialCd" maxLength="8"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_materialNm">材质名称</label>
																							<s:textfield id="queryEntity_materialNm" name="queryEntity.materialNm" maxLength="64"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_modelNo">型号</label>
																							<s:textfield id="queryEntity_modelNo" name="queryEntity.modelNo" maxLength="64"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_identificationCd">标识编码</label>
																							<s:textfield id="queryEntity_identificationCd" name="queryEntity.identificationCd" maxLength="20"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_identificationNm">标识名称</label>
																							<s:textfield id="queryEntity_identificationNm" name="queryEntity.identificationNm" maxLength="50"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_sender">发货人</label>
																							<s:textfield id="queryEntity_sender" name="queryEntity.sender" maxLength="20"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_receiver">接收人</label>
																							<s:textfield id="queryEntity_receiver" name="queryEntity.receiver" maxLength="11"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_sendAmount">发货数</label>
																							<s:textfield id="queryEntity_sendAmount" name="queryEntity.sendAmount" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_receivedAmount">实收数</label>
																							<s:textfield id="queryEntity_receivedAmount" name="queryEntity.receivedAmount" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_unit">单位</label>
																							<s:textfield id="queryEntity_unit" name="queryEntity.unit" maxLength="10"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_memo">备注</label>
																							<s:textfield id="queryEntity_memo" name="queryEntity.memo" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_statusCd">状态</label>
																							<s:textfield id="queryEntity_statusCd" name="queryEntity.statusCd" maxLength="1"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_usePlace">使用位置</label>
																							<s:textfield id="queryEntity_usePlace" name="queryEntity.usePlace" maxLength="100"/>
																							
																				</li>
														 </ul> --%>					
														 <div class="subBar">
																<ul>
																		<li>
																			<div class="buttonActive">
																				<div class="buttonContent">
																					<s:submit type="button">检索</s:submit>
																				</div>
																			</div>
																		</li>
																</ul>
														</div>
												</div>
										</div>
										
										<div class="pageContent">
												<div class="panelBar">
														<div>
														
												    </div>
												
													<ul class="toolBar">
														<li>
															<s:a encode="false" width="960" height="550" cssClass="add" target="dialog" mask="true">
																<s:param name="callbackId" value="%{_}"></s:param>
																<span>添加</span>
															</s:a>
														</li>
														<li>
															<s:a encode="false" width="960" height="550" cssClass="add" target="dialog" mask="true">
																<s:param name="callbackId" value="%{_}"></s:param>
																<span>修改</span>
															</s:a>
														</li>
														<li>
															<s:a cssClass="delete" callback="function refreshCurrentPageContainer(){navTabSearch('${_}');}" target="navTabTodo">
																<s:param name="callbackId" value="%{_}"></s:param>
															<span>删除</span>
															
															</s:a>
														</li>
														<li>
															<s:a encode="false" width="960" height="550" cssClass="add" target="dialog" mask="true">
																	
																	 <span>查看</span>
															</s:a>
														</li>
														<li class="line">line</li>
													</ul>
												</div>
										</div>
								</div>		
										
										
								<div region="center">
									
									<table class="table">
										<thead>
											<tr>
												<th>发货计划货物Id</th>
												<th>发货计划ID</th>
												<th>序号</th>
												<th>货物类别代码-备用</th>
												<th>货物类别名称</th>
												<th>分类代码</th>
												<th>分类名称</th>
												<th>材质代码</th>
												<th>材质名称</th>
												<th>型号</th>
												<th>标识编码</th>
												<th>标识名称</th>
												<th>发货人</th>
												<th>接收人</th>
												<th>发货数</th>
												<th>实收数</th>
												<th>单位</th>
												<th>备注</th>
												<th>状态</th>
												<th>使用位置</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="page.data">
												<tr target="entity.itemId" rel='<s:property value="itemId" />'>
													<td><s:property value="itemId" /></td>
													<td><s:property value="planId" /></td>
													<td><s:property value="itemNo" /></td>
													<td><s:property value="classCd" /></td>
													<td><s:property value="classNm" /></td>
													<td><s:property value="categoryCd" /></td>
													<td><s:property value="categoryNm" /></td>
													<td><s:property value="materialCd" /></td>
													<td><s:property value="materialNm" /></td>
													<td><s:property value="modelNo" /></td>
													<td><s:property value="identificationCd" /></td>
													<td><s:property value="identificationNm" /></td>
													<td><s:property value="sender" /></td>
													<td><s:property value="receiver" /></td>
													<td><s:property value="sendAmount" /></td>
													<td><s:property value="receivedAmount" /></td>
													<td><s:property value="unit" /></td>
													<td><s:property value="memo" /></td>
													<td><s:property value="statusCd" /></td>
													<td><s:property value="usePlace" /></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
								<div region="south" height="26">
										<div class="panelBar">
												<div class="pages">
													<span>显示</span>
													<s:select list="page.pageSizeList" name="queryEntity.pageSize" cssClass="pageSize" form="${_}"/>
													<span>条，共[<s:property value="page.count"/>]条</span>
												</div>
												<s:hidden name="queryEntity.pageNumber" id="pageNumber" />
												<s:hidden name="_"/>
												<div class="pagination" totalCount='<s:property value="page.count"/>' numPerPage='<s:property value="page.pageSize"/>' pageNumShown='<s:property value="page.pageNumShown"/>' currentPage='<s:property value="page.pageNumber"/>'></div>
										</div>
							</div></div></div>
					</s:form>
		</body>
</html>
