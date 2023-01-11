/*
 * Kargaki Maria 321/2015081
 * Exizoglou Athanasios 321/2015050
 * Project 1
 * 
 */
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event implements Serializable {

	private String titlos;
	private String eidos;
	private int theseis;                                        //dhmiourgia twn parametrwn tou antikeimenou event
	private int kostos;
	private int logos;

	Date date = new Date();

	public Event(String titlos, String eidos, int theseis, int kostos, Date date) { // overload costructor
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		this.titlos = titlos;
		this.eidos = eidos;
		this.date = date;
		this.theseis = theseis;
		this.kostos = kostos;

	}

	public Event() { // default costructor

	}

	public Event(String titlos, int logos) {

		this.titlos = titlos;                   //constructor
		this.logos = logos;
	}

	//getters setters

	public int getLogos() {
		return logos;
	}

	public void setLogos(int logos) {
		this.logos = logos;
	}

	public Event(String titlos, Date date) {

		this.titlos = titlos;
		this.date = date;
	}

	public String getTitlos() {
		return titlos;                                      //dhmiourgia setters kai getters
	}

	public String getEidos() {
		return eidos;
	}

	public int getTheseis() {
		return theseis;
	}

	public int getKostos() {
		return kostos;
	}

	public Date getDate() {
		return date;
	}

	public void setTitlos(String titlos) {
		this.titlos = titlos;
	}

	public void setEidos(String eidos) {
		this.eidos = eidos;
	}

	public void setTheseis(int theseis) {
		this.theseis = theseis;
	}

	public void setKostos(int kostos) {
		this.kostos = kostos;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Event [titlos=" + titlos + ", eidos=" + eidos + ", theseis=" + theseis + ", kostos=" + kostos
				+ ", logos=" + logos + ", date=" + date + "]\n";                                            //dhmiourgia toString
	}

}
