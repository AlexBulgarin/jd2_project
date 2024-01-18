<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@include file="header.jsp" %>

<h2>Transaction Form</h2>

<form method="post">
    <label for="recipientCardNumber">Input Recipient Card Number:</label>
    <input type="text" class="form-control" id="recipientCardNumber" name="recipientCardNumber"><br>

    <label for="amount">Input Amount</label>
    <input type="number" class="form-control" id="amount" name="amount" min="1"><br>

    <button type="submit" class="btn btn-primary">Make Transaction</button>
</form>

<%@include file="footer.jsp" %>