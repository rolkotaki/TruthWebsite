<%@page import="java.util.ArrayList"%>
<%@page import="java.io.File"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./style/style.css">
		<title>Truth website</title>
                <script>
                    function displayImage() {
                        var a = document.forms["imagelist"]["list"].value;
                        document.getElementById("displayed_image").innerHTML = '<img style="width: 400px;" src="./images/' + a + '" />';
                        return false;
                    }
                </script>
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
						-->
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
			
				<div class="admin_images_div">
                    
                                <div>
                                    <% if (request.getParameter("result") != null && request.getParameter("result").equals("success")) { %>
                                        <p style="margin-top: -30px; font-weight: bold; color:#008000;">File uploaded sussessfully!</p>
                                    <% } else if (request.getParameter("result") != null && request.getParameter("result").equals("missing")) { %>
                                        <p style="margin-top: -30px; font-weight: bold; color:#FF0000;">No file selected!</p>
                                    <% } else if (request.getParameter("result") != null && request.getParameter("result").equals("error")) { %>
                                        <p style="margin-top: -30px; font-weight: bold; color:#FF0000;">An error has occured during the upload process!</p>
                                    <% } else if (request.getParameter("result") != null && request.getParameter("result").equals("deleted")) { %>
                                        <p style="margin-top: -30px; font-weight: bold; color:#008000;">Image deleted!</p>
                                    <% } else if (request.getParameter("result") != null && request.getParameter("result").equals("deleteError")) { %>
                                        <p style="margin-top: -30px; font-weight: bold; color:#FF0000;">An error has occured during the delete process!</p>
                                    <% } %>
                                </div>
				
                                <table>
                                    <tr>
                                        <td style="width: 400px;">
                                            <fieldset style="width: 300px; margin-top: -65px;">
                                            <legend>Upload image:</legend>
                                            <form id="upload_image" method="POST" action="UploadImage" enctype="multipart/form-data" >   
                                                <input type="file" name="image" style="margin-bottom: 10px;" />
                                                <input type="submit" name="upload" value="Upload" />
                                            </form>
                                            </fieldset>
                                        </td>
                                       
                                        <td>
                                            <fieldset id="manage_images">
                                            <legend>View image:</legend>   
                                                <form style="margin-top: 10px;" method="POST" action="ManageImage" id="imagelist" >
                                                    Restriction: <input type="text" name="restriction" value="<%= (session.getAttribute("restriction") == null) ? "" : session.getAttribute("restriction") %>" />
                                                    <br/>
                                                    <select name="list" id="list" style="margin: 10px 0px;">                       
                                                        <%
                                                        if (session.getAttribute("imageList") == null) {
                                                            File imList = new File(request.getServletContext().getRealPath("/images"));
                                                            String[] list = imList.list();
                                                            if (list.length < 101) {
                                                                for (String f : list) { %>
                                                                    <option value="<%= f %>"><%= f %></option>
                                                        <%      }
                                                            } else {
                                                            for (int i = list.length-101; i < list.length-1; i++) { %>
                                                                <option value="<%= list[i] %>"><%= list[i] %></option>
                                                        <% 
                                                        } } } else {
                                                            for (String f : (ArrayList<String>)session.getAttribute("imageList")) {
                                                        %>
                                                            <option value="<%= f %>"><%= f %></option>
                                                        <% } } %>
                                                    </select>
                                                    <br/>
                                                    <input type="submit" name="listimage" value="Refresh" onclick="return confirm('Are you sure you want to begin searching?')" />
                                                    <input type="submit" name="delete" value="Delete" onclick="return confirm('Are you sure you want to delete the image?')" />
                                                </form>
                                                <form method="POST">
                                                    <input type="submit" name="view" value="View" onclick="return displayImage()" style="margin-top: 10px;" />
                                                </form>
                                            </fieldset>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding-top: 10px; text-align: center;" colspan="2">
                                            <p id ="displayed_image"></p>
                                        </td>
                                    </tr>
                                </table>

				</div>
			
			</div>
			
			<div class="body_content footer" style="position:fixed; bottom:0">
				<footer>© All rights reserved, 2016 | <a href="" style="color:black">Contact</a> | <a href="" style="color:black">About us</a></footer>
			</div>
			
		</div>
		
	</body>

</html>