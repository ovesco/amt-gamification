<%@include file="../includes/header.jsp" %>

<div class="main-content">

    <!-- Header -->
    <div class="header d-flex align-items-center" style="min-height: 330px;">
        <!-- Mask -->
        <span class="mask bg-gradient-default opacity-8"></span>
        <!-- Header container -->
        <div class="container-fluid d-flex align-items-center">
            <div class="row">
                <div class="col">
                    <h1 class="display-2 text-white">Change password</h1>
                    <p class="text-white mt-0 mb-5">This tool allows you to change your password</p>
                </div>
            </div>
        </div>

    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
        <div class="row">

            <div class="col-12">
                <div class="card bg-secondary shadow mb-5">
                    <div class="card-header bg-white border-0">
                        <div class="row align-items-center">
                            <div class="col-8">
                                <h3 class="mb-0">Password</h3>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <form method="post">
                            <div class="pl-lg-4">
                                <div class="row">
                                    <div class="col-lg-4">
                                        <div class="input-group input-group-alternative">
                                            <input class="form-control" name="oldPassword" placeholder="Current password" type="password">
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <div class="input-group input-group-alternative">
                                                <input class="form-control" name="newPassword" placeholder="New password" type="password">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <div class="input-group input-group-alternative">
                                                <input class="form-control" name="confirm" placeholder="Confirm password" type="password">
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>

                            <div class="pl-lg-4">
                                <div class="row">
                                    <div class="col">
                                        <button type="submit" class="btn btn-primary">Save changes</button>
                                    </div>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../includes/footer.jsp" %>
