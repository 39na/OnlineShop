package model;

import java.io.Serializable;
import java.time.LocalDate;

public class OrderInfo implements Serializable{
	private int order_id;
	private LocalDate order_day;
	private String goods_name;
	private int order_quantity;
	private int total_price;
	
	//コンストラクタ
	public OrderInfo() {}
	public OrderInfo(int order_id, LocalDate order_day, String goods_name, int order_quantity, int total_price) {
		this.order_id = order_id;
		this.order_day = order_day;
		this.goods_name = goods_name;
		this.order_quantity = order_quantity;
		this.total_price = total_price;
	}
	
	
	//ゲッター
	public int       getOrder_id() { return order_id; }
	public LocalDate getOrder_day() { return order_day; }
	public String       getGoods_name() { return goods_name; }
	public int       getOrder_quantity() { return order_quantity; }
	public int       getTotal_price() { return total_price; }
	
	
	//セッター
	public void       setOrder_id(int order_id) { this.order_id = order_id; }
	public void       setOrder_day(LocalDate order_day) { this.order_day = order_day; }
	public void       setGoods_id(String goods_name) { this.goods_name = goods_name; }
	public void       setOrder_quantity(int order_quantity) { this.order_quantity = order_quantity; }
	public void       setTotal_price(int total_price) { this.total_price = total_price; }
	
	
	
}