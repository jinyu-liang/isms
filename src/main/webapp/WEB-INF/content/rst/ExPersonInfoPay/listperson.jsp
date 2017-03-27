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
	<s:form method="post" action="ex-person-info-pay!toEdit"
		cssStyle="height:100%;width:100%;"
		onsubmit="return navTabSearch(this);" id="%{_}">
		<div class="page" style="height: 100%; width: 100%;">
			<div class="layout">
				<div region="north" height="95">
					<div class="pageHeader">
					
					</div>								
					</div>


						<div region="center">

							<table class="table">
								<thead>
									<tr>
										<th>姓名</th>
										<th>身份证号</th>
										<th>工种</th>
										<th>月份</th>
										<th>上午开始时间</th>
										<th>上午结束时间</th>
										<th>下午开始时间</th>
										<th>下午结束时间</th>
										<th>加班开始时间</th>
										<th>加班结束时间</th>
										<th>工作时长</th>
										<th>薪资</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="page.data">
										<tr key="entity.pid" value='<s:property value="pid" />'>
											<td><s:property value="name" />
											</td>
											<td><s:property value="identy_card_code" />
											</td>
											<td>
											<s:property value="work_type_name" />
											</td>
											<td><s:date name="addtime" format="yyyy-MM" />
											</td>
											<td><s:date name="start_time_am" format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td><s:date name="end_time_am" format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td><s:date name="start_time_pm" format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td><s:date name="end_time_pm" format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td><s:date name="start_time_other" format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td><s:date name="end_time_other" format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td><s:property value="work_timer" />
											</td>
											<td><s:property value="work_pay" />
											</td>
											<td>
											<s:if test="work_status==1 or work_status==2">
													<s:a namespace="/rst" action="ex-person-info-pay"
                                                        method="toUpdate" encode="false" width="600" height="500" cssClass="add" target="dialog" rel="%{_}" mask="true">
														<s:param name="callbackId" value="%{_}"></s:param>
														<s:param name="entity.pid" value="%{pid}"></s:param>
														<span>调整工作量</span>
													</s:a>
												</s:if>
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
