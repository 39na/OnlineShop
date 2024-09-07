//shop管理データベースへのアクセスを行う

package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ShopDAO {

	// TODO 自動生成されたメソッド・スタブ
	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/onlineshop";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "sql";

	//---------------------------------------------------------------------
	//新規アカウント登録(INSERT文)

	public boolean createAccount(String account_id, String account_name, String account_pass,
			String address_number, String address, String tel, String mailaddress, int payment) {
		//JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// エラーハンドリング
			System.out.println("JDBCドライバ関連エラー");
			e.printStackTrace();
		}

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備
			String sql = "INSERT INTO account (ACCOUNT_ID,ACCOUNT_PASS,ACCOUNT_NAME,ADDRESS_NUMBER,ADDRESS,TEL,MAILADDRESS,PAYMENT) VALUES (?, ?, ?, ?, ?, ?, ? ,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, account_id);
			pStmt.setString(2, account_pass);
			pStmt.setString(3, account_name);
			pStmt.setString(4, address_number);
			pStmt.setString(5, address);
			pStmt.setString(6, tel);
			pStmt.setString(7, mailaddress);
			pStmt.setInt(8, payment);

			//INSERT文を実行
			pStmt.executeUpdate();

		} catch (SQLException e) {
			// エラーハンドリング
			System.out.println("DAO 新規アカウント登録 SQLException");
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			System.out.println("DAO 新規アカウント登録 Exception");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//---------------------------------------------------------------------
	//ログイン時アカウント探索操作(SELECT文)

	public ArrayList<Account> findByAccount(String id, String pass) {

		// ユーザ情報を格納するためのリスト
		ArrayList<Account> resultList = new ArrayList<>();

		//JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// エラーハンドリング
			System.out.println("JDBCドライバ関連エラー");
			e.printStackTrace();
		}

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT * FROM Account WHERE ACCOUNT_ID = ? AND ACCOUNT_PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);
			pStmt.setString(2, pass);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			// SELECT文の結果をArrayListに格納
			while (rs.next()) {
				String account_id = rs.getString("account_id");
				String account_pass = rs.getString("account_pass");
				String account_name = rs.getString("account_name");
				String address_number = rs.getString("address_number");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String mailaddress = rs.getString("mailaddress");
				int payment = rs.getInt("payment");
				int role = rs.getInt("role");

				Account accountInfo = new Account(account_id, account_pass, account_name,
						address_number, address, tel, mailaddress, payment, role);
				resultList.add(accountInfo);
			}

		} catch (SQLException e) {
			// エラーハンドリング
			System.out.println("DAO ログイン SQLException");
			e.printStackTrace();
		} catch (Exception e) {
			// エラーハンドリング
			System.out.println("DAO ログイン Exception");
			e.printStackTrace();
		}
		//取得したアカウントデーターを含むArrayListを返す
		return resultList;
	}

	//---------------------------------------------------------------------
	//アカウント情報変更時アカウント探索操作(SELECT文)

	public Account findByAccountID(String id) {

		// ユーザ情報を格納するためのリスト
		Account result = new Account();

		//JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// エラーハンドリング
			System.out.println("JDBCドライバ関連エラー");
			e.printStackTrace();
		}

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT * FROM Account WHERE ACCOUNT_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			// SELECT文の結果をArrayListに格納
			while (rs.next()) {
				String account_id = rs.getString("account_id");
				String account_pass = rs.getString("account_pass");
				String account_name = rs.getString("account_name");
				String address_number = rs.getString("address_number");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String mailaddress = rs.getString("mailaddress");
				int payment = rs.getInt("payment");
				int role = rs.getInt("role");

				result = new Account(account_id, account_pass, account_name,
						address_number, address, tel, mailaddress, payment, role);
			}

		} catch (SQLException e) {
			// エラーハンドリング
			System.out.println("DAO ログイン SQLException");
			e.printStackTrace();
		} catch (Exception e) {
			// エラーハンドリング
			System.out.println("DAO ログイン Exception");
			e.printStackTrace();
		}
		//取得したアカウントデーターを含むArrayListを返す
		return result;
	}

	//-------------------------------------------------------
	//アカウント作成時重複確認操作(SELECT文)AccountInfoLogicに使用

	public ArrayList<String> checkAccountID(String Account_id) {

		// 複数のユーザ情報を格納するため、Beanを格納する配列を作成
		ArrayList<String> resultList = new ArrayList<>();

		//JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// エラーハンドリング
			System.out.println("JDBCドライバ関連エラー");
			e.printStackTrace();
		}

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT account_id FROM ACCOUNT WHERE account_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, Account_id);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			// SELECT文の結果をArrayListに格納
			while (rs.next()) {
				String getAccount_id = rs.getString("account_id");
				System.out.println(getAccount_id);
				resultList.add(getAccount_id);
			}

		} catch (SQLException e) {
			// エラーハンドリング
			System.out.println("DAO 重複チェック SQLException");
			e.printStackTrace();
		} catch (Exception e) {
			// エラーハンドリング
			System.out.println("DAO 重複チェック Exception");
			e.printStackTrace();
		}

		//取得したユーザーデーターを含むArrayListを返す
		return resultList;
	}

	//------------------------------------------------------------------------
	//Goodsリスト取得(SELECT文)

	public ArrayList<Goods> getList() {

		// 複数のtodoを格納するため、Beanを格納する配列を作成
		ArrayList<Goods> goodsList = new ArrayList<>();

		//JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// エラーハンドリング
			System.out.println("JDBCドライバ関連エラー");
			e.printStackTrace();
		}

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT * FROM goods ORDER BY goods_id";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECT文を実行して、結果を代入する
			ResultSet results = pStmt.executeQuery();

			while (results.next()) {
				int goods_id = results.getInt("goods_id");
				String goods_name = results.getString("goods_name");
				String goods_text = results.getString("goods_text").replaceAll("\n", "<br>");
				int price = results.getInt("price");
				int stock = results.getInt("stock");

				//DBから読み込んだデータをGoodsdのフィールドにセット
				Goods goods = new Goods(goods_id, goods_name, goods_text, price, stock);
				//postを仮引数のlistに追加していく。
				goodsList.add(goods);
			}

		} catch (SQLException e) {
			// エラーハンドリング
			System.out.println("リスト取得　SELECT文 SQLException e");
			e.printStackTrace();
		} catch (Exception e) {
			// エラーハンドリング
			System.out.println("リスト取得　SELECT文 Exception");
			e.printStackTrace();
		}
		//取得したタスクデーターを含むArrayListを返す
		return goodsList;
	}

	//------------------------------------------------------------------------
	//アカウント編集(UPDATE文)

	public boolean updateAccount(Account account) {

		//JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// エラーハンドリング
			System.out.println("JDBCドライバ関連エラー");
			e.printStackTrace();
		}

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// UPDATE文の準備
			String sql = "UPDATE account SET account_pass = ?, account_name = ?, address_number = ?, address = ?, tel = ?, mailaddress = ?, payment = ?  WHERE Account_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, account.getAccount_pass());
			pStmt.setString(2, account.getAccount_name());
			pStmt.setString(3, account.getAddress_number());
			pStmt.setString(4, account.getAddress());
			pStmt.setString(5, account.getTel());
			pStmt.setString(6, account.getMailaddress());
			pStmt.setInt(7, account.getPayment());
			pStmt.setString(8, account.getAccount_id());

			//UPDATE文を実行
			pStmt.executeUpdate();

		} catch (SQLException e) {
			// エラーハンドリング
			System.out.println("アカウント更新  UPDATE文 実行失敗");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//--------------------------------------------------------------------
	//購入履歴取得(SELECT文)

	public ArrayList<OrderInfo> getOrderHistry(Account account) {

		// ユーザ情報を格納するためのリスト
		ArrayList<OrderInfo> historyList = new ArrayList<>();

		//JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// エラーハンドリング
			System.out.println("JDBCドライバ関連エラー");
			e.printStackTrace();
		}

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "select ORDERHISTORY.order_id,ORDERHISTORY.order_day,GOODS.goods_name,order_quantity,total_price\n"
					+ "   from ORDERHISTORY\n"
					+ "   inner join ORDERINFO on ORDERHISTORY.order_id = ORDERINFO.order_id\n"
					+ "   inner join GOODS on ORDERINFO.Goods_id = GOODS.goods_id\n"
					+ "where ORDERINFO.account_id = ? ORDER BY ORDERHISTORY.order_id";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getAccount_id());

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			// SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int order_id = rs.getInt("order_id");
				LocalDate order_day = rs.getDate("order_day").toLocalDate();
				String goods_name = rs.getString("goods_name");
				int order_quantity = rs.getInt("order_quantity");
				int total_price = rs.getInt("total_price");

				OrderInfo history = new OrderInfo(order_id, order_day, goods_name, order_quantity, total_price);
				historyList.add(history);
			}

		} catch (SQLException e) {
			// エラーハンドリング
			System.out.println("DAO ログイン SQLException");
			e.printStackTrace();
		} catch (Exception e) {
			// エラーハンドリング
			System.out.println("DAO ログイン Exception");
			e.printStackTrace();
		}
		//取得したアカウントデーターを含むArrayListを返す
		return historyList;
	}

	//---------------------------------------------------------------------
	//購入履歴登録(INSERT文 + UPDATE文)

	public boolean insertOrderHistory(ArrayList<Goods> goodsList, Account account) {
		//JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// エラーハンドリング
			System.out.println("JDBCドライバ関連エラー");
			e.printStackTrace();
		}

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備①(orderhistoryに注文時間を追加)
			String sql1 = "INSERT INTO orderhistory (order_day) VALUES (?)";
			PreparedStatement pStmt1 = conn.prepareStatement(sql1);
			LocalDate date = LocalDate.now();
			pStmt1.setDate(1, Date.valueOf(date));
			//INSERT文を実行
			pStmt1.executeUpdate();

			for (Goods goods : goodsList) {
				//INSERT文の準備②orderhistoryテーブルのSIRIALの最終値を取得して引数のgoods情報と合わせてorderinfoテーブルに追加していく
				String sql2 = "INSERT INTO orderinfo (ORDER_ID,ACCOUNT_ID,GOODS_ID,ORDER_QUANTITY,TOTAL_PRICE) SELECT (SELECT last_value FROM orderhistory_order_id_seq),?,?,?, (SELECT price * ? AS total_price FROM goods WHERE goods_id = ? )";
				PreparedStatement pStmt2 = conn.prepareStatement(sql2);
				pStmt2.setString(1, account.getAccount_id());
				pStmt2.setInt(2, goods.getGoods_id());
				pStmt2.setInt(3, goods.getOrder_quantity());
				pStmt2.setInt(4, goods.getOrder_quantity());
				pStmt2.setInt(5, goods.getGoods_id());
				//INSERT文を実行
				pStmt2.executeUpdate();

				//UPDETA文を実行③注文分在庫数を減らす
				String sql3 = "UPDATE goods SET stock = stock - ? WHERE goods_id = ?";
				PreparedStatement pStmt3 = conn.prepareStatement(sql3);
				pStmt3.setInt(1, goods.getOrder_quantity());
				pStmt3.setInt(2, goods.getGoods_id());
				//UPDATE文を実行
				pStmt3.executeUpdate();

			}

		} catch (SQLException e) {
			// エラーハンドリング
			System.out.println("DAO 新規アカウント登録 SQLException");
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			System.out.println("DAO 新規アカウント登録 Exception");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//---------------------------------------------------------------------
	//グッズ編集(UPDATE文)
	public boolean updateGoods(Goods goods) {

		//JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// エラーハンドリング
			System.out.println("JDBCドライバ関連エラー");
			e.printStackTrace();
		}

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// UPDATE文の準備
			String sql = "UPDATE goods SET goods_name = ?, goods_text = ?, price = ?, stock = ?  WHERE goods_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, goods.getGoods_name());
			pStmt.setString(2, goods.getGoods_text());
			pStmt.setInt(3, goods.getPrice());
			pStmt.setInt(4, goods.getStock());
			pStmt.setInt(5, goods.getGoods_id());

			//UPDATE文を実行
			pStmt.executeUpdate();

		} catch (SQLException e) {
			// エラーハンドリング
			System.out.println("グッズ更新  UPDATE文 実行失敗");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//---------------------------------------------------------------------
	//グッズ削除(DELETE文)
	
	public boolean destoryGoods(int goods_id) {

		//JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// エラーハンドリング
			System.out.println("JDBCドライバ関連エラー");
			e.printStackTrace();
		}

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// DELETE文の準備
			String sql = "DELETE FROM goods WHERE goods_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, goods_id);

			//DELETE文を実行
			pStmt.executeUpdate();

		} catch (SQLException e) {
			// エラーハンドリング
			System.out.println("グッズ削除  DELETE文 実行失敗");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
