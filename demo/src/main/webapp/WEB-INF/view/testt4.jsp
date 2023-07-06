<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>News Articles</title>
</head>
<body>
	<h1>News Articles</h1>
	<c:forEach items="${newsList}"  var="news">

        <div>
            <h2>${news.title}</h2>
            <img src="${news.imgUrl}" alt="News Image" />
            <p>${news.body}</p>
            <a href="${news.articleUrl}">Read More</a>
            <p>categories-${news.categories}</p>
        </div>
        <hr />
    </c:forEach>
</body>
</html>
