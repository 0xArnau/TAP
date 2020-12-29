package p3.system;

import p1.mailbox.MailBox;
import p3.msfactory.MailStoreFactory;
import p1.users.User;
import p1.system.*;

public class MailSystemFactory extends MailSystem {
	
	MailStoreFactory msf  = null;
	public MailSystemFactory(MailStoreFactory msf) {
		this.msf = msf;
	}

	public MailBox newUser(User u) {
		return super.newUser(u, msf.createMailStore());
	}
}
