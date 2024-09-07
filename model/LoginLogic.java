package model;

import java.util.regex.Pattern;

public class LoginLogic {
	public String execute(String user_id,String user_pass) {
		String errorMessage = ""; 
		
		//ユーザーID判定

		if(user_id.length() == 0) {
			errorMessage += "ユーザーIDが入力がされていません。<br>";
		}else if(!(Pattern.matches("^[A-Za-z0-9]+$",user_id))){
			errorMessage += "ユーザーIDの入力が不正です。(半角英数字)<br>";
		}
		
		if(user_id.length() > 30) {
			errorMessage += "ユーザーIDの入力が長すぎます。(30文字以内)<br>";
		}
		
		
		//パスワード判定
		if(user_pass.length() == 0){
			errorMessage += "パスワードが入力がされていません。<br>";
		}else if(!(Pattern.matches("^[A-Za-z0-9]+$",user_id))){
			errorMessage += "パスワードの入力が不正です。(半角英数字)<br>";
		}
		
		if(user_id.length() > 30) {
			errorMessage += "パスワードの入力が長すぎます。(30文字以内)<br>";
		}
			
		return errorMessage;
	}
	
}