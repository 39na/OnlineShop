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
import model.UpdateGoodsInfoLogic;

/**
 * Servlet implementation class UpdateGoodsServlet
 */
@WebServlet("/UpdateGoodsServlet")
public class UpdateGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//jspよりパラメーターを取得後、そのままリクエストスコープに保存して遷移
		request.setCharacterEncoding("UTF-8");
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
//		System.out.println(goods_id);
		request.setAttribute("goods_id", goods_id);
		request.getRequestDispatcher("WEB-INF/jsp/updateGoods.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		ArrayList<Goods> goods = (ArrayList<Goods>)session.getAttribute("goods");
				
		//jspからパラメーターを取得
		request.setCharacterEncoding("UTF-8");
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		String goods_name = request.getParameter("goods_name");
		String goods_text = request.getParameter("goods_text");
		int price = request.getParameter("price").length() == 0 ? -1 : Integer.parseInt(request.getParameter("price"));
		int stock = request.getParameter("stock").length() == 0 ? -1 : Integer.parseInt(request.getParameter("stock"));
		
		//Logic判定で受け取ったパラメーターに不備がないか確認
		UpdateGoodsInfoLogic ug = new UpdateGoodsInfoLogic();
		String errorMsg = ug.execute(goods_name, goods_text, price, stock);
		if(errorMsg.length() != 0) {
			request.setAttribute("goods_id", goods_id);
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("WEB-INF/jsp/updateGoods.jsp").forward(request, response);
		} else {
			Goods update = new Goods(goods_id, goods_name, goods_text, price, stock);
			//DAOを起動してデータベースの情報を変更する
			ShopDAO dao = new ShopDAO();
			boolean result = dao.updateGoods(update);
			//成功すればadmin.jsp、失敗すればもう1度updateGoods.jspへ遷移する
			if(result) {
				goods = dao.getList();
				session.setAttribute("goods", goods);
				request.getRequestDispatcher("WEB-INF/jsp/admin.jsp").forward(request, response);
			} else {	//※不要であれば削除予定
				request.setAttribute("goods_id", goods_id);
				System.out.println("データベースにアップデートできませんでした");
				request.getRequestDispatcher("WEB-INF/jsp/updateGoods.jsp").forward(request, response);
			}
		}
		
		
	}

}
