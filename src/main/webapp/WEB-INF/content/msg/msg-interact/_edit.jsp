<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.UUID" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<div class="unit">
    <label>收件人</label>
    <s:hidden name="entity.receiver" id="msgreceiverId"></s:hidden>
    <s:a namespace="/ggkz" action="ggkz-choose-user" method="publicMultiChooseUserByPostFilter" encode="false" cssClass="add" target="dialog" maxable="false" width="850" height="600" minable="false" resizable="false" mask="true">
        <s:param name="callbackId" value="%{_}"></s:param>
        <s:param name="checkid" value="'msgreceiverId'"></s:param>
        <s:param name="checkname" value="'msgreceiverName'"></s:param>
        <s:param name="previewids" value="'%{entity.receiver}'" ></s:param>
        <s:param name="queryEntity.pageSize" value="1000" ></s:param>
        <input type="text" size="160" class="required" id="msgreceiverName" value="${entity.receivername}" readonly="readonly"><span>选择</span>
    </s:a>
</div>
<div class="unit">
    <label>主题</label>
    <s:textfield name="entity.theme" size="160" cssClass="required" ></s:textfield>
</div>
<div class="unit">
    <label>附件</label>
    <div class="anatomy container" style="margin-left:120px;">
        <%String anatomyuuid=UUID.randomUUID().toString().replaceAll("-", "");%>
        <s:hidden name="entity.attachment" id="msginteractattachment"></s:hidden>
        <a class="anatomy action" href="javascript:;"><var>{"button_action":"SWFUpload.BUTTON_ACTION.SELECT_FILES","file_types":"*.jpg;*.png;*.gif;*.xls;*.xlsx;*.doc;*.docx","file_types_description":"*.jpg;*.png;*.gif;*.xls;*.xlsx;*.doc;*.docx","file_upload_limit":"0","custom_settings":{"progressTarget":"<%=anatomyuuid%>","displayTarget":"<%=anatomyuuid%>","fillTarget":"msginteractattachment"}}</var><span>添加附件</span></a>
        <s:if test="forwardTp!=2">
        <s:iterator value="ids" var="id"><s:hidden name="ids" value="%{id}"></s:hidden></s:iterator>
        </s:if>
        <s:hidden name="msgbox"></s:hidden>
        <div id="<%=anatomyuuid%>" class="fsUploadDisplay">
        <s:if test="entity.attachment!=null&&entity.attachment!=''">
            <s:if test="msgbox=='draft'">
                <s:action name="sys-attach!exsitsFiles" executeResult="true" ignoreContextParams="true" flush="true" namespace="/sys">
                    <s:param name="busiId" value="%{entity.userId}"></s:param>
                    <s:param name="attachId" value="%{entity.attachment}"></s:param>
                    <s:param name="fileShowType" value="3"></s:param>
                </s:action>
            </s:if>
            <s:else>
                <s:action name="sys-attach!exsitsFiles" executeResult="true" ignoreContextParams="true" flush="true" namespace="/sys">
                    <s:param name="busiId" value="%{entity.userId}"></s:param>
                    <s:param name="attachId" value="%{entity.attachment}"></s:param>
                    <s:param name="fileShowType" value="2"></s:param>
                </s:action>
            </s:else>
        </s:if>
        <s:elseif test="page.data.size>1">
            <s:iterator value="page.data" var="msgdata">
            <a href="<%=basePath%>msg/msg-interact!viewMsg.action?entity.id=${msgdata.id}&msgbox=${msgbox}&forwardTp=0" target="dialog" width="1000" height="600">${msgdata.theme}</a>
            </s:iterator>
        </s:elseif>
        </div>
    </div>
</div>
<div>
    <div style="float:left; display:block; width:120px;">
    <label>正文</label>
    </div>
    <div class="unitBox" style="margin-left:130px;" layoutH="100" id="ckeditormainbodylabel">
        <s:if test="forwardTp==2">
        <s:textarea name="entity.body" id="ckeditormainbody" cols="157" rows="20"></s:textarea>
        </s:if>
        <s:else>
        <textarea name="entity.body" id="ckeditormainbody" cols="157" rows="20"></textarea>
        ${entity.body}
        </s:else>
    </div>
</div>