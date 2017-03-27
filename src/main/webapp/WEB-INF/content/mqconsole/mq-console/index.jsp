<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ActiveMQ Console</title>
</head>
<body>
    <h2>Welcome!</h2>
    <p>
        Welcome to the ActiveMQ Console of <b>${requestContext.brokerQuery.brokerName}</b> (${requestContext.brokerQuery.brokerAdmin.brokerId})
    </p>
    <p>
        You can find more information about ActiveMQ on the <a href="http://activemq.apache.org/">Apache ActiveMQ Site</a>
    </p>
    <h2>Broker</h2>
    <table>
        <tr>
            <td>Name</td>
            <td><b>${requestContext.brokerQuery.brokerAdmin.brokerName}</b></td>
        </tr>
        <tr>
            <td>Version</td>
            <td><b>${requestContext.brokerQuery.brokerAdmin.brokerVersion}</b></td>
        </tr>
        <tr>
            <td>ID</td>
            <td><b>${requestContext.brokerQuery.brokerAdmin.brokerId}</b></td>
        </tr>
        <tr>
            <td>Uptime</td>
            <td><b>${requestContext.brokerQuery.brokerAdmin.uptime}</b></td>
        </tr>
        <tr>
            <td>Store percent used</td>
            <td><b>${requestContext.brokerQuery.brokerAdmin.storePercentUsage}</b></td>
        </tr>
        <tr>
            <td>Memory percent used</td>
            <td><b>${requestContext.brokerQuery.brokerAdmin.memoryPercentUsage}</b></td>
        </tr>
        <tr>
            <td>Temp percent used</td>
            <td><b>${requestContext.brokerQuery.brokerAdmin.tempPercentUsage}</b></td>
        </tr>
    </table>
</body>
</html>

