package mailstore;

import java.util.List;

public class RedisClient implements RedisMailStore {

	private static RedisClient instance = null;

	public RedisClient() {
	}

	public static RedisClient getInstance() {
		if (RedisClient.instance == null)
			RedisClient.instance = new RedisClient();
		return RedisClient.instance;
	}

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
