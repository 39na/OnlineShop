<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Account, model.Goods, java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String[] paymentlist = { "代引き", "銀行振込", "クレジットカード" };
String[] items = { "商品ID", "商品名", "数量", "金額", "小計" };
String[] checked = { "checked", "", "" };

//セッションスコープ
ArrayList<Goods> wishlist = (ArrayList<Goods>) session.getAttribute("wishlist");
Account loginAccount = (Account) session.getAttribute("loginAccount");

int sumPrice = 0; // 小計金額
int sumAllPrice = 0; // 合計金額
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sweets Shop</title>
<link rel="stylesheet" href="././css/style.css">
</head>
<body class="orderCheck">
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
			<p>注文内容の確認</p>

			<form action="OrderCheck" method="post">

				<!-- 注文商品情報 -->
				<table>
					<!-- 注文内容の項目 -->
					<tr>
						<%
						for (String item : items) {
						%>
						<th><%=item%></th>
						<%
						}
						%>
					</tr>

					<!-- 注文内容の中身 -->
					<%
					for (Goods wish : wishlist) {
						sumPrice = wish.getOrder_quantity() * wish.getPrice(); // 小計
						sumAllPrice += sumPrice; // 合計
					%>
					<tr align="center">
						<td class="tb_1"><%=wish.getGoods_id()%></td>
						<td class="tb_2"><%=wish.getGoods_name()%></td>
						<td class="tb_3"><%=wish.getOrder_quantity()%>個</td>
						<td class="tb_4"><%=wish.getPrice()%>円</td>
						<td class="tb_5"><%=sumPrice%>円</td>

						<!-- 要素番号を取得 -->
						<input type="hidden" name="index" value="<%=wish%>" />
					</tr>
					<%
					}
					%>
					
					<tr align="right">
						<th>　</th>
					</tr>
					<tr align="right">
						<th colspan="5">合計：<%=sumAllPrice%>円
						</th>
					</tr>
				</table>
				<br>
				<!-- ユーザー情報 -->
				<table>
					<caption>ご注文商品配送先情報</caption>
					<tr>
						<th>ユーザーID</th>
						<td><%=loginAccount.getAccount_id()%> (変更不可)</td>
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
				<br>
				<input  class="chenge_submit" type="submit" align="center" value="注文を確定する">
				<br><br>
			</form>
			

		</div>

		<br> <a href="CartContentsServlet">前に戻る</a><a href="MenuServlet">Topに戻る</a>
	</main>
</body>
</html>