<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./style/style.css">
		<title>Truth website</title>
                <script type="text/javascript" src="./script/javascript/check_login.js" ></script>
	</head>

	<body>
		
		<div class="body_content">
		
			<div class="head">
				<div class="logo_div">
					<p class="date">
						<script language="JavaScript">
						<!--	
						var date = new Date();
						document.write(("0" + date.getDate()).slice(-2) + "." + ("0"+(date.getMonth()+1)).slice(-2) + "." + date.getFullYear());                                                
						//-->
						</script>
					</p>
					<p class="logo">(T)ruth</p>
				</div>
			
				<div class="menu_div">
					<table>
						<tr>
							<td class="menu_item"><a href="">Home</a></td>
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
			
				<div class="admin_login_div">
				
                                    <form action="Login" name="login_form" method="POST" onsubmit="return checkMisssingData()">
						<fieldset>
							<legend>Login:</legend>	
							<table>
								<tr>
									<td>Username:</td>
									<td><input type="text" name="username"></td>
								</tr>
								<tr>
									<td>Password:</td>
									<td><input type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="2" style="text-align: center"><input type="submit" name="admin_login_button" value="Login"></td>
								</tr>
							</table>
						</fieldset>
					</form>
                                    
					<div id="error_messages" style="color: red; font-weight: bold;">
					
					</div>

                                        <div>
                                            <% if (request.getParameter("result") != null && request.getParameter("result").equals("invalid")) { %>
                                                <p style="font-weight: bold; color:#FF0000;">Incorrect username or password!</p>
                                            <% } else if (request.getParameter("result") != null && request.getParameter("result").equals("error")) { %>
                                                <p style="font-weight: bold; color:#FF0000;">The login has failed, please try again.</p>
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