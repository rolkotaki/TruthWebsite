<%@page import="model.DataBase"%>
<%@page import="entities.Category"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./style/style.css">
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
			
				<div class="admin_categories_div">
                    
                    <jsp:include page="/GetCategories" />
                    <% List<Category> list = (List<Category>) request.getAttribute("categories"); %>
				
                    <fieldset>
                        <legend>Article categories:</legend>	
                        <table>
                            <tr>
                                <td>Id</td>
                                <td>Category name</td>
                                <td></td>
                                <td></td>
                            </tr>
                            <% if (list != null) {
                                    for (Category c: list) { %>
                                        <tr>
                                            <form action="ManageCategory" method="POST">
                                                <td><input type="hidden" name ="id" value="<%= c.getId() %>"><%= c.getId() %></td>
                                                <td><input type="text" name="category_name" value="<%= c.getName() %>"></td>
                                                <td><input type="submit" name="modify" value="Modify"></td>
                                                <td><input type="submit" name="delete" value="Delete" onclick="return confirm('Are you sure you want to delete the category?')"></td>
                                            </form>
                                        </tr>
                            <% } } %>

                            <tr>
                                <form action="ManageCategory" method="POST">
                                    <td></td>
                                    <td><input type="text" name="category_name" ></td>
                                    <td><input type="submit" name="save" value="Save"></td>
                                    <td></td>
                                </form>
                            </tr>
                        </table>
                    </fieldset>
                                
                    <div>
                        <% if (request.getParameter("result") != null && request.getParameter("result").equals("updated")) { %>
                            <p style="margin-bottom: 10px; font-weight: bold; color:#008000;">The category has been updated!</p>
                        <% } else if (request.getParameter("result") != null && request.getParameter("result").equals("saved")) { %>
                            <p style="margin-bottom: 10px; font-weight: bold; color:#008000;">The category has been saved!</p>
                        <% } else if (request.getParameter("result") != null && request.getParameter("result").equals("deleted")) { %>
                            <p style="margin-bottom: 10px; font-weight: bold; color:#008000;">The category has been deleted!</p>
                        <% } else if (request.getParameter("result") != null && request.getParameter("result").equals("missing")) { %>
                            <p style="margin-bottom: 10px; font-weight: bold; color:#FF0000;">Category name must not be null!</p>
                        <% } else if (request.getParameter("result") != null && request.getParameter("result").equals("error")) { %>
                            <p style="margin-bottom: 10px; font-weight: bold; color:#FF0000;">The process has failed due to DB error!</p>
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