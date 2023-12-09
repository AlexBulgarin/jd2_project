<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<form action="userdata.html" method="post">
    <label for="flogin">Login</label><br>
    <input type="text" id="flogin" name="login"/><br>
    <label for="fpassword">Password</label><br>
    <input type="text" id="fpassword" name="password"/><br>
    <input type="submit" value="Send"/>
</form>
<%@include file="footer.jsp"%>