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
<title>采购申请列表</title>
</head>
<body>
    <s:property value="message" escapeHtml="false" />
    <s:form method="post" action="d-report!list" cssStyle="height:100%;width:100%;" onsubmit="return navTabSearch(this);" id="%{_}">
        <div class="page" style="height: 100%; width: 100%;">
            <div class="layout">
                <div region="north" height="95">
                    <div class="pageHeader">
                        <div class="searchBar">
                            <ul class="searchContent">
                                <li><label for="entity_title">采购项目名称</label> <s:textfield id="queryEntity_title" name="queryEntity.title"
                                        maxLength="200" /></li>
                                <li><label for="entity_projName">工地名称</label> <s:textfield id="queryEntity_projName" name="queryEntity.projName"
                                        maxLength="100" /></li>
                                <li>
                                	<label for="entity_statusCd">状态</label>
									<s:select list="#{'':'请选择状态','0':'待审批/审批有问题','1':'已审批/发票待上传','2':'发票待处理','3':'发票已处理' }" name="queryEntity.statusCd"
										cssClass="required"></s:select>
                               </li> 
                                <li><label for="entity_reportUserCd">采购申请人</label> <s:textfield id="queryEntity_reportUserCd"
                                        name="queryEntity.reportUserCd" maxLength="32" /></li>
                                <li><label for="entity_processUserCd">发票处理人</label> <s:textfield id="queryEntity_processUserCd"
                                        name="queryEntity.processUserCd" maxLength="32" /></li>

                                <li><label for="entity_verifiedUserCd">采购审批人</label> <s:textfield id="queryEntity_verifiedUserCd"
                                        name="queryEntity.verifiedUserCd" maxLength="32" /></li>
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
                    <table class="table"  style="width:2000px; overflow:scroll;">
                        <thead >
                            <tr>
                                <th>操作</th>
                                <th>状态</th>
                                <th>图片</th>
                                <th>采购项目</th>
                                <th>工地中心</th>
                                <th>单价</th>
                                <th>金额</th>
                                <th>数量</th>
                                <th>申请人</th>
                                <th>申请时间</th>
                                <th>审批人</th>
                                <th>审批结果</th>
                               	<th width="11%">审批意见</th> 
                                <th>审批时间</th>
                                <th>处理人</th>
                                <th>财务处理结果</th>
                              	<th width="11%">处理意见</th>
                                <th>处理时间</th>
                                 <s:if test="sessionUser.userId=='USER0047'">
                                    <th style="width:3%;">操作</th>
                                </s:if>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="page.data">
                                <tr target="list_data_report_id" rel='<s:property value="reportId" />'
                                    ondblclick="handleWisely3('<s:property value="reportId" />','<s:property value="%{_}" />',event.type)">
                                    <td>
                                    	<sec:authorize ifAllGranted="采购管理-采购审批"><s:if test="verifiedHeadStatus==\"0\" and sessionUser.userId==verifiedUserCd">
	                                    	<s:a namespace="/rst" action="d-report" method="toVerfied" encode="false" width="1000" height="550"
	                                            cssClass="button" target="dialog" mask="true">
	                                            <s:param name="callbackId" value="%{_}"></s:param>
	                                            <s:param name="queryEntity.reportId" value="reportId" />
	                                            <span>审批</span>
	                                        </s:a>
	                                        </s:if>
                                        </sec:authorize>
                                       <sec:authorize ifAllGranted="采购管理-采购发票处理"> <s:if test="statusCd==2 and dealResult==\"0\"  and  sessionUser.userId!=reportUserCd and sessionUser.userId!=verifiedUserCd and (sessionUser.userId==processUserCd or processUserCd==null)">
	                                        <s:a namespace="/rst" action="d-report" method="toDeal" encode="false" width="1000" height="550"
	                                            cssClass="button" target="dialog" mask="true">
	                                            <s:param name="callbackId" value="%{_}"></s:param>
	                                            <s:param name="queryEntity.reportId" value="reportId" />
	                                            <span>发票处理</span>
	                                        </s:a>
                                        </s:if></sec:authorize>
	                                         <s:a id="%{_}showMsg" namespace="/rst" action="d-report" method="view" encode="false" cssClass="button"
	                                            target="dialog" mask="true" width="1000" height="423">
	                                            <s:param name="queryEntity.reportId" value="reportId" />
	                                            <s:param name="callbackId" value="%{_}"></s:param>
	                                            <span>详情</span>
	                                        </s:a></td>
                                    <td><s:if test="statusCd==\"0\"  and  verifiedHeadStatus==\"0\"">
                                            <font style="color:#6495ED;">待审批</font>
                                        </s:if>
                                         <s:if test="statusCd==\"0\"  and  verifiedHeadStatus==\"No\"">
                                            <font style="color: red;">采购审批有问题</font>
                                        </s:if>
                                        <s:if test="statusCd==1  and  fileName.size()==0  and  verifiedHeadStatus==\"Yes\" and dealResult==\"0\"">
                                            <font style="color: #6495ED;">发票待上传</font>
                                        </s:if>
                                         <s:if test="statusCd==1  and  dealResult==\"No\"">
                                            <font style="color: red;">发票处理有问题</font>
                                        </s:if> 
                                        <s:if test="statusCd==2">
                                            <font style="color: #6495ED;">发票待处理</font>
                                        </s:if>
                                         <s:if test="statusCd==3">
                                            <font style="color: green;">发票已处理</font>
                                        </s:if></td>
                                    <td><s:if test="fileName.size()>0">
                                       <s:a id="%{_}showMsg" namespace="/rst" action="d-report" method="imgView" encode="false" cssClass="button"
                                            target="dialog" mask="true" width="1000" height="800">
                                            <s:param name="queryEntity.reportId" value="reportId" />
                                            <s:param name="callbackId" value="%{_}"></s:param>
                                            <span>查看图片</span>
                                        </s:a>
                                        </s:if>
                                    </td>
                                    <td><s:property value="title" /></td>
                                    <td><s:property value="projName" /></td>
                                    <td> 
                                     <s:if test="unitPrice!=null&&unitPrice!=''">
                                            <s:text name="format.number">  
									  	      <s:param value="unitPrice"/>  
									        </s:text>  
									    </s:if>
									    <s:else>
									        --
									    </s:else>
                                    
                                    
                                    </td>
                                    <td>
                                        <s:if test="amount!=null&&amount!=''">
                                            <s:text name="format.number">  
									  	      <s:param value="amount"/>  
									        </s:text>  
									    </s:if>
									    <s:else>
									        --
									    </s:else>
                                   </td>
                                    <td><s:property value="number" /></td>
                                    <td><s:property value="reportUserName" /></td>
                                    <td><s:date name="reportTm" format="yyyy-MM-dd HH:mm:ss" /></td>
                                    <td><s:property value="verifiedUserName" /></td>
                                    <td><s:if test="verifiedHeadStatus==\"No\"">
                                            <font style="color: red;">有问题</font>
                                        </s:if> <s:if test="verifiedHeadStatus==\"Yes\"">
                                            <font style="color: green;">同意</font>
                                        </s:if> <s:if test="verifiedHeadStatus==\"0\"">
                                            <font style="color: #6495ED;">待审批</font>
                                        </s:if></td>
                                        <td title="<s:property value="verifiedHeadMemo" />"><div class="autocut"><s:property value="verifiedHeadMemo" /></div></td>
                                    <td><s:if test="verifiedHeadTm==null">
                                            <span>--</span>
                                        </s:if> <s:else>
                                            <s:date name="verifiedHeadTm" format="yyyy-MM-dd HH:mm:ss" />
                                        </s:else></td>
                                    <td><s:if test="processUserCd==null||processUserCd==''">
                                            <span>--</span>
                                        </s:if> <s:else>
                                        <s:property value="processUserName" />
                                        </s:else></td>
                                    <td><s:if test="dealResult==\"No\"">
                                            <font style="color: red;">有问题</font>
                                        </s:if> <s:if test="dealResult==\"Yes\"">
                                            <font style="color: green;">同意</font>
                                        </s:if> <s:if test="dealResult==\"0\"">
                                            <font style="color: #6495ED;">待处理</font>
                                        </s:if></td>
                                <td title="<s:property value="comment" />"><div class="autocut"><s:property value="comment" /></div></td>
                                    <td><s:if test="processTm==null">
                                            <span>--</span>
                                        </s:if> <s:else>
                                            <s:date name="processTm" format="yyyy-MM-dd HH:mm:ss" />
                                        </s:else></td>
                                     <s:if test="sessionUser.userId=='USER0047'">
                                        <td><s:a style="cursor:pointer" namespace="/rst" action="d-report" method="deleteReport" title="是否删除本条采购信息?"
                                        callback="refreshCurrentNavTabSearch" target="ajaxTodo" encode="false" cssClass="delete">
                                        <s:param name="entity.reportId" value="reportId" />
                                        <span>删除</span> 
                                        </s:a>
                                        </td>
                                    </s:if>
                                        
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
                        var url = "rst/d-report!list.action?queryEntity.reportId=" + planId;
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
                        var url = "/ISMS/rst/d-report!view.action?queryEntity.reportId=" + planId + "&callbackId=" + callbackId;
                        $("#${_}showMsg").attr("href", url).click();
                    };
                </script>
</body>
</html>
