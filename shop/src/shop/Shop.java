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
	
	private void showMenus() {
		System.out.println("------------");
		System.out.println("1. 유저");
		System.out.println("2. 관리자");
		System.out.println("3. 파일");
		System.out.println("0. 종료");
		System.out.println("------------");
	}
	
	public void run() {
		while(isRun) {
			printStatus();
			showMenus();
		}
	}
}
