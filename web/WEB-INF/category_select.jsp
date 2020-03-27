<%@page import="entities.Article"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entities.Category"%>
<%@page import="java.util.List"%>

<jsp:include page="/GetCategories" />

<% List<Category> list = (List<Category>) request.getAttribute("categories"); %>
<% Article art = (Article) request.getAttribute("loadedArticle"); %>

<select style="margin-top: 10px" form="article_form" name="category" size="6" multiple required>
    
    <% if (list != null) {
        for (Category c: list) { %>
            <% if (art != null) { %>
                <% if (art.getCategories().contains(c)) { %>
                    <option value="<%= c.getId() %>" selected ><%= c.getName() %></option>
                <% } else { %>
                    <option value="<%= c.getId() %>" ><%= c.getName() %></option>
                <% } %>
            <% } else { %>
                <option value="<%= c.getId() %>" ><%= c.getName() %></option>
            <% } %>
    <% } } %>

</select>

