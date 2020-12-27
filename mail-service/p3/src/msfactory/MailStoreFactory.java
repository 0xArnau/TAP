package msfactory;

import mailstore.EncodeDecorator;
import mailstore.InMemory;
import mailstore.MailStore;
import mailstore.OnFile;
import mailstore.RedisClient;
import mailstore.StoreAdapter;

public class MailStoreFactory {
	public static MailStore createMailStore(StoreType type) {
		MailStore store = null;
		switch (type) {
			case FILE:
				store = new EncodeDecorator(new OnFile());
			break;
			case MEMORY:
				store = new InMemory();
			break;
			case REDIS:
				store = new StoreAdapter(RedisClient.getInstance());
			break;
		}
		return store;
	}
}
