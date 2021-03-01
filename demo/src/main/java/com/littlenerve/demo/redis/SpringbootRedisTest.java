package com.littlenerve.demo.redis;

import com.littlenerve.demo.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class SpringbootRedisTest {
	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	void test(){
		redisTemplate.opsForValue().set("user", "littlenerve");
		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
	}
	@org.junit.Test(expected = Exception.class)
	public void test1(){
		redisTemplate.opsForValue().set("user",new Person("littlenerve",18));

	}
}
