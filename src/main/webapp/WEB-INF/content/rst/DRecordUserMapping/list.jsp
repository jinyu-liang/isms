<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>dRecordUserMapping列表</title>
    </head>
    <body>
		<s:property value="message" escapeHtml="false"/>
		<s:form method="post" action="dRecordUserMapping!list" cssStyle="height:100%;width:100%;" onsubmit="return navTabSearch(this);" id="%{_}">
				<div class="page" style="height:100%;width:100%;">
						<div class="layout">
								<div region="north" height="95">
										<div class="pageHeader">
												<div class="searchBar">
														<ul class="searchContent">
																				<li>
																						
																						
																						<label for="entity_mappingId">映射Id</label>
																							<s:textfield id="queryEntity_mappingId" name="queryEntity.mappingId" maxLength="32"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_recordId">流水号</label>
																							<s:textfield id="queryEntity_recordId" name="queryEntity.recordId" maxLength="32"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_recordType">记录种类</label>
																							<s:textfield id="queryEntity_recordType" name="queryEntity.recordType" maxLength="1"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_mappingUserCd">对应用户CD</label>
																							<s:textfield id="queryEntity_mappingUserCd" name="queryEntity.mappingUserCd" maxLength="32"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_levelFlg">权限区分</label>
																							<s:textfield id="queryEntity_levelFlg" name="queryEntity.levelFlg" maxLength="1"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_createTm">创建时间</label>
																							<s:textfield id="queryEntity_createTm" name="queryEntity.createTm" cssClass="date" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_updateTm">修改时间</label>
																							<s:textfield id="queryEntity_updateTm" name="queryEntity.updateTm" cssClass="date" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_deleteCd">删除区分</label>
																							<s:textfield id="queryEntity_deleteCd" name="queryEntity.deleteCd" maxLength="1"/>
																							
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
												<th>映射Id</th>
												<th>流水号</th>
												<th>记录种类</th>
												<th>对应用户CD</th>
												<th>权限区分</th>
												<th>创建时间</th>
												<th>修改时间</th>
												<th>删除区分</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="page.data">
												<tr key="entity.mappingId" value='<s:property value="mappingId" />'>
													<td><s:property value="mappingId" /></td>
													<td><s:property value="recordId" /></td>
													<td><s:property value="recordType" /></td>
													<td><s:property value="mappingUserCd" /></td>
													<td><s:property value="levelFlg" /></td>
													<td><s:date name="createTm" format="yyyy-MM-dd" /></td>
													<td><s:date name="updateTm" format="yyyy-MM-dd" /></td>
													<td><s:property value="deleteCd" /></td>
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
							</div>
					</s:form>
		</body>
</html>
