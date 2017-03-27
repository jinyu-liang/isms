<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.apache.http.protocol.RequestContent"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看用户信息</title>
</head>
<body>
    <div class="page" style="height: 100%; width: 100%;">
        <div class="layout">
            <s:if test="forwardTp==-1">
            <div region="north" height="30">
                <div class="pageContent">
                    <div class="panelBar" style="height: 30px; border-top: 0; line-height: 30px;">
                        <ul id="msgaddtoolbar" class="toolBar" style="margin-top: 3px;">
                            <li>
                            <s:if test="msgbox=='send'">
                            <s:a namespace="/msg" action="msg-interact" method="toSend" encode="false" cssClass="delete" target="ajax" rel="mainmsg">
                                <span>返回</span>
                            </s:a>
                            </s:if>
                            <s:elseif test="msgbox=='draft'">
                            <s:a namespace="/msg" action="msg-interact" method="toDraft" encode="false" cssClass="delete" target="ajax" rel="mainmsg">
                                <span>返回</span>
                            </s:a>
                            </s:elseif>
                            <s:elseif test="msgbox=='garbage'">
                            <s:a namespace="/msg" action="msg-interact" method="toGarbage" encode="false" cssClass="delete" target="ajax" rel="mainmsg">
                                <span>返回</span>
                            </s:a>
                            </s:elseif>
                            <s:elseif test="msgbox=='receive'">
                            <s:a namespace="/msg" action="msg-interact" method="toReceived" encode="false" cssClass="delete" target="ajax" rel="mainmsg">
                                <span>返回</span>
                            </s:a>
                            </s:elseif>
                            <s:elseif test="msgbox=='search'">
                            <s:a namespace="/msg" action="msg-interact" method="toSearch" encode="false" cssClass="delete" target="ajax" rel="mainmsg">
                                <span>返回</span>
                            </s:a>
                            </s:elseif>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            </s:if>
            <div id="msgeditor" region="center" style="border-top: 1px #B8D0D6 solid;">
                <div class="page" style="width: 100%; height: 100%;">
                    <div class="pageFormContent" style="width: 100%; height: 100%;">
                        <div class="unit">
                            <label style="letter-spacing: 1em;font-weight: bold;">主题</label> <span><b>${entity.theme}</b></span>
                        </div>
                        <div class="unit">
                            <label style="font-weight: bold;">发件人</label> <span><pt:usernameShow userId="entity.sender"></pt:usernameShow></span>
                        </div>
                        <div class="unit">
                            <label style="letter-spacing: 1em;font-weight: bold;">时间</label> <span><s:date name="entity.sendtime" format="yyyy-MM-dd HH:mm" /></span>
                        </div>
                        <div class="unit">
                            <label style="font-weight: bold;"> 收件人</label> <span><s:iterator value="%{entity.receiver.split(',')}" var="rec">[<pt:usernameShow userId="rec"></pt:usernameShow>]</s:iterator></span>
                        </div>
                        <div class="unit">
                            <label style="letter-spacing: 1em;font-weight: bold;">附件</label> <span>
                                <div class="fileUploadArea">
                                    <span> <s:action name="sys-attach!exsitsFiles" executeResult="true" ignoreContextParams="true" flush="true"
                                            namespace="/sys">
                                            <s:param name="busiId" value="%{entity.userId}"></s:param>
                                            <s:param name="attachId" value="%{entity.attachment}"></s:param>
                                            <s:param name="fileShowType" value="2"></s:param>
                                        </s:action>
                                    </span>
                                </div>
                                <s:if test="entity.attachment!=null&&entity.attachment!=''">
                                <s:a namespace="/sys" action="sys-attach" method="packDownload">
                                	<s:param name="busiId" value="%{entity.userId}"></s:param>
                                    <s:param name="attachId" value="%{entity.attachment}"></s:param>
                                	<span>打包下载</span>
                                </s:a>
                                </s:if>
                            </span>
                        </div>
                        <div class="unit">
                            <label style="letter-spacing: 1em;font-weight: bold;">正文</label> <div class="msgbodydivelem" layoutH="240">${entity.body}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
