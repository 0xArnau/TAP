package msfactory;

import mailstore.EncodeDecorator;
import mailstore.MailStore;
import mailstore.OnFile;

public class FileFactory implements MailStoreFactory {

	@Override
	public MailStore createMailStore() {
		return new EncodeDecorator(new OnFile());
	}
	
}
