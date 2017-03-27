<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>工地人员信息表列表</title>
</head>
<body>
	<s:property value="message" escapeHtml="false" />
	<s:form method="post" action="ex-person-info!list"
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
										<th>工地名称</th>
										<th>施工队</th>
										<th>主要工种</th>
										<th>联系电话</th>
										<th>最后更新日期</th>
										<th>操作</th>
										<th>修改</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="page.data">
										<tr key="entity.empId" value='<s:property value="empId" />'>
											<td><s:property value="name" />
											</td>
											<td><s:property value="identyCardCode" />
											</td>
												<td><s:property value="wsNm" />
											</td><td><s:property value="teamName" />
											</td>	<td><s:property value="workTypeName" />
											</td>
											<td><s:property value="telephone" />
											</td>
										
											<td><s:date name="updateDate" format="yyyy-MM-dd" />
											</td>
											
											<td><s:if test="statusCd==1 and flag==1">
													<s:a namespace="/rst" action="ex-person-info"
														method="addInsurance" title="是否加保?"
														callback="refreshCurrentNavTabSearch" target="ajaxTodo"
														encode="false" cssClass="delete">
														<s:param name="entity.identyCardCode"
															value="identyCardCode"></s:param>
														<span>处理(加保)</span>
													</s:a>
												</s:if> <s:elseif test="statusCd==0 and flag==0">
													<s:a namespace="/rst" action="ex-person-info"
														method="removeInsurance" title="是否撤保?"
														callback="refreshCurrentNavTabSearch" target="ajaxTodo"
														encode="false" cssClass="delete">
														<s:param name="entity.identyCardCode"
															value="identyCardCode"></s:param>
														<span>处理(撤保)</span>
													</s:a>
												</s:elseif> <s:elseif test="wsCd!=null and statusCd==0 ">
													<s:a namespace="/rst" action="ex-person-info"
														method="cutInsurance" title="是否剪出?"
														callback="refreshCurrentNavTabSearch" target="ajaxTodo"
														encode="false" cssClass="delete">
														<s:param name="entity.identyCardCode"
															value="identyCardCode"></s:param>
														<span>剪出</span>
													</s:a>
												</s:elseif> 
												<s:else>--</s:else></td>
												<td>
													<s:a namespace="/rst" action="ex-person-info"
                                                                method="toEdit" encode="false" width="500" height="450" cssClass="add" target="dialog" rel="%{_}" mask="true">
																<s:param name="callbackId" value="%{_}"></s:param>
																<s:param name="entity.identyCardCode" value="%{identyCardCode}"></s:param>
																<s:param name="entity.wsCd" value="%{entity.wsCd}"></s:param>
																<span>修改</span>
													</s:a>
												</td>
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
