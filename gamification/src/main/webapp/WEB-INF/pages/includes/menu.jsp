<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-vertical fixed-left navbar-expand-md navbar-light bg-white" id="sidenav-main">
    <div class="container-fluid">

        <div class="collapse navbar-collapse" id="sidenav-collapse-main">

            <!-- Navigation -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/game/developer/applications">
                        <i class="ni ni-bullet-list-67 text-primary"></i> Applications
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/game/developer/application">
                        <i class="ni ni-fat-add text-success"></i> New application
                    </a>
                </li>
            </ul>

            <c:if test="${not empty currentAccount && currentAccount.admin}">
                <hr class="my-3">
                <h6 class="navbar-heading text-muted">Admin section</h6>

                <ul class="navbar-nav mb-md-3">
                    <li class="nav-item">
                        <a class="nav-link" href="/game/admin/accounts">
                            <i class="ni ni-glasses-2"></i> Accounts
                        </a>
                    </li>
                </ul>
            </c:if>
            <hr class="my-3">
            <h6 class="navbar-heading text-muted">Account</h6>

            <ul class="navbar-nav mb-md-3">
                <li class="nav-item">
                    <a class="nav-link" href="/game/developer/profile">
                        <i class="ni ni-single-02 text-purple"></i> My profile
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/game/auth/logout">
                        <i class="ni ni-curved-next text-orange"></i> Logout
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

