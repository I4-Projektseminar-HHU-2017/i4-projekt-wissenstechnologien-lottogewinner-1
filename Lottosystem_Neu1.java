import java.util.ArrayList;

/**
 * Description:
 *
 *
 * @author	Manuela Winkens, Mehmet Memet
 * @version	2.0
 **/

public class Lottosystem_Neu1 {

	private static final String FILENAMEN = "lottozahlen_archiv.txt";
	
	public static int[][] sortFreq(int[][] array) {			//Methode zum sortieren nach Häufigkeit
	
		int 		x;
		boolean bool = true;		//Abbruchbedingung
		int 		temp;	
		int 		tempj;					//temporaere Variabel, die beim Tausch gefuellt wird

		
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

	public static int[] zufall(int[][] array, int start, int diff) {
		
		int[] zufallsarray = new int[6];
		int zufallszahl=0;
		int pos = 0;	// pos der aktuellen zz
		boolean erfolg;

		do {
			erfolg = true;
			zufallszahl = (int)(Math.random()*diff)+start;
			
			// schauen ob zz schon vorhanden immer !! 6 mal durchlaufen
			for (int i=0; i<6; i++){
				
				if (zufallsarray[i] == array[zufallszahl][0])
					erfolg = false;	// zz schon da
			}
			
			if (erfolg == false)
				continue;	//schleife nochmal weil zz schon da
			else {
				zufallsarray[pos] = array[zufallszahl][0];
				pos++;
			}
		}while(pos < 6);
			
		return zufallsarray;
	}

	
	public static void main(String[] args) {		//ausführen der einzelnen Methoden
		int[][] liste = new int [49][2];
		int[][] sortiertListe;
		int [] lottoZahlen;
		int count =0, zahl=0;;
		int []xRichtige = new int[7];	// 1-6 und kein richtiger
		int treffer =0;
//		int [] muster = {4, 11, 18, 22, 30, 45}; // 24.12.2016 zum Test
		int [] muster = {1, 2, 3, 4, 5, 6}; //
		
		ArrayList<int[]> listOfLottoZahlen = new ArrayList<int[]>();
		
		ReadFile1 rf1 = new ReadFile1(FILENAMEN); 
		
		long start = System.nanoTime();		// start CPU Messung
		
		while ((lottoZahlen = rf1.getLottoZahlen()) != null){
			listOfLottoZahlen.add(lottoZahlen);
			count++;
		}
		int listLength = listOfLottoZahlen.size();
		
		
//		 initialisiere Array
		for (int i = 0; i <49; i++)
			liste[i][0] = i+1;
			
		// loop ueber alle lottozahlen zum aufsummieren
		for (int i = 0; i < listOfLottoZahlen.size(); i++) {
			lottoZahlen = listOfLottoZahlen.get(i);
			
			for (int j = 0; j < 6; j++){
				zahl = lottoZahlen[j];
				zahl--;											// liste starts mit 0
				liste[zahl][1]++;
			}
		}
		
		// loop ueber alle lottozahlen wieviele richtige
		for (int i = 0; i < listOfLottoZahlen.size(); i++) {
			lottoZahlen = listOfLottoZahlen.get(i);
			treffer = 0;	//neue loop zuruecksetzen
			
			for (int j = 0; j < 6; j++){
				for (int k = 0; k < 6; k++){
					if(muster[k]==lottoZahlen[j])
						treffer++;
				}
			}
			xRichtige[treffer]++;
		}
		System.out.println("Kein Treffer: "+xRichtige[0]);
		System.out.println("   1 Treffer: "+xRichtige[1]);
		System.out.println("   2 Treffer: "+xRichtige[2]);
		System.out.println("   3 Treffer: "+xRichtige[3]);
		System.out.println("   4 Treffer: "+xRichtige[4]);
		System.out.println("   5 Treffer: "+xRichtige[5]);
		System.out.println("   6 Treffer: "+xRichtige[6]);
		
		
		long end = System.nanoTime();			// end CPU Messung
		
		System.out.println("Time Elaps:= "+ (end-start)/1000000+"ms");
		System.out.println("Zeilen gelesen: "+count);
		System.out.println("Listenlaenge: "+listLength);
				
		
		sortiertListe = sortFreq(liste);
		
	// differenz von den wertebereichen
	// hier 6 unterschiedliche zz aus einem Bereich!
	int[] bereich1 = zufall(sortiertListe, 0, 7);
	int[] bereich2 = zufall(sortiertListe, 8, 8);		//8-15
	int[] bereich3 = zufall(sortiertListe, 16, 8);		//16-23
	int[] bereich4 = zufall(sortiertListe, 24, 8);		//24-31
	int[] bereich5 = zufall(sortiertListe, 32, 8);		//32-39
	int[] bereich6 = zufall(sortiertListe, 40, 9);		//40-48
	// an dieser Stelle sind 6 Bereiche mit unterschiedlichen Zahlen gef�llt 

	for (int i=0; i<6; i++) {
		System.out.print(bereich1[i] + " " + bereich2[i] + " " + bereich3[i]+ " " + 
	                   bereich4[i] + " " + bereich5[i] + " " + bereich6[i]);
		System.out.println(); 
	}	

	}
} // end class
