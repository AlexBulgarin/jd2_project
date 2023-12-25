<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
 </head>
  <header class="p-3 text-bg-dark">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
          <svg class="bi me-2" width="30" height="30" role="img" aria-label="Logo"><img src="images/logo.png" height="40px" width="40px"></svg>
        </a>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="/bank/" class="nav-link px-2 text-white">Home</a></li>
          <li><a href="#" class="nav-link px-2 text-white">Products</a></li>
          <li><a href="#" class="nav-link px-2 text-white">FAQs</a></li>
          <li><a href="#" class="nav-link px-2 text-white">About</a></li>
        </ul>

        <div class="text-end">
          <a href="/bank/login">
            <button type="button" class="btn btn-outline-light me-2">Log in</button>
          </a>
          <a href="/bank/signup">
            <button type="button" class="btn btn-warning">Sign up</button>
          </a>
        </div>
      </div>
    </div>
  </header>