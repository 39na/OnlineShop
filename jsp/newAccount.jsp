<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% String[] paymentlist = {"代引き", "銀行振込", "クレジットカード"}; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sweets Shop</title>
<link rel="stylesheet" href="././css/style.css">
</head>
<body class="newAccount">
<!-- ヘッダー -->
	<header>
		<div class="container">
			<div class="item">
				<h1>Sweets shop</h1>
			</div>
			<div class="item">
				<h5 />　</h5>
			</div>
		</div>
	</header>

	<!-- メイン -->
	<main>
	<div class="list">
<p>新規アカウント作成</p>

<form action="NewAccountServlet" method="post">
<table>
	<tr>
		<th>ユーザーID</th>
		<td><input type="text" name="account_id"></td>
	</tr>
	<tr>
		<th>パスワード</th>
		<td><input type="password" name="account_pass"></td>
	</tr>
	<tr>
		<th>氏名</th>
		<td><input type="text" name="account_name"></td>
	</tr>
	<tr>
		<th>郵便番号</th>
		<td><input type="text" name="address_number" placeholder="000-0000"></td>
	</tr>
	<tr>
		<th>住所</th>
		<td><input type="text" name="address"></td>
	</tr>
	<tr>
		<th>電話番号</th>
		<td><input type="text" name="tel" placeholder="000-0000-0000"></td>
	</tr>
	<tr>
		<th>メールアドレス</th>
		<td><input type="text" name="mailaddress"></td>
	</tr>
	<tr>
		<th>支払方法</th>
		<td>
		<div class="radio-group">
		<% for(int i = 0; i < paymentlist.length; i++){ %>
		 <input type="radio" name="payment" value="<%=i + 1%>" id="<%= i + 1 %>"><label for="<%= i + 1 %>"><%=paymentlist[i]%></label>
		<% } %>
		 </div>
		</td>
	</tr>
</table>
<p style="color:red"><c:out value="${errorMsg}" escapeXml="false"/></p>
<br>
<input class="chenge_submit" type="submit" value="登録">
<br><br>
</form>
</div>

<br>
<a href="LoginServlet">ログイン画面へ戻る</a>

</body>
</html>