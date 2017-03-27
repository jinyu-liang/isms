<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>人员工资统计</title>
</head>
<body>
	<s:property value="message" escapeHtml="false" />
	<s:form method="post" action="ex-person-info-pay!list"
		cssStyle="height:100%;width:100%;"
		onsubmit="return navTabSearch(this);" id="%{_}">
		<div class="page" style="height: 100%; width: 100%;">
			<div class="layout">
				<div region="north" height="95">
					<div class="pageHeader">
						<div class="searchBar">
							<ul class="searchContent">
								<li><label for="entity_name">姓名</label> <s:textfield
										id="queryEntity_name" name="queryEntity.name" maxLength="50" />

								</li>
								<li><label for="entity_workTypeName">主要工种</label> 
								<s:hidden name="entity.wsCd" value="%{entity.wsCd}"></s:hidden>
								<s:select id="dePartList" list="userDepartList" name="queryEntity.workType" listKey="dictCode" listValue="dictName" onchange="changeHidenValue(this)" headerKey="" headerValue="请选择..." cssClass="required"></s:select>												
								</li>
								<li><label for="entity_statusCd">参保状态</label>
									<s:select list="#{'':'请选择状态','0':'无保/申请撤保','1':'在保/申请加保','2':'检出' }" name="queryEntity.statusCd"
										cssClass="required"></s:select>
								
								</li>
							</ul>
							<div class="subBar">
								<ul>
									<li>
										<div class="buttonActive">
											<div class="buttonContent">
												<s:submit type="button">查询</s:submit>
											</div>
										</div></li>
								</ul>
							</div>
						</div>
					</div>								
					</div>


						<div region="center">

							<table class="table">
								<thead>
									<tr>
										<th>姓名</th>
										<th>身份证号</th>
										<th>电话</th>
										<th>工地名称</th>
										<th>施工队</th>
										<th>工种</th>
										<th>计薪类型</th>
										<th>月份</th>
										<th>薪资</th>
										<th>状态</th>
										<th>银行卡号</th>
										<th>银行名称</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="page.data">
										<tr key="entity.empId" value='<s:property value="empId" />'>
											<td><s:property value="name" />
											</td>
											<td><s:property value="identy_card_code" />
											</td>
											<td><s:property value="telephone" />
											</td>
											<td><s:property value="work_ws_nm" />
											</td>
											<td><s:property value="team_name" />
											</td>
											<td>
											<s:property value="work_type_name" />
											</td>
											<td>
											<s:if test="work_count_type==0">按天</s:if>
											<s:if test="work_count_type==1">按量</s:if>
											<s:if test="work_count_type==2">其它</s:if>
											</td>
											<td><s:date name="addtime" format="yyyy-MM" />
											</td>
											<td><s:property value="work_pay" />
											</td>
											<td>
											<s:if test="work_status==1">录入</s:if>
											<s:if test="work_status==2">确认完成</s:if>
											<s:if test="work_status==4">发放完成</s:if>
											</td>
											<td><s:property value="bank_card" />
											</td>
											<td><s:property value="bank_card_name" /></td>	
											
											<td>
											<s:if test="work_status==1">
													<s:a namespace="/rst" action="ex-person-info-pay"
                                                        method="toEdit" encode="false" width="700" height="650" cssClass="add" target="navTab" rel="%{_}" mask="true">
														<s:param name="callbackId" value="%{_}"></s:param>
														<s:param name="entity.identy_card_code" value="%{identy_card_code}"></s:param>
														<s:param name="entity.work_ws_cd" value="%{entity.work_ws_cd}"></s:param>
														<s:param name="entity.team_id" value="%{entity.team_id}"></s:param>
														<s:param name="entity.work_status" ><s:date name="addtime" format="yyyy-MM" /></s:param>
														<span>调整工作量</span>
													</s:a>
												</s:if><s:if test="work_status==1">
													<s:a namespace="/rst" action="ex-person-info-pay"
														method="addInsurance" title="是否与员工确认完成，确认完成后，不能修改！！?"
														callback="refreshCurrentNavTabSearch" target="ajaxTodo"
														encode="false" cssClass="delete">
														<s:param name="entity.pid"
															value="pid"></s:param>
														<span>确认</span>
													</s:a>
												</s:if> <s:elseif test="work_status==2">
													<s:a namespace="/rst" action="ex-person-info-pay"
														method="fianshInsurance" title="员工工资是否发放完毕，确认完之后，不能修改！！?"
														callback="refreshCurrentNavTabSearch" target="ajaxTodo"
														encode="false" cssClass="delete">
														<s:param name="entity.pid"
															value="pid"></s:param>
														<span>完成</span>
													</s:a>
												</s:elseif> 
												<s:else>--</s:else></td>
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
				</div></div></div>
	</s:form>
</body>
</html>
