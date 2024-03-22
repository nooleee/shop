package shop;

import java.util.Vector;

public class ItemManager {
	private Vector<Item> list;
	
	public ItemManager() {
		list = new Vector<>();
		list.add(new Item("사과", list.size(), 500));
		list.add(new Item("바나나", list.size(), 600));
		list.add(new Item("딸기", list.size(), 1000));
	}
	
	// 상품 등록
	public Item addItem(String name) {
		if(!findItemByName(name)) {
			Item item = new Item(name, list.size());
			list.add(item);
			return item.clone();
		}
		return new Item();
	}
	
	// 아이템 중복 검사
	public boolean findItemByName(String name) {
		for(Item item : list) {
			if(item.getName().equals(name))
				return true;
		}
		return false;
	}
	
	// 아이템 삭제
	public boolean deleteItem(Item item) {
		String name = item.getName();
		Item taget = getItemByName(name);
		return list.remove(taget);
	}
	
	// 아이템 정보 (name)
	public Item getItemByName(String name) {
		for(Item item : list) {
			if(item.getName().equals(name))
				return item;
		}
		return new Item();
	}
	
	// 아이템 정보 (index)
	public Item getItemByCode(int index) {
		Item item = list.get(index);
		return item;
	}
	
	// 아이템 인덱스 찾기(name)
	public int getItemIndex(String name) {
		int index = -1;
		for(Item item : list) {
			if(item.getName().equals(name))
				index = item.getCode();
		}
		return index;
	}
	
	// 아이템 이름 찾기(code)
	public String getItemName(int code) {
		String name = "";
		
		for(Item item : list) {
			if(item.getCode() == code)
				name = item.getName();
		}
		return name;
	}
	
	// 아이템 리스트 확인
	public Vector<Item> findItemAll() {
		Vector<Item> copy = new Vector<>();
		for(Item item : list) {
			copy.add(item.clone());
		}
		return copy;
	}
	
	// 아이템 수정
	public void replaceItem(String name, int price) {
		int index = getItemIndex(name);
		
		if(index == -1) {
			System.err.println("아이템 이름을 다시 확인해주세요");
			return;
		}
		
		Item item = new Item(name, index, price);
		list.set(index, item);
	}
	
	public int getItemSize() {
		return list.size();
	}
 	
}
