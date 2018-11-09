<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="/game/">

    <title>Register</title>

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
                        <div class="card-body px-lg-5 py-lg-5">
                            <h2 class="text-center text-muted mb-4">Register</h2>

                            <form role="form" method="post">

                                <div class="form-group">
                                    <div class="input-group input-group-alternative">
                                        <input class="form-control ${errors.containsKey('email') ? 'is-invalid' : ''}" name="email" value="${dev.email}" placeholder="Email" type="email">
                                    </div>
                                    <c:if test="${errors.containsKey('email')}"><p class="text-danger">${errors.get('email')}</p></c:if>
                                </div>

                                <div class="form-group">
                                    <div class="input-group input-group-alternative">
                                        <input class="form-control ${errors.containsKey('firstName') ? 'is-invalid' : ''}" name="firstName" value="${dev.firstName}" placeholder="First name" type="text">
                                    </div>
                                    <c:if test="${errors.containsKey('firstName')}"><p class="text-danger">${errors.get('firstName')}</p></c:if>
                                </div>

                                <div class="form-group">
                                    <div class="input-group input-group-alternative">
                                        <input class="form-control ${errors.containsKey('lastName') ? 'is-invalid' : ''}" name="lastName" value="${dev.lastName}" placeholder="Last name" type="text">
                                    </div>
                                    <c:if test="${errors.containsKey('lastName')}"><p class="text-danger">${errors.get('lastName')}</p></c:if>
                                </div>

                                <div class="form-group">
                                    <div class="input-group input-group-alternative">
                                        <input class="form-control ${errors.containsKey('street') ? 'is-invalid' : ''}" name="street" value="${dev.street}" placeholder="Street" type="text">
                                    </div>
                                    <c:if test="${errors.containsKey('street')}"><p class="text-danger">${errors.get('street')}</p></c:if>
                                </div>

                                <div class="row">
                                    <div class="col-4">
                                        <div class="form-group">
                                            <div class="input-group input-group-alternative">
                                                <input class="form-control ${errors.containsKey('npa') ? 'is-invalid' : ''}" value="${dev.npa}" name="npa" placeholder="NPA" type="number">
                                            </div>
                                            <c:if test="${errors.containsKey('npa')}"><p class="text-danger">${errors.get('npa')}</p></c:if>
                                        </div>
                                    </div>
                                    <div class="col-8">
                                        <div class="form-group">
                                            <div class="input-group input-group-alternative">
                                                <input class="form-control ${errors.containsKey('city') ? 'is-invalid' : ''}" value="${dev.city}" name="city" placeholder="City" type="text">
                                            </div>
                                            <c:if test="${errors.containsKey('city')}"><p class="text-danger">${errors.get('city')}</p></c:if>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group input-group-alternative">
                                        <input class="form-control ${errors.containsKey('password') ? 'is-invalid' : ''}" placeholder="Password" name="password" type="password">
                                    </div>
                                    <c:if test="${errors.containsKey('password')}"><p class="text-danger">${errors.get('password')}</p></c:if>
                                </div>

                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary">Register</button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
