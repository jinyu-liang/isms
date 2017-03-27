<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@ taglib prefix="jms" tagdir="/WEB-INF/tags/jms" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Browse <form:short text="${JMSDestination}" /></title>
</head>
<body>
    <h2>
        Browse <form:tooltip text="${JMSDestination}" />
    </h2>
    <table class="sortable autostripe">
        <thead>
            <tr>
                <th>Message ID</th>
                <th>Correlation ID</th>
                <th>Persistence</th>
                <th>Priority</th>
                <th>Redelivered</th>
                <th>Reply To</th>
                <th>Timestamp</th>
                <th>Type</th>
                <th>Operations</th>
            </tr>
        </thead>
        <tbody>
            <jms:forEachMessage queueBrowser="${messages}" var="row">
                <tr>
                    <td>
                        <s:a namespace="mqconsole" action="create-destination" method="message" title="properties">
                            <s:param name="JMSMessageID" value="JMSMessageID"></s:param>
                            <s:param name="JMSDestination" value="JMSDestination"></s:param>
                            ${row.JMSMessageID}
                        </s:a>
                    </td>
                    <td><c:out value="${row.JMSCorrelationID}" /></td>
                    <td><jms:persistent message="${row}" /></td>
                    <td><c:out value="${row.JMSPriority}" /></td>
                    <td><c:out value="${row.JMSRedelivered}" /></td>
                    <td><c:out value="${row.JMSReplyTo}" /></td>
                    <td><jms:formatTimestamp timestamp="${row.JMSTimestamp}" /></td>
                    <td><c:out value="${row.JMSType}" /></td>
                    <td>
                        <s:a namespace="mqconsole" accesskey="delete-message.action">
                            <s:param name="JMSDestination" value="JMSDestination"></s:param>
                            <s:param name="JMSMessageID" value="JMSMessageID"></s:param>
                            <s:param name="secret" value="#session.secret"></s:param>
                            Delete
                        </s:a>
                    </td>
                </tr>
            </jms:forEachMessage>
        </tbody>
    </table>

    <div>
        <s:a namespace="mqconsole" action="" method="queueConsumers">
            <s:param name="JMSDestination" value="JMSDestination"></s:param>
            View Consumers
        </s:a>
    </div>
</body>
</html>

