<%@page import="java.util.Enumeration"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.DataBase"%>
<%@page import="entities.Category"%>
<%@page import="entities.Article"%>
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
			
				<div class="admin_articles_div">
                                    
                    <% if (session.getAttribute("session_type") != null) { %>
                        <jsp:include page="/GetArticles" />
                    <% } %>
                    <% List<Article> list = (List<Article>) request.getAttribute("session_article_list"); %>
                    
                    <div>
                        <% if (request.getParameter("result") != null && request.getParameter("result").equals("deleted")) { %>
                            <p style="margin-top: -30px; margin-bottom: 10px; font-weight: bold; color:#008000;">The article has been deleted!</p>
                        <% } else if (request.getParameter("result") != null && request.getParameter("result").equals("error")) { %>
                            <p style="margin-top: -30px; margin-bottom: 10px; font-weight: bold; color:#FF0000;">The process has failed due to DB error!</p>
                        <% } %>
                    </div>
                    
                    <form class="form_manage_article" action="ManageArtJobs" method="POST">
                        <input type="submit" name="add" value="Add new article">
                        <input type="submit" name="list_all" value="List all">
                        <input type="text" size="90" name="restriction" placeholder="select * from articles where" value="<%= (session.getAttribute("session_restriction") != null ? session.getAttribute("session_restriction") : "") %>" >
                        <input type="submit" name="list_by_restriction" value="List by restriction">
                    </form>
				
                    <fieldset>
                        <legend>Articles:</legend>	
                        <table>
                            <tr>
                                <td>Id</td>
                                <td>Date</td>
                                <td>Title</td>
				<td>Category</td>
                                <td>Source</td>
				<td>Cover image</td>
                            </tr>
                            <% if (list != null) {
                                    for (Article a: list) { %>
                                        <tr>
                                            <form action="ManageArticle" method="POST" >
                                                <td><input type="hidden" name ="id" value="<%= a.getId() %>"><%= a.getId() %></td>
                                                <td><input type="text" style="width: 110px;" disabled name="date" value="<%= a.getDate() + "-##:##" %>"></td>
                                                <td><input type="text" style="width: 200px;" disabled name="title" value="<%= a.getTitle() %>"></td>
                                                <td><input type="text" disabled name="category" value="<%= a.getCategoryList() %>"></td>
                                                <td><input type="text" style="width: 105px;" disabled name="source" value="<%= a.getSource() %>"></td>
                                                <td><input type="text" style="width: 105px;" disabled name="image" value="<%= a.getImage() %>"></td>
                                                <td><input type="submit" name="edit" value="Edit"></td>
                                                <td><input type="submit" name="delete" value="Delete" onclick="return confirm('Are you sure you want to delete the article?')"></td>
                                            </form>
                                        </tr>
                            <% } } %>

                        </table>
                    </fieldset>
                    
                    <%if (request.getAttribute("session_pagelist") != null) {%>
					<div class="page_list_div">
                        <% ArrayList<Integer> pageList = (ArrayList<Integer>) request.getAttribute("session_pagelist"); %>
                        <a href="<%= request.getContextPath() + "/admin_articles.jsp" %>" class="page_list">First page</a>
                        <% for (int i=0; i < pageList.size(); i++) { %>
                            <span class="page_list_separator">·</span>
                            <a href="<%= request.getContextPath() + "/admin_articles.jsp?page=" + pageList.get(i) %>" class="page_list"><%= pageList.get(i) %></a>
                        <% } %>
						<span class="page_list_separator">·</span>
                        <a href="<%= request.getContextPath() + "/admin_articles.jsp?page=" + request.getAttribute("session_lastpage") %>" class="page_list">Last page</a>					
					</div>
                    <% } %>

				</div>
			
			</div>
			
			<div class="body_content footer" style="position:fixed; bottom:0">
				<footer>Â© All rights reserved, 2016 | <a href="" style="color:black">Contact</a> | <a href="" style="color:black">About us</a></footer>
			</div>
			
		</div>
		
	</body>

</html>