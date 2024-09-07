package model;

import java.util.ArrayList;

import shopMail.ShopMail;

public class SendMail {
	String[] paymentlist = {"代引き", "銀行振込", "クレジットカード"}; 
	
	public void execute(ArrayList<Goods> wishList,Account loginAccount,String account_name,String address_number,String address,
			String tel,String mailaddress,int payment) {
		
		String orderMailDitail = "-----------------------------\n";
		int orderPrice = 0;
		int i = 1;
		
		for(Goods goods : wishList) {
			orderMailDitail += "(" + i++ + ")\n" +
							   "<商品名>　　　　　" + goods.getGoods_name() + "\n" +
							   "<注文数>　　　　　" + goods.getOrder_quantity() + "個\n" +
							   "<一つあたりの金額>" + goods.getPrice() + "円\n" +
							   "<合計金額>　　　　" + goods.getOrder_quantity() * goods.getPrice() + "円\n" +
							   "-----------------------------\n" ;
							   orderPrice += goods.getOrder_quantity() * goods.getPrice();
		}
		
		try {
			ShopMail.send(mailaddress, "Sweets Shop", "ご注文内容の確認",
							  "Sweets Shopをご利用いただきありがとうございます。\n"
							+ "\n"
							+ "【注文内容】\n"
							+ orderMailDitail
							+ "<お支払い合計金額>" + orderPrice +"円\n"
							+ "\n"
							+ "【配送情報】\n"
							+ "氏名　　："+ account_name +"\n"
							+ "郵便番号："+ address_number +"\n"
							+ "住所　　："+ address +"\n"
							+ "電話番号："+ tel +"\n"
							+ "アドレス："+ mailaddress +"\n"
							+ "支払方法："+ paymentlist[payment - 1] +"\n"
							+ "\n"
							+ "-----------------------------\n"
							+ "Sweets Shop\n"
							+ "sweets_shop_info@test.com\n"
							+ "〒920-8217\n"
							+ "石川県金沢市近岡町845-1\n"
							+ "Tel：000-1234-5678\n"
							);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
