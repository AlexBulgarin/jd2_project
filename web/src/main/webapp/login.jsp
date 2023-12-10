<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
    <div class="d-flex justify-content-center text-center">
        <div class="position-absolute top-50 start-50 translate-middle">
            <form action="/login" method="post">
                <h1 class="h3 mb-3 fw-normal">Please log in</h1>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" name="username" id="floatingUsername" placeholder="Username" autocomplete="off" autofocus>
                    <label for="floatingUsername">Username</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" name="password" id="floatingPassword" placeholder="Password" autocomplete="off">
                    <label for="floatingPassword">Password</label>
                </div>
                <button class="btn btn-light border" type="submit">Log In</button>
            </form>
        </div>
    </div>
<%@include file="footer.jsp"%>