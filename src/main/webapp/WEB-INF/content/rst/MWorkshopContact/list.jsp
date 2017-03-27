<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>工作联系单</title>
</head>
<body>
    <s:form method="post" action="m-workshop-contact!list.action" onsubmit="return navTabSearch(this);" cssStyle="height:100%;width:100%;" id="%{_}">
        <div class="page" style="height: 100%; width: 100%;">
            <div class="layout">
                <div region="north" height="95">
                    <div class="pageHeader">
                        <div class="searchBar">
                            <ul class="searchContent">
                                <li><label>项目ID</label> <s:textfield id="ws_cd" name="entity.ws_cd"></s:textfield></li>
                                <li><label>项目名称</label> <s:textfield name="entity.ws_nm"></s:textfield></li>
                                <li><label>标题</label> <s:textfield name="entity.title"></s:textfield></li>
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
                            	<li><s:a namespace="/rst" action="m-workshop-contact" method="toAdd" width="800" height="400" cssClass="add" target="dialog" mask="true">
                                        <s:param name="callbackId" value="%{_}"></s:param>
                                        <span>新增工作联系单</span>
                                    </s:a></li>    
                                <li><s:a namespace="/rst" action="m-workshop-contact" method="toEdit" width="800" height="600" encode="false" cssClass="edit" mask="true"
                                        target="dialog">
                                        <s:param name="entity.ID" value="'{list_data_ID}'" />
                                        <s:param name="callbackId" value="%{_}"></s:param>
                                        <span>修改工作联系单</span>
                                 </s:a></li>
                                 <li><s:a namespace="/rst" action="m-workshop-contact" method="delete" title="是否删除?"
                                        callback="refreshCurrentNavTabSearch" target="ajaxTodo" encode="false" cssClass="delete">
                                        <s:param name="entity.ID" value="'{list_data_ID}'" />
                                        <span>删除工作联系单</span>
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
                                <th>项目ID</th>
                                <th>项目名称</th>
                                <th>标 题</th>
                                <th>创建时间</th>
                                <th>状态</th>
                                <th>处理</th>

                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="page.data">
                            <tr target="list_data_ID" rel='<s:property value="ID" />'
									ondblclick="handleWisely3('<s:property value="ID" />','<s:property value="%{_}" />',event.type)">   
                                    <td>
                                    <s:a namespace="/rst" action="m-workshop-contact" method="view" encode="false" cssClass="button"
                                            target="dialog" mask="true" width="1000" height="523">
                                            <s:param name="entity.ID" value="ID" />
                                            <s:param name="callbackId" value="%{_}"></s:param>
                                            <span>详情</span>
                                        </s:a>
                                    
                                    </td>
                                    <td><s:property value="ws_cd" /></td>
                                    <td><s:property value="ws_nm" /></td>
                                    <td><s:property value="title" /></td>
                                    <td><s:date name="addtime" format="yyyy-MM-dd hh:mm:ss" /></td>
                                    <td><s:if test="status==\"0\"">
                                            <font style="color:green">发起</font>
                                        </s:if>
                                         <s:if test="status==\"1\"">
                                            <font style="color:green">部门领导审核完毕</font>
                                        </s:if>
                                        <s:if test="status==\"2\"">
                                            <font style="color:green">主管领导审核完毕</font>
                                        </s:if>
                                         <s:if test="status==\"3\"">
                                            <font style="color:green;">接收部门接收</font>
                                        </s:if> 
                                        <s:if test="status==\"4\"">
                                            <font style="color:green;">接收部门驳回</font>
                                        </s:if>
                                    </td>
                                    
                                    <td>
                                    <s:if test=" sessionUser.userId==acceptdepmangerID || sessionUser.userId==leaderID || sessionUser.userId==acceptdepID || sessionUser.userId==acceptuserID ">
                                    	<s:a namespace="/rst" action="m-workshop-contact"
											method="toEdit" encode="false" width="800" height="550"
											cssClass="button" target="dialog" mask="true" >
											<s:param name="callbackId" value="%{_}"></s:param>
											<s:param name="entity.ID" value="ID" />
											<s:if test="status==\"0\"">
                                            	<span>部门主管审核</span>
	                                        </s:if>
	                                         <s:if test="status==\"1\"">
	                                            <span>主管领导审核</span>
	                                        </s:if>
	                                        <s:if test="status==\"2\"">
	                                            <span>接收部门操作</span>
	                                        </s:if>
										</s:a>
									</s:if>
										
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
                        <s:hidden name="queryEntity.pageNumber" id="pageNumber" />
                        <s:hidden name="_"></s:hidden>
                        <div class="pagination" totalCount='<s:property value="page.count"/>' numPerPage='<s:property value="page.pageSize"/>'
                            pageNumShown='<s:property value="page.pageNumShown"/>' currentPage='<s:property value="page.pageNumber"/>'></div>
                    </div>
                </div>
            </div>
        </div>
    </s:form>
</body>
</html>
