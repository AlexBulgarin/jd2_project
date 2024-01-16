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
    <div class="mb-3">
      <label for="balance" class="form-label">Input sum</label>
      <input type="number" class="form-control" id="balance" name="balance">
    </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>

<%@include file="footer.jsp"%>