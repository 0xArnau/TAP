package p3.msfactory;

import p2.mailstore.EncodeDecorator;
import p1.mailstore.MailStore;
import p1.mailstore.OnFile;

public class FileFactory implements MailStoreFactory {

	/**
	 * Función encargada de devolver una Mailstore a partir de EncodeDecorator.
	 * 
	 * @return MailStore en fichero con el cuerpo encriptado
	 */
	@Override
	public MailStore createMailStore() {
		return  new EncodeDecorator(new OnFile());
	}

}
