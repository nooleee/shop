package shop;

import java.util.Vector;

public class Cart {
	private Vector<Item> list;

	public Cart() {
		list = new Vector<>();
	}
	
	// 장바구니 추가
	public void addItem(String name, int code) {
		list.add(new Item(name, code));
	}
	
	// 장바구니 삭제(모든 해당 아이템 삭제)
	public void deleteItem(String name) {
		for(Item item : list) {
			if(item.getName().equals(name))
				list.remove(item);
		}
	}
	
	// 수량 수정 (계산한 수량 받아서 바꿀만큼 - 하기)
	public void minusItemCount(int count, String name) {
		int cnt = count;
		while(cnt > 0) {
			for(Item item : list) {
				if(item.getName().equals(name)) {
					list.remove(item);
					break;
				}
			}
			cnt--;
		}
	}
	
	// 수량 수정 (계산한 수량 받아서 바꿀만큼 + 하기)
	public void plusItemCount(int count, String name) {
		int cnt = count;
		
		while(cnt > 0) {
			Item item = getItemByName(name);
			list.add(item);
			cnt--;
		}
	}
	
	// 아이템 정보
	public Item getItemByName(String name) {
		for(Item item : list) {
			if(item.getName().equals(name))
				return item.clone();
		}
		return new Item();
	}
	
	// 수량 계산 (int값 반환 - count)
	public int getItemCount(int code) {
		int count = 0;
		for(Item item : list) {
			if(item.getCode() == code)
				count++;
		}
		return count;
	}
	
	// 장바구니 보여주기
	public void printCartAll(Vector<Item> items) {
		for(int i = 0; i < items.size(); i++) {
			Item target = items.get(i);
			int count = 0;
			for(Item item : list) {
				int code = target.getCode();
				count = getItemCount(code);
			}
			if(count != 0)
				System.out.printf("[%s] : %d개\n", target.getName(), count);
		}
		System.out.println();
	}
	
//	while (true) {
//		for (int i = 0; i < itemCount; i++) {
//			System.out.printf("%d) %s\n", i + 1, items[i]);
//		}
//		System.out.println("0)종료");
//		
//		System.out.print("아이템 번호 선택 : ");
//		int itemNum = scan.nextInt();
//
//		if (itemNum == 0)
//			break;
//
//		if (itemNum < 1 || itemNum > itemCount)
//			continue;
//
//		// 구매이력을 jang에 기록
//		int[][] tempJang = jang;
//		jang = new int[jangCount + 1][];
//
//		for (int i = 0; i < jangCount; i++) {
//			jang[i] = tempJang[i];
//		}
//
//		jang[jangCount] = new int[2];
//		jang[jangCount][0] = log;
//		jang[jangCount][1] = itemNum;
//
//		jangCount++;
//	}
	
	

}
