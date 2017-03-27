<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>施工队列表</title>
    </head>
    <body>
		<s:property value="message" escapeHtml="false"/>
		<s:form method="post" action="m-team!list" cssStyle="height:100%;width:100%;" onsubmit="return pageSubmit(this);" id="%{_}">
        <s:hidden name="entity.wsCd" value="%{entity.wsCd}"></s:hidden>
				<div class="page" style="height:100%;width:100%;">
						<div class="layout">
								<div region="north" height="95">
										
										
										<div class="pageContent">
												<div class="panelBar">
														<div>
														
												    </div>
												
													<ul class="toolBar">
														<li>
															<s:a namespace="/rst" action="m-team"
                                                                method="toCreate" encode="false" width="700" height="450" cssClass="add" target="dialog" rel="%{_}" mask="true">
																<s:param name="callbackId" value="%{_}"></s:param>
																<s:param name="entity.wsCd" value="%{entity.wsCd}"></s:param>
																<span>添加施工队</span>
															</s:a>
														</li>
														<li>
															<s:a namespace="/rst" action="m-team"
                                                                cssClass="edit" method="toEdit" encode="false" width="960" height="550"target="dialog" rel="%{_}" mask="true">
																<s:param name="callbackId" value="%{_}"></s:param>
                                                                <s:param name="entity.teamCd" value="'{entity_teamCd}'"></s:param>
                                                                <s:param name="entity.wsCd" value="%{entity.wsCd}"></s:param>
																<span>修改施工队</span>
															</s:a>
														</li>
														<li>
															<s:a namespace="/rst" action="m-team" title="是否删除?"
                                                                cssClass="delete" method="delete" callback="refreshCurrentNavTabSearch" target="ajaxTodo" encode="false" cssClass="delete">
																<s:param name="callbackId" value="%{_}"></s:param>
                                                                <s:param name="entity.teamCd" value="'{entity_teamCd}'"></s:param>
															<span>删除施工队</span>
															
															</s:a>
														</li>
														<li>
															<s:a namespace="/rst" action="ex-person-info"
                                                                cssClass="add" method="toCreate" encode="false" width="700" height="500" target="dialog" rel="%{_}" mask="true">
																<s:param name="callbackId" value="%{_}"></s:param>
                                                                <s:param name="entity.teamId" value="'{entity_teamCd}'"></s:param>
                                                                <s:param name="entity.wsCd" value="%{entity.wsCd}"></s:param>
																<span>添加施工人员</span>
															</s:a>
														</li>
														<li>
															<s:a namespace="/rst" action="ex-person-info"
                                                                cssClass="edit" method="list" encode="false" width="960" height="600" target="navTab" rel="%{_}" mask="true">
																<s:param name="callbackId" value="%{_}"></s:param>
                                                                <s:param name="entity.teamId" value="'{entity_teamCd}'"></s:param>
                                                                <s:param name="entity.wsCd" value="%{entity.wsCd}"></s:param>
																<span>施工人员列表</span>
															</s:a>
														</li>
														<li>
															<s:a namespace="/rst" action="m-team"
                                                                cssClass="view" method="upload" encode="false" width="600" height="300" target="dialog" rel="%{_}" mask="true">
																<s:param name="callbackId" value="%{_}"></s:param>
                                                                <s:param name="entity.teamCd" value="'{entity_teamCd}'"></s:param>
                                                                <s:param name="entity.wsCd" value="%{entity.wsCd}"></s:param>
																<span>导入施工人员</span>
															</s:a>
														</li>
														<li>
															<s:a namespace="/rst" action="ex-person-info-pay"
                                                                cssClass="edit" method="list" encode="false" width="960" height="600" target="navTab" rel="345" mask="true">
																<s:param name="callbackId" value="%{_}"></s:param>
                                                                <s:param name="entity.team_id" value="'{entity_teamCd}'"></s:param>
                                                                <s:param name="entity.work_ws_cd" value="%{entity.wsCd}"></s:param>
																<span>工作量统计</span>
															</s:a>
														</li>
													</ul>
												</div>
										</div>
								</div>		
										
										
								<div region="center">
									
									<table class="table">
										<thead>
											<tr>
												<th>施工队名称</th>
												<th>施工队总人数</th>
												<th>所属工地</th>
												<th>操作人</th>
												<th>操作时间</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="page.data">
												<tr target="entity_teamCd" rel='<s:property value="teamCd" />'>
													<td><s:property value="teamNm" /></td>
													<td><s:property value="workerCount" /></td>
													<td><s:property value="wsNm" /></td>
													<td><s:property value="operUserName" /></td>
													<td><s:date name="operTime" format="yyyy-MM-dd HH:mm:ss" /></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
								<div region="south" height="36">
					<div class="panelBar">
						<div class="pages">
							<span>显示</span>
							<s:select list="page.pageSizeList" name="queryEntity.pageSize"
								cssClass="pageSize" form="${_}"/>
							<span>条，共[<s:property value="page.count" />]条</span>
						</div>
						<s:hidden name="queryEntity.pageNumber" id="pageNumber" />
						<s:hidden name="_"></s:hidden>
						<div class="pagination"
							totalCount='<s:property value="page.count"/>'
							numPerPage='<s:property value="page.pageSize"/>'
							pageNumShown='<s:property value="page.pageNumShown"/>'
							currentPage='<s:property value="page.pageNumber"/>'></div>
					</div>
				</div>
                            </div>
                            </div>
                        
					</s:form>
		</body>
</html>
