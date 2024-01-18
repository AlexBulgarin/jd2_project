<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <style>
        html, body {
            height: 100%;
        }

        body {
            display: flex;
            flex-direction: column;
        }

        .content {
            flex: 1 0 auto;
        }

        .footer {
            flex-shrink: 0;
        }
    </style>
</head>
<header class="p-3 text-bg-dark">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/bank/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="30" height="30" role="img" aria-label="Logo"><img src="/bank/static/img/logo.png" alt="no image" height="40px" width="40px"></svg>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/bank/" class="nav-link px-2 text-white">Home</a></li>
                <li><a href="/bank/products" class="nav-link px-2 text-white">Products</a></li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="/bank/admin" class="nav-link px-2 text-white">Admin Tools</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <li><a href="/bank/client" class="nav-link px-2 text-white">My Accounts</a></li>
                </sec:authorize>
            </ul>

            <div class="text-end">
                <sec:authorize access="!isAuthenticated()">
                    <a class="btn btn-outline-primary" href="/bank/login" role="button">Log in</a>
                    <a class="btn btn-outline-success" href="/bank/register" role="button">Register</a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <a class="btn btn-outline-danger" href="/bank/logout" role="button">Log out</a>
                </sec:authorize>
            </div>
        </div>
    </div>
</header>
<body>
    <div class="content">
        <div class="blank-line">
            <h1/>
        </div>
