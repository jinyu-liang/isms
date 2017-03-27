<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>编辑dexproject</title>
</head>
<body>
    <div class="page unitBox" style="height: 100%;">
        <s:property value="message" escapeHtml="false" />
        <div class="pageContent" style="height: 100%;">
            <s:form method="post" action="m-workshop!workDeal" cssClass="pageForm"
                onsubmit="return validateCallback(this,$.pdialog.closeCurrentRefresh);" cssStyle="height: 100%;width:100%;">
                <div style="width: 100%;" layoutH="38">
                    <div class="pageFormContent" layoutH="62" style="float: left; width: 470px;">
                        <s:hidden name="queryEntity.wsCd" value="%{projectEntity.wsCd}" />
                        <s:hidden name="entity.wsCd" value="%{projectEntity.wsCd}" />
                        <s:hidden name="projectEntity.projectId" value="%{projectEntity.projectId}" />
                        <s:include value="_workDeal.jsp" />
                    </div>
                    <div class="pageFormContent"  style="float: right; width: 495px;" layoutH="62">
                        <s:include value="_workDeal_equipment.jsp" />
                    </div>
                </div>
                <div class="formBar">
                    <ul>
                        <li>
                            <div class="buttonActive">
                                <div class="buttonContent">
                                    <s:submit type="button" onclick="return checkItem()" value="确定" />
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
    <script>
    //检查产品填写
    function checkItem(){
    	if(!$("tr").hasClass("unitBox")){
    		alertMsg.warn("请添加产品!");
    		return false;
    	}
    }
    </script>
</body>
</html>
