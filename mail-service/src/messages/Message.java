package messages;

import java.sql.Timestamp;
import java.time.Instant;

public class Message implements Comparable<Message> {
	private String from;
	private String to;
	private String subject;
	private String body;
	private Timestamp time;

	public Message(String from, String to, String subject, String body) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
		time = Timestamp.from(Instant.now());
	}
	public Message(String from, String to, String subject, String body, String time) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
		this.time = Timestamp.valueOf(time);
	}
	/***/
	public String getFrom() {return from;}
	/***/
	public String getTo() {return to;}
	/***/
	public String getSubject() {return subject;}
	/***/
	public String getBody() {return body;}
	/***/
	public Timestamp getTime() {return time;}
	
	@Override
	/***/
	public int compareTo(Message m) {return time.compareTo(m.getTime());}
	/***/
	public String toString() {
		return from + ";" + to + ";" + 
		subject + ";" + body + ";" + time.toString();
	}
}
