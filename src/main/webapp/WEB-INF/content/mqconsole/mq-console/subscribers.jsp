<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form" %>
<%@ taglib prefix="jms" tagdir="/WEB-INF/tags/jms" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Durable Topic Subscribers</title>
</head>
<body>
    <form action="createSubscriber.action" method="post">
        <input type="hidden" name="JMSDestinationType" value="topic" /> <input type="hidden" name="secret"
            value="<c:out value='${sessionScope["secret"]}'/>" />
        <table id="createSubscribers" class="sortable autostripe">
            <thead>
                <tr>
                    <th colspan="4">Create Durable Topic Subscribers</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="label"><label name="clientId">Client ID</label></td>
                    <td><input type="text" name="clientId" value="" /></td>
                    <td class="label"><label name="subscriberName">Subscriber Name</label></td>
                    <td><input type="text" name="subscriberName" value="" /></td>
                </tr>
                <tr>
                    <td><label name="JMSDestination">Topic Name</label></td>
                    <td><input type="text" name="JMSDestination" value="" /></td>
                    <td><label name="selector">JMS Selector</label></td>
                    <td><input type="text" name="selector" value="" /></td>
                </tr>
                <tr>
                    <td colspan="4" align="center"><input type="submit" value="Create Durable Topic Subscriber" /></td>
                </tr>
            </tbody>
        </table>
    </form>
    <h2>Active Durable Topic Subscribers</h2>
    <table id="topics" class="sortable autostripe">
        <thead>
            <tr>
                <th>Client ID</th>
                <th>Subscription Name</th>
                <th>Connection ID</th>
                <th>Destination</th>
                <th>Selector</th>
                <th>Pending Queue Size</th>
                <th>Dispatched Queue Size</th>
                <th>Dispatched Counter</th>
                <th>Enqueue Counter</th>
                <th>Dequeue Counter</th>
                <th>Operations</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestContext.brokerQuery.durableTopicSubscribers}" var="row">
                <tr>
                    <td><form:tooltip text="${row.clientId}" length="10" /></td>
                    <td><form:tooltip text="${row.subscriptionName}" length="10" /></td>
                    <td><form:tooltip text="${row.connectionId}" length="10" /></td>
                    <td><form:tooltip text="${row.destinationName}" length="10" /></td>
                    <td><c:out value="${row.selector}" /></td>
                    <td>${row.pendingQueueSize}</td>
                    <td>${row.dispatchedQueueSize}</td>
                    <td>${row.dispatchedCounter}</td>
                    <td>${row.enqueueCounter}</td>
                    <td>${row.dequeueCounter}</td>
                    <td><a
                        href="<c:url value="deleteSubscriber.action">
                    <c:param name="clientId" value="${row.clientId}"/>
                    <c:param name="subscriberName" value="${row.subscriptionName}"/>
                    <c:param name="secret" value='${sessionScope["secret"]}'/></c:url>">Delete</a>
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
    <h2>Offline Durable Topic Subscribers</h2>
    <table id="topics" class="sortable autostripe">
        <thead>
            <tr>
                <th>Client ID</th>
                <th>Subscription Name</th>
                <th>Connection ID</th>
                <th>Destination</th>
                <th>Selector</th>
                <th>Pending Queue Size</th>
                <th>Dispatched Queue Size</th>
                <th>Dispatched Counter</th>
                <th>Enqueue Counter</th>
                <th>Dequeue Counter</th>
                <th>Operations</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestContext.brokerQuery.inactiveDurableTopicSubscribers}" var="row">
                <tr>
                    <td><form:tooltip text="${row.clientId}" length="10" /></td>
                    <td><form:tooltip text="${row.subscriptionName}" length="10" /></td>
                    <td><form:tooltip text="${row.connectionId}" length="10" /></td>
                    <td><form:tooltip text="${row.destinationName}" length="10" /></td>
                    <td><c:out value="${row.selector}" /></td>
                    <td>${row.pendingQueueSize}</td>
                    <td>${row.dispatchedQueueSize}</td>
                    <td>${row.dispatchedCounter}</td>
                    <td>${row.enqueueCounter}</td>
                    <td>${row.dequeueCounter}</td>
                    <td><a
                        href="<c:url value="deleteSubscriber.action">
                    <c:param name="clientId" value="${row.clientId}"/>
                    <c:param name="subscriberName" value="${row.subscriptionName}"/>
                    <c:param name="secret" value='${sessionScope["secret"]}'/></c:url>">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

