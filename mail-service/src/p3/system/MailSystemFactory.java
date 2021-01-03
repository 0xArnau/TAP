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

	/**
	 * Función encargada de añadir un usuario a la Mailbox.
	 * 
	 * @param u Usuario a añadir.
	 * @return Devuelve la MailBox recién creade del usuario u.
	 */
	public MailBox newUser(User u) {
		return super.newUser(u, msf.createMailStore());
	}
}
