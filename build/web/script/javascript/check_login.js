
function checkMisssingData() {
    var userName = document.login_form.username.value;
    var pwd = document.login_form.password.value;
    var message = "";
    if (userName == "") {
        message += "Please fill in username!<br/>";
    }
    if (pwd == "") {
        message += "Please fill in password!";
    }
    if (message != "") {
        document.getElementById("error_messages").innerHTML = "<p>" + message + "</p>";
        return false;
    }
    return true;
}