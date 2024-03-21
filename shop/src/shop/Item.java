package shop;

public class Item {
	private String name;
	private int code;
	
	public Item() {
		
	}
	
	public Item(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return this.name;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public Item clone() {
		return new Item(this.name, this.code);
	}
}
