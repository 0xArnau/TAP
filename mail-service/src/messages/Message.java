package messages;

import users.User;

import java.sql.Timestamp;
import java.time.Instant;

public class Message {
	private User from;
	private User to;
	private String subject;
	private String body;
	private Timestamp time;

	public Message(User from, User to, String subject, String body) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
		time = Timestamp.from(Instant.now());
	}
	/***/
	public User getFrom() {return from;}
	/***/
	public User getTo() {return to;}
	/***/
	public String getSubject() {return subject;}
	/***/
	public String getBody() {return body;}
	/***/
	public Timestamp getTime() {return time;}
	/***/
	public int compareTo(Message m) {return time.compareTo(m.getTime());}
	/***/
	public String toString() {
		return from.getUserName() + ";" + to.getUserName() + ";" + 
		subject + ";" + body + ";" + time.toString();
	}
}
