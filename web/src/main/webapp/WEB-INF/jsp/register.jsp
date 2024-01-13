<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<form method="post">
  <div class="mb-3">
    <label for="email" class="form-label">Input your email address</label>
    <input type="email" class="form-control" id="email" name="email">
  </div>
      <div class="mb-3">
        <label for="login" class="form-label">Input your login</label>
        <input type="text" class="form-control" id="login" name="login">
      </div>
  <div class="mb-3">
    <label for="password" class="form-label">Input password</label>
    <input type="password" class="form-control" id="password" name="password">
  </div>
   <button type="submit" class="btn btn-primary">Submit</button>
</form>

<%@include file="footer.jsp"%>