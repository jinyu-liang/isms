<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form" %>
<%@ taglib prefix="jms" tagdir="/WEB-INF/tags/jms" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Network Bridges</title>
</head>
<body>
    <div style="margin-top: 5em">
        <h2>Network Bridges</h2>
        <table id="bridges" class="sortable autostripe">
            <thead>
                <tr>
                    <th>Remote Broker</th>
                    <th>Remote Address</th>
                    <th>Created By Duplex</th>
                    <th>Messages Enqueued</th>
                    <th>Messages Dequeued</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestContext.brokerQuery.networkBridges}" var="nb">
                    <tr>
                        <td>${nb.remoteBrokerName}</td>
                        <td>${nb.remoteAddress}</td>
                        <td>${nb.createdByDuplex}</td>
                        <td>${nb.enqueueCounter}</td>
                        <td>${nb.dequeueCounter}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>

