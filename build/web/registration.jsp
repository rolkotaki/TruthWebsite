<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./style/style.css">
		<title>Truth website</title>
		<script type="text/javascript" src="./script/javascript/user_validation.js" ></script>
	</head>

	<body>
		
		<div class="body_content">
		
			<div class="head">
				<div class="logo_div">
					<p class="date">
						<script language="JavaScript">
						<!--	
						var date = new Date();
						//document.write(date);
						document.write(("0" + date.getDate()).slice(-2) + "." + ("0"+(date.getMonth()+1)).slice(-2) + "." + date.getFullYear())
						//-->
						</script>
					</p>
					<p class="logo">(T)ruth</p>
				</div>
			
				<div class="menu_div">
					<table>
						<tr>
							<td class="menu_item"><a href="">All</a></td>
							<td class="menu_item"><a href="">Hungary</a></td>
							<td class="menu_item"><a href="">World</a></td>
							<td class="menu_item"><a href="">UFO</a></td>
							<td class="menu_item"><a href="">Interesting</a></td>
							<td class="menu_item"><a href="">Health</a></td>
							<td class="menu_item"><a href="">Video</a></td>
						</tr>
					</table>
				</div>
			</div>
			
			<div class="body_main_div">
			
				<div class="registration_div">
				
					<form action="Registrate" method="POST" name="user_data" onsubmit="return validateUserData()">
						<fieldset>
							<legend>Registration data:</legend>	
							<table>
								<tr>
									<td>Username:</td>
                                    <td><input type="text" name="username" maxlength="25" onfocusout="checkUser()">
                                        <p style="color: #FF0000" id="user_message"></p></td>
								</tr>
								<tr>
									<td>Email ID:</td>
									<td><input type="email" name="email_id" maxlength="100"></td>
								</tr>
								<tr>
									<td>Gender:</td>
									<td>
										<input type="radio" name="gender" value="Male"> Male
										<input type="radio" name="gender" value="Female"> Female
									</td>
								</tr>
								<tr>
									<td>Year of birth:</td>
									<td><input type="number" name="birth_year" min="1900" max="2200"></td>
								</tr>
								<tr>
									<td>Password:</td>
									<td><input type="password" name="pwd" maxlength="300"></td>
								</tr>
								<tr>
									<td>Password again:</td>
									<td><input type="password" name="password_again" maxlength="300"></td>
								</tr>
								<tr>
									<td colspan="2" style="text-align: center"><input type="submit" name="registration_button" value="Registration"></td>
								</tr>
							</table>
						</fieldset>
					</form>
					
					<div id="error_messages" style="color: red; font-weight: bold;">
					
					</div>
                                    
                                        <div>
                                            <% if (request.getParameter("result") != null && request.getParameter("result").equals("success")) { %>
                                                <p style="font-weight: bold; color:#008000;">Registration successful!</p>
                                                <a style="font-weight: bold; color:#0000FF;" href="login.jsp">Go to login</a>
                                            <% } else if (request.getParameter("result") != null && request.getParameter("result").equals("error")) { %>
                                                <p style="font-weight: bold; color:#FF0000;">The user could not be saved successfully. Please try again your registration.</p>
                                            <% } %>
                                        </div>

					
				</div>
			
			</div>
			
			<div class="body_content footer" style="position:fixed; bottom:0">
				<footer>© All rights reserved, 2016 | <a href="" style="color:black">Contact</a> | <a href="" style="color:black">About us</a></footer>
			</div>
			
		</div>
		
	</body>

</html>