<%@page import="java.util.Map"%>
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
                <script type = "text/javascript" src = "./script/javascript/jquery-3.1.1.min.js"></script>
                <script type = "text/javascript" language = "javascript">
                    <!--
                    
                    //-->
//                    $(document).ready(function() {
//                        alert($(document).height());
//                        alert($(window).height());
//                        alert($(".body_main_div").height());
//
////                        $("em").addClass("selected");
////                        $("#myid").addClass("highlight");
//                    });
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
			
			<div class="body_main_div" id="body_main_div">
			
				<div class="admin_articles_div">
                                    
                    <% if (session.getAttribute("session_sql") != null) { %>
                        <jsp:include page="/LoadSQLResult" />
                    <% } %>
                    <% List<Map<String,Object>> resultSet = (List<Map<String,Object>>) request.getAttribute("sql_resultset_list");
                        String result = (request.getAttribute("result") != null ? request.getAttribute("result").toString() : ""); %>
                    
                    <div>
                        <% if (result.equals("success")) { %>
                            <p style="margin-top: -30px; margin-bottom: 10px; font-weight: bold; color:#008000;">The SQL statement has been executed successfully!</p>
                        <% } else if (result.equals("syntax_error")) { %>
                            <p style="margin-top: -30px; margin-bottom: 10px; font-weight: bold; color:#FF0000;">The SQL statement contains syntax error!</p>
                        <% } else if (result.equals("hibernate_error")) { %>
                            <p style="margin-top: -30px; margin-bottom: 10px; font-weight: bold; color:#FF0000;">The SQL statement execution has failed due to hibernate exception!</p>
                        <% } else if (result.equals("error")) { %>
                            <p style="margin-top: -30px; margin-bottom: 10px; font-weight: bold; color:#FF0000;">The SQL statement execution has failed due to unknown error!</p>
                        <% } else if (resultSet != null && resultSet.size() == 0) { %>
                            <p style="margin-top: -30px; margin-bottom: 10px; font-weight: bold; color:#0000FF;">The query has no result!</p>
                        <% } %>
                    </div>
                    
                    <form class="form_manage_article" id="sql_form" action="ManageSQL" method="POST">
                        <textarea name="sql_statement" form="sql_form" rows="5" cols="140" placeholder="SQL statement" required ><%= (session.getAttribute("sql_statement") != null ? session.getAttribute("sql_statement").toString() : "" ) %></textarea></br>
                        <input type="submit" name="run_sql" value="Run">
                        <input type="submit" name="clear_sql" value="Clear">
                    </form>
				
                    <fieldset>
                        <legend>Query result:</legend>	
                        <table>
                            <% if (resultSet != null && resultSet.size() > 0) { %>
                                    <tr>
                                        <% for (String column : resultSet.get(0).keySet()) { %>
                                            <td><%= column %></td>
                                        <% } %>
                                    </tr>
                                <% for (Map<String,Object> m : resultSet) { %>
                                <tr>
                                    <%for (Object o : m.values()) { %>
                                        <td><input type="text" style="width: 140px;" disabled value="<%= o %>"></td>
                                <% } %>
                                </tr>
                            <% } %>
                            <% } %>
                        </table>
                    </fieldset>                   

				</div>
			
			</div>
                <script type = "text/javascript" language = "javascript">
//                    <!--
//                    alert(document.getElementById("body_main_div").innerHTML);
//                    alert(document.body.height);
//                    if (document.body.height) {
//                        alert(document.body.height);
//                    }
                    //-->
                    $(document).ready(function() {
                        var pos = $(".body_main_div fieldset").position();
                        var pos2 = $(".body_main_div").offset();
                        alert(pos2.top);
                        if ($(document).height() -15 - $(".body_main_div fieldset").height() > pos.top) {
                            alert("first" + $(document).height() + " " + $(".body_main_div fieldset").height() + " " + pos.top);
                            $("#div_footer").css({"position":"fixed", "bottom":"0"});
                        } else {
                            alert("else");
                            $("#div_footer").addClass("body_content footer");
                        }
                        

//                        $("em").addClass("selected");  style="position:fixed; bottom:0" class="body_content footer"
//                        $("#myid").addClass("highlight");
                    });
                </script>
			<div id="div_footer" class="body_content footer">
				<footer>© All rights reserved, 2016 | <a href="" style="color:black">Contact</a> | <a href="" style="color:black">About us</a></footer>
			</div>
			
		</div>
		
	</body>

</html>