<%@ include file="layout/header.jsp"%>
<div class="container-fluid">
	<c:forEach items="${newsList}" var="news">
		<div class="container-fluid">
			<div class="card">
				<h2>${news.title}</h2>
				<img src="${news.imgUrl}" alt="News Image"  height="280" width="300" />
				<p>${news.body}</p>
				<a href="${news.articleUrl}">Read More</a>
				<p>categories-${news.categories}</p>
			</div>
		</div>
	</c:forEach>
</div>
<%@ include file="layout/footer.jsp"%>