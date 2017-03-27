<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息检索</title>
</head>
<body>
    <s:form method="post" namespace="/msg" action="msg-interact!toSearch.action" onsubmit="return navTabSearch(this);"
        cssStyle="height:100%;width:100%;" id="%{_}">
        <s:hidden name="entity.post"></s:hidden>
        <div class="page" style="height: 100%; width: 100%;">
            <div class="layout">
            	<div region="north" height="66">
                    <div class="pageHeader">
                        <div class="searchBar" style="height: 26px;">
                            <font style="line-height: 26px;">其他消息 (共 <s:property value="page.count" /> 封)
                            </font>
                        </div>
                    </div>
                    <s:if test="'USER0047'==entity.owner and 'search'!=tomsgbox">
                    <div class="pageContent">
                        <div class="panelBar">
                            <ul class="toolBar">
                                <li><s:a namespace="/msg" action="msg-interact" method="deleteMsgCascade" encode="false" title="确实要删除所有收到该消息的记录吗?"
                                        cssClass="delete" callback="function refreshCurrentPageContainer(){divSearch('${_}','mainmsg');}"
                                        target="selectedTodo" rel="ids" mask="true">
                                        <s:param name="msgbox" value="'receive'"></s:param>
                                        <span>删除</span>
                                    </s:a></li>
                            </ul>
                        </div>
                    </div>
                    </s:if>
                </div>
                <div region="center">
                    <s:a id="%{_}view" namespace="/msg" action="msg-interact" method="viewMsg" encode="false" cssStyle="display:none;" target="ajax"
                        rel="mainmsg">
                        <s:param name="entity.id" value="'{list_data_sendMsgId}'" />
                        <s:param name="msgbox" value="'search'" />
                        <span>查看</span>
                    </s:a>
                    <table class="table">
                        <thead>
                            <tr>
                                <th width="24"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
                                <th width="46">状态</th>
                                <th>主题</th>
                                <th>发件人</th>
                                <th width="120">时间</th>
                            </tr>
                        </thead>
                        <tbody ondblclick="$('#${_}view').click();">
                            <s:iterator value="page.data" var="msg">
                                <tr target="list_data_sendMsgId" rel='<s:property value="id" />'>
                                    <td><input name="ids" value="${sendid}" type="checkbox"></td>
                                    <td><s:if test="attachment!=null and attachment!=''">
                                            <a href="javascript:;" style="letter-spacing: 5px;" class="anatomy icon">&nbsp;&nbsp;</a>
                                        </s:if>
                                        <s:if test="flag==0">
                                            <a href="javascript:;" style="letter-spacing: 5px;" class="msginteract unread">&nbsp;&nbsp;</a>
                                        </s:if>
                                        <s:elseif test="flag==1">
                                            <a href="javascript:;" style="letter-spacing: 5px;" class="msginteract read">&nbsp;&nbsp;</a>
                                        </s:elseif>
                                    </td>
                                    <td><s:property value="theme" /></td>
                                    <td><pt:usernameShow userId="sender"></pt:usernameShow></td>
                                    <td><s:date name="sendtime" format="yyyy-MM-dd HH:mm:ss" /></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
                <div region="south" height="26">
                    <div class="panelBar">
                        <div class="pages">
                            <span>显示</span>
                            <s:select list="page.pageSizeList" name="entity.pageSize" cssClass="pageSize" rel="mainmsg" form="${_}"/>
                            <span>条，共[<s:property value="page.count" />]条
                            </span>
                        </div>
                        <s:hidden name="entity.pageNumber" id="pageNumber" />
                        <s:hidden name="_"></s:hidden>
                        <div class="pagination" targetType="ajax" rel="mainmsg" totalCount='<s:property value="page.count"/>' numPerPage='<s:property value="page.pageSize"/>'
                            pageNumShown='<s:property value="page.pageNumShown"/>' currentPage='<s:property value="page.pageNumber"/>'></div>
                    </div>
                </div>
            </div>
        </div>
    </s:form>
    <s:url namespace="/msg" action="msg-interact" method="removeTo" encode="false" var="msgRemoveToURL">
        <s:param name="tomsgbox" value="'`~!'"></s:param>
        <s:param name="msgbox" value="'send'"></s:param>
    </s:url>
    <script type="text/javascript">
        function msgRemoveTo(o)
        {
            var t = $(o);
            var v = t.val();
            if (v != "")
            {
                $("#msgRemoveToElem").attr('href', "${msgRemoveToURL}".replace("%60%7E%21", v).replace("&amp;", "&")).click();
            }
        }
    </script>
</body>
</html>