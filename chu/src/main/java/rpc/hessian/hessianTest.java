package rpc.hessian;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chu.clothes.dao.domain.Test;
import com.chu.clothes.service.TestService;

public class hessianTest {


	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring.xml");
		TestService testService = (TestService) applicationContext.getBean("goGoodsService");
		List<Test> list = testService.findById(1);
		System.out.println(list.size());
	}
}
