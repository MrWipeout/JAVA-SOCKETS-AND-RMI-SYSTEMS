/*
 * Kargaki Maria 321/2015081
 * Exizoglou Athanasios 321/2015050
 * Project 1
 * 
 */


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

//dhmiourgia diepafhs h opoia periexei tis synarthseis tis opoies xrhsimopoioume wste na tis steiloume ston server1 mesw tou lookop pou vrisketai sth klassh graphics
public interface Operation extends Remote{
	public void createAccount(String name, String surname, String number, String email, String username, String password)  throws RemoteException;
	public boolean deleteUser(String username, String password) throws RemoteException;
	public User login(String username, String password) throws RemoteException;
	public void logout() throws RemoteException;
	public boolean createEvent(String title, String genre, int price, int seats, Date startDate) throws RemoteException;
	public boolean deleteEvent(int apenergopoihsh, String logos)  throws RemoteException;
	public int ticketOrder(String title, int seats, Date date, User loggedUser) throws RemoteException;
	public ArrayList <Event> availableEvents(int seats, Date date) throws RemoteException;
	public boolean cancel(User user, Event event) throws RemoteException;
	
	public boolean ticketOrderEpivevaiwsh(String onoma, String kwdikosKartas) throws RemoteException;
	
	 
		

}
