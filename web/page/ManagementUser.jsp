
<%@page import="java.sql.ResultSet"%>
<%@page import="DB.ConnectDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/jsp/LoginCheckVip.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>會員管理</title>
        <link rel="stylesheet" type="text/css" href="/PracticeWeb/css/ManagementUser.css">
    </head>
    <body>
        <h3 align="center">會員管理</h3>
        <% ResultSet rs = new ConnectDB().getAllUserData("user_email like \"%\" and user_delete = \"N\""); %>
        <% int no = 1;%>
        <div style="text-align: right;position: fixed;right:3vh;top:3vh;">
            <button type="button" onclick="window.location.href='/PracticeWeb/page/index.jsp';">返回</button>
        </div>
        <br/>
        <table>
            <tr>
                <th>No</th>
                <th>Email</th>
                <th>Name</th>
                <th>Password</th>
                <th>Birth</th>
                <th>Sex</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Level</th>
                <th>Setting</th>
            </tr>
            <%while (rs.next()) {%>
            <tr>
                <td><%= no%></td>
                <td><%= rs.getString("user_email")%></td>
                <td><%= rs.getString("user_name")%></td>
                <td><%= rs.getString("user_password")%></td>
                <td><%= rs.getString("user_birth")%></td>
                <td><%= rs.getString("user_sex")%></td>
                <td><%= rs.getString("user_address")%></td>
                <td><%= rs.getString("user_phone")%></td>
                <td><%= rs.getString("user_level")%></td>
                <td>
                    <button onclick="updateBotton(this.value)" value="<%= rs.getString("user_email")%>">修改</button>
                    <button onclick="deleteUser(this.value)" value="<%= rs.getString("user_email")%>">刪除</button>
                </td>
            </tr>
            <% no++;
                }%>
        </table>

        <script type="text/javascript" src="/PracticeWeb/js/ManagementUser.js"></script>
    </body>
</html>
