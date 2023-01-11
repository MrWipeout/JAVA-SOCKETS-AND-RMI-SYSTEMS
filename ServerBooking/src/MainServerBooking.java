/*
 * Exizoglou Athanasios 321/2015050
 * Kargaki Maria 321/2015081
 * Project 1
 * 
 */
import java.io.File;



public class MainServerBooking {
	
	
	public static void main(String args[]) {
		
		File file = new File("users.txt");
		
		if(!file.exists()) {
			User admin1 = new User("maria", "kargaki", "697854521", "@@@", "maria", "0000", "Admin");       //dhmiourgia antikeimenou user gia na einai o admin
			Util util = new Util();                                                                             
			util.writeTofile(admin1);                                                                               //stelnoume ton admin sthn util
			
			User simpleUser = new User("thanos", "exizoglou", "4234235", "$%#$#", "thanos", "1111", "User");        //dhmiourgia antikeimenou user gia na einai enas aplos user        
			util.writeTofile(simpleUser);                                                                   //stelnoume ton user sthn util
                                                                            
	       
	    
           
	        
			
			
		}

	



		ThreadHandler th = new ThreadHandler();     
		th.start();  //ksikanaei to thread      
                
		
		
	}
	
   
  

 
	


	
	
	
	

}
