package model;

import java.io.Serializable;

public class Account implements Serializable{
	private String account_id;
	private String account_pass;
	private String account_name;
	private String address_number;
	private String address;
	private String tel;
	private String mailaddress;
	private int payment;
	private int role;
	

	//コンストラクタ
	public Account() {}
	public Account(String account_id, String account_pass, String account_name,
			String address_number, String address, String tel, String mailaddress, int payment) {
		this.account_id = account_id;
		this.account_pass = account_pass;
		this.account_name = account_name;
		this.address_number = address_number;
		this.address = address;
		this.tel = tel;
		this.mailaddress = mailaddress;
		this.payment = payment;
		}
	
	public Account(String account_id, String account_pass, String account_name,
			String address_number, String address, String tel, String mailaddress, int payment,int role) {
		this.account_id = account_id;
		this.account_pass = account_pass;
		this.account_name = account_name;
		this.address_number = address_number;
		this.address = address;
		this.tel = tel;
		this.mailaddress = mailaddress;
		this.payment = payment;
		this.role = role;
		
	}
	
	//ゲッター
	public String getAccount_id(){ return account_id ; }
	public String getAccount_pass(){ return account_pass ; }
	public String getAccount_name(){ return account_name ; }
	public String getAddress_number(){ return address_number ; }
	public String getAddress(){ return address ; }
	public String getTel(){ return tel ; }
	public String getMailaddress(){ return mailaddress ; }
	public int getPayment(){ return payment ;}
	public int getRole(){ return role ;	}
	
	//セッター
	public void setAccount_id(String account_id){ this.account_id = account_id; }
	public void setAccount_pass(String account_pass){ this.account_pass = account_pass; ; }
	public void setAccount_name(String account_name){ this.account_name = account_name; ; }
	public void setAddress_number(String address_number){ this.address_number = address_number ; }
	public void setAddress(String address){ this.address = address ; }
	public void setTel(String tel){ this.tel = tel ; }
	public void setMailaddress(String mailaddress){ this.mailaddress = mailaddress ; }
	public void setPayment(int payment){ this.payment = payment ; }
	public void setRole(int role){ this.role = role ; }
}
