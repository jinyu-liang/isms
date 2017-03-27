<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>工种列表</title>
    </head>
    <body>
		<s:property value="message" escapeHtml="false"/>
		<s:form method="post" action="ex-work-type!list" cssStyle="height:100%;width:100%;" onsubmit="return pageSubmit(this);" id="%{_}">
        <s:hidden name="entity.work_ws_cd" value="%{entity.work_ws_cd}"></s:hidden>
				<div class="page" style="height:100%;width:100%;">
						<div class="layout">
								<div region="north" height="95">
										
										
										<div class="pageContent">
												<div class="panelBar">
														<div>
														
												    </div>
												
													<ul class="toolBar">
														<li>
															<s:a namespace="/rst" action="ex-work-type"
                                                                method="toCreate" encode="false" width="700" height="450" cssClass="add" target="dialog" rel="%{_}" mask="true">
																<s:param name="callbackId" value="%{_}"></s:param>
																<s:param name="entity.work_ws_cd" value="%{entity.work_ws_cd}"></s:param>
																<span>添加工种</span>
															</s:a>
														</li>
														<li>
															<s:a namespace="/rst" action="ex-work-type"
                                                                cssClass="edit" method="toEdit" encode="false" width="960" height="550"target="dialog" rel="%{_}" mask="true">
																<s:param name="callbackId" value="%{_}"></s:param>
                                                                <s:param name="entity.workCd" value="'{entity_workCd}'"></s:param>
                                                                <s:param name="entity.work_ws_cd" value="%{entity.work_ws_cd}"></s:param>
																<span>修改工种</span>
															</s:a>
														</li>
														<li>
															<s:a namespace="/rst" action="ex-work-type" title="是否删除?"
                                                                cssClass="delete" method="delete" callback="refreshCurrentNavTabSearch" target="ajaxTodo" encode="false" cssClass="delete">
																<s:param name="callbackId" value="%{_}"></s:param>
                                                                <s:param name="entity.workCd" value="'{entity_workCd}'"></s:param>
															<span>删除工种</span>
															
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
												<th width="65px">工种编号</th>
												<th>工种名称</th>
												<th>项目名称</th>
												<th>计价方式</th>
												<th>薪资（元）</th>
												<th>时间</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="page.data">
												<tr target="entity_workCd" rel='<s:property value="workCd" />'>
													<td><s:property value="workCd" /></td>
													<td><s:property value="workNm" /></td>
													<td><s:property value="work_ws_nm" /></td>
													<td>
													<s:if test="work_count_type==0">按天</s:if>
													<s:if test="work_count_type==1">按量</s:if>
													<s:if test="work_count_type==2">其它</s:if>
													</td>
													<td><s:property value="work_pay" /></td>
													<td><s:date name="operTime" format="yyyy-MM-dd HH:mm:ss" /></td>
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
                                                <s:hidden name="_"></s:hidden>
												<div class="pagination" count='<s:property value="page.count"/>' pageSize='<s:property value="page.pageSize"/>' pageNumShown='<s:property value="page.pageNumShown"/>' pageNumber='<s:property value="page.pageNumber"/>'></div>
										</div>
							</div>
                            </div>
                            </div>
                        
					</s:form>
		</body>
</html>
