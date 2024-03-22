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
	public void deleteItem(int code) {
		int count = getItemCount(code);
		while(count > 0) {
			for(int i = 0; i < list.size(); i++) {
				Item item = list.get(i);
				if(item.getCode() == code)
					list.remove(item);
			}
			count --;
		}
	}
	
	// 수량 수정 (계산한 수량 받아서 바꿀만큼 - 하기)
	public void minusItemCount(int count, String name) {
		int cnt = count;
		while(cnt > 0) {
			for(int i = 0; i < list.size(); i++) {
				Item item = list.get(i);
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
			if(item.getName() == null) {
				System.err.println("장바구니에 없는 항목입니다.");
				break;
			}
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
	
//	// 장바구니 아이템 개수
//	public int cartItemCount() {
//		int cnt = 0;
//		for(Item item : list) {
//			if(item.getCode() == cnt) 
//				cnt++;
//		}
//		return cnt;
//	}
	
	public int cartSize() {
		return list.size();
	}
	

}
