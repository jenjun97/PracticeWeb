function toLogin() {
    var xmlhttp = new XMLHttpRequest();
    var inputEmail = document.getElementById('inputEmail').value.toString();
    var inputPassword = document.getElementById('inputPassword').value.toString();
    var url = "loginServlet.html?inputEmail=" + inputEmail + "&inputPassword=" + inputPassword;
    xmlhttp.open("GET", url);
    xmlhttp.send();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState != 4) {
            document.getElementById('warningMessage').innerHTML = "帳號驗證中，請稍候";
        } else {
            var getStr = eval(xmlhttp.responseText);
            if (getStr === undefined) {
                document.getElementById('warningMessage').innerHTML = "帳號錯誤";
            } else if (!getStr.password) {
                document.getElementById('warningMessage').innerHTML = "密碼錯誤";
            } else {
                window.location.href = "page/index.jsp";
            }
        }
    }
}

function register() {
    window.location.href = "register.html";
}