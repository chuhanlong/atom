package com.chu.pattern.prototype;

import java.util.ArrayList;
import java.util.List;

public class test implements Cloneable{

	private String name;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Integer obj = new Integer(1);
		List abc = new ArrayList();
		test test = new test();
		test.setName("tom");
		test test1 =(test) test.clone();
		System.out.println(test.equals(test1));
		System.out.println(test1.getName());
	}

}
