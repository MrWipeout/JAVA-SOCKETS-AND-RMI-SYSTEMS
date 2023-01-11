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

	public void writeTofile(User s) { //synarthsh h opoia grafei sto arxeio tous xrhstes pou eggrafontai sto sto systhma

		File f = new File("users.txt"); // dhmiourgia arxeiou
		
		if (f.exists()) { // elegxos gia to ean to arxeio yparxei
			
			FileOutputStream fd = null; //anoigei th roh gia na diavasei apo to arxeio gia object
			ObjectOutputStream os = null;
			try {
				fd = new FileOutputStream(f, true); //anoigei thn roh gia na diavasei apo to arxeio , vazw true wste na mporei na ksana anoiksei thn "epikefalida" gia na mporesei na ksana grapsei sto arxeio kai na krathsei to prohgoumeno stoixeio pou exei kai na grapsei apo ketw
				os = new ObjectOutputStream(fd) {// to metatrepoume gia object giati prin eixame aplh roh pou tha grapsei, to kanoume gia na mporeoume na filtraroumr to
													// arxeio gia na diavasei ta antikeimena mesw serializable
					@Override
					protected void writeStreamHeader() throws IOException {
						reset(); //tha paei kai tha grapsei sto arxeio katw apo thn prohgoumenh plhrtoforia
					}
				};

				os.writeObject(s); //stelnei ton user
				os.flush(); // katharizei ton buffer meta apo kathe eggrafh

				fd.close();//kleinei tis roes
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

				os.writeObject(s);

				os.flush();
				fd.close();
				os.close();

			} catch (IOException e) {
				System.out.println(e);

			}

		}

	}

	

	public User readFromUser(User s) { //synarthsh h opoia diavazei tous xrhstes pou kanoun login sto systhma kai knaei elegxo gia na dei an yparxoun
		FileInputStream fi = null;
		ObjectInputStream ob = null;
		User u = null;
		try {
			fi = new FileInputStream("users.txt");
			ob = new ObjectInputStream(fi);
			
			
			while (ob != null && fi.available() != 0) { // oso den einai einai keno to arxeio
				User user; // metavlhth tupou User
				user = (User) ob.readObject(); //diavazei ton user
				System.out.println(user);
				
				//ean to username kai to password pou dineii einai idia me afta pou yparxoun san kataxwrhseis	
				if (user.getUsername().equals(s.getUsername()) && user.getPassword().equals(s.getPassword())) { //ean to username kai to password pou dewswi einai idia me afta pou yparxoun
					System.out.println(user); 
					u = user; //kai ton pernaei
				
				}

			}
			ob.close();
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return u;

	}
	
	public boolean deleteUser(User us) { //synarthsh h opoia diagrafei ton user
		boolean b = false;
		FileInputStream fi = null;
		ObjectInputStream ob = null;
		ArrayList <User> listUser = new ArrayList<>(); //dhmiourgia arraylist typou user
		File file = new File("users.txt"); 

		try {
			fi = new FileInputStream("users.txt");
			ob = new ObjectInputStream(fi);

			while (ob != null && fi.available() != 0) { // oso den einai einai keno to arxeio
				User user; // metavlhth tupou User
				user = (User) ob.readObject(); //diavazei ton user
				//elegxei to username kai to password pou dinei o xrhsths
				if (user.getUsername().equals(us.getUsername()) && user.getPassword().equals(us.getPassword())) { //ean to username kai to password pou dewswi einai idia me afta pou yparxoun
					b = true;
					System.out.println(user); 
					

				}
				else {
					listUser.add(user); //prosthetei ton user sthn arraylist
					
					
				}

			}
			
			ob.close();
			if (file.delete()) { //ean exei ginei delete
				for (int i = 0; i < listUser.size(); i++) {
					writeTofile(listUser.get(i));//kalei thn writetofile kai anadhmiourgountai ta announcements
				}
			}
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return b;
		
		
	}
	
	
	
	

}
