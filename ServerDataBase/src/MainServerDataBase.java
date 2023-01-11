import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

public class MainServerDataBase {





	public static void main(String args[]) {
		
		File file = new File("events.txt");
		if(!file.exists()) {
				
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
	       
	     

	    	
	        Util util = new Util();

			Event event1 = new Event("Tosca", "Opera", 185, 18, new Date());
			Event event2 = new Event("Samson and Dalida", "fwfnwe", 58, 78, new Date()) ;               //dhmiourgoume 4 antikeimena events kai ta stelnoume mesw tis sunarthshs sthn klash util
			Event event3 = new Event("peter pan", "xxxx", 58, 78, new Date()) ;
			Event event4 = new Event("alpaca", "uuuuu", 58, 78, new Date()) ;
			util.writeTofile(event1);
			util.writeTofile(event2);
			util.writeTofile(event3);
			util.writeTofile(event4);
		}
// initialize socket and input stream
		 Socket socket = null;
		 ServerSocket server = null;
		// starts server and waits for a connection
		try {

			System.out.println("Server started");                                       

			System.out.println("Waiting for ServerBooking ...");                        
			server = new ServerSocket(5555);
			while (true) {

				System.out.println("Server accepted");
				socket = server.accept();
				System.out.println("Server accepted");
				Server1Accepter servAcc = new Server1Accepter(socket);

				//servAcc.epikoinwnia();

			}

		} catch (IOException i) {
			System.out.println(i);
		} finally {
			// close connection
			try {
				socket.close();

				System.out.println("Closing connection");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	
	

	}
	
}