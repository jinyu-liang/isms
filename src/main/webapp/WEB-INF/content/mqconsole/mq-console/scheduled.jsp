<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form" %>
<%@ taglib prefix="jms" tagdir="/WEB-INF/tags/jms" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Messages Scheduled for Future Delivery</title>
</head>
<body>
    <c:choose>
        <c:when test="${requestContext.brokerQuery.jobSchedulerStarted}">
            <div style="margin-top: 5em">
                <table id="Jobs" class="sortable autostripe">
                    <thead>
                        <tr>
                            <th>Job ID</th>
                            <th>Cron Entry</th>
                            <th>next scheduled time</th>
                            <th>start</th>
                            <th>delay</th>
                            <th>period</th>
                            <th>repeat</th>
                            <th>Operations</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestContext.brokerQuery.scheduledJobs}" var="row">
                            <tr>
                                <td>${row.jobId}</td>
                                <td>${row.cronEntry}</td>
                                <td>${row.nextExecutionTime}</td>
                                <td>${row.start}</td>
                                <td>${row.delay}</td>
                                <td>${row.period}</td>
                                <td>${row.repeat}</td>
                                <td><a href="deleteJob.action?jobId=${row.jobId}&secret=<c:out value='${sessionScope["secret"]}'/>">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
        </c:when>
        <c:otherwise>
            <div style="margin-top: 5em">
                <p align="center">Scheduler not started!</p>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>

