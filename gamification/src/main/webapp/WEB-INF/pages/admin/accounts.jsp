<%@include file="../includes/header.jsp" %>

<div class="main-content">

    <div class="header bg-gradient-primary d-flex align-items-center" style="min-height:330px;">

        <div class="container-fluid d-flex align-items-center">
            <div class="row">
                <div class="col">
                    <h1 class="display-2 text-white">Accounts</h1>
                    <p class="text-white mt-0 mb-5">Manage all registered accounts</p>
                </div>
            </div>
        </div>
    </div>

    <div class="container-fluid mt--7">
        <!-- Table -->
        <div class="row">
            <div class="col">
                <div class="card shadow">
                    <div class="card-header border-0">
                        <h3 class="mb-0">List of registered accounts</h3>
                    </div>
                    <div class="table-responsive">
                        <table class="table align-items-center table-flush">
                            <thead class="thead-light">
                            <tr>
                                <th scope="col">First name</th>
                                <th scope="col">Last name</th>
                                <th scope="col">Email</th>
                                <th scope="col">Street</th>
                                <th scope="col">NPA</th>
                                <th scope="col">City</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${accounts}" var="account">
                                <tr>
                                    <td>${account.firstName}</td>
                                    <td>${account.lastName}</td>
                                    <td>${account.email}</td>
                                    <td>${account.street}</td>
                                    <td>${account.npa}</td>
                                    <td>${account.city}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${account.banned}">
                                                <a href="/game/admin/ban?action=unban&accountId=${account.id}" class="btn btn-success btn-sm" title="unlock account">
                                                    Unban
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="/game/admin/ban?action=ban&accountId=${account.id}" class="btn btn-warning btn-sm" title="Lock account">
                                                    Ban
                                                </a>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${account.admin}">
                                                <a href="/game/admin/make-admin?action=demote&accountId=${account.id}" class="btn btn-warning btn-sm" title="Demote admin">
                                                    Demote admin
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="/game/admin/make-admin?action=promote&accountId=${account.id}" class="btn btn-primary btn-sm" title="Promote admin">
                                                    Promote admin
                                                </a>
                                            </c:otherwise>
                                        </c:choose>

                                        <a href="/game/admin/reset-password?email=${account.email}&npa=${account.npa}&city=${account.city}" class="btn btn-danger btn-icon btn-sm" title="Reset password">
                                            <span class="btn-inner--icon"><i class="ni ni-key-25"></i></span>
                                        </a>

                                        <a href="/game/admin/delete?accountId=${account.id}" class="btn btn-danger btn-icon btn-sm" title="Delete account">
                                            <span class="btn-inner--icon"><i class="ni ni-fat-remove"></i></span>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="card-footer py-4">
                        <nav>
                            <%@include file="../includes/pagination.jsp"%>
                        </nav>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>

<%@include file="../includes/footer.jsp" %>