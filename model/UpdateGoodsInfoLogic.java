package model;

public class UpdateGoodsInfoLogic {
	public String execute(String goods_name ,String goods_text, int price,int stock) {
		String errorMessage = ""; 
		
        
		//商品名判定
		if(goods_name.length() == 0){
			errorMessage += "商品名が入力がされていません。<br>";
		}else if(goods_name.length() > 20) {
			errorMessage += "商品名の入力が長すぎます。(20文字以内)<br>";
		}
			
		//商品説明判定
		if(goods_text.length() == 0) {
			errorMessage += "商品説明が入力がされていません。<br>";
		}else if(goods_text.length() > 100) {
			errorMessage += "商品説明の入力が長すぎます。(100文字以内)<br>";
		}
		
		//単価判定
		if(price == -1) {
			errorMessage += "値段の入力が不正です。<br>";
		}else if(price >= 10000) {
			errorMessage += "値段が高すぎます。10000円以下<br>";
		}
		
		//ストック数判定
		if(stock == -1) {
			errorMessage += "ストック数の入力が不正です。<br>";
		}else if(stock > 1000){
			errorMessage += "ストック数が多すぎます。1000個以下<br>";
		}
		
		System.out.println(errorMessage);
		return errorMessage;
	}
}
