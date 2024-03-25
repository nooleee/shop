package shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Vector;

public class FileManager {
	
	public FileManager() {
		this.fileName = "shop.txt";
		this.file = new File(fileName);
	}

	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	
	private File file;
	private String fileName;
	
	
	public void saveData(ArrayList<User> user, Vector<Item> item) {
		String data = createDataString(user, item);
		
		try {
			fw = new FileWriter(file);
			fw.write(data);
			fw.close();
			
			System.out.println("파일 저장 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("파일 저장 실패");
		}
	}
	
	private String createDataString(ArrayList<User> user, Vector<Item> item) {
		String data = "";
		/*
		 * 아이템이름,코드,가격/아이템이름,코드,가격/
		 * 아이디,비밀번호,코드,매출/아이디,비밀번호,코드/...
		 * 아이템,코드/아이템,코드/...
		 */
		
		//ItemManager
		if(item.size() > 0) {
			for(Item items : item) {
				data += items.getName() + "," + items.getCode() + "," + items.getPrice();
				data += "/";
			}
			data = data.substring(0, data.length()-1);
		}
		
		// UserManager
		for(User users : user) {
			data += "\n" + users.getId() + "," + users.getPassword() + "," + users.getCode() + "," + users.getTotal();
			
			for(int i = 0; i < users.getCart().cartSize(); i++) {
				Vector<Item> items = users.getCart().findCartAll();
				
				data += "/" + items.get(i).getName() + "," + items.get(i).getCode();
			}
		}
		
		return data;
	}

}
