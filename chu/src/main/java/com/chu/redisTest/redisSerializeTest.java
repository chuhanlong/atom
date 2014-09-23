package com.chu.redisTest;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class redisSerializeTest {

//    @Autowired
//    public RedisConnectionFactory connectionFactory = new ClassPathXmlApplicationContext("classpath:spring.xml");
	private static ClassPathXmlApplicationContext cont = new ClassPathXmlApplicationContext("classpath:spring.xml");
	private static RedisConnectionFactory connectionFactory = (RedisConnectionFactory)cont.getBean("jedisConnectionFactory");
    public RedisConnectionFactory getConnectionFactory(){
//    	ClassPathXmlApplicationContext cont = new ClassPathXmlApplicationContext("classpath:spring.xml");
//    	RedisConnectionFactory connectionFactory = (RedisConnectionFactory)cont.getBean("jedisConnectionFactory");
//    	RedisConnection con = connectionFactory.getConnection();
    	return connectionFactory;
    }
    
    public RedisConnection getConnection(){
//    	ClassPathXmlApplicationContext cont = new ClassPathXmlApplicationContext("classpath:spring.xml");
//    	RedisConnectionFactory connectionFactory = (RedisConnectionFactory)cont.getBean("jedisConnectionFactory");
    	RedisConnection con = connectionFactory.getConnection();
    	return con;
    }
    public static void main(String[] args) {
    	redisSerializeTest test = new redisSerializeTest();
    	RedisConnection redis = test.getConnection();
    	System.out.println(redis.dbSize());
    	redis.get(new byte[]{1});
    	System.out.println(redis.get(new byte[]{1}));
    	RedisTemplate< String, String> tem = new RedisTemplate<String, String>();
    	tem.setConnectionFactory(test.getConnectionFactory());
    	tem.afterPropertiesSet();
    	tem.opsForValue().get("chuhanlong");
    	
    	System.out.println(tem.opsForValue().get("chuhanlong"));
//    	if (redis != null) {
//    		System.out.println("now dbsize = "+redis.dbSize());
//		}
	}
}
