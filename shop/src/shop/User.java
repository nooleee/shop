package shop;

public class User {
	private String id, password;
	private int code;
	private Cart cart;
	private int total;
	
	public User() {
	}
	
	public User(String id, String password, int code) {
		this.id = id;
		this.password = password;
		this.code = code;
		cart = new Cart();
	}
	
	public User(String id, String password, int code, int total, Cart cart) {
		this.id = id;
		this.password = password;
		this.code = code;
		this.total = total;
		this.cart = cart;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public int getTotal() {
		return this.total;
	}
	
	public Cart getCart() {
		return this.cart;
	}
	
	public void setTotal(int price) {
		total += price;
	}
	
//	public void getCode(int code) {
//		this.code = code;
//	}
	
	public User clone() {
		return new User(this.id, this.password, this.code, this.total, this.cart);
	}
}
