<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
						//document.write(date);
						document.write(("0" + date.getDate()).slice(-2) + "." + ("0"+(date.getMonth()+1)).slice(-2) + "." + date.getFullYear())
						//-->
						</script>
					</p>
					<p class="logo">(T)ruth</p>
				</div>
			
				<div class="menu_div">
                    <jsp:include page="./WEB-INF/menu_categories.jsp"/>
<!--					<table>
						<tr>
							<td class="menu_item"><a href="">Home</a></td>
							<td class="menu_item"><a href="hungary.html">Hungary</a></td>
							<td class="menu_item"><a href="">World</a></td>
							<td class="menu_item"><a href="">UFO</a></td>
							<td class="menu_item"><a href="">Interesting</a></td>
							<td class="menu_item"><a href="">Health</a></td>
							<td class="menu_item"><a href="">Video</a></td>
						</tr>
					</table>-->
				</div>
			</div>
			
			<div class="body_main_div">
			
				<div class="main_content_div">
                    <jsp:include page="./WEB-INF/leading_articles.jsp"/>
				</div>
				
				<div class="right_content_div">
                                    <form action="Login" name="login_form" method="POST" onsubmit="return checkMisssingData()">
						Username: <input type="text" name="username" style="margin-bottom:7px"></br>
						Password: <input type="password" name="password"></br>
						<input type="submit" name="login_button" value="Login" style="margin-top:7px; margin-bottom:7px"></br>
					</form>
                                    	<div id="error_messages" style="color: red; font-weight: bold;">
					
					</div>
					<a href="registration.jsp" style="color:black; font-weight: bold">Registration</a>
					<form action="" method="POST" style="margin-top: 20px; border-top-style:solid; border-top-width:1px">
						<input type="text" name="search" style="margin-top:20px"></br>
						<input type="submit" name="search_button" value="Search" style="margin-top:7px; margin-bottom:7px"></br>
					</form>
				</div>
			
			</div>
			
			<div class="body_content footer">
				<footer>Â© All rights reserved, 2016 | <a href="" style="color:black">Contact</a> | <a href="" style="color:black">About us</a></footer>
			</div>
	
		</div>
		
	</body>

</html>