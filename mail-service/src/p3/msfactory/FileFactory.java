package p3.msfactory;

import p2.mailstore.EncodeDecorator;
import p1.mailstore.MailStore;
import p1.mailstore.OnFile;

public class FileFactory implements MailStoreFactory {

	/**
	 * Función encargada de devolver una Mailstore a partir de EncodeDecorator.
	 * 
	 * @return MailStore
	 */
	@Override
	public MailStore createMailStore() {
		EncodeDecorator deco = new EncodeDecorator(new OnFile());
		deco.setCipher();
		return deco;
	}

}
