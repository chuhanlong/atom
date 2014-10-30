package com.chu.redisTest;

import redis.clients.jedis.Jedis;

import com.chu.dao.domain.User;

public class JedisTest {

	public static void main(String[] args) {
		
		Jedis jedis = new Jedis("10.1.77.17",6379);
		String keys = "chuhanlong";
		User user = new User();
		user.setId(1);
		user.setName("123chu");
		jedis.set(keys, "chu");
		jedis.set(keys, "1");
		jedis.incr(keys);
		String value = jedis.get("1");
		
		System.out.println(value);
		
		System.out.println(jedis.flushDB());
		System.out.print(jedis.dbSize());
		jedis.close();
	}
}
