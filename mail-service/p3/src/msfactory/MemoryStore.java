package msfactory;

import mailstore.InMemory;
import mailstore.MailStore;

public class MemoryStore extends InMemory implements MailStoreFactory {

	@Override
	public MailStore createMailStore() {
		return null;
	}
	
}
