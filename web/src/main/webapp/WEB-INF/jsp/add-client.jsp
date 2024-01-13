<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<form method="post">
  <div class="mb-3">
    <label for="firstname" class="form-label">Input client first name</label>
    <input type="text" class="form-control" id="firstname" name="firstName">
  </div>
  <div class="mb-3">
    <label for="lastname" class="form-label">Input client last name</label>
    <input type="text" class="form-control" id="lastname" name="lastName">
  </div>
  <div class="mb-3">
    <label for="email" class="form-label">Input your email address</label>
    <input type="email" class="form-control" id="email" name="email">
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>




<%@include file="footer.jsp"%>