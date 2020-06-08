<%-- 
    Document   : index
    Created on : 2020/6/3, 下午 06:50:40
    Author     : Jun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World2!</h1>
        <h1>userName = <%=session.getAttribute("userName")%></h1>
        <h1>userEmail = <%=session.getAttribute("userEmail")%></h1>
        <h1>userLevel = <%=session.getAttribute("userLevel")%></h1>
        <h1><%= request.getParameter("inputName")%></h1>
    </body>
</html>
