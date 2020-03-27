<%@page import="entities.Article"%>
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
			
				<div class="admin_add_article_div">
                    
                    <jsp:include page="/LoadArticle" />
                    <% Article art = (Article) request.getAttribute("loadedArticle"); %>
                    <% int type = (int) request.getAttribute("articleType"); %>
                    
                    <div>
                        <% if (request.getParameter("result") != null && request.getParameter("result").equals("success")) { %>
                            <p style="margin-top: -30px; font-weight: bold; color:#008000;">Article updated!</p>
                        <% } else if (request.getParameter("result") != null && request.getParameter("result").equals("missing")) { %>
                            <p style="margin-top: -30px; font-weight: bold; color:#FF0000;">Title and article text must be filled in!</p>
                        <% } else if (request.getParameter("result") != null && request.getParameter("result").equals("error")) { %>
                            <p style="margin-top: -30px; font-weight: bold; color:#FF0000;">The article could not be saved to DB due to error!</p>
                        <% } %>
                    </div>
                    
					<form action="EditArticle" method="POST" id="article_form">
						<fieldset>
							<legend>Edit article:</legend>
							<table>
								<tr>
									<td>Title:<input type="hidden" name ="id" value="<%= art.getId() %>"></td>
                                                                        <td><input type="text" name="title" size="90" value="<%= art.getTitle() %>"></td>
                                                                        <td rowspan="4">
                                                                            Category:<br/>
                                                                            <jsp:include page="/WEB-INF/category_select.jsp" />
                                                                        </td>
								</tr>
								<tr>
									<td>Source:</td>
                                                                        <td><input type="text" name="source" size="90" value="<%= art.getSource()%>"></td>
								</tr>
								<tr>
									<td>Image name:</td>
									<td><input type="text" name="image" size="90" value="<%= art.getImage()%>"></td>
								</tr>
                                                                <tr>
									<td>Article tpye:</td>
                                                                        <td><input type="radio" name="article_type" value="leading" <%= (type==1 ? "checked" : "") %>> leading
                                                                        <input type="radio" name="article_type" value="home" <%= (type==0 ? "checked" : "") %>> home
                                                                        <input type="radio" name="article_type" value="none" <%= (type==-1 ? "checked" : "") %>> none</td>
								</tr>
								<tr>
									<td>Text:</td>
                                                                        <td colspan="2"><textarea name="article_text" form="article_form" rows="36" cols="120" placeholder="Text of the article"><%= art.getText() %></textarea></td>
								</tr>
								<tr>
									<td colspan="3" style="text-align: center">
                                                                            <input type="submit" name="edit_article" value="Save changes">
                                                                            <input type="submit" name="cancel_edit_article" value="Back to list">
                                                                        </td>
								</tr>
							</table>
						</fieldset>
					</form>

				</div>
			
			</div>
			
			<div class="body_content footer">
				<footer>© All rights reserved, 2016 | <a href="" style="color:black">Contact</a> | <a href="" style="color:black">About us</a></footer>
			</div>
			
		</div>
		
	</body>

</html>