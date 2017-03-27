<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>工地设备明细列表</title>
    </head>
    <body>
		<s:property value="message" escapeHtml="false"/>
		<s:form method="post" action="mWorkshopItem!list" cssStyle="height:100%;width:100%;" onsubmit="return pageSubmit(this);">
				<div class="page" style="height:100%;width:100%;">
						<div class="layout">
								<div region="north" height="95">
										<div class="pageHeader">
												<div class="searchBar">
														<ul class="searchContent">
																				<li>
																						
																						
																						<label for="entity_itemId">发货计划货物Id</label>
																							<s:textfield id="queryEntity_itemId" name="queryEntity.itemId" maxLength="32"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_wsCd">工程中心编码</label>
																							<s:textfield id="queryEntity_wsCd" name="queryEntity.wsCd" maxLength="4"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_categoryCd">分类代码</label>
																							<s:textfield id="queryEntity_categoryCd" name="queryEntity.categoryCd" maxLength="8"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_materialCd">材质代码</label>
																							<s:textfield id="queryEntity_materialCd" name="queryEntity.materialCd" maxLength="8"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_modelNo">型号</label>
																							<s:textfield id="queryEntity_modelNo" name="queryEntity.modelNo" maxLength="64"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_totalAmount">总数</label>
																							<s:textfield id="queryEntity_totalAmount" name="queryEntity.totalAmount" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_remainNumber">剩余数量</label>
																							<s:textfield id="queryEntity_remainNumber" name="queryEntity.remainNumber" maxLength="16"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_unit">单位</label>
																							<s:textfield id="queryEntity_unit" name="queryEntity.unit" maxLength="10"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_usePlace">使用位置</label>
																							<s:textfield id="queryEntity_usePlace" name="queryEntity.usePlace" maxLength="100"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_materialNm">材质名称</label>
																							<s:textfield id="queryEntity_materialNm" name="queryEntity.materialNm" maxLength="64"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_categoryNm">分类名称</label>
																							<s:textfield id="queryEntity_categoryNm" name="queryEntity.categoryNm" maxLength="80"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_memo">备注</label>
																							<s:textfield id="queryEntity_memo" name="queryEntity.memo" />
																							
																				</li>
														 </ul>					
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
																<s:param name="callbackId" value="%{dwz_page_rel}"></s:param>
																<span>添加</span>
															</s:a>
														</li>
														<li>
															<s:a encode="false" width="960" height="550" cssClass="add" target="dialog" mask="true">
																<s:param name="callbackId" value="%{dwz_page_rel}"></s:param>
																<span>修改</span>
															</s:a>
														</li>
														<li>
															<s:a cssClass="delete" callback="refreshCurrentPageContainer(this)" target="navTabTodo">
																<s:param name="callbackId" value="%{dwz_page_rel}"></s:param>
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
												<th>工程中心编码</th>
												<th>分类代码</th>
												<th>材质代码</th>
												<th>型号</th>
												<th>总数</th>
												<th>剩余数量</th>
												<th>单位</th>
												<th>使用位置</th>
												<th>材质名称</th>
												<th>分类名称</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="page.data">
												<tr key="entity.itemId" value='<s:property value="itemId" />'>
													<td><s:property value="itemId" /></td>
													<td><s:property value="wsCd" /></td>
													<td><s:property value="categoryCd" /></td>
													<td><s:property value="materialCd" /></td>
													<td><s:property value="modelNo" /></td>
													<td><s:property value="totalAmount" /></td>
													<td><s:property value="remainNumber" /></td>
													<td><s:property value="unit" /></td>
													<td><s:property value="usePlace" /></td>
													<td><s:property value="materialNm" /></td>
													<td><s:property value="categoryNm" /></td>
													<td><s:property value="memo" /></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
								<div region="south" height="26">
										<div class="panelBar">
												<div class="pages">
													<span>显示</span>
													<s:select list="page.pageSizeList" name="queryEntity.pageSize" cssClass="pageSize" />
													<span>条，共[<s:property value="page.count"/>]条</span>
												</div>
												<s:hidden name="queryEntity.pageNumber" id="pageNumber" />
												<div class="pagination" count='<s:property value="page.count"/>' pageSize='<s:property value="page.pageSize"/>' pageNumShown='<s:property value="page.pageNumShown"/>' pageNumber='<s:property value="page.pageNumber"/>'></div>
										</div>
							</div>
					</s:form>
		</body>
</html>
