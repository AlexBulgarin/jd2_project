<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@include file="header.jsp"%>

<div class="container-fluid">
    <h2>Products</h2>
    <c:choose>
    <c:when test="${requestScope.products.size() > 0}">
    <table class="table">
      <thead>
       <tr>
          <th scope="col">#</th>
          <th scope="col">Product Name</th>
          <th scope="col">Product Description</th>
          <th scope="col">Product Duration, month </th>
          <th scope="col">&nbsp;</th>
        </tr>
      </thead>
      <tbody>
        <c:set value="0" var="count" scope="page"/>
        <c:forEach var="product" items="${requestScope.products}">
        <tr>
            <c:set value="${pageScope.count + 1}" var="count" scope="page"/>
            <td><a><c:out value="${count}"/></a></td>
            <td><a>${product.name}</a></td>
            <td><a>${product.description}</a></td>
            <td><a>${product.durationInMonth}</a></td>
            <td><a class="btn btn-primary" href="/bank/products/open-${product.id}" role="button">Open Account</a></td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
    </c:when>
    <c:otherwise>
        <a>No Products</a>
    </c:otherwise>
    </c:choose>

    <h2>Loans</h2>
    <c:choose>
    <c:when test="${requestScope.loans.size() > 0}">
    <table class="table">
      <thead>
       <tr>
          <th scope="col">#</th>
          <th scope="col">Loan Name</th>
          <th scope="col">Loan Description</th>
          <th scope="col">Loan Duration, month </th>
          <th scope="col">Loan Rate </th>
          <th scope="col">Loan Max Sum </th>
          <th scope="col">&nbsp;</th>
        </tr>
      </thead>
      <tbody>
        <c:set value="0" var="count" scope="page"/>
        <c:forEach var="loan" items="${requestScope.loans}">
        <tr>
            <c:set value="${pageScope.count + 1}" var="count" scope="page"/>
            <td><a><c:out value="${count}"/></a></td>
            <td><a>${loan.name}</a></td>
            <td><a>${loan.description}</a></td>
            <td><a>${loan.durationInMonth}</a></td>
            <td><a>${loan.loanRate}</a></td>
            <td><a>${loan.maxSum}</a></td>
            <td><a class="btn btn-primary" href="/bank/products/open-${loan.id}" role="button">Open Account</a></td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
    </c:when>
    <c:otherwise>
        <a>No Loans</a>
    </c:otherwise>
    </c:choose>

    <h2>Deposits</h2>
    <c:choose>
    <c:when test="${requestScope.deposits.size() > 0}">
    <table class="table">
      <thead>
       <tr>
          <th scope="col">#</th>
          <th scope="col">Deposit Name</th>
          <th scope="col">Deposit Description</th>
          <th scope="col">Deposit Duration, month </th>
          <th scope="col">Deposit Rate </th>
          <th scope="col">Deposit Min Sum </th>
          <th scope="col">&nbsp;</th>
        </tr>
      </thead>
      <tbody>
        <c:set value="0" var="count" scope="page"/>
        <c:forEach var="deposit" items="${requestScope.deposits}">
        <tr>
            <c:set value="${pageScope.count + 1}" var="count" scope="page"/>
            <td><a><c:out value="${count}"/></a></td>
            <td><a>${deposit.name}</a></td>
            <td><a>${deposit.description}</a></td>
            <td><a>${deposit.durationInMonth}</a></td>
            <td><a>${deposit.depositRate}</a></td>
            <td><a>${deposit.minSum}</a></td>
            <td><a class="btn btn-primary" href="/bank/products/open-${deposit.id}" role="button">Open Account</a></td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
    </c:when>
    <c:otherwise>
        <a>No Deposits</a>
    </c:otherwise>
    </c:choose>
</div>

<%@include file="footer.jsp"%>