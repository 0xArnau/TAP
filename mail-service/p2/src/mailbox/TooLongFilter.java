package mailbox;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import messages.Message;
import patterns.*;

public class TooLongFilter implements  Observer{
//	Subject s = null;
	List<Message> list = new ArrayList<Message>();
/*	public TooLongFilter(Subject s) {
		this.s = s;
	}*/
	@Override
	public void update(List<Message> l) {
		List<Message> ml = new LinkedList<Message>();
		if (l != null) {
			for (Message m: l) {
				if (m.getBody().length() > 20) {
					list.add(m);
					ml.add(m);
				}
			}
		}
		for (Message m: ml) {
			l.remove(m);
		}
	}

	public List<Message> getMessages() {return list;}
}
