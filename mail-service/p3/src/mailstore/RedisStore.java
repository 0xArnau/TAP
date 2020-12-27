package mailstore;

import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisStore implements RedisMailStore {

	static Jedis jedis = null;

	public static void setJedis(Jedis j) {
		jedis = j;
	}

	@Override
	public void lpush(String u, String m) {
		jedis.lpush(u, m);
	}

	@Override
	public List<String> lrange(String u) {
		return jedis.lrange(u, 0, -1);
	}
	
}
