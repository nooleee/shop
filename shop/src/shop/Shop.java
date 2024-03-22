package shop;

import java.util.Scanner;


public class Shop {
	private Scanner scan = new Scanner(System.in);
	private UserManager UserManager = new UserManager();
	private ItemManager ItemManager = new ItemManager();
	private boolean isRun;
	
	private int log;
	
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
		log = -1;
	}
	
	private boolean checkLog() {
		return log == -1 ? true : false;
	}
	
	private void printStatus() {
		int UserSize = UserManager.getUserSize();
		int ItemSize = ItemManager.getItemSize();
		String status = String.format("User2 size : %d\nAccount2 size : %d", UserSize, ItemSize);

		System.out.println("------------");
		System.out.println(status);
		System.out.println("log : " + log);
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
		String id = inputString("id");
		String password = inputString("password");
		
		if(!UserManager.findUserByUserId(id)) {
			User user = UserManager.createUser(id, password);
			printWelcomeMessage(user);
		}
		else
			System.err.println("이미 등록된 아이디 입니다.");
	}
	
	private void printWelcomeMessage(User User) {
		String message = User.getCode() != 0 ? String.format("%s(%d) 회원님 환영합니다.", User.getId(), User.getCode())
				: "회원가입 실패";
		System.out.println(message);
	}
	
	private void leave() {
		String password = inputString("비밀번호 확인");
		int code = UserManager.findUserByPassword(password);
		if(code != 0) {
			User user = UserManager.getUserByUserCode(code);
			UserManager.deleteUser(user);
			System.out.println("회원 탈퇴 완료");
		}
		else
			System.err.println("비밀번호를 다시 확인하세요.");
	}
	
	private void login() {
		String id = inputString("id");
		String password = inputString("password");
		int code = 0;
		
		for(User user : UserManager.findUserAll()) {
			if(user.getId().equals(id) && user.getPassword().equals(password)) {
				code = user.getCode();
			}
		}
		
		if(code != 0) {
			log = code;
			System.out.println("로그인 완료");
		}
		else
			System.out.println("회원 정보를 확인해주세요.");
	}
	
	private void logout() {
		log = -1;
		System.out.println("로그아웃 되었습니다.");
	}
	
	private void shopping() {
		printItemAll();
		int choice = inputNumber("선택")-1;
		
		if(choice < 1 || choice > ItemManager.getItemSize()) {
			System.out.println("아이템 번호를 다시 확인하세요.");
			return;
		}
		String name = ItemManager.getItemName(choice);
		Cart cart = UserManager.getUserByUserCode(log).getCart();
		cart.addItem(name, choice);
	}
	
	private void printItemAll() {
		for(Item item : ItemManager.findItemAll()) {
			System.out.printf("%d) %s\n", item.getCode() + 1, item.getName());
		}
	}
	
	private void mypage() {
		showMypageSubMenu();
		int choice = inputNumber("선택");
//		runMypageSubMenu(choice);
	}
	
	private void showMypageSubMenu() {
		System.out.println("------------");
		System.out.println("1. 장바구니");
		System.out.println("2. 항목 삭제");
		System.out.println("3. 수량 수정");
		System.out.println("4. 결제");
		System.out.println("0. 뒤로가기");
		System.out.println("------------");
	}
	
	private void runSubUserMenu(int sel) {
		if(sel < 0 || sel > 6)
			return;
		
		if(sel == JOIN && checkLog())
			join();
		else if(sel == LEAVE && !checkLog())
			leave();
		else if(sel == LOGIN && checkLog())
			login();
		else if(sel == LOGOUT)
			logout();
		else if(sel == SHOPPING && !checkLog())
			shopping();
		else if(sel == MYPAGE && !checkLog())
			mypage();
//		else if(sel == BACK)
//			return;
	}
	
	private void runMenu(int select) {
		switch (select) {
		case USER:
			showSubUserMenu();
			int sel = inputNumber("선택");
			runSubUserMenu(sel);
			break;
		case ADMIN:
		case FILE:
		case EXIT:
			isRun = false;
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
