<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/jsp/LoginCheck.jsp" %>
<!DOCTYPE html>
<html>
    <head lang="zh-Hant-TW">
        <title>修改個人資料</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="/PracticeWeb/css/template.css">
        <link rel="stylesheet" type="text/css" href="/PracticeWeb/css/register.css">
        <script type="text/javascript" src="/PracticeWeb/js/changeUserData.js"></script>
        <script src="/PracticeWeb/js/other/CityAndArea.js"></script>
    </head>
    <body>
        <div align="center" style="margin-top: 10vw;font-size:4rem;">修改個人資料</div>
        <form action="/PracticeWeb/ChangeUserDataServlet.html" method="GET" onsubmit="return check()">
            <div  align="center">
                <table style="margin-left: 2vw;">
                    <!---------------------------------------------------------姓名-->
                    <tr>
                        <td><label for="inputName">姓名</label></td>
                        <td><input type="text" id="inputName" name="inputName" required value="<%= session.getAttribute("userName")%>"></td>
                    </tr>
                    <!---------------------------------------------------------email-->
                    <tr>
                        <td><label for="inputEmail">E-mail</label></td>
                        <td>
                            <input type="email" id="inputEmail" name="inputEmail"  required value="<%= session.getAttribute("userEmail")%>" readonly>
                        </td>

                    </tr>
                    <!---------------------------------------------------------密碼-->
                    <tr>
                        <td><label for="inputPassword">設定密碼</label></td>
                        <td><input type="password" id="inputPassword" name="inputPassword" required value="<%= session.getAttribute("userPassword")%>"></td>
                    </tr>
                    <!---------------------------------------------------------密碼再一次-->
                    <tr>
                        <td><label for="inputPasswordAgain">再輸入一次</label></td>
                        <td><input type="password" id="inputPasswordAgain" name="inputPasswordAgain" value="<%= session.getAttribute("userPassword")%>"></td>
                    </tr>
                    <!---------------------------------------------------------性別-->
                    <tr>
                        <td><label for="inputSex">性別</label></td>
                        <td style="text-align:left;">
                            <select id="inputSex" name="inputSex">
                                <option id="inputSexM" value="M">男性</option>
                                <option id="inputSexF" value="F">女性</option>
                                <option id="inputSexO" value="O">其他</option>
                            </select>
                        </td>
                    </tr>
                    <!---------------------------------------------------------出生日-->
                    <tr>
                        <td><label for="inputBirth">出生日</label></td>
                        <td><input type="date" id="inputBirth" name="inputBirth" ></td>
                    </tr>
                    <!---------------------------------------------------------聯絡電話-->
                    <tr>
                        <td><label for="inputPhone">聯絡電話</label></td>
                        <td><input type="tel" id="inputPhone" name="inputPhone" value="<%= session.getAttribute("userPhone")%>"></td>
                    </tr>
                    <!---------------------------------------------------------地址-->
                    <tr>
                        <td><label for="inputAddress">地址</label></td>
                        <td><input type="text" id="inputAddress" name="inputAddress" value="<%= session.getAttribute("userAddress")%>"></td>
                    </tr>

                </table>

                <table align="center">
                    <tr>
                        <td><button type="submit">送出</button></td>
                        <td><button type="button" onclick="window.history.go(-1);">返回</button></td>
                    </tr>
                </table>
            </div>
        </form>

    </body>

    <script type="text/javascript">
        function SexDefault() {
            var sex = '<%= session.getAttribute("userSex").toString()%>';
            console.log("type>>" + typeof sex);
            switch (sex) {
                case 'M':
                    document.getElementById('inputSexM').selected = true;
                    break;
                case 'F':
                    document.getElementById('inputSexF').selected = true;
                    break;
                case 'O':
                    document.getElementById('inputSexO').selected = true;
                    break;
            }
        }
        SexDefault();

        document.getElementById('inputBirth').value = '<%= session.getAttribute("userBirth")%>';
    </script>
</html>
