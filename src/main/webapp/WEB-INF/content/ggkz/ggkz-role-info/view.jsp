<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看角色</title>
</head>
<body>
    <div class="page">
        <s:property value="message" escapeHtml="false" />
        <div class="pageContent">
            <s:form method="post" action="ggkz-role-info!toEdit.action" cssClass="pageForm"
                onsubmit="return navTabSearch(this);">
                <s:hidden name="entity.roleId" value="%{entity.roleId}" />
                <table class="list" width="100%">
                    <tr>
                        <td width="13%">角色名称</td>
                        <td><span><s:property value="entity.roleName" />
                        </span>
                        </td>
                    </tr>
                    <tr>
                        <td>拥有的Web权限</td>
                        <td><span> <s:iterator value="ggkzAuthInfoListChecked" id="ggkzAuthInfo" status='st'>
                                    <s:property value="#ggkzAuthInfo.authName" />&nbsp;&nbsp;
							</s:iterator> </span>
                        </td>
                    </tr>
                    <tr>
                        <td>拥有的终端权限</td>
                        <td><span> <s:iterator value="ggkzAuthMobileInfoListChecked" id="ggkzAuthMobileInfo" status='st'>
                                    <s:property value="#ggkzAuthMobileInfo.authName" />&nbsp;&nbsp;
							</s:iterator> </span>
                        </td>
                    </tr>
                    <tr>
                        <td>操作时间</td>
                        <td><span><s:date name="entity.operTime" format="yyyy-MM-dd" />
                        </span>
                        </td>
                    </tr>
                    <tr>
                        <td>操作用户</td>
                        <td><span><pt:usernameShow userId="entity.operUserId"></pt:usernameShow>
                        </span>
                        </td>
                    </tr>
                    <tr>
                        <td>备注</td>
                        <td><span><s:property value="entity.note" />
                        </span>
                        </td>
                    </tr>
                </table>
                <div class="formBar">
                    <ul>
                        <li>
                            <div class="button">
                                <div class="buttonContent">
                                    <button type="button" onclick="$.pdialog.closeCurrent();">关闭</button>
                                </div>
                            </div></li>
                    </ul>
                </div>

            </s:form>
        </div>
    </div>
</body>
</html>
