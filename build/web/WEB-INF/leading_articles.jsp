
<%@page import="java.util.List"%>
<%@page import="entities.Article"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/GenLeadingArticles" />
<% 
    Article leading = (Article) request.getAttribute("leading");
    if (leading == null) {
        leading = new Article();
    }
    List<Article> homeList = (List<Article>) request.getAttribute("home");
%>

<div class="leading_article_div">
    <article class="leading_article" style="height: 438px">
        <header><h1><a href="n00001.html" class="link_page_article_title"><%= leading.getTitle() %></a></h1></header>
        <div>
            <figure class="leading_figure">
                <img src="./images/img_lights.jpg" alt="This is an article"/>
                <figcaption>Figcaption of the image</figcaption>
            </figure>
            <p><%=leading.getText() %></p>

        </div>
    </article>
</div>

<div class="home_articles_div">

    <div class="home_column_a_div">
        
        <% 
        int counter = 0;
        while (counter < 3 && counter < homeList.size()) { %>
            
            <div class="home_article_div">
                <article>
                    <header><h1><a href="" class="link_page_article_title"><%= homeList.get(counter).getTitle() %></a></h1></header>
                    <div>
                        <figure class="figure">
                            <img src="./images/article_image.jpg" alt="This is an article"/>
                            <figcaption>Figcaption of the image</figcaption>
                        </figure>
                        <p><%= homeList.get(counter).getText() %></p>
                    </div>
                </article>
            </div>
            
        <% counter++;
        } %>
        
    </div>

    <div class="home_column_b_div">
        
        <% int counter2 = 3;
        while (counter2 < 6 && counter2 < homeList.size()) { %>
            
            <div class="home_article_div">
                <article>
                    <header><h1><a href="" class="link_page_article_title"><%= homeList.get(counter2).getTitle() %></a></h1></header>
                    <div>
                        <figure class="figure">
                            <img src="./images/article_image.jpg" alt="This is an article"/>
                            <figcaption>Figcaption of the image</figcaption>
                        </figure>
                        <p><%= homeList.get(counter2).getText() %></p>
                    </div>
                </article>
            </div>
            
        <% counter2++;
        } %>
        
    </div>

</div>