<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@include file="header.jsp" %>

<h2>Transaction List</h2>

<table class="table" border="1">
    <thead>
        <tr>
            <th scope="col">Sender IBAN</th>
            <th scope="col">Sum</th>
            <th scope="col">Transaction Date</th>
            <th scope="col">Recipient IBAN</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="transaction" items="${transactions}">
            <tr>
                <td><a>${transaction.senderIban}</a></td>
                <td><a>${transaction.sum}</a></td>
                <td><a>${transaction.transactionDateTime}</a></td>
                <td><a>${transaction.recipientIban}</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<div>
    <c:if test="${page > 0}">
        <a href="?iban=${iban}&page=${page - 1}&size=${size}">Previous</a>
    </c:if>
    Page ${page + 1} of ${totalPages}
    <c:if test="${not empty transactions && transactions.size() == size}">
        <c:if test="${page + 1 lt totalPages}">
            <a href="?iban=${iban}&page=${page + 1}&size=${size}">Next</a>
        </c:if>
    </c:if>
</div>

<%@include file="footer.jsp" %>
