<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="layout/header.jsp"%>

<div class="container-fluid">
    <div class="card">
        <button type="button" class="btn btn-primary dropdown-toggle"
            data-bs-toggle="dropdown" aria-expanded="false">Category List</button>
        <ul class="dropdown-menu">
            <c:set var="categories"
                value="${['AAVE', 'ALGO', 'APT', 'ATOM', 'AVAX', 'BCH', 'BTC', 'CRO', 'DOGE', 'DOT', 'EOS', 'ETC', 'ETH', 'GRS', 'HBAR', 'HIFI', 'KAVA', 'LINK', 'MATIC', 'MBL', 'MTL', 'NEAR', 'SAND', 'SHIB', 'SOL', 'STX', 'T', 'TRX', 'VET', 'WAVES', 'XLM', 'XRP']}" />
            <li class="list-group-item">
                <div class="d-flex flex-wrap gap-2" style="margin-left: 10px">
                    <c:forEach items="${categories}" var="category">
                        <c:set var="isSelected" value="${fn:contains(param.category, category)}" />
                        <c:choose>
                            <c:when test="${isSelected}">
                                <button type="button" class="btn btn-outline-dark"
                                    onclick="redirectToCategory('${category}', this)">${category}</button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" class="btn btn-outline-primary"
                                    onclick="redirectToCategory('${category}', this)">${category}</button>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
            </li>
        </ul>
    </div> <!-- Closing tag for the first <div> container -->

    <div class="row">
        <c:forEach items="${newsList}" var="news">
            <div class="card">
                <div class="row no-gutters">
                    <div class="col-md-4">
                        <img src="${news.imgUrl}" alt="News Image" height="280"
                            width="300" />
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title fw-semibold">${news.title}</h5>
                            <p class="card-text">${news.body}</p>
                            <c:set var="categories"
                                value="${fn:split(news.categories, '|')}" />
                            <c:forEach items="${categories}" var="category"
                                varStatus="status">
                                <span
                                    class="badge text-bg-light fs-2 rounded-4 py-1 px-2 lh-sm mt-3">${category}</span>
                            </c:forEach>
                            <div class="my-4"></div>
                            <!-- Add one line of blank space -->
                            <div class="d-flex justify-content-between">
                                <div class="align-self-end">
                                    <div class="card-text mb-0 fw-semibold">${news.source}</div>
                                    <div class="card-text fw-semibold">${news.time}</div>
                                </div>
                                <a href="${news.articleUrl}" class="btn btn-primary">Read more</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script>
    function redirectToCategory(category, button) {
        const currentURL = new URL(window.location.href);
        const searchParams = currentURL.searchParams;
        const currentCategories = searchParams.get('category');
        const updatedCategories = currentCategories ? currentCategories.split(',') : [];
        const isSelected = updatedCategories.includes(category);
        if (isSelected) {
            const categoryIndex = updatedCategories.indexOf(category);
            updatedCategories.splice(categoryIndex, 1);
        } else {
            updatedCategories.push(category);
        }
        searchParams.set('category', updatedCategories.join(','));
        window.location.href = currentURL.pathname + '?' + searchParams.toString();
    }
</script>
<%@ include file="layout/footer.jsp"%>
