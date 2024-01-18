<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@include file="header.jsp"%>

<form method="post">
    <div class="mb-3">
        <label for="currency" class="form-label">Select product currency</label>
        <select class="form-select" id="currency" name="currencyName">
            <option selected>Choose...</option>
            <option value="USD">USD</option>
            <option value="EUR">EUR</option>
            <option value="RUB">RUB</option>
            <option value="BYN">BYN</option>
        </select>
    </div>

    <c:choose>
        <c:when test="${productType != 'products'}">
            <div class="mb-3">
                <label for="balance" class="form-label">Input sum</label>
                <c:choose>
                    <c:when test="${productType == 'loans'}">
                        <input type="number" class="form-control" id="balance" name="balance" max="${sum}">
                    </c:when>
                    <c:when test="${productType == 'deposits'}">
                        <input type="number" class="form-control" id="balance" name="balance" min="${sum}">
                    </c:when>
                </c:choose>
            </div>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="balance" value="0">
        </c:otherwise>
    </c:choose>

    <button type="submit" class="btn btn-primary">Submit</button>
</form>

<%@include file="footer.jsp"%>