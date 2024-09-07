package model;

import java.io.Serializable;

public class Goods implements Serializable{
	private int goods_id;
	private String goods_name;
	private String goods_text;
	private int price;
	private int stock;
	private int order_quantity;


	//コンストラクタ
	public Goods() {}
	public Goods(int goods_id,String goods_name,String goods_text, int price,int stock) {
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.goods_text = goods_text;
		this.price = price;
		this.stock = stock;
	}
	public Goods(int goods_id,String goods_name,String goods_text, int price,int stock,
			int order_quantity) {
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.goods_text = goods_text;
		this.price = price;
		this.stock = stock;
		this.order_quantity = order_quantity;
	}
	
	//セッター
	public void setGoods_id(int goods_id) { this.goods_id = goods_id; }
	public void setGoods_name(String goods_name) { this.goods_name = goods_name; }
	public void setGoods_text(String goods_text) { this.goods_text = goods_text; }
	public void setPrice(int price) { this.price =  price; }
	public void setStock(int stock) { this.stock = stock; }
	public void setOrder_quantity(int order_quantity) { this.order_quantity = order_quantity; }
	
	//ゲッター
	public int      getGoods_id() { return goods_id; }		
	public String   getGoods_name() { return goods_name; }
	public String   getGoods_text() { return goods_text; }
	public int      getPrice() { return price; }
	public int      getStock() { return stock; }
	public int      getOrder_quantity() { return order_quantity; }
}
