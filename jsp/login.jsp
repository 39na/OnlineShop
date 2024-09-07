<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sweets Shop</title>
<link rel="stylesheet" href="././css/style.css">
</head>
<body class="login">
<!-- <h3>ユーザー情報入力</h3> -->
<h1 class="hero">Sweets Shop</h1>
<h2 style="font-size: 50px">Login</h2>
<form action="LoginServlet" method="post" class="input">
User ID &nbsp;	<input type="text" name="account_id" class="txt"><br>
Password	<input type="password" name="account_pass" class="txt"><br>
<br>
<input type="submit" value="サインイン" class="button">
</form>
<p style="color:red" class="attention"><c:out value="${errorMsg}" escapeXml="false"/></p>
<hr class="border">
<br>
<a href="NewAccountServlet" class="tag">Make a new account <br> (アカウント新規作成)</a>
</body>
</html>