<%-- 
    Document   : LoginCheck
    Created on : 2020/6/9, ?? 11:43:38
    Author     : Jun
--%>

<%@page import="com.mysql.cj.Session"%>
<%!
    String userName = "";
    String userEmail = "";
    String userLevel = "";
%>

<%
    try {
        userName = session.getAttribute("userName").toString();
        userEmail = session.getAttribute("userEmail").toString();
        userLevel = session.getAttribute("userLevel").toString();

        if (!userLevel.equals("vip")) {
            response.sendRedirect("/PracticeWeb/login.html");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        response.sendRedirect("/PracticeWeb/login.html");
    }

%>
