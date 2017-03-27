<%@ attribute name="name" type="java.lang.String" required="true"%>
<%@ attribute name="defaultValue" type="java.lang.String" required="false"%>
<%
    String value = request.getParameter(name);
    if (value == null || value.trim().length() == 0)
    {
        value = defaultValue;
    }
    if (value == null)
    {
        value = "";
    }
    value = org.apache.commons.lang.StringEscapeUtils.escapeHtml(value);
%>
<input type="text" name="${name}" value="<%= value %>" />
