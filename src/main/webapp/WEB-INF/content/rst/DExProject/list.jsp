<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>dexproject列表</title>
    </head>
    <body>
		<s:property value="message" escapeHtml="false"/>
		<s:form method="post" action="d-ex-project!list" cssStyle="height:100%;width:100%;" onsubmit="return navTabSearch(this);" id="%{_}">
				<div class="page" style="height:100%;width:100%;">
						<div class="layout">
								<div region="north" height="95">
										<div class="pageHeader">
												<div class="searchBar">
														<ul class="searchContent">
																				<li>
																						
																						
																						<label for="entity_projectId">工程ID</label>
																							<s:textfield id="queryEntity_projectId" name="queryEntity.projectId" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_wsCd">工程中心编码</label>
																							<s:textfield id="queryEntity_wsCd" name="queryEntity.wsCd" maxLength="4"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_projectNm">工程名称</label>
																							<s:textfield id="queryEntity_projectNm" name="queryEntity.projectNm" maxLength="100"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_weight">重量</label>
																							<s:textfield id="queryEntity_weight" name="queryEntity.weight" maxLength="16"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_contractStartDate">合同要求进驻日期</label>
																							<s:textfield id="queryEntity_contractStartDate" name="queryEntity.contractStartDate" cssClass="date" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_startDate">起始日期</label>
																							<s:textfield id="queryEntity_startDate" name="queryEntity.startDate" cssClass="date" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_contractOtherReq">合同其他要求</label>
																							<s:textfield id="queryEntity_contractOtherReq" name="queryEntity.contractOtherReq" maxLength="100"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_lastReportDt">最新报告日</label>
																							<s:textfield id="queryEntity_lastReportDt" name="queryEntity.lastReportDt" cssClass="date" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_lastReportId">最新报告ID</label>
																							<s:textfield id="queryEntity_lastReportId" name="queryEntity.lastReportId" />
																							
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
												<th>工程ID</th>
												<th>工程中心编码</th>
												<th>工程名称</th>
												<th>重量</th>
												<th>合同要求进驻日期</th>
												<th>起始日期</th>
												<th>合同其他要求</th>
												<th>最新报告日</th>
												<th>最新报告ID</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="page.data">
												<tr key="entity.projectId" value='<s:property value="projectId" />'>
													<td><s:property value="projectId" /></td>
													<td><s:property value="wsCd" /></td>
													<td><s:property value="projectNm" /></td>
													<td><s:property value="weight" /></td>
													<td><s:date name="contractStartDate" format="yyyy-MM-dd" /></td>
													<td><s:date name="startDate" format="yyyy-MM-dd" /></td>
													<td><s:property value="contractOtherReq" /></td>
													<td><s:date name="lastReportDt" format="yyyy-MM-dd" /></td>
													<td><s:property value="lastReportId" /></td>
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
