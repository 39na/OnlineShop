package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Goods;
import model.OrderAccountInfoLogin;
import model.SendMail;
import model.ShopDAO;

@WebServlet("/OrderCheck")
public class OrderCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/jsp/orderCheck.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String account_name = request.getParameter("account_name");
		String address_number = request.getParameter("address_number");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String mailaddress = request.getParameter("mailaddress");
		int payment = request.getParameter("payment") == null ? 100 : Integer.parseInt(request.getParameter("payment"));
		//エラー判定数(100)

		String errorMsg = "";

		OrderAccountInfoLogin check = new OrderAccountInfoLogin();
		errorMsg = check.execute(account_name, address_number, address, tel, mailaddress, payment);

		if (errorMsg.length() > 0) {
			//エラーメッセージがあるならエラーメッセージをリクエストスコープに
			System.out.println("注文失敗");
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("WEB-INF/jsp/orderCheck.jsp").forward(request, response);

		} else {

			HttpSession session = request.getSession();
			Account loginAccount = (Account) session.getAttribute("loginAccount");
			ArrayList<Goods> wishList = (ArrayList<Goods>) session.getAttribute("wishlist");

			SendMail sendMail = new SendMail();
			try {
			sendMail.execute(wishList, loginAccount, account_name, address_number, address, tel, mailaddress, payment);
			} catch(Exception e) {
				e.getStackTrace();
				request.setAttribute("errorMsg", "メールを送信できませんでした");
				request.getRequestDispatcher("WEB-INF/jsp/orderCheck.jsp").forward(request, response);
				return;
			}
			//DAO　データーベース操作
			ShopDAO dao = new ShopDAO();

			//DBに注文履歴を追加して、注文分をDB上の在庫数から減らす
			dao.insertOrderHistory(wishList, loginAccount);

			//カート情報削除
			session.removeAttribute("wishlist");

			//在庫数をinsertOrderHistoryで動かしたので商品情報を再取得
			
			ArrayList<Goods> goods = dao.getList();

			//エラーメッセージがないならをセッションスコープに上書きして更新
			System.out.println("注文成功");
			session.setAttribute("goods", goods);
			request.getRequestDispatcher("WEB-INF/jsp/orderOK.jsp").forward(request, response);
		}
	}
}
