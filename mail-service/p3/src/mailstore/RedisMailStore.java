package mailstore;

import java.util.List;

public interface RedisMailStore {

	public void lpush(String u, String m);
	public List<String> lrange(String u);
}
