package model;

import java.util.regex.Pattern;

public class UpdateAccountlifoLogic {
	public String execute(String account_pass,String account_name,String address_number,
			String address,String tel, String mailaddress ,int payment) {
		String errorMessage = ""; 
		
        
		//パスワード判定
		if(account_pass.length() == 0){
			errorMessage += "パスワードが入力がされていません。<br>";
		}else if(!(Pattern.matches("^[A-Za-z0-9]+$",account_pass))){
			errorMessage += "パスワードの入力が不正です。(半角英数字)<br>"
					+ "入力：" + account_pass +"<br>";
		}
		
		if(account_pass.length() > 30) {
			errorMessage += "パスワードの入力が長すぎます。(30文字以内)<br>"
					+ "入力文字数：" + account_pass.length() + "文字<br>";
		}
			
		//氏名判定 30文字以内
		if(account_name.length() == 0) {
			errorMessage += "名前が入力がされていません。<br>";
		}else if(account_name.length() > 30) {
			errorMessage += "名前の入力が長すぎます。(30文字以内)<br>"
					+ "入力文字数：" + account_name.length() + "文字<br>";
		}
		
		//郵便番号判定　正規表現
		if(!(Pattern.matches("[0-9]{3}-[0-9]{4}",address_number))){
			errorMessage += "郵便番号の入力が不正です。<br>"
					+ "入力：" + address_number +"<br>";
		}
			
		//住所判定 100文字 
		if(address.length() > 100) {
				errorMessage += "住所の入力が長すぎます。(100文字以内)<br>"
						+ "入力文字数：" + address.length() + "文字<br>";
		}
		//電話番号 正規表現
		if(!(Pattern.matches("^[0-9]{3}-[0-9]{4}-[0-9]{4}$",tel))){
			errorMessage += "電話番号の入力が不正です。<br>"
					+ "入力：" + tel +"<br>";
		}
		
		//メールアドレス判定　60文字以内　正規表現
		if(!(Pattern.matches("^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$",mailaddress))){
			errorMessage += "メールアドレスの入力が不正です。<br>"
					+ "入力：" + mailaddress +"<br>";
		}else if(mailaddress.length() > 60){
			errorMessage += "メールアドレスの入力が長すぎます。(60文字以内)<br>"
					+ "入力文字数：" + mailaddress.length() + "文字<br>";
		}
		
		if(payment == 100) {
			errorMessage += "支払情報が選択されていません。<br>";
		}
		
		System.out.println(errorMessage);
		return errorMessage;
	}
}
