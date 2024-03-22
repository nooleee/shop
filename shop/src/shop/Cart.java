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
	
	// 수량 수정 (계산한 수량 받아서 바꿀만큼 +- 하기)
	
	// 수량 계산 (int값 반환 - count)
	
	// 
	
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
