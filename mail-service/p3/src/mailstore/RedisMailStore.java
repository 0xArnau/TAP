package mailstore;

import java.util.List;

import redis.clients.jedis.Jedis;

public interface RedisMailStore {
	final Jedis jedis = new Jedis("localhost");
	public void lpush(String u, String m);
	public List<String> lrange(String u);
	public void flushAll();
}
