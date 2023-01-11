/*
 * Exizoglou Athanasios 321/2015050
 * Kargaki Maria 321/2015081
 * Project 1
 * 
 */
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class Server1Accepter {
	private ObjectInputStream input = null;
	private ObjectOutputStream out = null;

	Util util = new Util();

	public Server1Accepter(Socket socket) {

		try {
			this.out = new ObjectOutputStream(socket.getOutputStream());
			this.input = new ObjectInputStream(socket.getInputStream()); // dhmiourgia constructor me parametro socket
																			// pou exei mesa tis roes dedomenwn apo kai
																			// pros

		} catch (IOException e) {
			e.printStackTrace();
		}
		epikoinwnia();

	}

	public void epikoinwnia() {
		System.out.println("im waiting");
		try {
			String str = (String) input.readObject(); // diavazei to string str
			System.out.println(str);
			// while(!str.equals("Bye")) {
			if (str.equals("Create Event")) { // ean to str pou tha avei apo ton server 1 einai idio
				Event event = (Event) input.readObject();
				System.out.println(event);
				util.writeTofile(event); // dhmiourgei kai kataxwrei to event mesa sto arxeio
				out.writeObject("OK");
				out.flush();
			}

			else if (str.equals("Delete Event")) { // ean to str einai idio me to Delete event
				int logos = (int) input.readObject();
				String titlos = (String) input.readObject();

				out.writeObject(util.deleteEvent(titlos, 0, 0)); // tote diagrafei to event apo to arxeio
				out.flush();

			}

			else if (str.equals("Available Events")) {
				int theseis = (int) input.readObject(); // diavazei tis theseis kai thn hmeromhnia
				Date date = (Date) input.readObject();

				out.writeObject(util.readFromEvent(theseis, date)); // kai tis grafei sto arxeio
				out.flush();
			}

			else if (str.equals("Ticket Order")) { // an thelei na paraggeilei eishthrio
				String title = (String) input.readObject();
				int seats = (int) input.readObject();
				Date date = (Date) input.readObject();
				User loggedUser = (User) input.readObject();
				// diavazei ton titlo tis theseis thn hmeromhnia kai to user
				Event event = new Event(title, date);
				Event event2 = util.readFromEvent(event);

				out.writeObject(event2.getKostos()); // stelnei ston 1o eksuphrethth to kostos tou event
				out.flush();

				boolean check; // an apanthsei true client se krathsh sunexizei
				check = (boolean) input.readObject(); // diavazei auto pou tou epistrefei

				if (check == true) {
					Booking booking = new Booking(loggedUser, event2, seats);

					String onoma = (String) input.readObject(); // an thelei tlk na kanei thn krathsh o xrhsths tote thn
																// prosthetoume mesa sto arxeio
					String kwdikosKartas = (String) input.readObject();

					util.writeToBookings(booking);
					out.writeObject(util.deleteEvent(title, seats, 2));
					out.flush();
				}

			} else if (str.equals("Cancel Order")) { // an o xrhsths thelei na kanei akurwsh mia paraggelia

				User user = (User) input.readObject();
				Event event = (Event) input.readObject(); // diavazei user kai event

				Booking booking = new Booking(user, event); // dhmiourgei mia krathsh me autes tis parametrous

				int seats = util.readFromBooking(booking); // diavazei theseis

				ArrayList<Booking> list = new ArrayList(util.deleteFromBookings(booking));

				File file = new File("booking.txt");
				if (file.delete()) {
					for (int i = 0; i < list.size(); i++) {
						util.writeToBookings(list.get(i)); // an to arxeio exei diagreftei tote prosthetei sthn lista
															// tis eggrafes
					}
				}

				ArrayList<Event> eventList = new ArrayList(util.updateEvent(event, seats));
				File file2 = new File("events.txt");
				if (file.delete()) { // kanei update thn lista
					for (int i = 0; i < eventList.size(); i++) {
						util.writeTofile(eventList.get(i));
					}
				}

			}
			// }
		} catch (ClassNotFoundException | IOException e) {

			e.printStackTrace();
		}

	}

}
