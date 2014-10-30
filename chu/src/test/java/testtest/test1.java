package testtest;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class test1 {

	public static void main(String[] args) {
		String key = "todoset.abc";
//		key = key.replaceFirst("d", key);
		if (!key.equals("todoset") && key.contains("todoset")){
			key = key.replaceFirst("todoset", key);
			System.err.println(key);
		}
		System.out.println(key);
		
		String abc = ",1,2,3,4,5,6,7";
		String[] a = StringUtils.split(abc, ",");
		List<String> list = Arrays.asList(a);
		Integer i = 5;
		if (list.contains(String.valueOf(i))) {
			System.out.println("right");
		}
		System.out.println(list);
		System.out.println(list.size());
	}
}
