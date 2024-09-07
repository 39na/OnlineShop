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
 * Servlet implementation class CartInServlet
 */
@WebServlet("/CartInServlet")
public class CartInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
	
		}
	*/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//jspから受け取ったパラメーターを取得
		request.setCharacterEncoding("UTF-8");
		int wishGoods_id = Integer.parseInt(request.getParameter("goods_id"));
		int wishOrder_quantity = Integer.parseInt(request.getParameter("order_quantity"));

		System.out.println("注文商品ID:" + wishGoods_id + " 注文数:" + wishOrder_quantity);

		String errorMsg = "";

		//セッションスコープにある商品情報を取得し、選んだ商品IDが同じ商品の情報を取得する
		HttpSession session = request.getSession();
		ArrayList<Goods> goods = (ArrayList<Goods>) session.getAttribute("goods");

		//処理前の在庫数をコンソールに表示
		for (Goods g : goods) {
			System.out.println("処理前" + g.getGoods_name() + "ID:" + g.getGoods_id() + "在庫数" + g.getStock());
		}

		Goods wishGoods = null;
		for (Goods g : goods) {

			if (wishGoods_id == g.getGoods_id()) {
				wishGoods = g;
				if (g.getStock() < wishOrder_quantity) {
					errorMsg += g.getGoods_name() + "の在庫が不足しています";
				} else {
					wishGoods.setOrder_quantity(wishOrder_quantity);//取得後、購入したい数をセットする
					g.setStock(g.getStock() - wishOrder_quantity); //在庫数を差し引く

				}
			}
		}

		if (errorMsg.length() > 0) {
			//エラーメッセージがあるならエラーメッセージをリクエストスコープに
			System.out.println("カート追加失敗");
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("WEB-INF/jsp/top.jsp").forward(request, response);
		} else {

			//処理後の在庫数をコンソールに表示
			for (Goods g : goods) {
				System.out.println("処理後" + g.getGoods_name() + "ID:" + g.getGoods_id() + "在庫数" + g.getStock());
			}

			//セッションスコープに既に購入したい商品のリストがあればそれに追記し、無ければ新規作成する
			ArrayList<Goods> wishList = (ArrayList<Goods>) session.getAttribute("wishlist");
			if (wishOrder_quantity > 0) {

				if (wishList == null) {
					wishList = new ArrayList<Goods>();
					wishList.add(wishGoods);
				} else {
					boolean search = true;

					for (Goods w : wishList) {
						if (w.getGoods_id() == wishGoods.getGoods_id()) {
							System.out.println(w.getOrder_quantity());
							w.setOrder_quantity(w.getOrder_quantity() + wishGoods.getOrder_quantity());
							search = false;
						}
					}
					if (search) {
						wishList.add(wishGoods);
					}
				}

			} else {
				errorMsg += "個数は1以上の数値を入力してください";
			}

			for (Goods g : wishList) {
				System.out
						.println("カート" + g.getGoods_name() + "ID:" + g.getGoods_id() + "オーダー数" + g.getOrder_quantity());
			}

			//エラーメッセージがないなら商品情報とカート商品情報をセッションスコープに上書きして更新
			System.out.println("カート追加成功");
			session.setAttribute("wishlist", wishList);
			session.setAttribute("goods", goods);
			session.setAttribute("wishGoods", wishGoods);
			request.getRequestDispatcher("WEB-INF/jsp/top.jsp").forward(request, response);
		}
		
	}
	

}
