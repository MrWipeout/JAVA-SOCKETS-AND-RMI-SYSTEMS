/*
 * Exizoglou Athanasios 321/2015050
 * Kargaki Maria 321/2015081
 * Project 1
 * 
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

public class OperationServer extends UnicastRemoteObject implements Operation {
	private ObjectOutputStream out = null;
	private ObjectInputStream input = null;

        
	/**
	 * @throws RemoteException
	 * 
	 */
	public OperationServer(ObjectInputStream input, ObjectOutputStream out) throws RemoteException {
		super();
		this.out = out;
		this.input = input;         //dhmiourgia constructor me parametro socket pou exei mesa tis roes dedomenwn apo kai pros


	}

	Util util = new Util();


	@Override
	public void createAccount(String name, String surname, String number, String email, String username,
			String password) throws RemoteException {
		User user = new User(name, surname, number, email, username, password, "User");             //pairnoume tia analoges parametrous apo ton client kai dhmiourgoume enan user kai ton prosthetoume mesa sto arxeio

		util.writeTofile(user);

	}

	@Override
	public boolean deleteUser(String username, String password) throws RemoteException {
		User user = new User(username, password);                                               //pairnoume tis parametrous tous user pou einai gia na diagrafei kai dhmiourgoume me autes enan user 
		System.out.println(user);
		

		return util.deleteUser(user);                               //kaloume thn sunarthsh deleteUser
	}

	@Override
	public User login(String username, String password) throws RemoteException {
		User user = new User(username, password);
		User boo;                                                       //dhmiourgoume 2 user
		boo = util.readFromUser(user);                                  //kaloume thn sunarthsh read from user kai prostetoume ta stoixeia mesa sthn boo user
		System.out.println(boo);
		System.out.println(username);                               //emfanizoume to antikeimeno to username kai to password
		System.out.println(password);

		return boo;                                             //epistrefoume thn boo
	}

	@Override
	public void logout() throws RemoteException {

		System.out.print("Connection is closing");
		System.exit(1);                                     //kaloume thn logout gia na termatisei to project
                                                                    
	}

	@Override
	public boolean createEvent(String title, String genre, int price, int seats, Date startDate)                    
			throws RemoteException {
		Event event = new Event(title, genre, price, seats, startDate);                                 //gia thn dhmiourgia event pairnoume tis analoges parametrous apo ton client kai dhmiourgoiume mesa sthn sunarthsh ena even me autes
		System.out.println(title+" "+ genre+" "+ price+" "+ seats+" "+ startDate);
		boolean b = false;
		try {
			out.writeObject("Create Event");                                           
			out.flush();                                    
                                                                                                                
			out.writeObject(event);                                     //grafei to antikeimeno event mesa sto arxeio
			out.flush();

			String str = (String) input.readObject();                   //diavazei thn apanthsh 
			System.out.println(str);
			if (str.equals("OK")) {                             //kai an einai ok tote kanoume to b = me truw h false antistoixa
				b = true;
			} else
				b = false;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;                                       
	}

	boolean check = false;

	@Override
	public boolean deleteEvent(int logos, String titlos) throws RemoteException {

		Thread thread = new Thread(new Runnable() {

			Event event = new Event(titlos, logos);         //dhmiourgia enos event me tis parametrous pou exoume parei apo ton client

			@Override
			public void run() {                                     

				synchronized (event) {                      
					try {                                           
						out.writeObject("Delete Event");            
						out.flush();
                                                
						out.writeObject(logos);                     //stelnoume tis parametrous tou event gia na graftoune sto arxeio
						out.flush();

						out.writeObject(titlos);
						out.flush();

						check = (boolean) input.readObject();       //diavazei thn apanthsh

					} catch (IOException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}

		});
		thread.start();                     //ksekinaei to nhma

		return check;                   //epistrefei to check
	}

	int kostos = 0;                     

	@Override
	synchronized public int ticketOrder(String title, int seats, Date date, User loggedUser) throws RemoteException {
//ginetai sychronized h sygkekrimenh synarthsh kai xrhsimopoioume threads
		Event event = new Event(); //dhmiourgia antikeimenou  typou event
		Thread thread = new Thread(new Runnable() { //dhmiourgia antikeimenou typou thread

			@Override
			public void run() {
				synchronized (event) {//synarthsh run()
					//o kwdikas pou theloume na ekteleitai parallhla topotheteitai mesa se fth th synarthsh
					//ginetai syxronismow tou event
					
						
						try {
							out.writeObject("Ticket Order"); //stelnoume sto server2 to string afto
							out.flush(); //katharzisoume ton buffer

							out.writeObject(title); //stelnoume ton titlo sto server2
							out.flush();

							out.writeObject(seats); //stelnoume tis theseis
							out.flush();

							out.writeObject(date);
							out.flush();

							out.writeObject(loggedUser);
							out.flush();

							kostos = (int) input.readObject(); //diavazoume

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					
				
					
				}

			}

		});

		return kostos;
	}

	@Override
	public ArrayList<Event> availableEvents(int seats, Date date) throws RemoteException {
		ArrayList<Event> eventList = null;
		try {
			//stelnei
			out.writeObject("Available Events");
			out.flush();

			out.writeObject(seats);
			out.flush();

			out.writeObject(date);
			out.flush();

			eventList = (ArrayList) input.readObject(); //diavazei thn eventlist

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return eventList;
	}

	@Override
	public boolean ticketOrderEpivevaiwsh(String onoma, String kwdikosKartas) throws RemoteException {
		boolean check = false;

		try {
			//stelnoume sston server2 ta parakatw
			out.writeObject(true);
			out.flush();

			out.writeObject(onoma);
			out.flush();

			out.writeObject(kwdikosKartas);
			out.flush();

			check = (boolean) input.readObject(); // diavazei th deleteevent

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public boolean cancel(User user, Event event) throws RemoteException {

		Thread thread = new Thread(new Runnable() { //xrhsh thread

			@Override

			public void run() {
				synchronized (event) { //syxronismos tou event

					try {
						//stelnei sto server2 ta parakatw
						out.writeObject("Cancel Order");
						out.flush();

						out.writeObject(user);
						out.flush();

						out.writeObject(event);
						out.flush();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}

		});

		return true;
	}

}
