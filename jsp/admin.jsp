<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account, model.Goods, java.util.ArrayList" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% 
	// セッションスコープ
	Account loginAccount = (Account)session.getAttribute("loginAccount");
	ArrayList<Goods> goods = (ArrayList<Goods>)session.getAttribute("goods");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="././css/style.css">
<title>管理者専用 | Sweets Shop</title>
</head>

<body class="admin">
<!-- ヘッダー -->
<header>
<div class="container">
<div class="item"><h1>Sweets shop</h1></div>
<div class="item"><h5/>LoginUser : <%= loginAccount.getAccount_id() %><a href="LogoutServlet">  ログアウト</a></h5></div>
</div>

</header>

<!-- メイン -->
<main>
<div class="list">
	<form action="?" method="get">
	<p>変更する商品を選択</p>
	
	<div class="selectdiv">
		<select name="goods_id">
			<% for(Goods g : goods){ %>
			<option value="<%= g.getGoods_id() %>"><%= g.getGoods_name() %></option>
			<% } %>
		</select>
	</div>

	<br><br><br>
	<input class="chenge_submit" type="submit" value="変更" formaction="UpdateGoodsServlet">
	<input class="chenge_submit" type="submit" value="削除" formaction="DestroyGoodsServlet">
	
	<br><br>
	<p style="color:orange"><c:out value="${message}" escapeXml="false" /></p>
	</form>
</div>

<hr>
<a href="MenuServlet">メニューへ</a>

</main>

</body>
</html>