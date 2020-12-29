package p2.src.mailbox;


public interface Subject {
	public void attach(Observer o);
	public void detach(Observer o);
	public void notifyObservers();
}
