<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="/game/">

    <title>Forgot password</title>

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">

    <link href="static/vendor/nucleo/css/nucleo.css" rel="stylesheet">
    <link href="static/vendor/@fortawesome/fontawesome-free/css/all.min.css" rel="stylesheet">
    <link type="text/css" href="static/css/argon.css" rel="stylesheet">

<body class="bg-default">

<div class="main-content">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-5 col-md-7">
                <div style="height:100vh;" class="d-flex align-items-center">
                    <div class="card bg-secondary shadow border-0" style="width:100%;">
                        <div class="card-body px-lg-5 pt-lg-5 pb-lg-4">
                            <h2 class="text-center text-muted mb-4">Forgot password</h2>


                            <c:if test="${pageContext.request.method.equals('POST') && error != null}">
                                <div class="alert alert-danger">
                                    <p class="m-0">${error}</p>
                                </div>
                            </c:if>
                            <form role="form" method="post" action="/game/auth/reset-password">
                                <div class="form-group mb-3">
                                    <div class="input-group input-group-alternative">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><i class="ni ni-email-83"></i></span>
                                        </div>
                                        <input class="form-control" placeholder="Email" name="email" value="${email}" type="email">
                                    </div>
                                </div>
                                <div class="form-group mb-3">
                                    <div class="row">
                                        <div class="col-lg-4">
                                            <div class="input-group input-group-alternative">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text"><i class="ni ni-square-pin"></i></span>
                                                </div>
                                                <input class="form-control" placeholder="Npa" name="npa" value="${npa}" type="number">
                                            </div>
                                        </div>
                                        <div class="col-lg-8">
                                            <div class="input-group input-group-alternative">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text"><i class="ni ni-building"></i></span>
                                                </div>
                                                <input class="form-control" placeholder="City" name="city" value="${city}" type="text">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="text-center">
                                    <a class="btn btn-secondary" href="/game/auth/login">Back to login</a>
                                    <button type="submit" class="btn btn-primary my-4">Send new password</button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../includes/notifications.jsp" %>
<%@include file="../includes/footer.jsp" %>
