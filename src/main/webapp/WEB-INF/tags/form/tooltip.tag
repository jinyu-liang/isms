<%@ attribute name="text" type="java.lang.String" required="true"%>
<%@ attribute name="length" type="java.lang.Integer" required="false"%>
<%
    text = text.replaceAll("<", "&lt;");
    text = text.replaceAll(">", "&gt;");
    if (length == null)
        length = 23;
    if (text.length() <= length)
    {
        out.print(text);
    }
    else
    {
        out.println("<span class=\"tooltip\"> " + text.substring(0, length - 3) + "... <span>" + text + "</span></span>");
    }
%>