
function checkArticle() {
    document.getElementById("server_message").innerHTML = "";
    var title = document.article_form.title.value;
    var text = document.article_form.article_text.value;
    var missingMessage = "";
    
    if (title == "") {
        missingMessage += "Please provide the title!<br/>";
    }
    if (text == "") {
        missingMessage += "Please provide the article text!<br/>";
    }
    
    if (missingMessage != "") {
        document.getElementById("message").innerHTML = "<p>" + missingMessage + "</p>";
        return false;
    }
    return true;
}


