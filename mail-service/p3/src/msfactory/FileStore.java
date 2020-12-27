package msfactory;

import mailstore.MailStore;
import mailstore.OnFile;
// extends OnFile / EncodeDecorator
public class FileStore extends OnFile implements MailStoreFactory {

	@Override
	public MailStore createMailStore() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
