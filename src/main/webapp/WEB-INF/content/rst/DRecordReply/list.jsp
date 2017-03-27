<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>dRecordReply列表</title>
    </head>
    <body>
		<s:property value="message" escapeHtml="false"/>
		<s:form method="post" action="dRecordReply!list" cssStyle="height:100%;width:100%;" onsubmit="return navTabSearch(this);" id="%{_}">
				<div class="page" style="height:100%;width:100%;">
						<div class="layout">
								<div region="north" height="95">
										<div class="pageHeader">
												<div class="searchBar">
														<ul class="searchContent">
																				<li>
																						
																						
																						<label for="entity_replyId">回复Id</label>
																							<s:textfield id="queryEntity_replyId" name="queryEntity.replyId" maxLength="32"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_recordId">流水号</label>
																							<s:textfield id="queryEntity_recordId" name="queryEntity.recordId" maxLength="32"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_replyType">回复类别</label>
																							<s:textfield id="queryEntity_replyType" name="queryEntity.replyType" maxLength="2"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_rootReplyId">根回复项目ID</label>
																							<s:textfield id="queryEntity_rootReplyId" name="queryEntity.rootReplyId" maxLength="32"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_parentReplyId">父回复项目Id</label>
																							<s:textfield id="queryEntity_parentReplyId" name="queryEntity.parentReplyId" maxLength="32"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_title">计划表标题</label>
																							<s:textfield id="queryEntity_title" name="queryEntity.title" maxLength="200"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_content">内容</label>
																							<s:textfield id="queryEntity_content" name="queryEntity.content" maxLength="400"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_replyUserCd">回复者用户Cd</label>
																							<s:textfield id="queryEntity_replyUserCd" name="queryEntity.replyUserCd" maxLength="32"/>
																							
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
																						
																						
																						<label for="entity_extraFlg">额外区分</label>
																							<s:textfield id="queryEntity_extraFlg" name="queryEntity.extraFlg" maxLength="1"/>
																							
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
												<th>回复Id</th>
												<th>流水号</th>
												<th>回复类别</th>
												<th>根回复项目ID</th>
												<th>父回复项目Id</th>
												<th>计划表标题</th>
												<th>内容</th>
												<th>回复者用户Cd</th>
												<th>创建时间</th>
												<th>修改时间</th>
												<th>额外区分</th>
												<th>删除区分</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="page.data">
												<tr key="entity.replyId" value='<s:property value="replyId" />'>
													<td><s:property value="replyId" /></td>
													<td><s:property value="recordId" /></td>
													<td><s:property value="replyType" /></td>
													<td><s:property value="rootReplyId" /></td>
													<td><s:property value="parentReplyId" /></td>
													<td><s:property value="title" /></td>
													<td><s:property value="content" /></td>
													<td><s:property value="replyUserCd" /></td>
													<td><s:date name="createTm" format="yyyy-MM-dd" /></td>
													<td><s:date name="updateTm" format="yyyy-MM-dd" /></td>
													<td><s:property value="extraFlg" /></td>
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
