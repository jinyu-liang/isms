<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form" %>
<%@ taglib prefix="jms" tagdir="/WEB-INF/tags/jms" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Connections</title>
</head>
<body>
    <h2>Connections</h2>
    <c:forEach items="${requestContext.brokerQuery.connectors}" var="connectorName">
        <h3>Connector ${connectorName}</h3>
        <table id="connections" class="sortable autostripe">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Remote Address</th>
                    <th>Active</th>
                    <th>Slow</th>
                </tr>
            </thead>
            <tbody>
                <jms:forEachConnection broker="${requestContext.brokerQuery}" connectorName="${connectorName}" connection="con"
                    connectionName="conName">
                    <tr>
                        <td><a href="connection.jsp?connectionID=${conName}">${conName}</a></td>
                        <td>${con.remoteAddress}</td>
                        <td>${con.active}</td>
                        <td>${con.slow}</td>
                    </tr>
                </jms:forEachConnection>
            </tbody>
        </table>
    </c:forEach>
    <div style="margin-top: 5em">
        <h2>Network Connectors</h2>
        <table id="connections" class="sortable autostripe">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Network TTL</th>
                    <th>Dynamic Only</th>
                    <th>Conduit Subscriptions</th>
                    <th>Bridge Temps</th>
                    <th>Decrease Priorities</th>
                    <th>Dispatch Async</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestContext.brokerQuery.networkConnectors}" var="nc">
                    <tr>
                        <td>${nc.name}</td>
                        <td>${nc.networkTTL}</td>
                        <td>${nc.dynamicOnly}</td>
                        <td>${nc.conduitSubscriptions}</td>
                        <td>${nc.bridgeTempDestinations}</td>
                        <td>${nc.decreaseNetworkConsumerPriority}</td>
                        <td>${nc.dispatchAsync}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>

