<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pt" uri="/is-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>选择人员</title>
    </head>
    <body>
		<s:form method="post" action="ggkz-choose-user!publicSingleChooseUserByPostFilter.action" onsubmit="return dialogSearch(this);" cssStyle="height:100%;width:100%;" id="%{_}">
			<div class="page" style="height:100%;width:100%;">
				<div class="layout">
					<div region="north" height="67">
						<div class="pageHeader">
							<div class="searchBar">
								<ul class="searchContent">
									<li>
										<label>用户名</label>
										<s:textfield name="queryEntity.name"></s:textfield>
									</li>
									<li>
										<label>员工编号</label>
										<s:textfield name="queryEntity.workCode"></s:textfield>
									</li>
                                    <li>
                                        <label>职位</label>
                                        <%-- <s:textfield name="queryEntity.postName"></s:textfield> --%>
                                        <s:select list="userPostList" name="queryEntity.postName" listKey="dictName" headerKey="" headerValue="请选择..." listValue="dictName"></s:select>
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
										<li>
											<div class="buttonActive">
												<div class="buttonContent">
													<button type="button" onclick="chooseSingleUser(this);">确定</button>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div region="center">
						<table class="table" style="width:758px;">
							<thead>
								<tr>
									<th width="25"></th>
									<th>用户名</th>
									<th>员工编号</th>
									<th>职位</th>
									<th>性别</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="page.data" var="user">
									<tr target="list_data_userId" rel='<s:property value="userId" />'>
										<td width="27px">
											<input type="radio" name="radigroup" value="<s:property value="%{userId}"/>|<s:property value="%{name}"/>"/>
										</td>
										<td><s:property value="name"/></td>
										<td><s:property value="workCode"/></td>
										<td><pt:dictshow name="post" listValue="dictName" listKey="dictCode" list="userPostList"></pt:dictshow></td>
										<td>
											<pt:dictshow name="sex" listValue="dictName" listKey="dictCode" list="sexList"></pt:dictshow>
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
                                <s:select list="page.pageSizeList" name="queryEntity.pageSize" targetType="dialog" cssClass="pageSize"/>
                                <span>条，共[<s:property value="page.count" />]条
                                </span>
                            </div>
                            <s:hidden name="queryEntity.pageNumber" id="pageNumber" />
                            <s:hidden name="_"></s:hidden>
                            <s:hidden name="callbackId"></s:hidden>
                            <s:hidden name="checkid"></s:hidden>
                            <s:hidden name="checkname"></s:hidden>
                            <s:hidden name="toolTpid"></s:hidden>
                            <div class="pagination" targetType="dialog" totalCount='<s:property value="page.count"/>' numPerPage='<s:property value="page.pageSize"/>'
                                pageNumShown='<s:property value="page.pageNumShown"/>' currentPage='<s:property value="page.pageNumber"/>'></div>
                        </div>
                    </div>
				<div>
			</div>
		</s:form>
		<script type="text/javascript">
			function chooseSingleUser(obj){
				var containerId = "${callbackId}";
				var idzone = "${checkid}";
				var namezone = "${checkname}";
				var ids = "";
				var names = "";
				var checkojb = $("#${_} :radio[checked]");
				if($(checkojb).val() != undefined){
					var idname = $(checkojb).val().split("|");
					ids += idname[0];
					names += idname[1];
					try{
						if(containerId != ''){
							$("#"+ containerId + ",#" + idzone).val(ids);
							$("#"+ containerId + ",#" + namezone).val(names);
							if("${toolTpid}"!="")
							{
								$("#"+ containerId + ",#${toolTpid}").val("user");
							}
						}
					}catch(e){}
				}

				$.pdialog.closeCurrent();
			}
		</script>
	</body>
</html>
