<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>编辑用户信息</title>
</head>
<body>
    <div class="page">
        <s:property value="message" escapeHtml="false" />
        <div class="pageContent">
            <s:form method="post" action="m-workshop-contact!edit.action" cssClass="pageForm"
                onsubmit="return validateCallback(this,$.pdialog.closeCurrentRefresh);">

                <div class="pageFormContent col1" layoutH="56">
                    <s:if test="sessionUser.userId==entity.fquserid">
                    	<s:include value="_edit.jsp" />
                    </s:if>
                    <s:if test="sessionUser.userId==entity.acceptdepmangerID || sessionUser.userId==entity.leaderID">
                    	<s:include value="_edit_lead.jsp" />
                    </s:if>
                    <s:if test="sessionUser.userId==entity.acceptuserID">
                    	<s:include value="_edit_accept.jsp" />
                    </s:if>
                </div>
                <div class="formBar">
                    <s:hidden id="entity_userId" name="sessionUser.userId" value="%{sessionUser.userId}"></s:hidden>
                    <s:hidden id="entity_ID" name="entity.ID" ></s:hidden>
                    <ul>
                        <li >
                            <div class="buttonActive">
                                <div class="buttonContent">
                                    <s:submit type="button" value="确定" />
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="button">
                                <div class="buttonContent">
                                    <button type="button" onclick="$.pdialog.closeCurrent();">关闭</button>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </s:form>
        </div>
    </div>
</body>
</html>
