package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Goods;
import model.ShopDAO;

/**
 * Servlet implementation class DestroyGoodsServlet
 */
@WebServlet("/DestroyGoodsServlet")
public class DestroyGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		ArrayList<Goods> goods = (ArrayList<Goods>)session.getAttribute("goods");
		
		request.setCharacterEncoding("UTF-8");
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		System.out.println(goods_id);
		
		//DAO起動後、削除
		ShopDAO dao = new ShopDAO();
		boolean result = dao.destoryGoods(goods_id);
		String message;
		if(result) {
			message = "商品を削除しました";	
			goods = dao.getList();
			session.setAttribute("goods", goods);
		} else {
			message = "商品を削除できませんでした";
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("WEB-INF/jsp/admin.jsp").forward(request, response);
	}	
	
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//		
//	}

}
