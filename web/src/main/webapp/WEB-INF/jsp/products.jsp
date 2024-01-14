<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@include file="header.jsp"%>

<style>
    .content-container {
        align-items: center;
        justify-content: center;
        text-align: center;
        min-height: 100vh;
    }
</style>

<div class="content-container">
    <c:forEach var="productType" items="${['Products', 'Loans', 'Deposits']}">
        <h2>${productType}</h2>
        <c:choose>
            <c:when test="${requestScope[productType.toLowerCase()].size() > 0}">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">${(productType == 'Loans') ? 'Loan' : (productType == 'Deposits') ? 'Deposit' : 'Product'} Name</th>
                            <th scope="col">${(productType == 'Loans') ? 'Loan' : (productType == 'Deposits') ? 'Deposit' : 'Product'} Description</th>
                            <th scope="col">${(productType == 'Loans') ? 'Loan' : (productType == 'Deposits') ? 'Deposit' : 'Product'} Duration, month </th>
                            <c:if test="${productType == 'Loans'}">
                                <th scope="col">Loan Rate</th>
                                <th scope="col">Loan Max Sum</th>
                            </c:if>
                            <c:if test="${productType == 'Deposits'}">
                                <th scope="col">Deposit Rate</th>
                                <th scope="col">Deposit Min Sum</th>
                            </c:if>
                            <th scope="col">&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set value="0" var="count" scope="page"/>
                        <c:forEach var="product" items="${requestScope[productType.toLowerCase()]}">
                            <tr>
                                <c:set value="${pageScope.count + 1}" var="count" scope="page"/>
                                <td><a><c:out value="${count}"/></a></td>
                                <td><a>${product.name}</a></td>
                                <td><a>${product.description}</a></td>
                                <td><a>${product.durationInMonth}</a></td>
                                <c:if test="${productType == 'Loans'}">
                                    <td><a>${product.loanRate}</a></td>
                                    <td><a>${product.maxSum}</a></td>
                                </c:if>
                                <c:if test="${productType == 'Deposits'}">
                                    <td><a>${product.depositRate}</a></td>
                                    <td><a>${product.minSum}</a></td>
                                </c:if>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_USER')">
                                        <a class="btn btn-primary" href="/bank/products/open-${product.id}" role="button">Open Account</a>
                                    </sec:authorize>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <a>No ${productType}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>

<%@include file="footer.jsp"%>
