package mailstore;

import messages.*;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class InMemory implements MailStore {
	private static Map<String, List<Message>> messages = new HashMap<String, List<Message>>();

	/**
	 * Acción encargada de enviar un correo de un usuario a otro.
	 * 
	 * @param u Persona a la que irá a parar el correo.
	 * @param m Variable de tipo Message (Consultar Message.java para más
	 *          información).
	 */
	public void sendMail(String u, Message m) {
		if (messages.containsKey(u)) {
			if (m.toString().matches(".+;" + u + ";.+")) {
				messages.get(u).add(m);
			} else
				System.out.println("ERROR ==> Message(to) != User");
			return;
		}
		messages.put(u, new LinkedList<Message>() {
			{
				add(m);
			}
		});
	}

	/**
	 * Función que permite obtener los correos del buzón del usuario u.
	 * 
	 * @param u Usuario del que se quiere conseguir los correos.
	 * @return Devuelve una lista de los mensajes del usuario.
	 */
	public List<Message> getMail(String u) throws Exception {
		if (messages.get(u) == null)
			return new LinkedList<Message>();
		return new LinkedList<Message>(messages.get(u));
	}
}