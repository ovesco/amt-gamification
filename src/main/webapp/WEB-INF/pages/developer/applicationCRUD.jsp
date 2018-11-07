<%@include file="../includes/header.jsp" %>
<%@include file="../includes/devmenu.jsp" %>



<div class="main-content">

    <div class="header bg-gradient-primary d-flex align-items-center" style="min-height:330px;">

        <div class="container-fluid d-flex align-items-center">
            <div class="row">
                <div class="col">
                    <h1 class="display-2 text-white">${action.equals("create") ? 'Create a new' : 'Update existing'} application</h1>
                    <p class="text-white mt-0 mb-5">This tools allow you to ${action} an application</p>
                </div>
            </div>
        </div>
    </div>

    <div class="container-fluid mt--7">
        <!-- Table -->
        <div class="row">
            <div class="col">
                <div class="card shadow bg-secondary mb-5">
                    <div class="card-header border-0">
                        <h3 class="mb-0">Application fields</h3>
                    </div>
                    <div class="card-body">
                        <form method="post">
                            <div class="form-group">
                                <input type="text" placeholder="Application name" name="name" value="${name}" class="form-control form-control-alternative" />
                                <c:if test="${errors.containsKey('name')}">
                                    <p class="text-danger">${errors.get("name")}</p>
                                </c:if>
                            </div>

                            <div class="form-group">
                                <textarea type="text" placeholder="Description" name="description" class="form-control form-control-alternative">${description}</textarea>
                                <c:if test="${errors.containsKey('description')}">
                                    <p class="text-danger">${errors.get("description")}</p>
                                </c:if>
                            </div>

                            <div class="d-flex justify-content-end">
                                <a class="btn btn-secondary" href="/game/developer/applications">Cancel</a>
                                <button type="submit" class="btn btn-primary">${action} app</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>

<%@include file="../includes/footer.jsp" %>