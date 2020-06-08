// 匯入城市選項
setCity(document.getElementById('inputCity'));
// 依選擇的城市匯入地區選項
setArea(document.getElementById('inputCity').value, document.getElementById('inputArea'));
// 城市選項改變後，地區隨之選擇改變
function optCityChange(cityName) {
    setArea(cityName, document.getElementById('inputArea'));
}

// submit前確認各欄位之檢核
function check() {
    var reBoolean = true;
    var requiredValue = {};
    requiredValue.inputName = document.getElementById('inputName').value;
    requiredValue.inputEmail = document.getElementById('inputEmail').value;
    requiredValue.inputPassword = document.getElementById('inputPassword').value;
    requiredValue.inputPasswordAgain = document.getElementById('inputPasswordAgain').value;
    if (requiredValue.inputPassword != requiredValue.inputPasswordAgain) {
        reBoolean = false;
        alert("密碼不正確，請檢查")
        var inputPasswordAgain = document.getElementById('inputPasswordAgain');
        inputPasswordAgain.select();
    } else if (EmailCheckBeforeSubmit() != "") {
        reBoolean = false;
        responseEmail(inputEmail, document.getElementById('EmailMessage'), document.getElementById('inputEmail'));
    }

    return reBoolean;
}

// 檢查email是否已被註冊
function checkEmail(inputEmailElmt) {
    var inputEmail = inputEmailElmt.value;
    var EmailMessageElmt = document.getElementById('EmailMessage');
    
    // 如果email輸入值不為空
    if (inputEmail != "") {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("GET", "checkEmailServlet.html?inputEmail=" + inputEmail);
        xmlhttp.send();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4) {
                reStr = xmlhttp.responseText;
                console.log(reStr);
                responseEmail(reStr, EmailMessageElmt, inputEmailElmt);
            }
        }
    } else {
        responseEmail(inputEmail, EmailMessageElmt);
    }
}

// 顯示email是否已被註冊訊息
function responseEmail(reStr, EmailMessageElmt, inputEmailElmt) {
// 未被註冊
    if (reStr == "") {
        EmailMessageElmt.style.display = 'none';
    } else { // 已被註冊
        EmailMessageElmt.style.display = 'block';
        EmailMessageElmt.innerHTML = "此Email已被註冊";
        inputEmailElmt.select();
    }
}

// 表單送出前的檢查
function EmailCheckBeforeSubmit() {
    var inputEmail = document.getElementById('inputEmail').value;
    var EmailMessageElmt = document.getElementById('EmailMessage');
    var xmlhttp = new XMLHttpRequest();

    xmlhttp.open("GET", ("checkEmailServlet.html?inputEmail=" + inputEmail), false);
    xmlhttp.send();

    return xmlhttp.responseText;
}