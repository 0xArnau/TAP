package patterns;

import java.util.List;

import messages.Message;

public interface Observer {
	public void update(List<Message> m);
}
