<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>角色列表信息</title>
    </head>
    <body>
		<s:form method="post" action="ggkz-role-info!list.action" onsubmit="return navTabSearch(this);" cssStyle="height:100%;width:100%;" id="%{_}">
			<div class="page" style="height:100%;width:100%;">
				<div class="layout">
					<div region="north" height="93">
					<s:property value="message" escapeHtml="false"/>
						<div class="pageHeader">
							<div class="searchBar">
								<ul class="searchContent">
									<li>	
										<label for="ggkzRoleInfoQuery.roleName">角色名称</label>
										<s:textfield id="ggkzRoleInfoQuery.roleName" name="ggkzRoleInfoQuery.roleName" maxLength="60"/>
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
								<ul class="toolBar">
									<sec:authorize ifAllGranted="角色管理-添加角色">
									<li>
										<s:a nameSpace="/ggkz" action="ggkz-role-info" method="toCreate" cssClass="add"  width="800" height="600" target="dialog" mask="true" title="添加角色权限"  >
											<span>添加角色</span>
										</s:a>
									</li>
									</sec:authorize>
									<sec:authorize ifAllGranted="角色管理-权限分配">
									<li>
										<s:a nameSpace="/ggkz" action="ggkz-role-info" method="toEdit" encode="false" cssClass="edit" target="dialog" mask="true" width="800" height="700" title="权限分配"  >
											<s:param name="entity.roleId" value="'{list_data_roleId}'"/>
											<span>权限分配</span>
										</s:a>
									</li>
									</sec:authorize>
									<sec:authorize ifAllGranted="角色管理-删除角色">
									<li>
										<s:a nameSpace="/ggkz" action="ggkz-role-info" method="delete" encode="false" cssClass="delete" title="是否删除角色?" callback="function refreshCurrentPageContainer(){navTabSearch('${_}');}" target="ajaxTodo">
											<s:param name="entity.roleId" value="'{list_data_roleId}'"/>
											<span>删除角色</span>
										</s:a>
									</li>
									</sec:authorize>
									<sec:authorize ifAllGranted="角色管理-查看角色权限">
									<li>
										<s:a nameSpace="/ggkz" action="ggkz-role-info" method="view" encode="false"  width="800" height="600" cssClass="view" target="dialog" mask="true" callback="closeCurrentPageContainer(this)">
											<s:param name="entity.roleId" value="'{list_data_roleId}'"/>
											<span>查看角色权限</span>
										</s:a>
									</li>
									</sec:authorize>
									<li class="line">line</li>
								</ul>
							</div>
						</div>
					</div>
					<div region="center">
						<table class="table">
							<thead>
								<tr>
									<th>角色名称</th>
									<th>操作人</th>
									<th>操作时间</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="page.data">
									<tr  target="list_data_roleId" rel="<s:property value="roleId" />">
										<td><s:property value="roleName" /></td>
										<td><pt:usernameShow userId="operUserId"></pt:usernameShow></td>
										<td><s:date name="operTime"/></td>
										<td><s:property value="note" /></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					<div region="south" height="26">
						<div class="panelBar">
							<div class="pages">
								<span>显示</span>
								<s:select list="page.pageSizeList" name="ggkzRoleInfoQuery.pageSize" cssClass="pageSize" form="${_}"/>
								<span>条，共[<s:property value="page.count"/>]条</span>
							</div>
							<s:hidden name="ggkzRoleInfoQuery.pageNumber" id="pageNumber" />
							<s:hidden name="_"/>
							<div class="pagination" totalCount='<s:property value="page.count"/>' numPerPage='<s:property value="page.pageSize"/>' pageNumShown='<s:property value="page.pageNumShown"/>' currentPage='<s:property value="page.pageNumber"/>'></div>
						</div>
					</div>
				</div>
			</div>
		</s:form>
	</body>
</html>
