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
import model.LoginLogic;
import model.ShopDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//パラメーターの取得
		request.setCharacterEncoding("UTF-8");
		String account_id = request.getParameter("account_id");
		String account_pass = request.getParameter("account_pass");
		
		//パラメーターの判定
		LoginLogic loginlogic = new LoginLogic();
		String errorMsg = loginlogic.execute(account_id, account_pass);
		
		if(errorMsg.length() == 0) {
			
			//DBからパラメーターと一致するユーザーを探してArrayListに入れる
			ShopDAO dao = new ShopDAO();
			ArrayList<Account> list = dao.findByAccount(account_id,account_pass);
			
			// ユーザ情報なし、もしくは複数件の場合はログイン画面に戻る
			if (list == null || list.size() != 1) {
				
				//NGフラグをリクエストスコープに渡して、ログイン画面に遷移
				request.setAttribute("errorMsg", "ユーザーIDまたはパスワードが正しくありません");
				request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
			
			} else {
				// ユーザ情報を保持するためのBeanクラスのインスタンス
				Account loginAccount = new Account();

				// ユーザIDとユーザ名をインスタンスフィールドにセットする
				loginAccount.setAccount_id(list.get(0).getAccount_id());
				loginAccount.setAccount_pass(list.get(0).getAccount_pass());
				loginAccount.setAccount_name(list.get(0).getAccount_name());
				loginAccount.setAddress_number(list.get(0).getAddress_number());
				loginAccount.setAddress(list.get(0).getAddress());
				loginAccount.setTel(list.get(0).getTel());
				loginAccount.setMailaddress(list.get(0).getMailaddress());
				loginAccount.setPayment(list.get(0).getPayment());
				loginAccount.setRole(list.get(0).getRole());

				// アカウント情報をセッションスコープに登録して、メニュー画面に遷移
				HttpSession session = request.getSession();
				session.setAttribute("loginAccount", loginAccount);
				request.getRequestDispatcher("WEB-INF/jsp/menu.jsp").forward(request, response);
			}
 
		} else {
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
		}
		
	}

}
