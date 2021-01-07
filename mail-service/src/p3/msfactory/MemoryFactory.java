package p3.msfactory;

import p1.mailstore.InMemory;
import p1.mailstore.MailStore;


public class MemoryFactory implements MailStoreFactory {
	
	/**
	 * Función alternativa encargada de devolver una Mailstore a partir de InMemory.
	 * 
	 * @return MailStore en memória
	 */
	@Override
	public MailStore createMailStore() {
		return new InMemory();
	}
}
