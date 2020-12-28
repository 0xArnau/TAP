package system;

import mailbox.MailBox;
import mailstore.MailStore;
import msfactory.MailStoreFactory;
import users.User;

public class MailSystemFactory extends MailSystem {
	
	MailStoreFactory msf  = null;
	public MailSystemFactory(MailStoreFactory msf) {
		this.msf = msf;
	}

	@Override
	public MailBox newUser(User u, MailStore ignore) {
		return super.newUser(u, msf.createMailStore());
	}
}
