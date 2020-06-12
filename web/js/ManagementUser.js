function updateBotton(userEmailValue) {
    window.location.href = "/PracticeWeb/ManagementGetUserDataServlet.html?changeUserEmail=" + userEmailValue;
}

function deleteUser(deleteUserEmail) {
    var yes = confirm('確定該筆資料嗎？');
    if (yes) {
        window.location.href = "/PracticeWeb/deleteUserEmailServlet.html?deleteUserEmail=" + deleteUserEmail;
    }
}