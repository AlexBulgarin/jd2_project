<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<div class="col-lg-8 mx-auto p-4 py-md-5">
  <main>
    <h1 class="text-body-emphasis">Get acquainted with our products</h1>
    <p class="fs-5 col-md-8">Take a look at the list of Metro Bank products and their terms and conditions.</p>

    <div class="mb-5">
      <a href="/bank/products" class="btn btn-primary btn-lg px-4">Products</a>
    </div>

    <hr class="col-3 col-md-2 mb-5">

    <sec:authorize access="hasRole('ROLE_USER')">
      <h1 class="text-body-emphasis">Your Accounts</h1>
      <p class="fs-5 col-md-8">Take a look at the list of accounts.</p>

      <div class="mb-5">
        <a href="/bank/client" class="btn btn-primary btn-lg px-4">Accounts</a>
      </div>
    </sec:authorize>
  </main>
</div>

<%@include file="footer.jsp"%>
