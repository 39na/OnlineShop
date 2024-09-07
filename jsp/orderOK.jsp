<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Account"%>
<%
Account loginAccount = (Account) session.getAttribute("loginAccount");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sweets shop</title>
<link rel="stylesheet" href="././css/style.css">
</head>
<body class="orderOK">
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
		<p>
			注文が完了しました。<br> 注文の詳細内容はメールをご確認ください。
		</p>
		
		<br>
		<a href="MenuServlet">メニュー画面へ</a>
	</main>
</body>
</html>