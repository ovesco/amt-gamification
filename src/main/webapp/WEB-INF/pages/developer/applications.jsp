<%@include file="../includes/header.jsp" %>
<%@include file="../includes/devmenu.jsp" %>



<div class="main-content">

    <div class="header bg-gradient-primary d-flex align-items-center" style="min-height:330px;">

        <div class="container-fluid d-flex align-items-center">
            <div class="row">
                <div class="col">
                    <h1 class="display-2 text-white">Applications</h1>
                    <p class="text-white mt-0 mb-5">Here you can manage your registered applications or create a new one.</p>
                </div>
            </div>
        </div>

        <div class="container-fluid d-flex justify-content-end">
            <a href="/game/developer/application" class="btn btn-secondary">New application</a>
        </div>
    </div>

    <div class="container-fluid mt--7">
        <!-- Table -->
        <div class="row">
            <div class="col">
                <div class="card shadow mb-5">
                    <div class="card-header border-0">
                        <h3 class="mb-0">My registered apps</h3>
                    </div>
                    <div class="table-responsive">
                        <table class="table align-items-center table-flush">
                            <thead class="thead-light">
                                <tr>
                                    <th scope="col">Name</th>
                                    <th scope="col">Description</th>
                                    <th scope="col">Creation</th>
                                    <th scope="col">API key</th>
                                    <th scope="col">API secret</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${applications}" var="app">
                                    <tr>
                                        <td>${app.name}</td>
                                        <td>${app.description}</td>
                                        <td>${app.creation}</td>
                                        <td>${app.apiKey}</td>
                                        <td>${app.apiSecret}</td>
                                        <td>
                                            <a href="/game/developer/application?action=update&appId=${app.id}" class="btn btn-icon btn-primary btn-sm">
                                                <span class="btn-inner--icon"><i class="ni ni-settings-gear-65"></i></span>
                                            </a>
                                            <a href="/game/developer/application?action=delete&appId=${app.id}" class="btn btn-icon btn-danger btn-sm">
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
                            <ul class="pagination justify-content-end mb-0">
                                <c:forEach var="i" begin="1" end="${pages}">
                                    <li class="page-item active">
                                        <a class="page-link ${page == i ? 'active' : ''}" href="/game/developer/applications?page=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>

<%@include file="../includes/footer.jsp" %>