package mailstore;

import java.util.List;

public class RedisStore implements RedisMailStore {

	private static RedisStore instance = null;

	public RedisStore() {}

	public static RedisStore getInstance() {
		if (RedisStore.instance == null)
			RedisStore.instance = new RedisStore();
		return RedisStore.instance;
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
