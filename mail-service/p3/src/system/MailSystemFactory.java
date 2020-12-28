package system;

import mailbox.MailBox;
import msfactory.MailStoreFactory;
import users.User;

public class MailSystemFactory extends MailSystem {
	
	MailStoreFactory msf  = null;
	public MailSystemFactory(MailStoreFactory msf) {
		this.msf = msf;
	}

	public MailBox newUser(User u) {
		return super.newUser(u, msf.createMailStore());
	}
}
