package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.ShopDAO;
import model.UpdateAccountlifoLogic;

/**
 * Servlet implementation class AccountUpdateServlet
 */
@WebServlet("/AccountUpdateServlet")
public class AccountUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/accountInfo.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		Account loginAccount = (Account)session.getAttribute("loginAccount");
		
		request.setCharacterEncoding("UTF-8");
		String account_id = loginAccount.getAccount_id();
		String account_pass = request.getParameter("account_pass");
		String account_name = request.getParameter("account_name");
		String address_number = request.getParameter("address_number");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String mailaddress = request.getParameter("mailaddress");
		int payment = request.getParameter("payment") == null ? 100 : Integer.parseInt(request.getParameter("payment"));
		
		UpdateAccountlifoLogic up = new UpdateAccountlifoLogic();
		String errorMsg = up.execute(account_pass, account_name, address_number, address, tel, mailaddress, payment);
		
		ShopDAO dao = new ShopDAO();
		
		if(errorMsg.length() != 0) {
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("WEB-INF/jsp/accountInfo.jsp").forward(request, response);
		} else {
			Account account = new Account(account_id, account_pass, account_name, address_number, address, tel, mailaddress, payment);
			
			boolean result = dao.updateAccount(account);
			if(result) {
				account = dao.findByAccountID(account_id);
				session.setAttribute("loginAccount", account);
				request.getRequestDispatcher("WEB-INF/jsp/menu.jsp").forward(request, response);
			}
		}
		
	}

}
