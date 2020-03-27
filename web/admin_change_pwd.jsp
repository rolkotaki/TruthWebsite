<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./style/style.css">
                <script type="text/javascript" src="./script/javascript/admin_pwd_validation.js" ></script>
		<title>Truth website</title>
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
					<p class="logo">(T)ruth Admin</p>
				</div>
			
				<div class="menu_div">
					<table>
						<tr>
							<td class="menu_item"><a href="admin_categories.jsp">Categories</a></td>
							<td class="menu_item"><a href="admin_articles.jsp">Articles</a></td>
							<td class="menu_item"><a href="admin_images.jsp">Images</a></td>
							<td class="menu_item"><a href="admin_sql.jsp">SQL</a></td>
							<td class="menu_item"><a href="admin_change_pwd.jsp">Change password</a></td>
						</tr>
					</table>
				</div>
			</div>
			
			<div class="body_main_div">
			
				<div class="admin_change_pwd_div">
				
                                        <form action="AdminChangePassword" name="pwd_form" method="POST" onsubmit="return checkAdminPassword()">
						<fieldset>
							<legend>Admin password change:</legend>	
							<table>
								<tr>
									<td>Old password:</td>
									<td><input type="password" name="old"></td>
								</tr>
								<tr>
									<td>New password:</td>
									<td><input type="password" name="new"></td>
								</tr>
								<tr>
									<td>New password again:</td>
									<td><input type="password" name="new_again"></td>
								</tr>
								<tr>
									<td colspan="2" style="text-align: center"><input type="submit" name="admin_change_pwd_button" value="Change password"></td>
								</tr>
							</table>
						</fieldset>
					</form>
                    
					<div id="message" style="color: red; font-weight: bold;">
					
					</div>
                                        <div id="server_message">
                                            <% if (request.getParameter("result") != null && request.getParameter("result").equals("success")) { %>
                                                <p style="font-weight: bold; color:#008000;">Password updated successfully!</p>
                                            <% } else if (request.getParameter("result") != null && request.getParameter("result").equals("invalid")) { %>
                                                <p style="font-weight: bold; color:#FF0000;">Incorrect password!</p>
                                            <% } else if (request.getParameter("result") != null && request.getParameter("result").equals("error")) { %>
                                                <p style="font-weight: bold; color:#FF0000;">The password could not be updated successfully. Please try again.</p>
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