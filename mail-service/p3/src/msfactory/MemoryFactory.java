package msfactory;

import mailstore.InMemory;
import mailstore.MailStore;

public class MemoryFactory implements MailStoreFactory {
	
	@Override
	public MailStore createMailStore() {
		return new InMemory();
	}
}
