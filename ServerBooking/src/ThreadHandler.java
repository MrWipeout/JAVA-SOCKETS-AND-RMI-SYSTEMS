/*
 * Exizoglou Athanasios 321/2015050
 * Kargaki Maria 321/2015081
 * Project 1
 * 
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ThreadHandler extends Thread{ //klhronomei apo thn threads
	 // initialize socket and input output streams 
    private Socket socket            = null; 
	
    private  ObjectInputStream  input   = null;
    private  ObjectOutputStream out     = null;
    
    
    
	public ThreadHandler() { // default costructor

	}


	public void run() { //synarthsh run()
		//o kwdikas pou theloume na ekteleitai parallhla topotheteitai mesa se fth th synarthsh
		
		
	    
		// establish a connection 
        try
        { 
            socket = new Socket("127.0.0.1", 5555); // put ip address and port
            System.out.println("Connected");// sends output to the socket
            out    = new  ObjectOutputStream(socket.getOutputStream());
// takes input from terminal
            input  = new  ObjectInputStream(socket.getInputStream());


            try
            {
                OperationServer serverOp = new OperationServer(input, out); //dhmiourgia antikeimenou
                LocateRegistry.createRegistry(1099);
                Naming.rebind("//localhost/OperationServer", serverOp); //orizetai h dieuthinsh antikeimenou apo thn opoio tha ginei anazhthsh

            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

  

        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 

    }

}
