package p2.mailbox;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import p1.messages.Message;


public class SpamUserFilter implements Observer {
	// Subject s = null;
	List<Message> list = new ArrayList<Message>();
	/*
	 * public SpamUserFilter(Subject s) { this.s = s; }
	 */

	/**
	 * Acción a la cual se le pasa una lista con los Messages, este detecta cuáles
	 * cumplen con el SpamUserFilter (contiene spam en el nombre de usuario) y los
	 * añade y borra de la lista correspondiente..
	 */
	@Override
	public void update(List<Message> l) {
		List<Message> ml = new LinkedList<Message>();
		if (l != null) {
			for (Message m : l) {
				if (m.getFrom().contains("spam")) {
					list.add(m);
					ml.add(m);
				}
			}
		}
		for (Message m : ml) {
			l.remove(m);
		}
	}

	/**
	 * Función que permite obtener todos los mensajes.
	 * 
	 * @return Devuelve una lista con todos los mensajes.
	 */
	public List<Message> getMessages() {
		return list;
	}
}
