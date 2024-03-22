package shop;

import java.util.Scanner;
import java.util.Vector;


public class Shop {
	private Scanner scan = new Scanner(System.in);
	private UserManager UserManager = new UserManager();
	private ItemManager ItemManager = new ItemManager();
	private boolean isRun;
	
	private int log;
	private final int ADMINCODE = 123;
	
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
	
	private final int VIEW_CART = 1;
	private final int DELETE_ITEM = 2;
	private final int MODIFY_ITEM = 3;
	private final int PAYMENT = 4;
	
	private final int ADD_ITEM = 1;
	private final int DELETE_ITEMS = 2;
	private final int MODIFY_PRICE = 3;
	private final int SEARCH_TOTAL_PRICE = 4;
	
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
		while(true) {
			printItemAll();
			System.out.println("0) 종료");
			int choice = inputNumber("선택");
			
			if(choice == 0)
				break;
			
			if(choice < 1 || choice > ItemManager.getItemSize()) {
				System.out.println("아이템 번호를 다시 확인하세요.");
				continue;
			}
			String name = ItemManager.getItemName(choice-1);
			Cart cart = UserManager.getUserByUserCode(log).getCart();
			cart.addItem(name, choice-1);
			
		}
	}
	
	private void printItemAll() {
		for(Item item : ItemManager.findItemAll()) {
			System.out.printf("%d) %s (%d원)\n", item.getCode() + 1, item.getName(), item.getPrice());
		}
	}
	
	private void mypage() {
		showMypageSubMenu();
		int choice = inputNumber("선택");
		runMypageSubMenu(choice);
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
	
	private void viewCart() {
		Vector<Item> items = ItemManager.findItemAll();
		User user = UserManager.getUserByUserCode(log);
		
		user.getCart().printCartAll(items);
	}
	
	private void deleteItem() {
		Cart cart = UserManager.getUserByUserCode(log).getCart();
		printItemAll();
		int code = inputNumber("선택")-1;
		
		if(code < 0 || code > ItemManager.getItemSize()) {
			System.err.println("아이템 번호를 다시 확인해주세요.");
			return;
		}
		
		cart.deleteItem(code);
	}
	
	private void modifyItemCount() {
		showModifySubMenu();
		int sel = inputNumber("선택");
		runModifySubMenu(sel);
	}
	
	private void runModifySubMenu(int sel) {
		if(sel < 1 || sel > 2)
			return;
		
		if(sel == 1)
			plus();
		else if(sel == 2)
			minus();
	}
	
	private void minus() {
		User user = UserManager.getUserByUserCode(log);
		printItemAll();
		
		int code = inputNumber("원하는 항목")-1;
		
		if(code < 0 || code >= ItemManager.getItemSize()) {
			System.err.println("장바구니에 없는 항목입니다.");
			return;
		}
		
		String name = ItemManager.getItemName(code);
		int count = inputNumber("원하는 수량");
		
		user.getCart().minusItemCount(count, name);
	}
	
	private void plus() {
		User user = UserManager.getUserByUserCode(log);
		
		printItemAll();
		int code = inputNumber("원하는 항목")-1;
		if(code < 0 || code > user.getCart().cartSize()) {
			System.err.println("아이템 목록에 없는 항목입니다.");
			return;
		}
		
		String name = ItemManager.getItemName(code);
		int count = inputNumber("원하는 수량");
		
		user.getCart().plusItemCount(count, name);
	}
	
	private void showModifySubMenu() {
		System.out.println("1)추가");
		System.out.println("2)삭제");
	}
	
	private void payMent() {
		Vector<Item> items = ItemManager.findItemAll();
		int total = getTotal(items);
		
		System.out.println("==== 영수증 ====");
		viewCart();
		System.out.printf("결제 금액 : %d원", total);
		System.out.println("[결제 완료]");
		
		Cart cart = UserManager.getUserByUserCode(log).getCart();
		cart.resetCart();
		User admin = UserManager.getUserByUserCode(ADMINCODE);
		admin.setTotal(total);
	}
	
	private int getTotal(Vector<Item> items) {
		int total = 0;
		Cart cart = UserManager.getUserByUserCode(log).getCart();
		for(int i = 0; i < items.size(); i++) {
			Item target = items.get(i);
			int price = target.getPrice();
			for(Item item : cart.findCartAll()) {
				if(item.getCode() == target.getCode())
					total += price;
			}
		}
		return total;
	}
	
	private void runMypageSubMenu(int choice) {
		if(choice < 0 || choice > 4)
			return;
		
		if(choice == VIEW_CART)
			viewCart();
		else if(choice == DELETE_ITEM)
			deleteItem();
		else if(choice == MODIFY_ITEM)
			modifyItemCount();
		else if(choice == PAYMENT)
			payMent();
		else if(choice == BACK)
			return;
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
		else if(sel == BACK)
			return;
	}
	
	private boolean checkAdminCode() {
		boolean isAdmin = false;
		if(log == ADMINCODE)
			return isAdmin = true;
		
		if(!isAdmin)
			System.out.println("관리자 계정이 아닙니다.");
		
		return isAdmin;
	}
	
	private void showAdminMenu() {
		System.out.println("------------");
		System.out.println("1. 아이템 등록");
		System.out.println("2. 아이템 삭제");
		System.out.println("3. 아이템 가격 수정");
		System.out.println("4. 총 매출 조회");
		System.out.println("0. 뒤로가기");
		System.out.println("------------");
	}
	
	private void addItem() {
		String name = inputString("등록할 아이템");
		int price = inputNumber("가격");
		
		if(!ItemManager.findItemByName(name)) {
			Item item = ItemManager.addItem(name, price);
			printAddMessege(item);
		}
	}
	
	private void printAddMessege(Item item) {
		String message = String.format("%s(%d)이 등록되었습니다.", item.getName(), item.getPrice());
		System.out.println(message);
	}
	
	private void runAdminMenu(int choice) {
		if(choice < 0 || choice > 4) 
			return;
		
		if(choice == ADD_ITEM)
			addItem();
//		else if(choice == DELETE_ITEMS)
//		else if(choice == MODIFY_PRICE)
//		else if(choice == SEARCH_TOTAL_PRICE)
		else if(choice == BACK)
			return;
	}
	
	private void runMenu(int select) {
		switch (select) {
		case USER:
			showSubUserMenu();
			int sel = inputNumber("선택");
			runSubUserMenu(sel);
			break;
		case ADMIN:
			if(!checkLog() && checkAdminCode()) {
				showAdminMenu();
				int choice = inputNumber("선택");
				runAdminMenu(choice);
			}
			break;
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
