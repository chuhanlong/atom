package testtest;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import com.chu.clothes.dao.domain.User;

public class test2 {

	public static void main(String[] args) {
		String[] ccc = {"1","2","3"};
		String ss = Arrays.toString(ccc);
		System.out.println(ss.substring(1, ss.length()-1).replace(" ", ""));
		//计算距离的算法    (2 * 6378137* ASIN(
//	    SQRT(
//	    	    POW(
//	    	    SIN(
//	    	    PI()*(#{latitude,jdbcType=DECIMAL}-shop_lat)/360
//	    	    ),
//	    	    2)+COS(PI()*#{latitude,jdbcType=DECIMAL}/180)* COS(shop_lat * PI()/180)*POW(SIN(PI()*(#{longitude,jdbcType=DECIMAL}-shop_long)/360),2)))) 
		double abc = Math.asin(Math.sqrt(Math.pow(Math.sin(Math.PI*(100.1234/360)), 2)))
		+ Math.cos(Math.PI*(122.111/180)*Math.cos(123.00*Math.PI/180)*Math.pow(Math.sin(Math.PI*(123.12/360)), 2));
		System.out.println(abc);
		
		String aaa = ",112312123";
		String bbb = StringUtils.substringBetween(aaa, ",");
		System.err.println(bbb);
		if (aaa.startsWith(",")) {
			aaa = aaa.substring(1, aaa.length());
			System.out.println(aaa);
		}
		
		String phone = ",123";
		if (StringUtils.isNotBlank(phone)) {
			if (phone.startsWith(",")) {
				phone = StringUtils.substring(phone, 1);
			}
			if (phone.endsWith(",")) {
				phone = StringUtils.substring(phone, 0, phone.length()-1);
			}
		}
		System.out.println("phone = " + phone);
		
		
		
		String[] array = {"30","24"};
		String zzz = Arrays.toString(array);
		System.out.println("zzz"+zzz);
		String ddd = zzz.substring(1, zzz.length()-1).replace(" ", "");
		System.out.println("ddd = " + ddd);
		
		
		User user = new User();
		user.setAge((short)4);
		System.err.println(  user.getAge() == (new Short("4")).shortValue());
		
		
		System.out.println(System.currentTimeMillis());
	}
}
