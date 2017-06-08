/** 
 * Beschreibung:
 * 
 * Autor: Manuela Winkens
 * Class: Lottosystem
 * Aktualisiert: 11.01.2017
 * 
 * Version: Alpha 1.0
 * */
import java.util.Scanner;

public class Lottosystem {

	public static int[][] aktualisieren(int[][] array, int zahl) {	//Methode zur Eingabe der neu gezogenen Zahlen und aktualisierung der Häufigkeit
	
		int index = 0;
		int i = 0;
		
		do { 
			if (array[0][i]!=zahl) {
				i++;}
			else {
				index = array[1][i];
				array[1][i] = index + 1; }
		} while (i<array.length);
		
		int[][] aktuellArray = sortFreq(array);
		
		return aktuellArray;
		
}


	public static int[][] sortFreq(int[][] array) {			//Methode zum sortieren nach Häufigkeit
	
		int x;
		boolean bool = true;						//Abbruchbedingung
		int temp;	
		int tempj;								//temporäre Variabel, die beim Tausch gefüllt wird
	
		while(bool) {
			bool = false;
		
			for(x=0; x<48; x++) {				//Durchlaufen des Arrays
			
				if(array[x][1] > array[x+1][1]) {			//Abfrage, ob größer oder kleiner
				
					temp = array[x][1];				//Tausch des Wertes
					array[x][1] = array[x+1][1];
					array[x+1][1] = temp;
					
					tempj = array[x][0];
					array[x][0] = array[x+1][0];
					array[x+1][0] = tempj;
					bool = true; }
			}
		}
		
		return array;
	}
	
	
	public static int[] zufall(int[][] array, int start, int diff) { 	//keine doppelten Zahlen ziehen
		
		int[] zufallsarray = new int[6];
		int zufallszahl=0;
		int pos = 0; 		//position der aktuellen Zufallszahl
		boolean erfolg;
		
		do {
			erfolg=true;
			zufallszahl = (int)(Math.random()*diff)+start;
			
			for (int i=0; i<6; i++) {
				if (zufallsarray[i] == array[zufallszahl][0]) {
					erfolg = false;} 		//zufallszahl bereits vorhanden
			}
			
			if (erfolg == false){
				continue; }
			else {
				zufallsarray[pos] = array[zufallszahl][0];
				pos++; }
		} while (pos<6);
		
		return zufallsarray;
		
	}
	
	public static void main(String[] args) {		//ausfuehren der einzelnen Methoden
	
	int[][] liste = {{1,487},{2,489},{3,503},{4,492},{5,486},{6,547},{7,498},{8,454},{9,497},{10,498},{11,513},{12,472},{13,429},{14,478},{15,467},{16,481},{17,502},{18,483},{19,486},{20,470},{21,463},{22,517},{23,437},{24,491},{25,501},{26,532},{27,493},{28,466},{29,483},{30,475},{31,516},{32,535},{33,521},{34,480},{35,485},{36,495},{37,485},{38,522},{39,496},{40,467},{41,508},{42,500},{43,520},{44,476},{45,439},{46,472},{47,481},{48,495},{49,533}};
	
	int a;
	int b;
	int c;
	int d;
	int e;
	int f;
	
	Scanner input = new Scanner(System.in);
	
	System.out.println("Geben Sie die 1.Zahl ein: ");
	a=input.nextInt();
	System.out.println("Geben Sie die 2.Zahl ein: ");
	b=input.nextInt();
	System.out.println("Geben Sie die 3.Zahl ein: ");
	c=input.nextInt();
	System.out.println("Geben Sie die 4.Zahl ein: ");
	d=input.nextInt();
	System.out.println("Geben Sie die 5.Zahl ein: ");
	e=input.nextInt();
	System.out.println("Geben Sie die 6.Zahl ein: ");
	f=input.nextInt(); 
		
	System.out.println();
	System.out.println("Die Zahlen fuer diese Ziehnung lauten: ");
	
	int[][] aktuellListe = new int[2][49];			//hier gibt es noch ein out of bounds problem
	aktuellListe = aktualisieren(liste, a);
	aktuellListe = aktualisieren(liste, b);
	aktuellListe = aktualisieren(liste, c);
	aktuellListe = aktualisieren(liste, d);
	aktuellListe = aktualisieren(liste, e);
	aktuellListe = aktualisieren(liste, f);
	
	//differenz von den wertebereichen
	int[] bereich1 = zufall(aktuellListe, 0, 7);
	int[] bereich2 = zufall(aktuellListe, 8, 8);		//8-15
	int[] bereich3 = zufall(aktuellListe, 16, 8);		//16-23
	int[] bereich4 = zufall(aktuellListe, 24, 8);		//24-31
	int[] bereich5 = zufall(aktuellListe, 32, 8);		//32-39
	int[] bereich6 = zufall(aktuellListe, 40, 9);		//40-48
	
	for (int i=0; i<6; i++) {
		System.out.print(bereich1[i] + " " + bereich2[i] + " " + bereich3[i] + " " + bereich4[i] + " " + bereich5[i] + " " + bereich6[i]);
		System.out.println(); }	
	
	}
}
