<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>外线人员管理表列表</title>
<style type="text/css">
.autocut {
	width: 200px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	-o-text-overflow: ellipsis;
	-icab-text-overflow: ellipsis;
	-khtml-text-overflow: ellipsis;
	-moz-text-overflow: ellipsis;
	-webkit-text-overflow: ellipsis;
}
</style>
</head>
<body>
	<s:property value="message" escapeHtml="false" />
	<s:form method="post" action="ex-person-manager!list"
		cssStyle="height:100%;width:100%;"
		onsubmit="return navTabSearch(this);" id="%{_}">
		<div class="page" style="height: 100%; width: 100%;">
			<div class="layout">
				<div region="north" height="95">
					<div class="pageHeader">
						<div class="searchBar">
							<ul class="searchContent">
								<li><label for="entity_workCenterId">工地中心</label> <s:textfield
										id="queryEntity_workCenterId" name="queryEntity.workCenterId"
										maxLength="32" />
								</li>
								<li><label for="entity_title">标题</label> <s:textfield
										id="queryEntity_title" name="queryEntity.title"
										maxLength="200" />
								</li>
								<li><label for="entity_reportUserName">报告人</label> <s:textfield
										id="queryEntity_reportUserName"
										name="queryEntity.reportUserName" maxLength="32" />
								</li>
								<li><label for="entity_processUserCd">处理人</label> <s:textfield
										id="queryEntity_processUserCd"
										name="queryEntity.processUserCd" maxLength="32" />
								</li>
								<li><label for="entity_statusCd">状态</label> <s:select
										list="#{'':'请选择状态','0':'待处理','Yes':'已处理','No':'有问题' }"
										name="queryEntity.statusCd" cssClass="required"></s:select></li>
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

				</div>
				<div region="center" layoutH="88">
					<div class="pageContent">
						<div class="panelBar">
							<div></div>

							<ul class="toolBar">
								<li><s:a namespace="/rst" action="ex-person-info"
										method="list" encode="false" width="960" height="550"
										cssClass="view" target="navTab" mask="true">
										<s:param name="callbackId" value="%{dwz_page_rel}"></s:param>
										<span>查看所有外线人员详细</span>
									</s:a> <%-- 					<s:a namespace="/rst" action="ex-person-info"
										method="list" encode="false" width="960" height="480"
										cssClass="view" target="navTab" mask="true">
										<s:param name="callbackId" value="%{_}"></s:param>
										<s:param name="queryEntity.wsCd" value="'{list_data_report_id}'"></s:param>
															
															 --%></li>
								<li class="line">line</li>
							</ul>
						</div>
						<table class="table" style="overflow: scroll;" >
							<thead>
								<tr>
									<th>操作</th>
									<th>状态</th>
									<th>图片</th>
									<th>工地中心</th>
									<th>标题</th>
									<!-- <th>班组编号</th> -->
									<th>报告人</th>
									<th>联系电话</th>
									<th>报告时间</th>
									<th>处理人</th>
									<th>处理时间</th>
									<th>处理意见</th>
									<s:if test="sessionUser.userId=='USER0047'"><th>操作</th></s:if>
		
								</tr>
							</thead>
							<tbody>
								<s:iterator value="page.data">
									<tr target="list_data_report_id"
										rel='<s:property value="reportId" />'
										onclick="handleWisely4('<s:property value="reportId" />','<s:property value="%{_}" />',event.type)"
										ondblclick="handleWisely4('<s:property value="reportId" />','<s:property value="%{_}" />',event.type)">
										<td><sec:authorize ifAllGranted="外线人员管理-处理上传外线人员信息">
												<s:if test="statusCd==0 and sessionUser.userId!=reportUserId">
													<s:a namespace="/rst" action="ex-person-manager"
														method="toDeal" encode="false" width="1000" height="668"
														cssClass="edit" target="dialog" mask="true" title="外线人员信息处理">
														<s:param name="callbackId" value="%{_}"></s:param>
														<s:param name="queryEntity.reportId" value="reportId" />
														<span>处理</span>
													</s:a>
												</s:if>
											</sec:authorize>
											<s:a id="%{_}showMsg" namespace="/rst"
												action="ex-person-manager" method="view" encode="false"
												cssClass="view" target="dialog" mask="true" width="1000"
												height="668" title="外线人员管理详情">
												<s:param name="queryEntity.reportId" value="reportId" />
												<s:param name="callbackId" value="%{_}"></s:param>
												<span>详情</span>
											</s:a>
										</td>
										<td><s:if test="statusCd==\"Yes\"">
												<font style="color: green;">已处理</font>
											</s:if> <s:if test="statusCd==0||statusCd==''||statusCd==null">
												<font style="color: #6495ED;">待处理</font>
											</s:if> <s:if test="statusCd==\"No\"">
												<font style="color: red;">有问题</font>
											</s:if>
										</td>
										<td><s:if test="fileName.size()>0">
												<s:a id="%{_}showMsg" namespace="/rst"
													action="ex-person-manager" method="imgView" encode="false"
													cssClass="button" target="dialog" mask="true" width="1000"
													height="800" title="外线人员管理图片">
													<s:param name="queryEntity.reportId" value="reportId" />
													<s:param name="callbackId" value="%{_}"></s:param>
													<span>查看图片</span>
												</s:a>
											</s:if>
										</td>
										<td><s:property value="workCenterId" /></td>
										<td><s:property value="title" /></td>
										<%-- 	<td>
															<pt:dictshow name="teamCd" listValue="dictName" listKey="dictCode" list="workShopList"></pt:dictshow>
															</td> --%>
										<td><s:property value="reportUserName" /></td>
										<td><s:property value="reportUserTel" /></td>
										<td><s:date name="reportTm" format="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td><pt:usernameShow userId="processUserCd"></pt:usernameShow>
										</td>
										<td><s:date name="processTm" format="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td title="<s:property value="memo" />"><div
												class="autocut">
												<s:property value="dealComment" />
											</div></td>
											<s:if test="sessionUser.userId=='USER0047'"><td>
												
													<s:a style="cursor:pointer" namespace="/rst" action="ex-person-manager" method="delete" title="是否删除本条采购信息?"
		                                        callback="refreshCurrentNavTabSearch" target="ajaxTodo" encode="false" cssClass="delete">
		                                        <s:param name="entity.reportId" value="reportId" />
		                                        <span>删除</span> 
		                                        </s:a>
											
											</td></s:if>	
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
				<div region="south" height="26">
					<div class="panelBar">
						<div class="pages">
							<span>显示</span>
							<s:select list="page.pageSizeList" name="queryEntity.pageSize"
								cssClass="pageSize" form="${_}" />
							<span>条，共[<s:property value="page.count" />]条</span>
						</div>
						<s:hidden name="queryEntity.pageNumber" id="pageNumber" />
						<s:hidden name="_" />
						<div class="pagination"
							totalCount='<s:property value="page.count"/>'
							numPerPage='<s:property value="page.pageSize"/>'
							pageNumShown='<s:property value="page.pageNumShown"/>'
							currentPage='<s:property value="page.pageNumber"/>'></div>
					</div>
				</div>
			</div>
	</s:form>
	<script>
		var dcTime = 250; // doubleclick time
		var dcDelay = 100; // no clicks after doubleclick
		var dcAt = 0; // time of doubleclick
		var savEvent = null; // save Event for handling doClick().
		var savEvtTime = 0; // save time of click event.
		var savTO = null; // handle of click setTimeOut
		/*点击查看出门单信息*/
		/* 查看事件  */
		function handleWisely4(planId, callbackId, which) {
			switch (which) {
			case "click":
				savEvent = which;
				plancd = planId;//不转换不行？
				d = new Date();
				savEvtTime = d.getTime();
				savTO = setTimeout("doClick(plancd,savEvent)", dcTime);
				break;
			case "dblclick":
				doDoubleClick1(planId, callbackId, which);
				break;
			default:
			}
		}
		/* 单击  */
		function doClick(planId, which) {
			/* if (savEvtTime - dcAt <= 0) {
				return false;
			}
			var url = "rst/ex-person-manager!list.action?queryEntity.reportId="
					+ planId;
			$("#plan_child_form").attr("action", url).submit(); */
		}
		/* 双击 */
		function doDoubleClick1(planId, callbackId, which) {
			var d = new Date();
			dcAt = d.getTime();
			if (savTO != null) {
				savTO = null;
			}
			var url = "/ISMS/rst/ex-person-manager!view.action?queryEntity.reportId="
					+ planId + "&callbackId=" + callbackId;
			$("#${_}showMsg").attr("href", url).click();
		};
	</script>
</body>
</html>
