package p3.msfactory;

import p1.mailstore.MailStore;
import p3.mailstore.RedisClient;
import p3.mailstore.StoreAdapter;

public class RedisFactory implements MailStoreFactory{
	
	@Override
	public MailStore createMailStore() {
		return new StoreAdapter(RedisClient.getInstance());
	}
}
