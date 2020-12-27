package msfactory;

import mailstore.MailStore;

public interface MailStoreFactory extends MailStore {
	public MailStore createMailStore();
}
