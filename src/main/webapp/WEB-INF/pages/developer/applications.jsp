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
            <button class="btn btn-secondary" data-toggle="modal" data-target="#new-app-modal">New application</button>
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
                                            <button class="btn btn-icon btn-danger" type="button">
                                                <span class="btn-inner--icon"><i class="ni ni-fat-remove"></i></span>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="card-footer py-4">
                        <nav>
                            <ul class="pagination justify-content-end mb-0">
                                <li class="page-item disabled">
                                    <a class="page-link" href="#" tabindex="-1">
                                        <i class="fas fa-angle-left"></i>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                </li>
                                <li class="page-item active">
                                    <a class="page-link" href="#">1</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
                                </li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                <li class="page-item">
                                    <a class="page-link" href="#">
                                        <i class="fas fa-angle-right"></i>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>

<div class="modal fade" id="new-app-modal" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Create a new application</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post">
                    <div class="form-group">
                        <input type="text" placeholder="Application name" class="form-control" />
                    </div>

                    <div class="form-group">
                        <textarea type="text" placeholder="Description" class="form-control"></textarea>
                    </div>

                    <div class="d-flex justify-content-end">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Register app</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>



<%@include file="../includes/footer.jsp" %>