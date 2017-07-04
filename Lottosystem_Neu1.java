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

				if (array[x][1] > array[x + 1][1]) {

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

	public static void main(String[] args) {

		JFrame meinFrame = new JFrame("Beispiel JFrame");
		meinFrame.setSize(200, 200);
		JPanel panel = new JPanel();

		JLabel label = new JLabel("Geben Sie Ihre Lottozahlen ein");
		panel.add(label);
		JTextField tfName = new JTextField("Ihre Zahlen", 15);
		panel.add(tfName);
		
		JButton buttonOK = new JButton("OK");
		panel.add(buttonOK);
		String textfeld = tfName.getText();
		System.out.println(textfeld);

		meinFrame.add(panel);
		meinFrame.setVisible(true);

		int[][] liste = new int[49][2];

		int[] lottoZahlen;
		int count = 0, zahl = 0;
		;
		int[] xRichtige = new int[7]; // 1-6 und kein richtiger
		int treffer = 0;
		String tmp = " ";
		int[] muster = new int[6];

		for (int i = 0; i < muster.length; i++) {
			System.out.println("Bitte geben Sie eine Zahl ein und dann Enter: ");
			muster[i] = StdIn.readInt();
		}

		for (int i = 0; i < muster.length; i++) {
			if (i == 5) {
				tmp = tmp + " " + String.valueOf(muster[i]);
			} else {
				tmp = tmp + String.valueOf(muster[i]) + ", ";
			}
			meinFrame.add(new JLabel(tmp));
			meinFrame.setVisible(true);
		}

		ArrayList<int[]> listOfLottoZahlen = new ArrayList<int[]>();

		ReadFile1 rf1 = new ReadFile1(FILENAMEN);

		while ((lottoZahlen = rf1.getLottoZahlen()) != null) {
			listOfLottoZahlen.add(lottoZahlen);
			count++;
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

		System.out.println("   3 Treffer: " + xRichtige[3]);
		System.out.println("   4 Treffer: " + xRichtige[4]);
		System.out.println("   5 Treffer: " + xRichtige[5]);
		System.out.println("   6 Treffer: " + xRichtige[6]);
	}
}