package p3.mailstore;

import java.util.List;

import p1.messages.Message;
import redis.clients.jedis.Jedis;

public interface RedisMailStore {
	final Jedis jedis = new Jedis("localhost");
	public void lpush(String u, String m);
	public List<Message> lrange(String u);
	public void flushAll();
}
