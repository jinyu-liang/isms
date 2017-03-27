<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息互动</title>
</head>
<body>
    <div class="pageContent">
        <div class="pageHeader" defH="40">
            <div class="searchBar">
                <div class="searchBar" style="height: 26px;">
                    <label><s:a namespace="/msg" action="msg-interact" method="toSetup" encode="false" cssClass="delete" target="ajax" rel="mainmsg">
                        <span>设置</span>
                    </s:a></label>
                    <span style="float:right;">
                    <s:form namespace="/msg" action="msg-interact!toSearch.action" method="post" id="%{_}search">
                    <s:textfield name="entity.theme"></s:textfield>
                    <s:hidden name="_"></s:hidden>
                    <s:a href="javascript:;" onclick="divSearch('%{_}search','mainmsg')">
                        <span>查询</span>
                    </s:a>
                    </s:form>
                    </span>
                </div>
            </div>
        </div>
        <div layoutH="38"
            style="float: left; display: block; overflow: auto; width: 160px; border-width: 1px 1px 0 0; border-style: solid; border-color: #B8D0D6; line-height: 21px;">
            <div class="unitBox accordion" style="height: 46px; border-bottom: 1px #B8D0D6 solid;">
                <div class="accordionHeaderpiano">
                    <s:a namespace="/msg" action="msg-interact" method="toAdd" target="ajax" rel="mainmsg">
                        <h2>写信</h2>
                    </s:a>
                </div>
                <div class="accordionHeaderpiano">
                    <s:a namespace="/msg" action="msg-interact" method="toReceived" target="ajax" rel="mainmsg">
                        <h2>收信</h2>
                    </s:a>
                </div>
            </div>
            <div class="unitBox accordion" layoutH="93" style="border-top: 1px #B8D0D6 solid; margin-top: 5px; background-color: #fff;">
                <div class="accordionHeaderpiano">
                    <s:a namespace="/msg" action="msg-interact" method="toReceived" target="ajax" rel="mainmsg">
                        <h2>收件箱</h2>
                    </s:a>
                </div>
                <div class="accordionHeaderpiano">
                    <s:a namespace="/msg" action="msg-interact" method="toDraft" target="ajax" rel="mainmsg">
                        <h2>草稿箱</h2>
                    </s:a>
                </div>
                <div class="accordionHeaderpiano">
                    <s:a namespace="/msg" action="msg-interact" method="toSend" target="ajax" rel="mainmsg">
                        <h2>发件箱</h2>
                    </s:a>
                </div>
                <div class="accordionHeaderpiano">
                    <h2>
                        <s:a namespace="/msg" action="msg-interact" method="toGarbage" target="ajax" rel="mainmsg">垃圾箱</s:a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <s:a namespace="/msg" action="msg-interact" method="cleanGarbage" target="ajax" rel="mainmsg">清空</s:a>
                    </h2>
                </div>
                <s:if test="post!=null&&post==1">
                <div class="accordionHeaderpiano">
                    <s:a namespace="/msg" action="msg-interact" method="toOther" target="ajax" rel="mainmsg">
                        <h2>其他消息</h2>
                    </s:a>
                </div>
                </s:if>
            </div>
        </div>
        <div id="mainmsg" layoutH="38" class="unitBox"
            style="margin-left: 165px; border-width: 1px 0 0 1px; border-style: solid; border-color: #B8D0D6; background-color: #fff;">
            <s:action name="msg-interact!toReceived" executeResult="true" ignoreContextParams="false" flush="true" namespace="/msg"></s:action>
        </div>
    </div>
</body>
</html>