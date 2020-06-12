
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
    } 

    return reBoolean;
}
