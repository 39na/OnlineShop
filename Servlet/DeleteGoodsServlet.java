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

/**
 * Servlet implementation class DeleteGoodsServlet
 */
@WebServlet("/DeleteGoodsServlet")
public class DeleteGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//カート内の商品を削除する
		//セッションスコープからカート内の情報を取得
		HttpSession session = request.getSession();
		ArrayList<Goods> wishlist = (ArrayList<Goods>)session.getAttribute("wishlist");
		ArrayList<Goods> goods = (ArrayList<Goods>)session.getAttribute("goods");
		
		request.setCharacterEncoding("UTF-8");
		int index = Integer.parseInt(request.getParameter("index"));
		
		System.out.println(index);
	
		
		//削除したい商品の数を在庫数に加算する
		for(Goods g : goods) {
			if(wishlist.get(index).getGoods_id() == g.getGoods_id()) {
				g.setStock(g.getStock() + wishlist.get(index).getOrder_quantity()); 
			}
		}
		//カート内から商品を削除
		wishlist.remove(wishlist.get(index));
		//商品情報を更新
		session.setAttribute("goods", goods);
		
		if(wishlist.size() == 0) {
			request.setAttribute("nullMsg", "カートに商品がありません");
		} else {
			session.setAttribute("wishlist", wishlist);
		}
		request.getRequestDispatcher("WEB-INF/jsp/cartContents.jsp").forward(request, response);
		
	}

}
