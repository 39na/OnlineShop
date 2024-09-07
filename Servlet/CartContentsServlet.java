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
 * Servlet implementation class CartContentsServlet
 */
@WebServlet("/CartContentsServlet")
public class CartContentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//セッションスコープを取得後、jspに遷移する
		HttpSession session = request.getSession();
		ArrayList<Goods> wishlist = (ArrayList<Goods>)session.getAttribute("wishlist");
		//セッションスコープに保存されたインスタンスが存在し、リスト内に1つでも商品があればスコープにセットする
		if(wishlist != null && wishlist.size() > 0) {
			session.setAttribute("wishlist", wishlist);
		} else {
			request.setAttribute("nullMsg", "カートに商品がありません");
		}
		request.getRequestDispatcher("WEB-INF/jsp/cartContents.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//カート内で数量を変更した時の処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String errorMsg = "";
		
		//カートに入っている情報を取得
		HttpSession session = request.getSession();
		ArrayList<Goods> wishlist = (ArrayList<Goods>)session.getAttribute("wishlist");
		ArrayList<Goods> goods = (ArrayList<Goods>) session.getAttribute("goods");
		
		//jspからパラメーターを取得
		request.setCharacterEncoding("UTF-8");
		int UpdateOrder_quantity = Integer.parseInt(request.getParameter("order_quantity"));
		int index = Integer.parseInt(request.getParameter("index"));

		System.out.println("変更前の購入数：" + wishlist.get(index).getOrder_quantity());
		System.out.println("index：" + index);
		
		//購入したい数のみ変更してセッションスコープに保存(購入数が0の場合、何もせず画面を再表示する)
		if(UpdateOrder_quantity > 0) {
			Goods UpdateGoods = wishlist.get(index);
			int gap = UpdateGoods.getOrder_quantity() - UpdateOrder_quantity;//元の購入数-変更後の購入数
			for (Goods g : goods) {
				if (UpdateGoods.getGoods_id() == g.getGoods_id()) {
					int result = g.getStock() +  (gap); //在庫数を加算(減算)
					if (result < 0) {
						errorMsg += g.getGoods_name() + "の在庫が不足しています<br>残り在庫数" + g.getStock();
					} else {
						g.setStock(result); 
						UpdateGoods.setOrder_quantity(UpdateOrder_quantity);//購入したい数を再度セットする
						session.setAttribute("wishlist", wishlist);
						session.setAttribute("goods", goods);
					}
				}
			}
		}
		if(errorMsg.length() > 0) {
			request.setAttribute("errorMsg", errorMsg);
		}
		request.getRequestDispatcher("WEB-INF/jsp/cartContents.jsp").forward(request, response);
		
		System.out.println("変更後の購入数：" + wishlist.get(index).getOrder_quantity());
	}

}
