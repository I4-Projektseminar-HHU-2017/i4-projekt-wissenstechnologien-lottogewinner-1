import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Description:
 *
 *
 * @author Manuela Winkens, Mehmet Memet
 * @version 2.0
 **/

public class Lottosystem_Neu1 {

	private static final String FILENAMEN = "lottozahlen_archiv.txt";

	public static int[][] sortFreq(int[][] array) {

		int x;
		boolean bool = true; // Abbruchbedingung
		int temp;
		int tempj; // temporaere Variabel, die beim Tausch gefuellt wird

		while (bool) {
			bool = false;

			for (x = 0; x < 48; x++) { // Durchlaufen des Arrays

				if (array[x][1] < array[x + 1][1]) {

					temp = array[x][1]; // Tausch des Wertes
					array[x][1] = array[x + 1][1];
					array[x + 1][1] = temp;

					tempj = array[x][0];
					array[x][0] = array[x + 1][0];
					array[x + 1][0] = tempj;
					bool = true;
				}
			}
		}

		return array;
	}

	public static int[] zufall(int[][] array, int start, int diff) {

		int[] zufallsarray = new int[6];
		int zufallszahl = 0;
		int pos = 0;
		boolean erfolg;

		do {
			erfolg = true;
			zufallszahl = (int) (Math.random() * diff) + start;

			// schauen ob zz schon vorhanden immer !! 6 mal durchlaufen
			for (int i = 0; i < 6; i++) {

				if (zufallsarray[i] == array[zufallszahl][0])
					erfolg = false; // zz schon da
			}

			if (erfolg == false)
				continue; // schleife nochmal weil zz schon da
			else {
				zufallsarray[pos] = array[zufallszahl][0];
				pos++;
			}
		} while (pos < 6);

		return zufallsarray;
	}

	public static void printReihen(int[][] liste) {
		System.out.println();
		System.out.println("Die Zahlen fuer diese Ziehnung lauten: ");

		// differenz von den wertebereichen
		int[] bereich1 = zufall(liste, 0, 7);
		int[] bereich2 = zufall(liste, 8, 8); // 8-15
		int[] bereich3 = zufall(liste, 16, 8); // 16-23
		int[] bereich4 = zufall(liste, 24, 8); // 24-31
		int[] bereich5 = zufall(liste, 32, 8); // 32-39
		int[] bereich6 = zufall(liste, 40, 9); // 40-48

		for (int i = 0; i < 6; i++) {
			System.out.print(bereich1[i] + " " + bereich2[i] + " " + bereich3[i] + " " + bereich4[i] + " " + bereich5[i]
					+ " " + bereich6[i]);
			System.out.println();
		}
	}

	public static void testMuster(int[] muster, ArrayList<int[]> listOfLottoZahlen, int[] lottoZahlen) {
		int[] xRichtige = new int[7];
		int treffer = 0;

		for (int i = 0; i < muster.length; i++) {
			System.out.println("Bitte geben Sie eine Zahl ein und dann Enter: ");
			muster[i] = StdIn.readInt();
		}

		// loop ueber alle lottozahlen wieviele richtige
		for (int i = 0; i < listOfLottoZahlen.size(); i++) {
			lottoZahlen = listOfLottoZahlen.get(i);
			treffer = 0; // neue loop zuruecksetzen

			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 6; k++) {
					if (muster[k] == lottoZahlen[j])
						treffer++;
				}
			}
			xRichtige[treffer]++;
		}

		System.out.println();
		System.out.println("   3 Treffer: " + xRichtige[3]);
		System.out.println("   4 Treffer: " + xRichtige[4]);
		System.out.println("   5 Treffer: " + xRichtige[5]);
		System.out.println("   6 Treffer: " + xRichtige[6]);
	}

	public static void search(int[][] liste, int x) {
		boolean found = false;
		for(int i=0; i<49; i++){
			if(liste[i][0]==x) {
				System.out.println("Dein gesuchter Wert " + x + " kam so oft vor: " + liste[i][1]);
				found = true;
			}
		}
		if (found==false) {
			System.out.println("Falsche Zahl eingegeben.");
		}
	}

	public static void main(String[] args) {
		// Stand 23.07.17
		int[][] liste = { { 1, 500 }, { 2, 499 }, { 3, 513 }, { 4, 504 }, { 5, 494 }, { 6, 558 }, { 7, 508 },
				{ 8, 457 }, { 9, 507 }, { 10, 501 }, { 11, 521 }, { 12, 482 }, { 13, 439 }, { 14, 483 }, { 15, 474 },
				{ 16, 489 }, { 17, 512 }, { 18, 495 }, { 19, 497 }, { 20, 476 }, { 21, 472 }, { 22, 530 }, { 23, 482 },
				{ 24, 498 }, { 25, 513 }, { 26, 547 }, { 27, 507 }, { 28, 478 }, { 29, 493 }, { 30, 485 }, { 31, 523 },
				{ 32, 549 }, { 33, 530 }, { 34, 487 }, { 35, 490 }, { 36, 507 }, { 37, 496 }, { 38, 536 }, { 39, 511 },
				{ 40, 495 }, { 41, 520 }, { 42, 514 }, { 43, 527 }, { 44, 486 }, { 45, 448 }, { 46, 479 }, { 47, 493 },
				{ 48, 504 }, { 49, 543 } };
		int[][] sortedList = sortFreq(liste);
		int[] lottoZahlen;
		int counter = 0;
		int zahl = 0;
		String tmp = " ";
		int[] muster = new int[6];

		for (int i = 0; i < muster.length; i++) {
			if (i == 5) {
				tmp = tmp + " " + String.valueOf(muster[i]);
			} else {
				tmp = tmp + String.valueOf(muster[i]) + ", ";
			}
		}

		ArrayList<int[]> listOfLottoZahlen = new ArrayList<int[]>();

		ReadFile1 rf1 = new ReadFile1(FILENAMEN);

		while ((lottoZahlen = rf1.getLottoZahlen()) != null) {
			listOfLottoZahlen.add(lottoZahlen);
			counter++;
		}

		// initialisiere Array
		for (int i = 0; i < 49; i++)
			liste[i][0] = i + 1;

		// loop ueber alle lottozahlen zum aufsummieren
		for (int i = 0; i < listOfLottoZahlen.size(); i++) {
			lottoZahlen = listOfLottoZahlen.get(i);

			for (int j = 0; j < 6; j++) {
				zahl = lottoZahlen[j];
				zahl--; // liste starts mit 0
				liste[zahl][1]++;
			}
		}

		System.out.println("Muster ueberpruefen?: 1 eingeben.");
		System.out.println("Ausgabe 6 Lottoreihen?: 2 eingeben.");
		System.out.println("Du suchst eine Zahl?: 3 eingeben.");
		int eingabe = StdIn.readInt();
		if (eingabe == 1) {
			testMuster(muster, listOfLottoZahlen, lottoZahlen);
		} else if (eingabe == 2) {
			printReihen(sortedList);
		} else if (eingabe==3) {
			search(liste, 5); //funktioniert nicht
		}
		else {
			System.out.println("Keine gueltige Eingabe, Programmabbruch.");
		}
	}
}