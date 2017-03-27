<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>选择人员</title>
</head>
<body>
    <s:form method="post" action="ggkz-choose-user!publicMultiChooseUserByPostFilterData.action" onsubmit="return dialogSearch(this);"
        cssStyle="height:100%;width:100%;" id="%{_}">
        <div class="pageContent" style="height: 100%; width: 100%;">
            <div class="unitBox" style="float: left; display: block; overflow: hidden; width: 100%; height: 100%;" id="maindata">
                <div class="layout">
                    <div region="north" height="67">
                        <div class="pageHeader">
                            <div class="searchBar">
                                <ul class="searchContent">
                                    <li><label>用户名</label> <s:textfield name="queryEntity.name"></s:textfield>
                                    </li>
                                    <li>
                                        <label>员工编号</label>
                                        <s:textfield name="queryEntity.workCode"></s:textfield>
                                    </li>
                                    <li>
                                        <label>职位</label>
                                        <s:select list="userPostList" name="queryEntity.postName" listKey="dictName" listValue="dictName" headerKey="" headerValue="请选择..."></s:select>
                                        <%-- <s:textfield name="queryEntity.postName"></s:textfield> --%>
                                    </li>
                                    <%-- <s:hidden
                                            name="queryEntity.postName"></s:hidden> --%>
                                </ul>
                                <div class="subBar">
                                    <ul>
                                        <li>
                                            <div class="buttonActive">
                                                <div class="buttonContent">
                                                    <s:submit type="button">查询</s:submit>
                                                </div>
                                            </div>
                                            <div class="buttonActive">
                                                <div class="buttonContent">
                                                    <button onclick="return checkback(this);">确定</button>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div region="center">
                        <table class="table" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th width="30"><s:checkbox name="checkAll" onclick="leftAllChange(this)"></s:checkbox></th>
                                    <th width="100">用户名</th>
                                    <th width="100">员工编号</th>
                                    <th width="100">职位</th>
                                </tr>
                            </thead>
                            <tbody id="left-table">
                                <s:iterator value="page.data" var="user">
                                    <tr target="list_data_userId" rel='<s:property value="userId" />'>
                                        <td><s:checkbox name="userIds" fieldValue="%{userId}|%{name}"></s:checkbox></td>
                                        <td><s:property value="name" /></td>
                                        <td><s:property value="workCode" /></td>
                                        <td><pt:dictshow name="post" listValue="dictName" listKey="dictCode" list="userPostList"></pt:dictshow></td>
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
                            <div class="pagination" targetType="dialog" totalCount='<s:property value="page.count"/>' numPerPage='<s:property value="page.pageSize"/>'
                                pageNumShown='<s:property value="page.pageNumShown"/>' currentPage='<s:property value="page.pageNumber"/>'></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </s:form>
    <script type="text/javascript">
        function leftAllChange(obj) {
    		var allFlag = obj.checked;
    		if (allFlag) {
    			// 全部选中
    			$("#left-table :checkbox").attr("checked", true);
    		} else {
    			// 全部关闭
    			$("#left-table :checkbox").attr("checked", false);
    		}
    	}
		// 确认选择
		function checkback(obj) {
			var containerId = "${callbackId}";
			var idzone = "${checkid}";
			var namezone = "${checkname}";
			var ids = [];
			var names = [];
			$("#${_} #left-table input:checked").each(function(i, o) {
				if (o.value != '') {
					var idname = o.value.split("|");
					ids[i] = idname[0];
					names[i] = idname[1];
				}
			});
			try {
				if (containerId != '' && ids.length > 0) {
					$("#" + containerId + ",#" + idzone).val(
							ids.join(","));
					$("#" + containerId + ",#" + namezone).val(
							names.join(";") + ";");
				}
			} catch (e) {
			}
			$.pdialog.closeCurrent();
			return false;
		}
	</script>
</body>
</html>
