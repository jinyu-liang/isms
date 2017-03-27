<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>工地管理列表</title>
</head>
<body>
	<s:property value="message" escapeHtml="false" />
	<s:form method="post" action="m-workshop!list"
		cssStyle="height:100%;width:100%;"
		onsubmit="return navTabSearch(this);" id="%{_}">
		<div class="page" style="height: 100%; width: 100%;">
			<div class="layout">
				<div region="north" height="95">
					<div class="pageHeader">
						<div class="searchBar">
							<ul class="searchContent">
								<li><label for="entity_wsNm">工地中心名称</label> <s:textfield
										id="queryEntity_wsNm" name="queryEntity.wsNm" maxLength="64" />

								</li>
								<li><label for="entity_typeCd">类型</label> 
								<s:select list="#{'':'请选择类型','0':'管理部门','1':'内线车间','2':'外线工地','3':'仓库' }" name="queryEntity.typeCd"
									cssClass="required"></s:select>
								</li>
								<li><label for="entity_managerUserId">负责人</label> <s:textfield
										id="queryEntity_managerUserId"
										name="queryEntity.managerUserId" maxLength="32" /></li>
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

					<div class="pageContent">
						<div class="panelBar">
							<ul class="toolBar">
								<sec:authorize ifAllGranted="工地管理-新增工地"><li><s:a namespace="/rst" action="m-workshop"
										cssClass="add" method="toCreate" encode="false" width="1000"
										height="550" target="dialog" mask="true">
										<s:param name="callbackId" value="%{_}"></s:param>
										<span>添加工地</span>
									</s:a></li></sec:authorize>
								<sec:authorize ifAllGranted="工地管理-修改工地"><li><s:a namespace="/rst" action="m-workshop"
										method="toEdit" encode="false" width="960" height="550"
										cssClass="edit" target="dialog" mask="true">
										<s:param name="callbackId" value="%{_}"></s:param>
										<s:param name="entity.wsCd" value="'{list_data_report_id}'"></s:param>
										<span>修改工地信息</span>
									</s:a></li></sec:authorize>
								<sec:authorize ifAllGranted="工地管理-删除工地"><li>
									<s:a namespace="/rst" action="m-workshop" method="delete" title="是否删除?"
                                        callback="refreshCurrentNavTabSearch" target="ajaxTodo" encode="false" cssClass="delete">
                                        <s:param name="queryEntity.wsCd" value="'{list_data_report_id}'" />
                                        <span>删除工地</span>
                                    </s:a>
									</li></sec:authorize>
                                    <li class="line">line</li>
									<li><s:a namespace="/rst" action="m-workshop"
										method="view" encode="false" width="960" height="480"
										cssClass="view" target="dialog" mask="true">
										<s:param name="callbackId" value="%{_}"></s:param>
										<s:param name="queryEntity.wsCd" value="'{list_data_report_id}'"></s:param>
										<span>工地设备明细</span>
									</s:a></li>
									<li><s:a namespace="/rst" action="m-workshop"
										method="perinfoListLiew" encode="false" width="960" height="480"
										cssClass="view" target="dialog" mask="true">
										<s:param name="callbackId" value="%{_}"></s:param>
										<s:param name="queryEntity.wsCd" value="'{list_data_report_id}'"></s:param>
										<span>工地外线人员明细</span>
									</s:a></li>
                                    <li class="line">line</li>
									<sec:authorize ifAllGranted="工地管理-修改工地,工地管理-新增工地"><li><s:a namespace="/rst" action="m-team"
										method="list" encode="false" width="960" height="480"
										cssClass="view" target="navTab" mask="true" >
                                        <s:param name="entity.wsCd" value="'{list_data_report_id}'"></s:param>
										<span>施工队管理</span>
									</s:a></li></sec:authorize>
									<li class="line">line</li>
									<sec:authorize ifAllGranted="工地管理-工种管理"><li><s:a namespace="/rst" action="ex-work-type"
										method="list" encode="false" width="960" height="480"
										cssClass="view" target="navTab" mask="true" rel="11111" >
                                        <s:param name="entity.work_ws_cd" value="'{list_data_report_id}'"></s:param>
										<span>工种管理</span>
									</s:a></li></sec:authorize>
									
								
							</ul>
						</div>
					</div>
				</div>


				<div region="center">

					<table class="table"  style="width:1200px; overflow:scroll;">
						<thead>
							<tr>
								<th width="8%">合同预览</th>
								<th width="8%">处理</th>
								<th >工地中心名称</th>
								<th >所在部门</th>
								<th >类型</th>
								<th >负责人</th>
								<th >联系电话</th>
								<th>开始时间</th>
								<th>结束时间</th>
							</tr>
						</thead>
						<tbody>
                            <div style="display:none">
                                <s:a id="%{_}showcontractMsg" namespace="/rst" action="m-workshop"
                                    method="contractImage" encode="false" cssClass="button" target="dialog"
                                    mask="true" width="1000" height="480" title="工地设备明细信息">
                                    <s:param name="queryEntity.wsCd" value="wsCd" />
                                    <s:param name="callbackId" value="%{_}"></s:param>
                                </s:a>
                            </div>
							<s:iterator value="page.data">
								<tr target="list_data_report_id"
									rel='<s:property value="wsCd" />'
									ondblclick="handleWisely5('<s:property value="wsCd" />','<s:property value="%{_}" />',event.type)">
									<td>
									<sec:authorize ifAllGranted="工地管理-查看合同图片"><s:if test="filenames!=null&&filenames!=''">
									<s:a id="%{_}showMsg" namespace="/rst" action="m-workshop"
											method="contractImage" encode="false" cssClass="view" target="dialog"
											mask="true" width="1025" cssClass="button" height="790" >
											<s:param name="queryEntity.wsCd" value="wsCd" />
											<s:param name="callbackId" value="%{_}"></s:param>
											<span>合同图片</span>
										</s:a></s:if></sec:authorize></td>
									<td><sec:authorize ifAllGranted="工地管理-工地处理"><s:a namespace="/rst" action="m-workshop"
											method="toWorkDeal" encode="false" width="1050" height="550"
											cssClass="button" target="dialog" mask="true" >
											<s:param name="callbackId" value="%{_}"></s:param>
											<s:param name="queryEntity.wsCd" value="wsCd" />
											<s:param name="projectEntity.projectNm" value="wsNm" />
											<s:param name="projectEntity.wsCd" value="wsCd" />
											<span>工地处理</span>
										</s:a></sec:authorize>
									</td>
									<td><s:property value="wsNm" />
									</td>
									<td><pt:dictshow name="divCd" listValue="dictName"
											listKey="dictCode" list="userDepartList"></pt:dictshow></td>
											
									<td>
									<s:if test="typeCd==1">内线车间</s:if>
										<s:if test="typeCd==0">管理部门</s:if>
										<s:if test="typeCd==2">外线工地</s:if>
										<s:if test="typeCd==3">仓库</s:if>
										</td>
									<td><pt:usernameShow userId="managerUserId"></pt:usernameShow>
									</td>
									<td><s:property value="mobileTel" />
									</td>
									<td><s:date name="beginTime" format="yyyy-MM-dd" />
									</td>
									<td><s:date name="endTime" format="yyyy-MM-dd" />
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div region="south" height="26">
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
	<script>
		var dcTime = 250; // doubleclick time
		var dcDelay = 100; // no clicks after doubleclick
		var dcAt = 0; // time of doubleclick
		var savEvent = null; // save Event for handling doClick().
		var savEvtTime = 0; // save time of click event.
		var savTO = null; // handle of click setTimeOut
		/*点击查看出门单信息*/
		/* 查看事件  */
		function handleWisely5(planId, callbackId, which) {
			switch (which) {
			case "click":
				savEvent = which;
				plancd = planId;//不转换不行？
				d = new Date();
				savEvtTime = d.getTime();
				savTO = setTimeout("doClick(plancd,savEvent)", dcTime);
				break;
			case "dblclick":
				doDoubleClick2(planId, callbackId, which);
				break;
			default:
			}
		}
		/* 单击  */
		function doClick(planId, which) {
		}
		/* 双击 */
		function doDoubleClick2(planId, callbackId, which) {
			var d = new Date();
			dcAt = d.getTime();
			if (savTO != null) {
				savTO = null;
			}
			var url = "/ISMS/rst/m-workshop!view.action?queryEntity.wsCd="
					+ planId + "&callbackId=" + callbackId;
			$("#${_}showcontractMsg").attr("href", url).click();
		};
	</script>
</body>
</html>
