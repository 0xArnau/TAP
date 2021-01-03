package p1.mailstore;

import p1.messages.*;

import java.util.List;

public interface MailStore {

	static final String regex = ";";

	/**
	 * Acción encargada de enviar un correo de un usuario a otro.
	 * 
	 * @param u Usuario al que irá a parar el correo.
	 * @param m Variable de tipo Message (Consultar Message.java para más
	 *          información).
	 */
	public void sendMail(String u, Message m);

	/**
	 * Permite obtener los correos del buzón del usuario u.
	 * 
	 * @param u Usuario del que se quiere conseguir los correos.
	 * @return Devuelve una lista de los mensajes del usuario.
	 */
	public List<Message> getMail(String u) throws Exception;
}
