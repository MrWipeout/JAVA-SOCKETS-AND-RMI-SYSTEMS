/*
 * Exizoglou Athanasios 321/2015050
 * Kargaki Maria 321/2015081
 * Project 
 * 
 */
import java.util.Scanner;



public class MainClient {
	static User LogedUser;

	public static void main(String[] args) {

		Graphics graphics = new Graphics(); // dhmiourgia antikeimenou typou graphics

		Scanner input = new Scanner(System.in);
		User b;

		int choice_menu;
		do {

			choice_menu = 0;
			//menou epilogwn
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------");
			System.out.println("1. Eggrafh Xrhsth");
			System.out.println("2. Diagrafh Xrhsth");
			System.out.println("3. Login");
			System.out.println("4. Eksodos");
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------");

			choice_menu = input.nextInt();
			if (choice_menu == 1) {
				graphics.createAccount(); //kalei th synarthsh gia th dhmiourgia logariasmou

			} else if (choice_menu == 2) { //diagrafetai
				graphics.logIn(choice_menu);
				

			} else if (choice_menu == 3) { //kanei syndesh
				graphics.logIn(choice_menu);

			} else if (choice_menu == 4) { //vgainei
				System.exit(0);

			}

		} while (choice_menu > 0 && choice_menu < 5);

	}
}
