
function checkAdminPassword() {
    document.getElementById("server_message").innerHTML = "";
    var oldPwd = document.pwd_form.old.value;
    var newPwd = document.pwd_form.new.value;
    var newPwdAgain = document.pwd_form.new_again.value;
    var missingMessage = "";
    var invalidMessage = "";
    
    if (oldPwd == "") {
        missingMessage += "Please provide old password!<br/>";
    }
    if (newPwd == "") {
        missingMessage += "Please provide new password!<br/>";
    }
    if (newPwdAgain == "") {
        missingMessage += "Please confirm new password!<br/>";
    }
    if (newPwd != newPwdAgain) {
        invalidMessage += "The passwords are not the same!<br/>";
    }
    
    if (missingMessage != "" || invalidMessage != "") {
        document.getElementById("message").innerHTML = "<p>" + missingMessage + invalidMessage + "</p>";
        return false;
    }
    return true;
}
