package p3.msfactory;

import p1.mailstore.InMemory;
import p1.mailstore.MailStore;

public class MemoryFactory implements MailStoreFactory {
	
	@Override
	public MailStore createMailStore() {
		return new InMemory();
	}
}
