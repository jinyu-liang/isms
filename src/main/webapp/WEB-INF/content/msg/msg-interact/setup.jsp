<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.apache.http.protocol.RequestContent"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>消息设置</title>
</head>
<body>
    <div class="page" style="width: 100%; height: 100%;">
        <div class="pageFormContent" style="width: 100%; height: 100%;">
            <div class="unit">
                <label style="font-weight: bold;">消息提醒</label> <span class="labelnarrow"> <s:radio id="msgsettingelem" list="#{'0':'不提醒','1':'自动提醒'}" name="msgInteractRemind" onchange="msgInteractRemindf(this)"></s:radio>
                </span>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function msgInteractRemindf(o)
        {
            var t = $(o);
            msgInteractRemind=t.val();
        }
        $("#msgsettingelem1").ready( function() {
            if(msgInteractRemind=="1")
            {
                $("#msgsettingelem1").attr("checked",true);
            }
            else
            {
                $("#msgsettingelem0").attr("checked",true);
            }
        });
    </script>
</body>
</html>
