<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form" %>
<%@ taglib prefix="jms" tagdir="/WEB-INF/tags/jms" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Topics</title>
</head>
<body>
    <div>
        <form action="createDestination.action" method="post">
            <input type="hidden" name="JMSDestinationType" value="topic" /> <input type="hidden" name="secret"
                value="<c:out value='${sessionScope["secret"]}'/>" /> <label name="destination">Topic Name</label> <input type="text"
                name="JMSDestination" value="" /> <input type="submit" value="Create" />
        </form>
    </div>
    <h2>Topics</h2>
    <table id="topics" class="sortable autostripe">
        <thead>
            <tr>
                <th>Name</th>
                <th>Number Of Consumers</th>
                <th>Messages Enqueued</th>
                <th>Messages Dequeued</th>
                <th>Operations</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestContext.brokerQuery.topics}" var="row">
                <tr>
                    <td><a
                        href="<c:url value="send.jsp">
                        <c:param name="JMSDestination" value="${row.name}" />
                        <c:param name="JMSDestinationType" value="topic"/></c:url>"><form:tooltip
                                text="${row.name}" length="50" /></a></td>
                    <td>${row.consumerCount}</td>
                    <td>${row.enqueueCount}</td>
                    <td>${row.dequeueCount}</td>
                    <td><a
                        href="<c:url value="send.jsp">
                        <c:param name="JMSDestination" value="${row.name}" />
                        <c:param name="JMSDestinationType" value="topic"/></c:url>">Send
                            To</a> <a
                        href="<c:url value="deleteDestination.action">
                   <c:param name="JMSDestination" value="${row.name}" />
                   <c:param name="JMSDestinationType" value="topic"/>
                   <c:param name="secret" value='${sessionScope["secret"]}'/></c:url>">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

