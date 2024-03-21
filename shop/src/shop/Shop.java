package shop;

public class Shop {
	public Shop(String name) {
		this.isRun = true;
	}
	private UserManager UserManager = new UserManager();
	private ItemManager ItemManager = new ItemManager();
	private boolean isRun;
	
	private void printStatus() {
		int UserSize = UserManager.getUserSize();
		int ItemSize = ItemManager.getItemSize();
		String status = String.format("User2 size : %d\nAccount2 size : %d", UserSize, ItemSize);

		System.out.println("------------");
		System.out.println(status);
	}
	
	public void run() {
		while(isRun) {
			printStatus();
			break;
		}
	}
}
