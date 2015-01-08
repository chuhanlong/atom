package testtest;

import java.sql.Timestamp;

public class test3 {

	public static void main(String[] args) {
		long millis = System.currentTimeMillis();
		Timestamp time = new Timestamp(millis);
		Timestamp time1 = new Timestamp(millis + 60*1000l);
		System.out.println(time);
		System.out.println(time.getTime() + "     millis = " +millis);
		System.out.println(time1);
		
		
		System.out.println(",abc".substring(1));
	}
}
