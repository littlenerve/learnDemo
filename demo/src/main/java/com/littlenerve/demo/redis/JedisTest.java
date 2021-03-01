package com.littlenerve.demo.redis;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class JedisTest {
	static Jedis jedis;
	static {
		jedis = new Jedis("127.0.0.1", 6379);
	}
	@Test
	void testPing(){
		System.out.println(jedis.ping());
	}

	/**
	 * redis事务就是一次性、顺序性、排他性的执行一个队列中的一系列命令。
	 * 若在事务队列中存在命令性错误（类似于java编译性错误），则执行EXEC命令时，所有命令都不会执行
	 * 若在事务队列中存在语法性错误（类似于java的1/0的运行时异常），则执行EXEC命令时，其他正确命令会被执行，错误命令抛出异常。
	 */
	@org.junit.Test(expected = Exception.class)
	public void testTX(){
		jedis.flushDB();
		Transaction multi = jedis.multi();
		try {
			multi.set("user", "littlenerve");
			multi.set("age", "18");
			multi.incr("user");
			multi.exec();
		} catch (Exception e) {
//			multi.discard();
			e.printStackTrace();
		} finally {
			System.out.println(jedis.get("user"));
			System.out.println(jedis.get("age"));
			jedis.close();
		}
	}
}
