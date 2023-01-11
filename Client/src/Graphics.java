/*
 * Exizoglou Athanasios 321/2015050
 * Kargaki Maria 321/2015081
 * Project 1
 * 
 */

import java.awt.Component;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Graphics implements ActionListener {
	
	private static User loggedUser;

	private static int kostos;
	
	//syndesh client server me rmi
	Operation look_op; //dhmiourgia antikeimenou idiou typou me to interface

	public Graphics() {
		String name = "//localhost/OperationServer"; //orizetai h dieuthinsh antikeimenou apo thn opoio tha ginei anazhthsh
		try {
			look_op = (Operation) Naming.lookup(name); //to antikeimeno look_op xrhsimopoieitai gia na anaferthoume pros to apomakrymeno
			//antikeimeno to opoio ylopoiei thn diepafh kai vrisketai ston server

			System.out.println("Client Connected"); //emfanish mhnumatos otan ginei h syndesh tou serverBooking me ton client

		} catch (MalformedURLException | RemoteException | NotBoundException e) {

			e.printStackTrace();
		}

	}

	//dhlwsh aparaithtwn pediwn gia th dhmiourgia tou grafikou perivallontos
	private String loginName, loginPassword;

	private JPanel panel = new JPanel();
	
	
	
	private JFrame event = new JFrame("Event"); //kentriko frame pou emfanizetai kata thn ektelesh tou programmatos
	
	//gia thn kataxwrhsh neou theamtos xreiazontai ta akoloutha label kai textfield
	private JLabel title = new JLabel("Title");
	private JLabel genre = new JLabel("Genre");
	private JLabel seats = new JLabel("Seats");
	private JLabel price = new JLabel("Price");
	private JLabel startDate = new JLabel("Start Date");

	private JTextField tfTitle = new JTextField(25);
	private JTextField tfGenre = new JTextField(25);
	private JTextField tfSeats = new JTextField(25);
	private JTextField tfPrice = new JTextField(25);
	
	//gia thn anazhthsh me vash thn hmeromhnia gia thn paraggelia eisithriou xreiazomaste ta parakatw
	private String[] months = { "Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	private String[] days = { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
			"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	private String[] year = { "Year", "2020", "2021", "2022" };

	private JComboBox comboMonths = new JComboBox(months);
	private JComboBox comboDays = new JComboBox(days);
	private JComboBox comboYears = new JComboBox(year);
	private JButton saveButton = new JButton("Save");
	private JButton proceedButton = new JButton("Proceed");

	//dimiourgia twn button gia kathe energeia
	private JButton kataxwrhshButton = new JButton("Kataxwrhsh");
	private JButton apenergopoihshButton = new JButton("Apenergopoihsh");
	private JButton paraggeliaButton = new JButton("Paraggelia");
	private JButton akyrwshButton = new JButton("Akyrwsh");

	private JButton logIn = new JButton("Log In");

	class Koumpi1 implements ActionListener { // prwto koumbi tou frame pou einia ipeuthino gia thn kataxwrhsh neou event
											

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == kataxwrhshButton) { // otan epilegei to koumpi

				kataxwrhsh(); // ekteleitai h synarthsh pou kanei thn kataxwrhsh twn pediwn pou apaitountai
								// gia thn kataxwrhsh mias ekdhlwshs

			}

		}

	}

	class Koumpi2 implements ActionListener { // 2o koumpi gia thn apenergopoihsh tou theamatos

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == apenergopoihshButton) {
				apenergopoihsh(); // ekteleitai h synarthsh gia thn apenergopoihsh tou theamatos
			}

		}

	}

	class Koumpi3 implements ActionListener { // 3o button gia thn paraggelia eisithriou kai thn eksoflhsh tou

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == paraggeliaButton) {

				paraggelia(); //ekteleitai h synarthsh pou einai h ypeuthinh gia thn paraggelia eisithriwn

			}

		}

	}

	class Koumpi4 implements ActionListener { // 4o button gia thn akyrwsh ths paraggelias eisithriwn

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == akyrwshButton) {
				akyrwshParaggelias();

			}

		}

	}
	
	
	public void akyrwshParaggelias() {
	
		JFrame akyrwsh = new JFrame("Akyrwsh Paraggelias");
		akyrwsh.setSize(200, 200);
		akyrwsh.setLocationRelativeTo(null);
		akyrwsh.setLayout(new GridLayout(4, 3));
		akyrwsh.setVisible(true);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JLabel labelEvent = new JLabel("Dwse to onoma tou event");
		JTextField fieldEvent = new JTextField(25);
		
		JButton buttonEvent = new JButton("Cancel");
		
		buttonEvent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					
					StringBuilder stb = new StringBuilder(); // dhmiourgia stringBuilder
					stb.append(comboDays.getSelectedItem().toString()); // pairnei to string pou yparxei mesa h hmera
					stb.append("/");
					stb.append(comboMonths.getSelectedItem().toString()); // pairnei to string pou yparxei mesa o mhnas
					stb.append("/");
					stb.append(comboYears.getSelectedItem().toString()); // pairnei to string pou yparxei mesa h xronia
					String stringCon = stb.toString(); // pairnei oloklhrh thn hmeromhnia pou epilegei o xrhsths

					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
					
					Date date = dateFormat.parse(stringCon); // kanei parse thn hmeromhnia kai thn pairnei se morfh string
					String titlos = fieldEvent.getText();  //pairnei ton titlo tou event [pu dinei o xrhsths sto grafiko kai to kanei string
					Event event = new Event(titlos, date); //antikeimeno ths klashs event
					
					
				boolean deleted = look_op.cancel(loggedUser, event); //kaleitai h synarthsh cancel gia thn akyrwsh ths paraggelias kai einai ish me mia metavlhth typou boolean wste na epistepsei th diagrafh
				//sth synarthsh cancel() h opoia einai boolean
				if(deleted == true) { //ean afto pou epistrefei einai true
					System.out.println("TRUE");
					JOptionPane.showMessageDialog(new JFrame(),
							"O xrhsths " + loggedUser + "ektelese epityxws thn akyrwsh tou event" + titlos, null, //emfanizei ena optionpane me to mhnuma ths epityxias kai ton xrhsth me ton titlo
							JOptionPane.INFORMATION_MESSAGE);
					
				}
				else {
					System.out.println("FALSE");
					JOptionPane.showMessageDialog(new JFrame(), //diaforetika vgazei ena error message
							"O xrhsths " + loggedUser + "den ektelese epityxws thn akyrwsh tou event" + titlos, null,
							JOptionPane.ERROR_MESSAGE);
				}
					
					
					
	
					}catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				
				
			}
			
		});
		
		
		panel1.add(labelEvent);
		panel1.add(fieldEvent);
		
		panel4.add(comboDays);
		panel4.add(comboMonths);
		panel4.add(comboYears);
		
		panel3.add(buttonEvent);
		
		akyrwsh.add(panel1);
		akyrwsh.add(panel4);
		
		akyrwsh.add(panel3);
		akyrwsh.pack();
	
		
	}
	String chosenEvent = null;
	
	public void paraggelia() { // synarthsh gia thn paraggelia eisithriwn

		JFrame stoixeia = new JFrame("Paraggelia Eisithriou");
		stoixeia.setSize(200, 200);
		stoixeia.setLocationRelativeTo(null);
		stoixeia.setLayout(new GridLayout(3, 3));
		stoixeia.setVisible(true);

		JButton search = new JButton("Search");
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();

		JTextField theseis = new JTextField(25);

		JLabel theseiL = new JLabel("Dwse theseis");
		
		

		
		
		
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stoixeia.dispose();

			
				try {
					StringBuilder stb = new StringBuilder(); // dhmiourgia stringBuilder
					stb.append(comboDays.getSelectedItem().toString()); // pairnei to string pou yparxei mesa h hmera pou exei epileksei o user
					stb.append("/");
					stb.append(comboMonths.getSelectedItem().toString()); // pairnei to string pou yparxei mesa o mhnas pou exei epileksei o user
					stb.append("/");
					stb.append(comboYears.getSelectedItem().toString()); // pairnei to string pou yparxei mesa h xronia pou exei epileksei o user
					String stringCon = stb.toString(); // pairnei oloklhrh thn hmeromhnia pou epilegei o xrhsths

					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  //o typos ths hmeromhnias
					
					Date date = dateFormat.parse(stringCon); // kanei parse thn hmeromhnia
					
					int seats = Integer.parseInt(theseis.getText()); //pairnei tis theseis pou dinei sto textfield kai tis kanei int
					displayer(seats, date, 1); //kaleitai h synarthsh dispayer
				
					
					
					
				
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				

			

			}

		});
		panel2.add(theseiL);
		panel2.add(theseis);
		
		

		panel.add(comboDays);
		panel.add(comboMonths);
		panel.add(comboYears);

		stoixeia.add(panel);
		stoixeia.add(panel2);
		stoixeia.add(search);

		stoixeia.pack();
	}

	public void apenergopoihsh() { //synarthsh gia thn apenergopoihsh theamatos
		JFrame frameApen = new JFrame("Apenergopoihsh Theamatos");
		frameApen.setSize(200, 200);
		frameApen.setLocationRelativeTo(null);
		frameApen.setLayout(new GridLayout(1, 2));
		frameApen.setVisible(true);
		String[] array = { "Canceled", "Completed" }; //dhmiourgia combobox wstw o admin na epilegei gia poion apo tous dyo logous thelei na apenergopoihsei ena theama
		JComboBox comboEvent = new JComboBox(array);
		JTextField field1 = new JTextField(25);

		frameApen.add(field1);
		frameApen.add(comboEvent);
		frameApen.add(proceedButton);
		frameApen.pack();

		proceedButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboEvent.getSelectedItem().equals("Canceled")) { //ean o admin epelekse thn akyrwsh tou theamatos
					frameApen.dispose();

					try {
						look_op.deleteEvent(0, field1.getText()); //stelnei ston server1 me th  synarthsh deleteEvent() to logo dld 0 pou gia thn akyrwsh kai to onoma tou theamatos
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}

				} else if (comboEvent.getSelectedItem().equals("Completed")) { //ean o admin epelekse thn oloklhrwsh tou theamatos
					frameApen.dispose();

					try {
						look_op.deleteEvent(1, field1.getText()); //stelnei ston server1 me th synarthsh delete to logo kai to theama
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}

				}

			}

		});

	}

	private User b;
	int counter = 0;
	public User logIn(int choice) { //dhmiourgia synarthshs gia th syndesh tou xrhsth sto systhma
		System.out.println("fhfhrt");
		JFrame login2 = new JFrame("Log In");
		JButton but = new JButton("Log In");
		login2.setSize(200, 200);
		login2.setLocationRelativeTo(null);
		login2.setLayout(new GridLayout(3, 1));
		login2.setVisible(true);

		JLabel username = new JLabel("Username: ");
		JLabel password = new JLabel("Password: ");
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JTextField onoma = new JTextField(null, 20);
		JTextField kwdikos = new JTextField(null, 20);

		but.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {


				loginName = onoma.getText();
				loginPassword = kwdikos.getText();
				login2.setVisible(false);
				try {

						b= look_op.login(loginName, loginPassword); //stelnei ston server to username kai to passeword gia na kanie to login o xrhsrths
						System.out.println(b);
						
						
						
						if(b==null && counter<3) {
							JOptionPane.showMessageDialog(new JFrame(),
									"The username or password you've entered doesn't match any account", null, //tou emfanizetai afto to mhnyyma
									JOptionPane.ERROR_MESSAGE);
							counter++;
							onoma.setText(null);
							kwdikos.setText(null);
							login2.setVisible(true);

						}
						else if(b.getRolos().equalsIgnoreCase("User")) { //ean o rolos tou xrhsth einai user tote
							if(choice == 2) { //kai epileksei apo to menu epilogwn ths klashs mainClient kalei thn synarthsh delete pou diagrafei ton xrhsth
								delete();
							}
							else {
								newGraphicsUser(); //diaforetika kalei thn newgraphcs pou einai gia ta grafika
							}
								
							onoma.setText(null);
							kwdikos.setText(null);
						}

						else if(b.getRolos().equalsIgnoreCase("Admin")){ //omoiws
							
							
							if(choice == 2) {
								delete();
							}
							else {
								newGraphicsAdmin();
							}
							
							
							onoma.setText(null);
							kwdikos.setText(null);

						}

						if(counter>=3)
						{
							System.exit(1);
						}



				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			}

		});
		
		panel1.add(username);//prosthetei sto panel
		panel1.add(onoma);
		panel2.add(password);
		panel2.add(kwdikos);
		panel3.add(but);
		login2.add(panel1); //prosthetei sto frame ta panel
		login2.add(panel2);
		login2.add(panel3);
		login2.pack();
		
		
		return b;

		
	}

	public void kataxwrhsh() { //shnarthsh gia thn kataxwrhsh neou theamatos
		
		JFrame frameKat = new JFrame("Kataxwrhsh Ekdhlwshs");
		frameKat.setSize(200, 200);
		frameKat.setLocationRelativeTo(null);
		frameKat.setLayout(new GridLayout(6, 4)); //6 grammes kai 4 sthles
		frameKat.setVisible(true);

		JPanel panel1 = new JPanel(); //kathe panel antistoixei se mia grammh sto frame frameKat
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();

		panel1.add(title); //prostithontai ta textfields kai ta labels sta antistoixa panels
		panel1.add(tfTitle);
		panel2.add(genre);
		panel2.add(tfGenre);
		panel3.add(seats);
		panel3.add(tfSeats);
		panel4.add(price);
		panel4.add(tfPrice);
		panel5.add(startDate);
		panel5.add(comboDays);
		panel5.add(comboMonths);
		panel5.add(comboYears);

		frameKat.add(panel5); //prostithontai ta panels kai to button sto frameKAt
		frameKat.add(panel1);
		frameKat.add(panel2);
		frameKat.add(panel3);
		frameKat.add(panel4);
		frameKat.add(saveButton);
		
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String titlos = tfTitle.getText(); //pairnei to periexomeno tou textfield kai to vasei se ena string
				String eidos = tfGenre.getText();
				int theseis = Integer.parseInt(tfSeats.getText()); //pairnei to periexomeno tou textfiled to metatrepei se int kai to vazei sth metavlhth timh
				int timh = Integer.parseInt(tfPrice.getText());
				
			
				
				try {
					
					StringBuilder stb = new StringBuilder(); // dhmiourgia stringBuilder
					stb.append(comboDays.getSelectedItem().toString()); // pairnei to string pou yparxei mesa h hmera
					stb.append("/");
					stb.append(comboMonths.getSelectedItem().toString()); // pairnei to string pou yparxei mesa o mhnas
					stb.append("/");
					stb.append(comboYears.getSelectedItem().toString()); // pairnei to string pou yparxei mesa h xronia
					String stringCon = stb.toString(); // pairnei oloklhrh thn hmeromhnia pou epilegei o xrhsths

					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
					Date date = dateFormat.parse(stringCon);
					
					
					
					
					
					boolean check = look_op.createEvent(titlos, eidos, timh, theseis, date); //steleni ston server1
					System.out.print(check);
					if(check == true) {
						JOptionPane.showMessageDialog(new JFrame(), //frame me mhnyma
								"H kataxwrhsh egine epituxws", null,
								JOptionPane.INFORMATION_MESSAGE);
						frameKat.dispose();
						
					}
					else
						JOptionPane.showMessageDialog(new JFrame(),
								"H kataxwrhsh den egine swsta", null,
								JOptionPane.ERROR_MESSAGE);
					
				} catch (ParseException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // kanei parse thn hmeromhnia
				
				
				
			}
			
		});

		frameKat.pack(); //ftiaxnei tis diastaseis

	}
	String str = null;
	JComboBox combo = null;
	
	public void displayer(int seats, Date date, int flag) {
		
		
		
		JFrame frameDisplayer =  new JFrame("Epilogh Theamatos");
		frameDisplayer.setSize(200, 200);
		frameDisplayer.setLocationRelativeTo(null);
		frameDisplayer.setLayout(new GridLayout(2, 1));
		frameDisplayer.setVisible(true);

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JButton button = new JButton("Procced");
		try {
			
			ArrayList <Event> listEvents = new ArrayList<>(look_op.availableEvents(seats, date)); //dhmiourgia arraylist mesw ths opoia stelneoume sto server1 ths thseis kai thn hmeromhnia tou theamats
			
			String[] array = new String[listEvents.size()];
			for(int i=0; i<listEvents.size(); i++) {
				array[i] = listEvents.get(i).getTitlos(); //pairnoume ton titlo tou theamatos pou vrisketai sthn arrayllist kai to vazoume se ena pinaka 
			}
			combo = new JComboBox(array); 
			
			
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frameDisplayer.dispose(); //kleinei to parathiro
					chosenEvent = combo.getSelectedItem().toString(); //pairnei ro event pou exoume epileksei apo to combobox
					System.out.println(chosenEvent);
					
					int kostos = 0;
					try {
						kostos = look_op.ticketOrder(chosenEvent, seats, date, loggedUser); //stelnei mesw ths ticketorder sto server1
					} catch (RemoteException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					System.out.println(kostos);
					if(kostos != 0) { //ean to kostos einai diaforo tou 0 dld exei mpei sth diadikasia na kanei krathsh
						
						//tou emfanizetai to parakatw pane me tis epiloges yes h no gia to an thelei na kanei h oxi th krathsh
						 int input = JOptionPane.showConfirmDialog(null, "Thelete na kanete thn krathsh?" +  kostos * seats ,  "epivevaiwsh " , JOptionPane.YES_NO_OPTION);
					        // 0=yes, 
					       if(input == 0) {  //ean pathsei to yes
					    	   JFrame stoixeia1 = new JFrame("Stoixeia");
					    	   stoixeia1.setSize(200, 200);
					   		stoixeia1.setLocationRelativeTo(null);
					   		stoixeia1.setLayout(new GridLayout(3, 3));
					   		stoixeia1.setVisible(true);
					   		JTextField onoma = new JTextField(25);
					   		

							JLabel onomaL = new JLabel("Dwse onoma");
							JTextField kwdikos = new JTextField(25);

							JLabel kwdikosL = new JLabel("Dwse kwdiko kartas");
							JButton epivevaiwsh = new JButton("Epivevaiwsh");
							
							epivevaiwsh.addActionListener(new ActionListener() { //dinei zwh sto koumpi

								@Override
								public void actionPerformed(ActionEvent e) {
									String onomaK = onoma.getText(); //pairnei mesw string afta pou dwsame sta textfields
									String kwdikosKartas = kwdikos.getText();
									  try {
										boolean epivevaiwsh = look_op.ticketOrderEpivevaiwsh(onomaK, kwdikosKartas); //stelnei ston server1
										if(epivevaiwsh == true) {
											JOptionPane.showMessageDialog(new JFrame(),
													"H paraggelia oloklirwthike epityxws", null, //tou emfanizetai afto to mhnyyma
													JOptionPane.INFORMATION_MESSAGE);
										}
										else
										{
											JOptionPane.showMessageDialog(new JFrame(),
													"H paraggelia den mporei na oloklirwthei", null, //tou emfanizetai afto to mhnyyma
													JOptionPane.ERROR_MESSAGE);
										}
									} catch (RemoteException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								}
								
							});
							
							stoixeia1.add(onomaL);
							stoixeia1.add(onoma);
							stoixeia1.add(kwdikosL);
							stoixeia1.add(kwdikos);
							stoixeia1.add(epivevaiwsh);
							stoixeia1.pack();
					   		
					    	 
					       }
					}
					
					
					
					
				}
				
			});
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		panel1.add(combo);
		panel2.add(button);
		
		frameDisplayer.add(panel1);
		frameDisplayer.add(panel2);
		frameDisplayer.pack();
		
	
		
	}
	public void createAccount() {//synarthsh gia thn dhmiourgia logariasmou sto systhma
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		Scanner sc3 = new Scanner(System.in);
		String name, surname, phoneNumber, emailAddress;
		
		//dinei ta stoixei pou apaitountai
		System.out.println("Dwse onoma: ");
		name = sc.nextLine();
		System.out.println("Dwse epwnymo: ");
		surname = sc1.nextLine();
		System.out.println("Dwse thlefwno epikoinwnias: ");
		phoneNumber = sc2.nextLine();
		System.out.println("Dwse dieythinsh email: ");
		emailAddress = sc3.nextLine();
		Scanner sc4 = new Scanner(System.in);
		Scanner sc5 = new Scanner(System.in);
		Scanner sc6 = new Scanner(System.in);

		String username, kwdikos, rolos;
		System.out.println("Dwse username: ");
		username = sc4.nextLine();

		System.out.println("Dwse kwdiko: ");
		kwdikos = sc5.nextLine();

		

		try {
			look_op.createAccount(name, surname, phoneNumber, emailAddress, username, kwdikos); //stelnoume  ta stoixeia tou eggrafomenou xrhsth ston server1
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void delete() {
		
		try {
			
			boolean b = look_op.deleteUser(loginName, loginPassword); //stelnoume to username kai to password ston ston server1 gia na diagrapsei ton xrhsth apo to systhma
			if(b == true) {
				JOptionPane.showMessageDialog(new JFrame(), //frame me mhnyma
						"O xrhsths " + loginName +  " diagrafthke epituxws", null,
						JOptionPane.INFORMATION_MESSAGE);
				
			}
			else
				JOptionPane.showMessageDialog(new JFrame(), //frame me mhnyma
						"O xrhsths " + loginName +  " den diagrafthke ", null,
						JOptionPane.ERROR_MESSAGE);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/*dhmiourgia synnarthshs h opoia otan o xrhsths syndeetai kai einia admin tha 
	 *tou emfanizei to frame me tis 4 epiloges,kataxwrhsh, apenergopoihsh, paraggelia kai diagrafh
	 */
	public void newGraphicsAdmin() {
		//dinei zwh sta plhktra
		kataxwrhshButton.addActionListener(new Koumpi1());
		apenergopoihshButton.addActionListener(new Koumpi2());
		paraggeliaButton.addActionListener(new Koumpi3());
		akyrwshButton.addActionListener(new Koumpi4());
		panel.add(kataxwrhshButton); // prosthikh twn koumpiwn sto panel
		panel.add(apenergopoihshButton);
		panel.add(paraggeliaButton);
		panel.add(akyrwshButton);
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.setPreferredSize(new Dimension(450, 100));//dinei tis diastaseis sto panel
		panel.setMaximumSize(new Dimension(450, 100));

		// panel.setBorder(BorderFactory.createTitledBorder(" "));
		event.getContentPane().add(panel); // prosthetei sto frame to panel
		event.setSize(550, 300);
		event.setLocationRelativeTo(null);

		event.setVisible(true);

		event.pack();

	}

	/*dhmiourgia synnarthshs h opoia otan o xrhsths syndeetai kai einai aplos user tou systhmatos tha 
	 *tou emfanizei to frame me tis 2 apo tis 4 epiloges paraggelia kai diagrafh
	 */
	public void newGraphicsUser() {

		
		paraggeliaButton.addActionListener(new Koumpi3());
		akyrwshButton.addActionListener(new Koumpi4());

		
		panel.add(paraggeliaButton);
		panel.add(akyrwshButton);
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.setPreferredSize(new Dimension(250, 100));
		panel.setMaximumSize(new Dimension(250, 100));

		//panel.setBorder(BorderFactory.createTitledBorder(" "));
		event.getContentPane().add(panel); 
		event.setSize(550, 300);
		event.setLocationRelativeTo(null);

		event.setVisible(true);

		event.pack();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
