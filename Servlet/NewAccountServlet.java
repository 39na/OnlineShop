package Servlet; 

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import model.NewAccountInfoLogic;
import model.ShopDAO;

/**
 * Servlet implementation class NewAccountServlet
 */
@WebServlet("/NewAccountServlet")
public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/newAccount.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String account_id = request.getParameter("account_id");
		String account_pass = request.getParameter("account_pass");
		String account_name = request.getParameter("account_name");
		String address_number = request.getParameter("address_number");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String mailaddress = request.getParameter("mailaddress");
		int payment = request.getParameter("payment") == null ? 100 : Integer.parseInt(request.getParameter("payment"));
		
		//logicで判定後、問題がなければインスタンスを作成し、データベースに追加する
		NewAccountInfoLogic logic = new NewAccountInfoLogic();
		String errorMsg = logic.execute(account_id, account_pass, account_name, address_number, address, tel, mailaddress, payment);
		
		if(errorMsg.length() != 0) {
			
			request.setAttribute("errorMsg", errorMsg);
			RequestDispatcher  dispatcher = request.getRequestDispatcher("WEB-INF/jsp/newAccount.jsp");
			dispatcher.forward(request, response);
			
		} else {
			
			//DAOで新規アカウント情報をデータベースに追加
			ShopDAO dao = new ShopDAO();
			boolean result = dao.createAccount(account_id, account_name, account_pass, address_number, address, tel, mailaddress, payment);
			
			if(result) {
				Account account = new Account(account_id, account_pass, account_name, address_number, address, tel, mailaddress, payment);
//				HttpSession session = request.getSession();
//				session.setAttribute("loginAcccount", account);
				request.getRequestDispatcher("WEB-INF/jsp/newAccountOK.jsp").forward(request, response);
			}
			
		}
	}

}
