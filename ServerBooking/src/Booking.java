/*
 * Exizoglou Athanasios 321/2015050
 * Kargaki Maria 321/2015081
 * Project 1
 * 
 */
import java.io.Serializable;

public class Booking implements Serializable{

	private User user;
	private Event event;                            //dhmiourgia twn parametrwn tou antikeimenou Booking    
	private int theseis;

	public Booking(User user, Event event, int theseis) {

		this.user = user;                                   //overload constructor
		this.event = event;
		this.theseis = theseis;
	}

	
	public Booking(User user, Event event) {

		this.user = user;                               //constructor
		this.event = event;
		
	}
	
	public User getUser() {
		return user;
	}

	public Event getEvent() {
		return event;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setEvent(Event event) {                         //dhmiourgia twn setters kai getters
		this.event = event;
	}

	public int getTheseis() {
		return theseis;
	}

	public void setTheseis(int theseis) {
		this.theseis = theseis;
	}

	@Override
	public String toString() {
		return "Booking [user=" + user + ", event=" + event + ", theseis=" + theseis + "]";                         //dhmiorgia toString
	}

}
