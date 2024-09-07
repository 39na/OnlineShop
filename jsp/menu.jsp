<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account" %> 
<% 
	// セッションスコープ
	Account loginAccount = (Account)session.getAttribute("loginAccount");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="././css/style.css">
<title>メニュー | Sweets Shop</title>
</head>

<body class="menu">
<!-- ヘッダー -->
<header>
<div class="container">
<div class="item"><h1>Sweets shop</h1></div>
<div class="item"><h5/>LoginUser : <%= loginAccount.getAccount_id() %><a href="LogoutServlet">  ログアウト</a>
<!-- 管理者分岐 -->
<% if(loginAccount.getRole() == 2){ %>
<h5><a href="AdminServlet">商品の編集へ</a></h5>
<% } %>
</h5></div>
</div>

</header>

<!-- メイン -->
<main>
	<br>
	<a href="OrderServlet">  商品一覧へ</a>
	<a href="CartContentsServlet">  ショッピングカートを見る</a>
	<a href="OrderHistoryServlet">  注文履歴を見る</a>
	<a href="AccountUpdateServlet">  会員情報の変更</a>
	<br><br><br><br>
	<div style="width: 400px; margin-left:  60%; ">
	<p>Sweets Shopでは、身体と環境に配慮したサスティナブルな栽培方法で栽培、
	収穫された優しい原料を極力使用し、シンプルな工程で作り上げています。
	素朴でありながらも混じりっけのない原料本来の旨味をを最大限引き出し、
	原料そのものの優しい色合いとその焼き色の美しさを再現しています。
	自然と調和した身体にも自然にも優しいお菓子を日々研究しています。
	</p>
	</div>
</main>
</body>
</html>
