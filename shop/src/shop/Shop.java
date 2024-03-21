package shop;

import java.util.Scanner;

public class Shop {
	private Scanner scan = new Scanner(System.in);
	private UserManager UserManager = new UserManager();
	private ItemManager ItemManager = new ItemManager();
	private boolean isRun;
	
	private final int USER = 1;
	private final int ADMIN = 2;
	private final int FILE = 3;
	private final int EXIT = 0;
	
	public Shop(String name) {
		this.isRun = true;
	}
	
	
	
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
	
	private int inputNumber(String messege) {
		int number = 0;
		System.out.println(messege + " : ");
		try {
			String input = scan.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println("숫자만 입력 가능합니다.");
		}
		return number;
	}
	
	private void runMenu(int select) {
		switch (select) {
		case USER:
		case ADMIN:
		case FILE:
		case EXIT:
		}
	}
	
	public void run() {
		while(isRun) {
			printStatus();
			showMenus();
			int select = inputNumber("선택");
			runMenu(select);
		}
	}
}
