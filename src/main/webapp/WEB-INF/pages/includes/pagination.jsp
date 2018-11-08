<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="pagination justify-content-end mb-0">
    <c:if test="${pages > 0}">
        <c:forEach var="i" begin="1" end="${pages}">
            <li class="page-item active">
                <a class="page-link ${page == i ? 'active' : ''}" href="${requestScope['javax.servlet.forward.request_uri']}?page=${i}">${i}</a>
            </li>
        </c:forEach>
    </c:if>
</ul>