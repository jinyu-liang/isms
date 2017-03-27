<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pt" uri="/is-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>操作日志列表</title>
    </head>
    <body>
		<s:property value="message" escapeHtml="false"/>
		<s:form method="post"  action="sys-oper-log!list" cssStyle="height:100%;width:100%;" onsubmit="return navTabSearch(this);" id="%{_}">
				<div class="page" style="height:100%;width:100%;">
						<div class="layout">
								<div region="north" height="95">
										<div class="pageHeader">
												<div class="searchBar">
														<ul class="searchContent">
																				<li>
																						<label for="entity_modelId">模块名称</label>
																							<s:textfield name="queryEntity.modelName" id="queryEntity_modelName" />
																				</li>
																				<li>
																						<label for="entity_busiId">业务Id</label>
																							<s:textfield name="queryEntity.busiId" id="entity_busiId" />
																				</li>
																				<li>
																						<label for="entity_modelName">姓名</label>
																							<s:textfield id="queryEntity_name" name="queryEntity.name" maxLength="40"/>
																				</li>
																				<li>
																						<label for="entity_beginTime">开始时间</label>
																							<s:textfield id="queryEntity_beginTime" name="queryEntity.operateTimeStart" cssClass="date">
																								<s:param name="value">
													                                                <s:date name="queryEntity.operateTimeStart" format="yyyy-MM-dd"/>
													                                            </s:param>
																							</s:textfield>
																				</li>
																				<li>
																						<label for="entity_endTime">结束时间</label>
																							<s:textfield id="queryEntity_endTime" name="queryEntity.operateTimeEnd" cssClass="date" dateAfter="#queryEntity_beginTime">
																								<s:param name="value">
													                                                <s:date name="queryEntity.operateTimeEnd" format="yyyy-MM-dd"/>
													                                            </s:param>
																							</s:textfield>
																				</li>
														 </ul>					
														 <div class="subBar">
																<ul>
																		<li>
																			<div class="buttonActive">
																				<div class="buttonContent">
																					<s:submit type="button">查询</s:submit>
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
														<ul class="toolBar">
															<li>
																<s:a action="sys-oper-log" method="view" encode="false" cssClass="view" target="dialog" mask="true" width="960" height="350" title="日志查看">
																	<s:param name="entity.logId" value="'{entity_logId}'"></s:param>
																	<span>查看</span>
																</s:a>
															</li>
														</ul>	
												    </div>
												</div>
										</div>
								</div>		
										
										
								<div region="center">
									
									<table class="table">
										<thead>
											<tr>
												<th>业务Id</th>
												<th>模块名称</th>
												<th>操作功能名称</th>
												<th>操作事件</th>
												<th>操作结果</th>
												<th>操作时间</th>
												<th>姓名</th>
												<th>操作人Ip</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="page.data">
												<tr target="entity_logId" rel='<s:property value="logId" />'>
													<td><s:property value="busiId" /></td>
													<td>
													<s:property value="modelName" />
													</td>
													<td><s:property value="operFuncName" /></td>
													<td><s:property value="operEvent" /></td>
													<td><s:property value="operResult" /></td>
													<td><s:date name="operateTime" format="yyyy-MM-dd HH:mm:ss" /></td>
													<td><s:property value="name" /></td>
													<td><s:property value="operIp" /></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
								<div region="south" height="26">
										<div class="panelBar">
                                            <div class="pages">
                                                <span>显示</span>
                                                <s:select list="page.pageSizeList" name="queryEntity.pageSize" cssClass="pageSize" form="${_}" />
                                                <span>条，共[<s:property value="page.count" />]条</span>
                                            </div>
                                            <s:hidden name="queryEntity.pageNumber" id="pageNumber" />
                                            <s:hidden name="_"></s:hidden>
                                            <div class="pagination" totalCount='<s:property value="page.count"/>' numPerPage='<s:property value="page.pageSize"/>'
                                                pageNumShown='<s:property value="page.pageNumShown"/>' currentPage='<s:property value="page.pageNumber"/>'></div>
                                        </div>
							</div>
						</div>
					</div>
				</s:form>
		</body>
</html>
