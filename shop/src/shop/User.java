package shop;

public class User {
	private String id, password;
	private int code;
	private Cart cart;
	
	public User() {
	}
	
	public User(String id, String password, int code) {
		this.id = id;
		this.password = password;
		this.code = code;
		cart = new Cart();
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
	
	public Cart getCart() {
		return cart;
	}
	
//	public void getCode(int code) {
//		this.code = code;
//	}
	
	public User clone() {
		return new User(this.id, this.password, this.code);
	}
}
