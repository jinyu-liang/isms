<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Queues</title>
</head>
<body>
    <div>
        <s:form action="mqconsole/destination!create.action" target="ajax" real="mqconsole/mq-console.action" method="post">
            <s:hidden name="JMSDestinationType" value="queue"></s:hidden>
            <s:hidden name="secret" value="#session.secret"></s:hidden>
            <label>Queue Name</label>
            <s:textfield name="JMSDestination"></s:textfield>
            <s:submit value="Create"></s:submit>
        </s:form>
    </div>
    <h2>Queues</h2>
    <table class="sortable autostripe">
        <thead>
            <tr>
                <th>Name</th>
                <th>Number Of Pending Messages</th>
                <th>Number Of Consumers</th>
                <th>Messages Enqueued</th>
                <th>Messages Dequeued</th>
                <th>Views</th>
                <th>Operations</th>
            </tr>
        </thead>
        <tbody>
        <s:iterator value="queueList" var="row">
            <tr>
                <td>
                    <s:a namespace="mqconsole" action="message" method="browse" target="ajax" real="mqconsole/mq-console.action">
                        <s:param name="JMSDestination" value="name"></s:param>
                        <s:param name="JMSDestinationType" value="queue"></s:param>
                        <form:tooltip text="${row.name}" length="50"/>
                    </s:a>
                </td>
                <td>${row.queueSize}</td>
                <td>${row.consumerCount}</td>
                <td>${row.enqueueCount}</td>
                <td>${row.dequeueCount}</td>
                <td>
                    <s:a namespace="mqconsole" action="destination" method="browse">
                        <s:param name="JMSDestination" value="name"></s:param>
                        <s:param name="JMSDestinationType" value="queue"></s:param>
                        Browse
                    </s:a>
                    <s:a namespace="mqconsole" action="destination" method="queueConsumers">
                        <s:param name="JMSDestination" value="name"></s:param>
                        <s:param name="JMSDestinationType" value="queue"></s:param>
                        Active Consumers
                    </s:a>
                    <br />
                    <s:a namespace="mqconsole" action="queue-browse" method="view" title="Atom 1.0">
                        <s:param name="JMSDestination" value="name"></s:param>
                        <s:param name="JMSDestinationType" value="queue"></s:param>
                        <s:param name="view" value="rss"></s:param>
                        <s:param name="feedType" value="atom_1.0"></s:param>
                        <s:url var="_url" value="/mqconsole/images/feed_atom.png"></s:url>
                        <img src="${_url}"/>
                    </s:a>
                    <s:a namespace="mqconsole" action="queue-browse" method="view" title="Atom 1.0">
                        <s:param name="JMSDestination" value="name"></s:param>
                        <s:param name="JMSDestinationType" value="queue"></s:param>
                        <s:param name="view" value="rss"></s:param>
                        <s:param name="feedType" value="RSS 2.0"></s:param>
                        <s:url var="_url" value="/mqconsole/images/feed_rss.png"></s:url>
                        <img src="${_url}"/>
                    </s:a>
                </td>
                <td>
                    <s:a namespace="mqconsole" action="mq-console" method="send">
                        <s:param name="JMSDestination" value="name"></s:param>
                        <s:param name="JMSDestinationType" value="queue"></s:param>
                        Send To
                    </s:a>
                    <s:a namespace="mqconsole" action="destination" method="purge">
                        <s:param name="JMSDestination" value="name"></s:param>
                        <s:param name="JMSDestinationType" value="queue"></s:param>
                        <s:param name="secret" value="#session.secret">
                        </s:param>
                        Purge
                    </s:a>
                    <s:a namespace="mqconsole" action="destination" method="delete">
                        <s:param name="JMSDestination" value="name"></s:param>
                        <s:param name="JMSDestinationType" value="queue"></s:param>
                        <s:param name="secret" value="#session.secret">
                        </s:param>
                        Delete
                    </s:a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</body>
</html>

