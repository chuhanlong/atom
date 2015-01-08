package testtest;

import com.chu.dao.domain.User;

public class test5 {

	public static void main(String[] args) {
		User u = new User();
		System.out.println(u.getClass().getClassLoader().getClass().getName());
		Integer i = new Integer(0);
		System.out.println(i.getClass());
	}
}
