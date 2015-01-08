package testtest;

import java.util.ArrayList;
import java.util.List;

public class test4 {
	public int num;
	

	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public static void main(String[] args) {
		List<test4> listA = new ArrayList<test4>();
		for (int i = 0; i < 10; i++) {
			test4 test = new test4();
			test.setNum(i);
			listA.add(test);
		}
		List<test4> tmp = new ArrayList<test4>();
		tmp.addAll(listA);
		for (test4 test : tmp) {
			if (test.getNum() == 6) {
				listA.remove(test);
			}
		}
		System.out.println(listA.size());
	}
}
