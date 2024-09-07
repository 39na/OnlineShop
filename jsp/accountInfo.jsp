<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String[] paymentlist = { "代引き", "銀行振込", "クレジットカード" };

// セッションスコープ
Account loginAccount = (Account) session.getAttribute("loginAccount");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報変更 | Sweets Shop</title>
<link rel="stylesheet" href="././css/style.css">
</head>
<body class="accountInfo">

	<!-- ヘッダー -->
	<header>
		<div class="container">
			<div class="item">
				<h1>Sweets shop</h1>
			</div>
			<div class="item">
				<h5 />
				LoginUser :
				<%=loginAccount.getAccount_id()%><a href="LogoutServlet"> ログアウト</a>
				</h5>
			</div>
		</div>
	</header>

	<!-- メイン -->
	<main>

		<div class="list">
			<p>会員情報の変更</p>

			<form action="AccountUpdateServlet" method="post">
				<table>
					<tr>
						<th>ユーザーID</th>
						<td><%=loginAccount.getAccount_id()%><div style="color: red">※ユーザーIDは変更不可</div></td>
					</tr>
					<tr>
						<th>パスワード</th>
						<td><input type="password" name="account_pass"
							value="<%=loginAccount.getAccount_pass()%>"></td>
					</tr>
					<tr>
						<th>氏名</th>
						<td><input type="text" name="account_name"
							value="<%=loginAccount.getAccount_name()%>"></td>
					</tr>
					<tr>
						<th>郵便番号</th>
						<td><input type="text" name="address_number"
							placeholder="000-0000"
							value="<%=loginAccount.getAddress_number()%>"></td>
					</tr>
					<tr>
						<th>住所</th>
						<td><input type="text" name="address"
							value="<%=loginAccount.getAddress()%>"></td>
					</tr>
					<tr>
						<th>電話番号</th>
						<td><input type="text" name="tel" placeholder="000-0000-0000"
							value="<%=loginAccount.getTel()%>"></td>
					</tr>
					<tr>
						<th>メールアドレス</th>
						<td><input type="text" name="mailaddress"
							value="<%=loginAccount.getMailaddress()%>"></td>
					</tr>
					<tr>
						<th>支払方法</th>
						<td>
							<div class="radio-group">
							<% for(int i = 0; i < paymentlist.length; i++){ %>
		 						<input type="radio" name="payment" value="<%=i + 1%>" id="<%= i + 1 %>" checked><label for="<%= i + 1 %>"><%=paymentlist[i]%></label>
							<% } %>
		 					</div>
						</td>
					</tr> 

				</table>
				<p style="color: red">
					<c:out value="${errorMsg}" escapeXml="false" />
				</p>
				<br> <input class="chenge_submit" type="submit" value="変更">
			</form>
			<br>
		</div>

		<br> <a href="MenuServlet">メニューへ</a>


	</main>
</body>
</html>




