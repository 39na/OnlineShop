package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.OrderInfo;
import model.ShopDAO;

/**
 * Servlet implementation class OrderHistoryServlet
 */
@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//DAOで履歴を取得後、ArrayList<OrderHistory>に入れてリクエストスコープに保存後、jspに遷移する
		
		//セッションスコープに保存されているアカウント情報を取得
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("loginAccount");
		
		//アカウントIDからDAOで注文履歴情報を取得
		ShopDAO dao = new ShopDAO();
		ArrayList<OrderInfo> orderhistory = dao.getOrderHistry(account);
			
		if(orderhistory != null) {
			session.setAttribute("orderhistory", orderhistory);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/orderHistory.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
*/
}
