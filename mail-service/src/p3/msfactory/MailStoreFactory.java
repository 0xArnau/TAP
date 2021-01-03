package p3.msfactory;

import p1.mailstore.MailStore;

public interface MailStoreFactory {
	
	/**
	 * Función encargada de devolver una Mailstore a partir de EncodeDecorator.
	 * 
	 * @return MailStore
	 */
	public MailStore createMailStore();
}
