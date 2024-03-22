package shop;

import java.util.Random;
import java.util.Vector;

public class UserManager {
	private Random ran = new Random();
	private Vector<User> list;
	
	public UserManager() {
		list = new Vector<>();
		list.add(new User("admin","1234", 123));
	}
	
	// 유저 등록
	public User createUser(String id, String password) {
		int code = generateUserCode();
		if(!findUserByUserId(id)) {
			User user = new User(id, password, code);
			list.add(user);
			return user.clone();
		}
		return new User();
	}
	
	// 코드 중복 검사
	public User findUserByUserCode(int code) {
		for(User user : list) {
			if(user.getCode() == code)
				return user.clone();
		}
		return new User();
	}
	
	// 코드 생성
	private int generateUserCode() {
		int code = 0;
		while(true) {
			code = ran.nextInt(9000) + 1000;
			
			User user = findUserByUserCode(code);
			if(user.getCode() == 0)
				break;
		}
		return code;
	}
	
	// id중복 검사
	public boolean findUserByUserId(String id) {
		for(User user : list) {
			if(user.getId().equals(id))
				return true;
		}
		return false;
	}
	
	// 유저 삭제
	public boolean deleteUser(User user) {
		int userCode = user.getCode();
		User target = getUserByUserCode(userCode);
		return list.remove(target);
	}
	
	public int getUserSize() {
		return this.list.size();
	}
	
	// 유저 정보
	public User getUserByUserCode(int code) {
		for(User user : list) {
			if(user.getCode() == code)
				return user;
		}
		return new User();
	}
	
	// 비밀번호 확인
	public int findUserByPassword(String password) {
		int code = 0;
		for(User user : list) {
			if(user.getPassword().equals(password))
				return code = user.getCode();
		}
		return code;
	}
	
	// 장바구니 담기
	
	// 장바구니 삭제
	
	// 장바구니 수정
	
}
