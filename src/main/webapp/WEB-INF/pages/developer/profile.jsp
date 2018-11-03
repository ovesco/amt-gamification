<%@include file="../includes/header.jsp" %>
<%@include file="../includes/devmenu.jsp" %>

<div class="main-content">

    <!-- Header -->
    <div class="header d-flex align-items-center" style="min-height: 330px; background-image: url(../assets/img/theme/profile-cover.jpg); background-size: cover; background-position: center top;">
        <!-- Mask -->
        <span class="mask bg-gradient-default opacity-8"></span>
        <!-- Header container -->
        <div class="container-fluid d-flex align-items-center">
            <div class="row">
                <div class="col">
                    <h1 class="display-2 text-white">Hello ${dev.firstName}</h1>
                    <p class="text-white mt-0 mb-5">This is your profile page, you can edit your profile information.</p>
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
                                <h3 class="mb-0">My account</h3>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <form method="post">
                            <h6 class="heading-small text-muted mb-4">User information</h6>
                            <div class="pl-lg-4">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="input-group input-group-alternative">
                                            <input class="form-control ${errors.containsKey('email') ? 'is-invalid' : ''}" name="email" value="${dev.email}" placeholder="Email" type="email">
                                        </div>
                                        <c:if test="${errors.containsKey('email')}"><p class="text-danger">${errors.get('email')}</p></c:if>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <div class="input-group input-group-alternative">
                                                <input class="form-control ${errors.containsKey('password') ? 'is-invalid' : ''}" placeholder="Password" name="password" type="password">
                                            </div>
                                            <c:if test="${errors.containsKey('password')}"><p class="text-danger">${errors.get('password')}</p></c:if>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <div class="input-group input-group-alternative">
                                                <input class="form-control ${errors.containsKey('firstName') ? 'is-invalid' : ''}" name="firstName" value="${dev.firstName}" placeholder="First name" type="text">
                                            </div>
                                            <c:if test="${errors.containsKey('firstName')}"><p class="text-danger">${errors.get('firstName')}</p></c:if>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <div class="input-group input-group-alternative">
                                                <input class="form-control ${errors.containsKey('lastName') ? 'is-invalid' : ''}" name="lastName" value="${dev.lastName}" placeholder="Last name" type="text">
                                            </div>
                                            <c:if test="${errors.containsKey('lastName')}"><p class="text-danger">${errors.get('lastName')}</p></c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr class="my-4" />

                            <h6 class="heading-small text-muted mb-4">Contact information</h6>
                            <div class="pl-lg-4">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="input-group input-group-alternative">
                                                <input class="form-control ${errors.containsKey('street') ? 'is-invalid' : ''}" name="street" value="${dev.street}" placeholder="Street" type="text">
                                            </div>
                                            <c:if test="${errors.containsKey('street')}"><p class="text-danger">${errors.get('street')}</p></c:if>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-3">
                                        <div class="form-group">
                                            <div class="input-group input-group-alternative">
                                                <input class="form-control ${errors.containsKey('npa') ? 'is-invalid' : ''}" value="${dev.npa}" name="npa" placeholder="NPA" type="number">
                                            </div>
                                            <c:if test="${errors.containsKey('npa')}"><p class="text-danger">${errors.get('npa')}</p></c:if>
                                        </div>
                                    </div>

                                    <div class="col-9">
                                        <div class="form-group">
                                            <div class="input-group input-group-alternative">
                                                <input class="form-control ${errors.containsKey('city') ? 'is-invalid' : ''}" value="${dev.city}" name="city" placeholder="City" type="text">
                                            </div>
                                            <c:if test="${errors.containsKey('city')}"><p class="text-danger">${errors.get('city')}</p></c:if>
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
