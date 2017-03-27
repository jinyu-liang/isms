<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>工作日志列表</title>
</head>
<body>
    <s:form method="post" action="m-workshop-joblog!list.action" onsubmit="return navTabSearch(this);" cssStyle="height:100%;width:100%;" id="%{_}">
        <div class="page" style="height: 100%; width: 100%;">
            <div class="layout">
                <div region="north" height="95">
                    <div class="pageHeader">
                        <div class="searchBar">
                            <ul class="searchContent">
                                <li><label for="entity_fquserid">员工姓名</label> <s:textfield id="entity_fqusername" name="entity.fqusername"></s:textfield></li>
                                <li><label for="entity_fquserid">员工编号</label> <s:textfield id="entity_fquserid" name="entity.fquserid"></s:textfield></li>
                                <li><label>开始时间</label> 
									<span>
									    <s:textfield name="entity.start_date" cssClass="required date" id="entity_start_date" dateFmt="yyyy-MM-dd" readonly="true">
										<s:param name="value">
											<s:date name="entity.start_date" format="yyyy-MM-dd" />
										</s:param>
										</s:textfield> <a class="inputDateButton" href="javascript:;">选择</a>
									</span>

								</li>
                                <li><label>结束时间</label>
									<span>
									    <s:textfield name="entity.end_date" cssClass="required date" id="entity_end_date" dateFmt="yyyy-MM-dd" readonly="true">
										<s:param name="value">
											<s:date name="entity.end_date" format="yyyy-MM-dd" />
										</s:param>
										</s:textfield> <a class="inputDateButton" href="javascript:;">选择</a>
									</span>

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
	                                <li><s:a namespace="/rst" action="m-workshop-joblog" method="toAdd" width="800" height="600" cssClass="add" target="dialog" mask="true">
	                                        <s:param name="callbackId" value="%{_}"></s:param>
	                                        <span>新增日志</span>
	                                    </s:a></li>    
	                                <li><s:a namespace="/rst" action="m-workshop-joblog" method="toEdit" width="800" height="600" encode="false" cssClass="edit" mask="true"
	                                        target="dialog">
	                                        <s:param name="entity.ID" value="'{list_data_ID}'" />
	                                        <s:param name="callbackId" value="%{_}"></s:param>
	                                        <span>修改日志</span>
	                                 </s:a></li>
                                <li class="line">line</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div region="center">
                    <table class="table" style="overflow:scroll;">
                        <thead>
                            <tr>
                            	<th>详情</th>
                            	<th>日志ID</th>
                                <th>员工姓名</th>
                                <th>员工编码</th>
                                <th>部门名称</th>
                                <th>部门编码</th>
                                <th>填写时间</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="page.data" var="user">
                                <tr target="list_data_ID" rel='<s:property value="ID" />'
									ondblclick="handleWisely3('<s:property value="ID" />','<s:property value="%{_}" />',event.type)">   
                                   <td>
                                         <s:a namespace="/rst" action="m-workshop-joblog" method="view" encode="false" cssClass="button"
                                            target="dialog" mask="true" width="1000" height="523">
                                            <s:param name="entity.ID" value="ID" />
                                            <s:param name="callbackId" value="%{_}"></s:param>
                                            <span>详情</span>
                                        </s:a>
	                                 </td>
                                    <td><s:property value="ID" /></td>
                                    <td><s:property value="fqusername" /></td>
                                    <td><s:property value="fquserid" /></td>
                                    <td><s:property value="ws_name" /></td>
                                    <td><s:property value="ws_cd" /></td>
                                    <td><s:date name="accepttime" format="yyyy-MM-dd hh:mm:ss" /></td>
                                    
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
                        <s:hidden name="queryEntity.pageNumber" id="pageNumber" />
                        <s:hidden name="_"></s:hidden>
                        <s:hidden name="__"></s:hidden>
                        <div class="pagination" 
                        totalCount='<s:property value="page.count"/>' 
                        numPerPage='<s:property value="page.pageSize"/>'
                        pageNumShown='<s:property value="page.pageNumShown"/>' 
                        currentPage='<s:property value="page.pageNumber"/>'></div>
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
                        var url = "http://localhost:8080/isms/rst/m-workshop-joblog!list.action?entity.ID=" + planId;
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
                        var url = "http://localhost:8080/isms/rst/m-workshop-joblog!view.action?entity.ID=" + planId + "&callbackId=" + callbackId;
                        $("#%{__}").attr("href", url).click();
                    };
                </script>
                
</body>
</html>
