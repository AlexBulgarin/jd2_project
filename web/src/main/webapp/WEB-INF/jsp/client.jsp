<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@include file="header.jsp" %>

<a class="btn btn-primary" href="products" role="button">Open new account</a>
<div class="blank-line">
    <h1></h1>
</div>

<h2>Accounts Information</h2>
<c:choose>
    <c:when test="${not empty accountDtos}">
        <c:forEach var="accountDto" items="${accountDtos}">
            <div class="card">
                <p>Account IBAN: ${accountDto.iban}</p>
                <p>Product Name: ${accountDto.productName}</p>
                <p>Account Balance: ${accountDto.balance}</p>
                <p>Account Currency: ${accountDto.currencyName}</p>
                <p>Cards:</p>
                <ul>
                    <c:forEach var="cardDto" items="${accountDto.cards}">
                        <li>Card Number: ${cardDto.number}, Expiry Date: ${cardDto.expiryDate}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p>No Accounts</p>
    </c:otherwise>
</c:choose>

<%@include file="footer.jsp" %>
