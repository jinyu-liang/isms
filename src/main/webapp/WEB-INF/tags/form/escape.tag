<%@ attribute name="text" type="java.lang.String" required="true"%>
<%
    out.println(java.net.URLEncoder.encode(text, "UTF-8"));
%>