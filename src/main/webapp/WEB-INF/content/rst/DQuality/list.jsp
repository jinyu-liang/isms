<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
.autocut {
    width:150px;
    overflow:hidden;
    white-space:nowrap;
    text-overflow:ellipsis;
    -o-text-overflow:ellipsis;
    -icab-text-overflow: ellipsis;
    -khtml-text-overflow: ellipsis;
    -moz-text-overflow: ellipsis;
    -webkit-text-overflow: ellipsis;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>质量管理列表</title>
</head>
<body>
    <s:property value="message" escapeHtml="false" />
    <s:form method="post" action="d-quality!list" cssStyle="height:100%;width:100%;" onsubmit="return navTabSearch(this);" id="%{_}">
        <div class="page" style="height: 100%; width: 100%;">
            <div class="layout">
                <div region="north" height="95">
                    <div class="pageHeader">
                        <div class="searchBar">
                            <ul class="searchContent">
                                <li><label for="entity_ws_nm">质量标题</label> <s:textfield id="entity_ws_nm" name="entity.ws_nm"
                                        maxLength="200" /></li>
								<li><label for="entity_departId">归属部门</label> 
									<s:select list="userDepartList" name="entity.div_cd" listKey="dictCode" listValue="dictName" headerKey="" headerValue="请选择..." cssClass="required"></s:select></li>
								
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
                <div region="center">
                    <table class="table"   overflow:scroll;">
                        <thead >
                            <tr>
                                <th>操作</th>
                                <th>项目名称</th>
                                <th>状态</th>
                                <th>发起方详情</th>
                                <th>整改方详情</th>
                                <th>监理方详情</th>
                                 <s:if test="sessionUser.userId=='USER0047'">
                                    <th style="width:3%;">操作</th>
                                </s:if>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="page.data">
                                <tr target="list_data_report_id" rel='<s:property value="pid" />'
                                    ondblclick="handleWisely3('<s:property value="pid" />','<s:property value="%{_}" />',event.type)">    
                                    <td>
                                         <s:a namespace="/rst" action="d-quality" method="view" encode="false" cssClass="button"
                                            target="dialog" mask="true" width="1000" height="523">
                                            <s:param name="queryEntity.pid" value="pid" />
                                            <s:param name="callbackId" value="%{_}"></s:param>
                                            <span>详情</span>
                                        </s:a>
	                                 </td>
	                                 <td><s:property value="ws_nm" /></td>
                                    <td><s:if test="fqstatus==\"1\"">
                                            <font style="color:#6495ED;">录入</font>
                                        </s:if>
                                         <s:if test="fqstatus==\"2\"">
                                            <font style="color:red">整改中</font>
                                        </s:if>
                                        <s:if test="fqstatus==\"3\"">
                                            <font style="color:green">发起确认完成</font>
                                        </s:if>
                                         <s:if test="fqstatus==\"4\"">
                                            <font style="color:green;">监理确认中</font>
                                        </s:if> 
                                        <s:if test="fqstatus==\"5\"">
                                            <font style="color:green;">关闭</font>
                                        </s:if>
                                    <td>
	                                    <s:a namespace="/rst" action="d-quality" method="toDealfq" encode="false" width="1000" height="550"
	                                        cssClass="button" target="dialog" mask="true">
	                                        <s:param name="callbackId" value="%{_}"></s:param>
	                                        <s:param name="queryEntity.pid" value="pid" />
	                                        <span>发起方详情</span>
	                                    </s:a>
                                    <!-- 
                                    <s:if test="fqattchid!=null&&fqattchid!=''">
                                       <s:a id="%{_}showMsg" namespace="/rst" action="d-quality" method="imgView" encode="false" cssClass="button"
                                            target="dialog" mask="true" width="1000" height="800">
                                            <s:param name="queryEntity.fqattchid" value="fqattchid" />
                                            <s:param name="callbackId" value="%{_}"></s:param>
                                            <span>查看图片</span>
                                        </s:a>
                                        </s:if>
                                     -->
                                    </td>
                                    <td>
                                    	<s:a namespace="/rst" action="d-quality" method="toDealzq" encode="false" width="1000" height="550"
	                                        cssClass="button" target="dialog" mask="true">
	                                        <s:param name="callbackId" value="%{_}"></s:param>
	                                        <s:param name="queryEntity.pid" value="pid" />
	                                        <span>整改方详情</span>
	                                    </s:a>
                                    </td>
									<td>
                                    	<s:a namespace="/rst" action="d-quality" method="toDealjl" encode="false" width="1000" height="600"
	                                        cssClass="button" target="dialog" mask="true">
	                                        <s:param name="callbackId" value="%{_}"></s:param>
	                                        <s:param name="queryEntity.pid" value="pid" />
	                                        <span>监理方详情</span>
	                                    </s:a>
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
                            <s:select list="page.pageSizeList" name="queryEntity.pageSize" cssClass="pageSize" form="${_}"/>
                            <span>条，共[<s:property value="page.count" />]条
                            </span>
                        </div>
                        <s:hidden name="_"></s:hidden>
                        <s:hidden name="queryEntity.pageNumber" id="pageNumber" />
                        <div class="pagination" totalCount='<s:property value="page.count"/>' numPerPage='<s:property value="page.pageSize"/>'
                            pageNumShown='<s:property value="page.pageNumShown"/>' currentPage='<s:property value="page.pageNumber"/>'></div>
                    </div>
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
                    function handleWisely3(planId, callbackId, which)
                    {
                        switch (which)
                        {
                        case "click":
                            savEvent = which;
                            plancd = planId;//不转换不行？
                            d = new Date();
                            savEvtTime = d.getTime();
                            savTO = setTimeout("doClick(plancd,savEvent)", dcTime);
                            break;
                        case "dblclick":
                            doDoubleClickreport(planId, callbackId, which);
                            break;
                        default:
                        }
                    }
                    /* 单击  */
                    function doClick(planId, which)
                    {
                        if (savEvtTime - dcAt <= 0)
                        {
                            return false;
                        }
                        var url = "http://localhost:8080/isms/rst/d-quality!list.action?queryEntity.pid=" + planId;
                        $("#%{_}").attr("action", url).submit();
                    }
                    /* 双击 */
                    function doDoubleClickreport(planId, callbackId, which)
                    {
                        var d = new Date();
                        dcAt = d.getTime();
                        if (savTO != null)
                        {
                            savTO = null;
                        }
                        var url = "http://localhost:8080/isms/rst/d-quality!view.action?queryEntity.pid=" + planId + "&callbackId=" + callbackId;
                        $("#${_}showMsg").attr("href", url).click();
                    };
                </script>
</body>
</html>
