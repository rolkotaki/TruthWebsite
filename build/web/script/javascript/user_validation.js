
function validateUserData() {
	var invalidMessage = "";
	var missingMessage = "";	
	var username = document.user_data.username.value;
	var email = document.user_data.email_id.value;
	var gender = document.user_data.gender.value;
	var birthYear = document.user_data.birth_year.value;
	var pwd = document.user_data.pwd.value;
	var pwd_again = document.user_data.password_again.value;
	
	if (username == "") {
		missingMessage += "Username must be filled in!<br/>";
	} else if (username.indexOf("'") != -1 || username.indexOf("\"") != -1 || username.indexOf("?") != -1 || username.indexOf(",") != -1 || username.indexOf(".") != -1 || username.indexOf(";") != -1 || username.indexOf("!") != -1 || username.indexOf("/") != -1 || username.indexOf("\\") != -1 || username.indexOf(" ") != -1) {
		invalidMessage += "Username must not contain the following characters: ' \" ? , . ; ! / \ and space!<br/>";
	}
	
	if (email == "") {
		missingMessage += "Email address must be filled in!<br/>";
	} else if (!validateEmail(email)) {
		invalidMessage += "Email address has invalid format!<br/>";
	}
	
	if (gender == "") {
		missingMessage += "Please select a gender!<br/>";
	}
	
	if (birthYear == "") {
		missingMessage += "Birth year must be filled in!<br/>";
	}
	
	if (pwd == "") {
		missingMessage += "Password must be filled in!<br/>";
	}
	
	if (pwd_again == "") {
		missingMessage += "Please confirm your password!<br/>";
	}
	
	if (pwd != pwd_again) {
		invalidMessage += "The passwords are not the same!<br/>";
	}
	
	if (missingMessage != "" || invalidMessage != "") {
		document.getElementById("error_messages").innerHTML = "<p>" + missingMessage + invalidMessage + "</p>";
		return false;
	}
	
	return true;
}

function validateEmail(email) {
	var email_regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return email_regex.test(email);
}

var request;
function checkUser() {
        document.getElementById("user_message").innerHTML = "";
	var userName = document.user_data.username.value;
	if (userName != "") {
		try {
			request = new XMLHttpRequest();
		} catch (e) {
			try {
				request = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					request = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert("Your browser broke!");
					return false;
				}
			}
		}
		request.onreadystatechange = getAjaxResponse;
		request.open("GET", "CheckUser?username=" + userName, true);
		request.send();
	}
}

function getAjaxResponse() {
	if (request.readyState == 4 && request.status == 200) {
		if (request.responseText != null && request.responseText != "") {
			var jsonObj = JSON.parse(request.responseText);
                        if (jsonObj.message != "") {
                            document.user_data.username.style.color = "#000000";
                            document.getElementById("user_message").innerHTML = jsonObj.message;
                            document.user_data.username.focus();
                        } else {
                            document.user_data.username.style.color = "#008000";
                        }
                }
		
	}
}