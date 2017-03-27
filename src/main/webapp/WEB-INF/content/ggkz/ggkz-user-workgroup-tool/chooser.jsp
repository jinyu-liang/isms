<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>选择员工信息</title>
</head>
<body>
    <div class="tabs" currentIndex="0">
        <div class="tabsHeader">
            <div class="tabsHeaderContent">
                <ul>
                    <li class="selected"><s:a namespace="/ggkz" action="ggkz-choose-user"
                            method="publicSingleChooseUser" cssClass="j-ajax">
                            <s:param name="callbackId" value="%{callbackId}"></s:param>
                            <s:param name="checkid" value="%{checkid}"></s:param>
                            <s:param name="checkname" value="%{checkname}"></s:param>
                            <s:param name="toolTpid" value="%{toolTpid}"></s:param>
                            <span>人员选择</span>
                        </s:a>
                    </li>
                    <li><s:a namespace="/stbd" action="stbd-work-term"
                            method="publicWorkTermChooser" cssClass="j-ajax">
                            <s:param name="callbackId" value="%{callbackId}"></s:param>
                            <s:param name="checkid" value="%{checkid}"></s:param>
                            <s:param name="checkname" value="%{checkname}"></s:param>
                            <s:param name="toolTpid" value="%{toolTpid}"></s:param>
                            <%-- <s:param name="checkTpid" value="%{checkTpid}"></s:param> --%>
                            <s:param name="queryEntity.classTypeId" value="%{checkTp}"></s:param>
                            <span class="home_icon">班次选择</span>
                        </s:a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="tabsContent" style="height: 150px;">
            <div></div>
            <div></div>
        </div>
        <div class="tabsFooter">
            <div class="tabsFooterContent"></div>
        </div>
    </div>
</body>
</html>
