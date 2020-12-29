package p1.src.messages;

import java.sql.Timestamp;
import java.time.Instant;

public class Message implements Comparable<Message> {
	private String from;
	private String to;
	private String subject;
	private String body;
	private Timestamp time;

	/**
	 * Constructor de Message.
	 * 
	 * @param from    Persona que envía el correo.
	 * @param to      Persona que recibe el correo.
	 * @param subject Sujeto del correo.
	 * @param body    Contenido en sí del correo.
	 */
	public Message(String from, String to, String subject, String body) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
		time = Timestamp.from(Instant.now());
	}

	/**
	 * Constructor alternativo de Message.
	 * 
	 * @param from    Persona que envía el correo.
	 * @param to      Persona que recibe el correo.
	 * @param subject Sujeto del correo.
	 * @param body    Contenido en sí del correo.
	 * @param time    Momento en el que se ha enviado el correo.
	 */
	public Message(String from, String to, String subject, String body, String time) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
		this.time = Timestamp.valueOf(time);
	}

	/**
	 * Constructor alternativo para facilitar la P3
	 * 
	 * @param m String del mensaje que se quiere crear. Este se separa en partes.
	 */
	public Message(String m) {
		String[] parts = m.split(";");
		if (parts.length == 4) {
			this.from = parts[0];
			this.to = parts[1];
			this.subject = parts[2];
			this.body = parts[3];
		} else if (parts.length == 5) {
			this.from = parts[0];
			this.to = parts[1];
			this.subject = parts[2];
			this.body = parts[3];
			this.time = Timestamp.valueOf(parts[4]);
		}
	}

	/**
	 * Getter del from.
	 * 
	 * @return Devuelve el usuario que ha enviado el correo.
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Getter del To.
	 * 
	 * @return Devuelve el usuario que ha recibido el correo.
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Getter del Subject.
	 * 
	 * @return Devuelve el sujeto del correo.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Getter del Body
	 * 
	 * @return Devuelve el contenido del correo.
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Getter del Time
	 * 
	 * @return Devuelve el momento en el que se ha enviado el correo.
	 */
	public Timestamp getTime() {
		return time;
	}

	/**
	 * Función que permite comparar el tiempo de dos Messages.
	 * 
	 * @param M Mensaje con el que se va a comparar.
	 * @return Devuelve el resultado de la comparación de los tiempos. -1 Si va
	 *         primero, 1 si va después, 0 si son iguales.
	 */
	@Override
	public int compareTo(Message m) {
		return time.compareTo(m.getTime());
	}

	/**
	 * Función encargada de devolver un Message en forma de string.
	 * 
	 * @return Devuelve un string con las variables de Message.
	 */
	public String toString() {
		return from + ";" + to + ";" + subject + ";" + body + ";" + time.toString();
	}
}
