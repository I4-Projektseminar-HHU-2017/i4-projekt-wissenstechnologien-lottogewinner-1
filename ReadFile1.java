
/**
 * Description: Einlesen der Statistik aus einer txt Datei
 *
 *
 * @author	Manuela Winkens
 * @version	1.0.0
 **/
import java.io.*;

class ReadFile1 {

	// private static final int LOTTOZAHLEN = 6;
	private static final int MAXZAHLEN = 8;

	String fileNamen;
	BufferedReader zeileLesen = null;
	File file = null;
	String zeile = null;
	int goodLines = 0;
	int badLines = 0;

	// prepare for reading lines
	public ReadFile1(String fileNamen) {
		this.fileNamen = fileNamen;
		this.file = new File(this.fileNamen);
		if (!file.canRead() || !file.isFile())
			System.exit(0);

		try {
			zeileLesen = new BufferedReader(new FileReader(this.fileNamen));
		} catch (FileNotFoundException e) {
			System.out.println("ReadFile Bufferreader fehlgeschlagen");
			e.printStackTrace();
		}
	}

	/**
	 * liefert ein int 8-stelliges array zurueck pos 0 - 5: Lottozahlen pos 6 :
	 * Zusatzzahl, wenn nicht vorhanden "0" pos 7 : Superzahl, wenn nicht
	 * vorhanden "0"
	 * 
	 * oder Null, falls keine Zahlen vorhanden oder Dateiende
	 **/
	int[] getLottoZahlen() {

		String[] splitLines;
		String zeileOrig;
		boolean zeilenOK;

		do {
			zeileOrig = this.readLine(); // lese naechste zeile
			if (zeileOrig == null)
				return null; //

			splitLines = zeileOrig.split(",");
			if (splitLines.length < MAXZAHLEN) {
				zeilenOK = false;
				badLines++;
			} else {
				zeilenOK = true;
				goodLines++;
			}
		} while (!zeilenOK);

		int[] zarray = textAlsZahl(splitLines);

		return zarray;
	}

	String readLine() {
		try {
			zeile = zeileLesen.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			zeile = null;
			e.printStackTrace();
		}
		return zeile;
	}

	/**
	 * Ermitteln eines int[] aus einem String[]
	 * 
	 * @param text
	 *            String[], das ausgewertet werden soll
	 * @return 6 zahlen + ggf. zusatzzahl und superzahlgleichgrosses int[] es
	 *         wird solange gesucht bis leere Strings (int 0) oder zahlen
	 *         (0<z<50)gefunden werden.
	 */
	private int[] textAlsZahl(String[] text) {
		int[] zahl = new int[MAXZAHLEN];
		int idx;

		idx = 0;
		for (int i = 0; i < text.length; i++) {

			if (text[i].equals("")) {
				zahl[idx++] = 0; // leerer string setze 0
				continue;
			}

			try {
				zahl[idx++] = Integer.parseInt(text[i]);
			} catch (java.lang.NumberFormatException e) {
				idx--;
			}
		}
		return zahl;
	}
}