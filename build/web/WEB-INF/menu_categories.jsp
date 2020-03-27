<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entities.Category"%>
<%@page import="java.util.List"%>

<jsp:include page="/GetCategories" />

<% List<Category> list = (List<Category>) request.getAttribute("categories"); %>

<table>
    <tr>
    <% if (list != null) {
        for (Category c: list) { %>
        <td class="menu_item"><a href=""><%= c.getName() %></a></td>
    <% } } %>
    
<!--        <td class="menu_item"><a href="">Home</a></td>
        <td class="menu_item"><a href="hungary.html">Hungary</a></td>
        <td class="menu_item"><a href="">World</a></td>
        <td class="menu_item"><a href="">UFO</a></td>
        <td class="menu_item"><a href="">Interesting</a></td>
        <td class="menu_item"><a href="">Health</a></td>
        <td class="menu_item"><a href="">Video</a></td>-->
    </tr>
</table>

