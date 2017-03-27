<%@ attribute name="text" type="java.lang.String" required="true"%>
<%@ attribute name="length" type="java.lang.Integer" required="false"%>
<%
    text = org.apache.commons.lang.StringEscapeUtils.escapeHtml(text);
    text = org.apache.commons.lang.StringEscapeUtils.escapeJavaScript(text);
    if (length == null || length < 20)
        length = 20;
    if (text.length() <= length)
    {
        out.print(text);
    }
    else
    {
        out.println(text.substring(0, (length - 10)) + "..." + text.substring(text.length() - 5));
    }
%>