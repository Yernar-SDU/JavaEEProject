<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.04.2021
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Refresh</title>
</head>
<body>
    <%
        String second = (String)request.getAttribute("second");
        String minute = (String)request.getAttribute("minute");
    %>
    <p><%=minute%></p>
    <p><%=second%></p>
</body>
</html>
