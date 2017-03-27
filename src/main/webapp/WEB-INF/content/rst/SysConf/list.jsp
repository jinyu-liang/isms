<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>系统维护列表</title>
</head>
<body>
	<s:property value="message" escapeHtml="false" />
	<s:form method="post" action="sys-conf!list"
		cssStyle="height:100%;width:100%;"
		onsubmit="return navTabSearch(this);" id="%{_}">
		<div class="page" style="height: 100%; width: 100%;">
			<div class="layout">
				<div region="north" height="95">
					<div class="pageHeader">
								<ul>
									<li><img style="vertical-align: middle;" alt="" width="28" height="26.5" src="image/xtwh.jpg">
									<span>系统维护页面</span>
										</li>
								</ul>
					</div>

				</div>


				<div region="center">

					<table class="table">
						<thead>
							<tr>
								<th>系统维护类型</th>
								<th>操作</th>
								<th>当前属性</th>
								<th>操作人</th>
								<th>操作时间</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody>
								<tr target="list_data_report_id"
									rel='<s:property value="reportId" />'
									onclick="handleWisely4('<s:property value="reportId" />','<s:property value="%{_}" />',event.type)"
									ondblclick="handleWisely4('<s:property value="reportId" />','<s:property value="%{_}" />',event.type)">
										<td>
										安卓客户端版本维护(<font color="red">手动更新</font>)
										</td>
										<td>
										<sec:authorize ifAllGranted="系统维护-版本维护">
												<s:a namespace="/rst" action="rst-ver-info"
													method="toVerConf" encode="false" width="1025" height="550"
													cssClass="button" target="dialog" mask="true" title="版本维护">
													<s:param name="callbackId" value="%{_}"></s:param>
													<span>版本维护</span>
												</s:a>
										</sec:authorize>		
										<sec:authorize ifNotGranted="系统维护-版本维护">
										<span>无维护权限</span>
										</sec:authorize>
											</td>
										<td>当前版本<s:property value="nowVerInfo.verCode" />
										</td>
										<td><s:property value="nowVerInfo.operUserName" />
										</td>
										<td><s:date name="nowVerInfo.operTime" />
										</td>
										<td><s:property value="nowVerInfo.memo" /></td>
									</tr>
									<tr>
														<td>
										上传图片大小控制
										</td>
												<td>
												<sec:authorize ifAllGranted="系统维护-上传图片大小设置">
												<s:a namespace="/rst" action="rst-upload-conf"
													method="toEdit" encode="false" width="300" height="150"
													cssClass="button" target="dialog" mask="true" title="上传图片大小设置">
													<s:param name="callbackId" value="%{_}"></s:param>
													<span>上传图片大小设置</span>
												</s:a></sec:authorize>
												<sec:authorize ifNotGranted="系统维护-上传图片大小设置">
												<span>无设置权限</span>
												</sec:authorize>
											</td>

										<td><s:property value="imgInfo.sizeConf" />K
										</td>
										<td><s:property value="imgInfo.operUserName" />
										</td>
										<td><s:date name="imgInfo.operTime" format="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<td>建议图片设置不要太大，否则影响手机下载速度</td>
									
								</tr>
								<tr>
										<td>系统运行操作日志
												</td>
										<td>
										
										
										
													<sec:authorize ifAllGranted="系统维护-查看操作日志"> <s:a id="%{_}showMsg" namespace="/sys"  target="navTab"
														action="sys-oper-log" method="list" encode="false" cssClass="button"
														  mask="true"  
														  title="操作日志">
														<s:param name="callbackId" value="%{_}"></s:param>
														<span>查看操作日志</span>
													</s:a></sec:authorize>
													<sec:authorize ifNotGranted="系统维护-查看操作日志"><span>无查看权限</span> 
													</sec:authorize>
													</td>
										<td>
										系统操作日志正常
										</td>
										
										<td>--
										</td>
										<td>--
										</td>
										<td>系统操作日志，具有权限者查看所有的用户登录系统后做的所有操作</td>
									
								</tr>
						</tbody>
					</table>
				</div>
			<%-- 	<div region="south" height="26">
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
				</div> --%>
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
