/*
 * Kargaki Maria 321/2015081
 * Exizoglou Athanasios 321/2015050
 * Project 1
 * 
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Util {

	
	public void writeTofile(Event s) { //synarthsh h opoia grafei sto arxeio ta event pou prosthetoun oi xrhstes

		File f = new File("events.txt"); // shmiourgia arxeiou
		
		if (f.exists()) {  // elegxos gia to ean to arxeio yparxei
			FileOutputStream fd = null; //anoigei th roh gia na diavasei apo to arxeio gia object
			ObjectOutputStream os = null;
			try {
				fd = new FileOutputStream(f, true); //anoigei thn roh gia na diavasei apo to arxeio , vazw true wste na mporei na ksana anoiksei thn "epikefalida" gia na mporesei na ksana grapsei sto arxeio kai na krathsei to prohgoumeno stoixeio pou exei kai na grapsei apo ketw
				os = new ObjectOutputStream(fd) {// to metatrepoume gia object giati prin eixame aplh roh pou tha grapsei, to kanoume gia na mporeoume na filtraroumr to
					// arxeio gia na diavasei ta antikeimena mesw serializable
					@Override
					protected void writeStreamHeader() throws IOException {
						reset();  //tha paei kai tha grapsei sto arxeio katw apo thn prohgoumenh plhrtoforia
					}
				};

				os.writeObject(s); //stelnei to event
				os.flush(); // katharizei ton buffer meta apo kathe eggrafh

				fd.close(); //kleinei tis roes
				os.close(); 

			} catch (IOException e) {
				System.out.println(e);

			}

		} else {
			FileOutputStream fd = null;
			ObjectOutputStream os = null;
			try { // block try catch
				fd = new FileOutputStream(f); // dhmiourgia rohs pros to arxeio
				os = new ObjectOutputStream(fd) {// pou tha grapsei to kanoume gia na mporeoume na filtraroumr to arxeio gia na diavasei ta antikeimena mesw serializable

				};

				os.writeObject(s);

				os.flush();
				fd.close();
				os.close();

			} catch (IOException e) {
				System.out.println(e);

			}

		}

	}

	

	public Event readFromEvent(Event s) { //synarthsh h opoia diavazei ta event
		FileInputStream fi = null;
		ObjectInputStream ob = null;

		Event events = null;

		try {
			fi = new FileInputStream("events.txt");
			ob = new ObjectInputStream(fi);

			while (ob != null && fi.available() != 0) { // oso den einai einai keno to arxeio
				Event event; //dhmiourgei ena antikeimeno typou event
				event = (Event) ob.readObject(); //to diavazei

				if (event.getDate() == s.getDate() && event.getTitlos().equals(s.getTitlos())) { //kai an h meromhnia kai o tiltos einai idioi me afta pou esteile o xrhsths
					events = event; //kai pernaei sto event sto events
				}

			}
			ob.close();
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return events;  //epistrefei to event

	}

	public ArrayList <Event> readFromEvent(int theseis, Date hmeromhnia) { // synarthsh h opoia diavazei apo  ta events tis theseis kai thn hmeromhnia
		FileInputStream fi = null;
		ObjectInputStream ob = null;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
		
		
		
		ArrayList <Event> events = new ArrayList<>(); //dhmiourgia arraylist typou events
		try {
			fi = new FileInputStream("events.txt");
			ob = new ObjectInputStream(fi);
			String strDate = dateFormat.format(hmeromhnia); 
			hmeromhnia = dateFormat.parse(strDate); 
			
			
			while (ob != null && fi.available() != 0) { // oso den einai einai keno to arxeio
				Event event; 
				event = (Event) ob.readObject(); //diavasei thn hmeromhnia
		
				
				if (hmeromhnia.compareTo(event.getDate()) < 0 || event.equals(event)) {
					// sygkrinei tis hmeromhniespou einai oi anakoinwseis me aftes pou dinei o
					// xrhsths

					if (event.getTheseis() >= theseis) {
						events.add(event); //prosthetei to event sthn arraylist
					}
				}
				

			}
			ob.close();
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return events;
		

	}
	
public boolean deleteEvent(String us, int theseis, int choice) { //synarthsh gia th diagrafh enos event
		
		FileInputStream fi = null;
		ObjectInputStream ob = null;
		ArrayList <Event> listEvent = new ArrayList<>();
		File file = new File("events.txt");
		boolean check =  false; //epistefei an einetai h diagrafh h oxi

		try {
			fi = new FileInputStream("events.txt");
			ob = new ObjectInputStream(fi);

			while (ob != null && fi.available() != 0) { // oso den einai einai keno to arxeio
				Event event; // metavlhth tupou User
				event = (Event) ob.readObject(); //diavazei to event
				
				if(choice == 0) { 
					if(!event.getTitlos().equalsIgnoreCase(us)){ //ean to event einai diaforo me afto pou dinei o xrhsths
						listEvent.add(event); //to prosthetei mesa sth lista
					}
					
				}
				else {
					if (theseis == 0) { // ean den yparxoun theseis
						if (event.getTitlos().equalsIgnoreCase(us)) { // ean to username kai to password pou dewswi einai idia me
															// afta pou yparxoun

							System.out.println(event);
							check = true; 

						} else {
							listEvent.add(event);

						}

					} else {
						if (event.getTitlos().equalsIgnoreCase(us)) {
							event.setTheseis(event.getTheseis() - theseis);
							listEvent.add(event);

						} else {
							listEvent.add(event);
						}

					}
					
				}
				
				

			}
			
			ob.close();
			if (file.delete()) { //ean exei ginei delete
				for (int i = 0; i < listEvent.size(); i++) {
					writeTofile(listEvent.get(i));//kalei thn writetofile kai anadhmiourgountai ta announcements
					System.out.println(listEvent.get(i));
				}
			}
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return check;
		
		
	}
	

public ArrayList <Event> updateEvent(Event s, int seats) { //synarthsh pou kanei thn enhmerwsh twn event
	
	FileInputStream fi = null;
	ObjectInputStream ob = null;

	ArrayList <Event> events = new ArrayList <>();

	try {
		fi = new FileInputStream("events.txt");
		ob = new ObjectInputStream(fi);

		while (ob != null && fi.available() != 0) { // oso den einai einai keno to arxeio
			Event event;
			event = (Event) ob.readObject();
			
			if(event.getTitlos().equals(s.getTitlos()) && event.getDate() == s.getDate()) { //kanei elegxo gia ton titlo kai to date pou edwse o xrhsths
				int newTheseis = event.getTheseis() + seats; //kai prosthetei stis yparxouses theseis aftes pou o xrhsths ekane akyrwsh ths paraggelias toy
				event.setTheseis(newTheseis); //vazei tis thesei aftes sto ekastote theama
				
			}
			events.add(event); //kai tis pernaei sthn arraylist
			

		}
		ob.close();
	} catch (IOException e) {

	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	return events;

	
	
	
	
}

	
public void writeToBookings(Booking booking) { //synarthsh h opoia pernaei se arxeio tis krathseis twn ekdhlwsewn

	File f = new File("booking.txt"); //dhmiourgia arxeiou
	
	if (f.exists()) { //elegxos gia to ean to arxeio yparxei
		
		FileOutputStream fd = null; 
		ObjectOutputStream os = null; 
		
		try {
			fd = new FileOutputStream(f, true);  
			os = new ObjectOutputStream(fd) { 
												
				@Override
				protected void writeStreamHeader() throws IOException {
					reset();
				}
			};

			os.writeObject(booking); //stelnei
			os.flush(); // katharizei ton buffer meta apo kathe eggrafh

			fd.close();
			os.close(); 

		} catch (IOException e) {
			System.out.println(e);

		}

	} else {
		FileOutputStream fd = null;
		ObjectOutputStream os = null;
		try {
			fd = new FileOutputStream(f); // dhmiourgia rohs pros to arxeio
			os = new ObjectOutputStream(fd) {// pou tha grapsei to kanoume gia na mporeoume na filtraroumr to arxeio gia na diavasei ta antikeimena mesw serializable

			};

			os.writeObject(booking);

			os.flush();
			fd.close();
			os.close();

		} catch (IOException e) {
			System.out.println(e);

		}

		}

	}

	public int readFromBooking(Booking booking) {

		FileInputStream fi = null;
		ObjectInputStream ob = null;

		int seats = 0;

		try {
			fi = new FileInputStream("booking.txt");
			ob = new ObjectInputStream(fi);

			while (ob != null && fi.available() != 0) { // oso den einai einai keno to arxeio
				Booking bk;
				bk = (Booking) ob.readObject();

				if (bk.getUser().equals(booking.getUser()) && bk.getEvent().equals(booking.getEvent())) {
					seats = bk.getTheseis();
				}

			}
			ob.close();
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return seats;

	}
	
	
	
	
	
	
	
	
	public ArrayList <Booking> deleteFromBookings(Booking booking) { // synarthsh h opoia diagrafei ta bookings
		FileInputStream fi = null;
		ObjectInputStream ob = null;
		
		
		
		
		ArrayList <Booking> bookings = new ArrayList<>();
		try {
			fi = new FileInputStream("events.txt");
			ob = new ObjectInputStream(fi);
			while (ob != null && fi.available() != 0) { // oso den einai einai keno to arxeio
				Booking bk;
				bk = (Booking) ob.readObject();
				//ean o xrhsths kai to event einia diaforetika apo afta pou dinei o xrhsths tote
				if (!bk.getUser().equals(booking.getUser()) && !bk.getEvent().equals(booking.getEvent())) {
					bookings.add(booking); //prosthetei to diaforetiko booking sth lista
				}

			}
			
			
			
			ob.close();
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return bookings;
		

	}

}
