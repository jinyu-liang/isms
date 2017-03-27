<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户信息列表</title>
</head>
<body>
    <s:form method="post" action="ggkz-user-info!list.action" onsubmit="return navTabSearch(this);" cssStyle="height:100%;width:100%;" id="%{_}">
        <div class="page" style="height: 100%; width: 100%;">
            <div class="layout">
                <div region="north" height="95">
                    <div class="pageHeader">
                        <div class="searchBar">
                            <ul class="searchContent">
                                <li><label>用户名</label> <s:textfield name="queryEntity.name"></s:textfield></li>
                                <li><label>员工编号</label> <s:textfield name="queryEntity.workCode"></s:textfield></li>
                                <li><label>职位</label> <s:textfield name="queryEntity.post"></s:textfield></li>
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
                            <sec:authorize ifAllGranted="用户管理-新增用户">
                                <li><s:a namespace="/ggkz" action="ggkz-user-info" method="toAdd" width="800" height="600" cssClass="add" target="dialog" mask="true">
                                        <s:param name="callbackId" value="%{_}"></s:param>
                                        <span>添加用户</span>
                                    </s:a></li>
                             </sec:authorize>  
                             <sec:authorize ifAllGranted="用户管理-修改用户">     
                                <li><s:a namespace="/ggkz" action="ggkz-user-info" method="toEdit" width="800" height="600" encode="false" cssClass="edit" mask="true"
                                        target="dialog">
                                        <s:param name="entity.userId" value="'{list_data_userId}'" />
                                        <s:param name="callbackId" value="%{_}"></s:param>
                                        <span>修改用户信息</span>
                                    </s:a></li>
                             </sec:authorize>
                             <sec:authorize ifAllGranted="用户管理-删除用户 ">
                                <li><s:a namespace="/ggkz" action="ggkz-user-info" method="delete" title="是否删除用户?"
                                        callback="refreshCurrentNavTabSearch" target="ajaxTodo" encode="false" cssClass="delete">
                                        <s:param name="entity.userId" value="'{list_data_userId}'" />
                                        <span>删除用户</span>
                                    </s:a></li>
                               </sec:authorize>   
                                <li class="line">line</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div region="center">
                    <table class="table" style="width:1400px; overflow:scroll;">
                        <thead>
                            <tr>
                                <th>姓 名</th>
                                <th>登陆名</th>
                                <th>职 位</th>
                                <th>修改密码</th>
                                <th>部 门</th>
                                <th>员工编号</th>
                                <th>电子邮件</th>
                                <th>办公电话</th>
                                <th>移动电话</th>
                                <th>设备Id</th>
                                <th>设备sn</th>
                                <th>设备状态</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="page.data" var="user">
                                <tr target="list_data_userId" rel='<s:property value="userId" />'>
                                    <td><s:property value="name" /></td>
                                    <td><s:property value="loginName" /></td>
                                    <td><pt:dictshow name="post" listValue="dictName" listKey="dictCode" list="userPostList"></pt:dictshow></td>
                                    <td><%-- <li><s:a namespace="/ggkz" action="ggkz-user-info" method="toModiPassword"  cssClass="edit" target="ajaxTodo" >
                                    <s:param name="entity.userId" value="%{userId}"></s:param>
                                    <span>修改密码</span>
                                    </s:a></li> --%>
                                    <s:url id="toModiPasswordUrl" namespace="/ggkz" action="ggkz-user-info" method="toResetPwd">
                                    <s:param name="queryEntity.userId" value="%{userId}"></s:param>
                                        </s:url> 
                                        <s:a href="%{toModiPasswordUrl}" encode="false" width="450" height="200" target="dialog" mask="true">
                                        <span>修改密码</span>
                                    </s:a>
                                    
                                    </td>
                                    <td><pt:dictshow name="departId" listValue="dictName" listKey="dictCode" list="userDepartList"></pt:dictshow></td>
                                    <td><s:property value="userId" /></td>
                                    <td><s:property value="email" /></td>
                                    <td><s:property value="officeTel" /></td>
                                    <td><s:property value="mobileTel" /></td>
                                    <td><s:property value="mobileId" /></td>
                                    <td><s:property value="mobileSn" /></td>
                                    <td>
                                    <s:if test="orgId==\"1\"">
                                    
                                     <s:if test="mobileId==null || mobileId==''">
                                    <font style="color: green;">自动注册并登录</font></s:if>
                                       <s:else><font style="color: green;">已注册可用</font>
                                    </s:else>
                                    
                                    </s:if>
                                    <s:else>
                                    
                                     <s:if test="mobileId==null || mobileId==''"><font style="color: blue;">未注册</font>
                                    </s:if><s:else>
                                    <font style="color: red;">已注册未启用</font>
                                    </s:else>
                                    </s:else>
                                    
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
