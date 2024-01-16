<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@include file="header.jsp" %>

<style>
    .horizontal-accounts {
        display: flex;
    }

    .card {
        margin-right: 10px;
    }
</style>

<a class="btn btn-primary" href="products" role="button">Open new account</a>
<div class="blank-line">
    <h1></h1>
</div>

<h2>Accounts Information</h2>

<c:choose>
    <c:when test="${not empty accountDtos}">
        <div class="horizontal-accounts">
            <c:forEach var="accountDto" items="${accountDtos}">
                <div class="card">
                    <p>Account IBAN: ${accountDto.iban}</p>
                    <p>Product Name: ${accountDto.productDto.name}</p>
                    <p>Account Balance: ${accountDto.balance}</p>
                    <p>Account Currency: ${accountDto.currencyName}</p>
                    <p>Cards:</p>

                    <c:forEach var="cardDto" items="${accountDto.cards}">
                        <div style="border: 1px solid #ddd; padding: 5px; margin-bottom: 5px;">
                            <p>Card Number: ${cardDto.number}</p>
                            <p>Expiry Date: ${cardDto.expiryDate}</p>
                        </div>
                    </c:forEach>

                    <div style="margin-top: 10px;">
                        <a class="btn btn-success" href="client/add-card-${accountDto.iban}" role="button">Issue Additional Card</a>
                        <a class="btn btn-success" href="client/transaction-${accountDto.iban}" role="button">Make Transaction</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:when>
    <c:otherwise>
        <p>No Accounts</p>
    </c:otherwise>
</c:choose>

<%@include file="footer.jsp" %>
