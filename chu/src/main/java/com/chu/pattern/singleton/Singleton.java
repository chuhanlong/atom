package com.chu.pattern.singleton;

/**
 * 单例模式
 * @author Administrator
 *
 */
public class Singleton {

	private static Singleton obj	= new Singleton();
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		return obj;
	}
	
	public static void main(String[] args) {
		Singleton obj = new Singleton();
		if (obj == Singleton.obj) {
			System.out.println("123");
		}
		if (Singleton.obj == Singleton.getInstance()) {
			System.out.println(456);
		}
	}
}
