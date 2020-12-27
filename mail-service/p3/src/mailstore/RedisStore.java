package mailstore;

import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisStore implements RedisMailStore {

	@Override
	public void lpush(String u, String m) {
		jedis.lpush(u, m);
	}

	@Override
	public List<String> lrange(String u) {
		return jedis.lrange(u, 0, -1);
	}

	@Override
	public void flushAll() {
		jedis.flushAll();
	}
}
