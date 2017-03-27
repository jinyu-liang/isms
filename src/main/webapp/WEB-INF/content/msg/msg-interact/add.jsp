<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div class="page" style="height: 100%; width: 100%;">
        <s:form id="%{_}" method="post" namespace="/msg" action="msg-interact!create.action" cssClass="pageForm"
            onsubmit="return validateCallback(this,toSendMsgBox);" cssStyle="width: 100%;height: 100%;overflow:hidden;">
            <s:hidden name="callbackId" value="%{_}"></s:hidden>
            <s:hidden name="entity.sendtime"></s:hidden>
            <div class="layout">
                <div region="north" height="30">
                    <div class="pageContent">
                        <div class="panelBar" style="height: 30px; border-top: 0; line-height: 30px;">
                            <ul id="msgaddtoolbar" class="toolBar" style="margin-top: 3px;">
                                <li><s:a id="create" cssClass="add" href="javascript:void(0);">
                                        <span>发送</span>
                                    </s:a></li>
                                <%-- <li><s:a id="create" cssClass="edit" href="javascript:void(0);">
                                        <span>定时发送</span>
                                    </s:a>
                                </li> --%>
                                <s:if test="%{ids==null || ids.size()==0}">
                                    <li><s:a id="createDraft" cssClass="add" href="javascript:void(0);">
                                            <span>存草稿</span>
                                        </s:a></li>
                                </s:if>
                                <li class="line">line</li>
                                <s:if test="msgbox=='send'">
                                <s:url namespace="/msg" action="msg-interact" method="toSend" encode="false" var="backURL"></s:url>
                                </s:if>
                                <s:elseif test="msgbox=='draft'">
                                <s:url namespace="/msg" action="msg-interact" method="toDraft" encode="false" var="backURL"></s:url>
                                </s:elseif>
                                <s:elseif test="msgbox=='garbage'">
                                <s:url namespace="/msg" action="msg-interact" method="toGarbage" encode="false" var="backURL"></s:url>
                                </s:elseif>
                                <s:elseif test="msgbox=='receive'">
                                <s:url namespace="/msg" action="msg-interact" method="toReceived" encode="false" var="backURL"></s:url>
                                </s:elseif>
                                <s:elseif test="msgbox=='search'">
                                <s:url namespace="/msg" action="msg-interact" method="toReceived" encode="false" var="backURL"></s:url>
                                </s:elseif>
                                <s:if test="msgbox!=null&&msgbox!=''">
                                <li>
                                    <dd class="add" onclick="backreal('mainmsg','${backURL}')"><span>返回</span></dd>
                                </li>
                                </s:if>
                            </ul>
                        </div>
                    </div>
                </div>
                <div id="msgeditor" region="center" style="border-top: 1px #B8D0D6 solid;">
                    <div class="page" style="width: 100%; height: 100%;">
                        <div class="pageFormContent" style="width: 100%; height: 100%;">
                            <s:include value="_edit.jsp" />
                        </div>
                    </div>
                </div>
            </div>
        </s:form>
        <s:url id="actionurl" namespace="/msg" action="msg-interact">
        </s:url>
        <s:form id="%{_}send" method="post" namespace="/msg" action="msg-interact!toSend.action" cssStyle="display: none;">
        <s:hidden name="_"></s:hidden>
        </s:form>
    </div>
    <script type="text/javascript">
                    $("#${_} #ckeditormainbody").ready(function()
                    {
                        CKEDITOR.replace('ckeditormainbody', {
                            height : $('#${_} #ckeditormainbody').height()
                        });
                        $("#${_} #msgaddtoolbar>li>a").click(function()
                        {
                            $("#${_} #ckeditormainbody").val(CKEDITOR.instances.ckeditormainbody.getData());

                            var actionurl = "${actionurl}".replace(".", "!" + $(this).attr("id") + ".");
                            $("#${_}").attr("action", actionurl).submit();
                        });
                    });
                    function toSendMsgBox(json)
                    {
                        if (json.statusCode == DWZ.statusCode.error)
                        {
                            if (json.message && alertMsg)
                                alertMsg.error(json.message);
                        } else if (json.statusCode == DWZ.statusCode.timeout)
                        {
                            if (alertMsg)
                                alertMsg.error(json.message || DWZ.msg("sessionTimout"), {
                                    okCall : DWZ.loadLogin
                                });
                            else
                                DWZ.loadLogin();
                        } else
                        {
                            if (json.warnMessage && alertMsg)
                            {
                                alertMsg.error(json.warnMessage);
                            } else if (json.infoMessage && alertMsg)
                            {
                                alertMsg.info(json.infoMessage);
                            } else if (json.message && alertMsg)
                            {
                                alertMsg.correct(json.message);
                                divSearch("${_}send","mainmsg");
                            }
                        }
                    }
                </script>
</body>
</html>