<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Account,model.Goods, java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String[] items = { "商品ID", "商品名", "数量", "金額", "小計", "数量変更", "商品削除" };

//セッションスコープ
ArrayList<Goods> wishlist = (ArrayList<Goods>) session.getAttribute("wishlist");
Account loginAccount = (Account) session.getAttribute("loginAccount");
int sumPrice = 0; // 小計金額
int sumAllPrice = 0; // 合計金額
int sumStock = 0; //合計ストック数
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="././css/style.css">
<title>カート | Sweets Shop</title>
</head>
<body class="cartContents">
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
			<p>カートの中身</p>
			<c:choose>
				<c:when test="${not empty wishlist}">

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
							sumStock = wish.getStock() + wish.getOrder_quantity(); //現在個数
						%>
						<form action="?" method="post">
							<tr align="center">
								<td class="tb_6"><%=wish.getGoods_id()%></td>
								<td class="tb_6"><%=wish.getGoods_name()%></td>
								<td class="tb_6"><input class="order_num" type="number" name="order_quantity"
									value="<%=wish.getOrder_quantity()%>" min="0"
									max="<c:out value="<%=sumStock%>" />">個</td>
								<td class="tb_6"><%=wish.getPrice()%>円</td>
								<td class="tb_6"><%=sumPrice%>円</td>
								<td class="tb_6"><input class="chenge_submit" type="submit" value="変更"
									formaction="CartContentsServlet?index=<%=wishlist.indexOf(wish)%>"></td>
								<td class="tb_6"><input class="chenge_submit" type="submit" value="削除"
									formaction="DeleteGoodsServlet?index=<%=wishlist.indexOf(wish)%>"></td>
						</form>
						<!-- 要素番号を取得 -->
						<!-- <input type="hidden" name="index" value=" wish " /> -->
						</tr>
						<%
						}
						%>
						<tr><td>　</td></tr>
						<tr align="right">
							<th colspan="7">合計：<%=sumAllPrice%>円
							</th>
						</tr>
					</table>
					<a href="OrderCheck" style="background: gray; opacity: 1">注文手続きへ</a>
					<br>
					<br>
				</c:when>
				<c:otherwise>
					<p style="color: red">
						<c:out value="${nullMsg}" escapeXml="false" />
					</p>
				</c:otherwise>
			</c:choose>
		</div>


		<br> <br> <a href="OrderServlet">商品一覧へ</a><a href="MenuServlet">メニューへ</a>

	</main>
</body>
</html>


