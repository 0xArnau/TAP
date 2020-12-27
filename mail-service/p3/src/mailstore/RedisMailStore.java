package mailstore;

import java.util.List;

import messages.Message;
import redis.clients.jedis.Jedis;

public interface RedisMailStore {
	final Jedis jedis = new Jedis("localhost");
	public void lpush(String u, String m);
	public List<Message> lrange(String u);
	public void flushAll();
}
