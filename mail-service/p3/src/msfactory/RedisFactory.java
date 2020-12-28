package msfactory;

import mailstore.MailStore;
import mailstore.RedisClient;
import mailstore.StoreAdapter;

public class RedisFactory implements MailStoreFactory{
	
	@Override
	public MailStore createMailStore() {
		return new StoreAdapter(RedisClient.getInstance());
	}
}
