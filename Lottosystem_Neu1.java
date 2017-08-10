import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Description: Lottoprogramm mit den Funktionen:
 * Muster ueberpruefen
 * 6 Lottoreihen nach Haeufigkeits-System ausgeben
 * Ausgabe der 6 haeufigsten Zahlen
 * Suchen nach einer Zahl; Ausgabe der Haeufigkeit
 *
 * @author Manuela Winkens
 * @version 4.0
 **/

public class Lottosystem_Neu1 {

	private static final String FILENAMEN = "lottozahlen_archiv.txt";

	/**
	 * 
	 * @param array
	 * @return sortiert ein 2D Array nach Haeufigkeit und gibt dieses aus
	 */
	public static int[][] sortFreq(int[][] array) {
		int tmp;
		int tmp2;
		for (int i = 1; i < array.length; i++) {
			for (int j = 0; j < array.length - i; j++) {
				// pruefen welche der beiden Zahlen goesser bzw. kleiner ist
				// absteigende Sortierung
				if (array[j][1] < array[j + 1][1]) {

					// Tausch der Haeufigkeit
					tmp = array[j][1];
					array[j][1] = array[j + 1][1];
					array[j + 1][1] = tmp;

					// Tausch der Lottozahl
					tmp2 = array[j][0];
					array[j][0] = array[j + 1][0];
					array[j + 1][0] = tmp2;
				}

			}
		}
		return array;
	}

	/**
	 *
	 * @param array
	 * @param start
	 * @param diff
	 * @return gibt ein Array mit 6 zufaellig generierten Zahlen aus einem
	 *         Bereich zurueck
	 */
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

	/**
	 * Gibt die 6 Lottoreihen auf der Kommandozeile aus
	 * 
	 * @param liste
	 */
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

	/**
	 * Eingabe eines Muster, dass ueberprueft wird, ob es bereits gezogen wurde
	 * 
	 * @param muster
	 * @param listOfLottoZahlen
	 * @param lottoZahlen
	 */
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

	/**
	 * Sucht eine Zahl im 2D Array und gibt die Haeufigkeit aus
	 * @param liste
	 * @param x
	 */
	public static void search(int[][] liste, int x) {
		boolean found = false;
		for (int i = 0; i < 49; i++) {
			if (liste[i][0] == x) {
				System.out.println("Dein gesuchter Wert " + x + " kam so oft vor: " + liste[i][1]);
				found = true;
			}
		}
		if (found == false) {
			System.out.println("Falsche Zahl eingegeben.");
		}
	}
	/**
	 * Main-Methode zum initialisieren des Programmes
	 * @param args
	 */
	public static void main(String[] args) {

		int[][] liste = new int[49][2];
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

		int[][] sortedList = sortFreq(liste);

		System.out.println("Muster ueberpruefen?: 1 eingeben.");
		System.out.println("Ausgabe 6 Lottoreihen?: 2 eingeben.");
		System.out.println("Die haeufigsten 6 Zahlen?: 3 eingeben.");
		System.out.println("Du suchst eine Zahl?: 4 eingeben.");
		int eingabe = StdIn.readInt();
		if (eingabe == 1) {
			testMuster(muster, listOfLottoZahlen, lottoZahlen);
		} else if (eingabe == 2) {
			printReihen(sortedList);
		} else if (eingabe == 3) {
			for (int i = 0; i < 6; i++) {
				System.out.println(i + 1 + ". " + sortedList[i][0] + " " + sortedList[i][1]);
			}
		} else if (eingabe == 4) {
			System.out.println("Bitte gib deine gesuchte Zahl ein: ");
			int ges = StdIn.readInt();
			search(liste, ges);
		} else {
			System.out.println("Keine gueltige Eingabe, Programmabbruch.");
		}
	}
}