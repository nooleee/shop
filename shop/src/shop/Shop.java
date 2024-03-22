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
	
	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int LOGIN = 3;
	private final int LOGOUT = 4;
	private final int SHOPPING = 5;
	private final int MYPAGE = 6;
	private final int BACK = 0;
	
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
	
	private String inputString(String messege) {
		System.out.println(messege + " : ");
		return scan.next();
	}
	
	private void showSubUserMenu() {
		System.out.println("------------");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원탈퇴");
		System.out.println("3. 로그인");
		System.out.println("4. 로그아웃");
		System.out.println("5. 쇼핑하기");
		System.out.println("6. 마이페이지");
		System.out.println("0. 뒤로가기");
		System.out.println("------------");
	}
	
	private void join() {
		
	}
	
	private void runSubUserMenu(int sel) {
		if(sel < 0 || sel > 6)
			return;
		
		if(sel == JOIN)
			join();
//		else if(sel == LEAVE)
//			leave();
//		else if(sel == LOGIN)
//			login();
//		else if(sel == LOGOUT)
//			ligout();
//		else if(sel == SHOPPING)
//			shopping();
//		else if(sel == MYPAGE)
//			myPage();
//		else if(sel == BACK)
//			return;
	}
	
	private void runMenu(int select) {
		switch (select) {
		case USER:
			showSubUserMenu();
			int sel = inputNumber("선택");
			runSubUserMenu(sel);
			
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
