<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="${pageContext.request.contextPath}/">

    <title>${pageTitle}</title>

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">

    <link href="static/vendor/nucleo/css/nucleo.css" rel="stylesheet">
    <link type="text/css" href="static/css/argon.css" rel="stylesheet">

</head>
<body>

<%@include file="../includes/menu.jsp" %>