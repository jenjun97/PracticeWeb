<%-- 
    Document   : index
    Created on : 2020/6/3, 下午 06:50:40
    Author     : Jun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/jsp/LoginCheck.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="../css/index.css">
    </head>
    <body>
        <div class="MenuBar">
            你好:<%= session.getAttribute("userName")%>
            <% if (userLevel.equals("vip")) { %>
            <button onclick="ManagementUser()">會員管理</button>
            <% }%>
            <button onclick="changeUserData()">個人資料</button>
            <button onclick="CleanSession()">登出</button>
        </div>

        <br/>
    </body>
    <script type="text/javascript" src="/PracticeWeb/js/index.js"></script>
    <script type="text/javascript" >
                function CleanSession() {
                    window.location.href = "/PracticeWeb/logoutServlet.html";
                }

                function changeUserData() {
                    window.location.href = "/PracticeWeb/getUserDataServlet.html";
                }
        <% if (userLevel.equals("vip")) { %>
                function ManagementUser() {
                    window.location.href = "/PracticeWeb/page/ManagementUser.jsp";
                }
        <% }%>
    </script>
</html>
