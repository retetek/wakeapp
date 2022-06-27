package wakeapp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WakeApp {
	static Scanner in = new Scanner(System.in);
	static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	static int option;
	static float fahrtweg, zeitReady, zeitBus, mehrZeit;
	static double gesamteZeit;
	static String ankunftszeit;
	static String line = "----------------------------";
	static String fileName = "file.json";
	boolean repeat = true;

	@SuppressWarnings("unchecked")
	public static void main(String[] args)
			throws FileNotFoundException, IOException, ParseException, InterruptedException {
//do {
		// Einleitung
		System.out.println("Uhrzeit: " + format.format(Calendar.getInstance().getTime())
				+ "\nWillkommen in Ihrer WakeApp!\n" + line
				+ "\nHaben Sie gespeicherte Daten und wollen diese laden?\nJa (1) | Nein (2) | Programm beenden (3) | Neustart (4)");

		option = in.nextInt();

		if (option == 2) {
			option2();
			option = in.nextInt();
			berechnung();
			System.out.println("Möchtest du die Daten speichern?\nJa (1) Nein (2)");
			option = in.nextInt();
			if (option == 1) {
				writeFile();
			} else if (option == 1) {
				option1();
				option = in.nextInt();
				berechnung();
				option = in.nextInt();

			} else if (option == 3) {
				closeTerminal();
			}
		}
//}while(option == 4);

	}

	static void closeTerminal() {
		System.out.println("Bis zum nächsten Mal!");
		System.exit(0);
	}

	@SuppressWarnings("unchecked")
	static void writeFile() throws IOException {
		JSONObject jsonObject = new JSONObject();
		String fahrtwegStr = String.valueOf(fahrtweg);
		String zeitReadyStr = String.valueOf(zeitReady);
		String zeitBusStr = String.valueOf(zeitBus);
		String mehrZeitStr = String.valueOf(mehrZeit);
		jsonObject.put("ankunftszeit", fahrtwegStr);
		jsonObject.put("fahrtweg", zeitReadyStr);
		jsonObject.put("zeitReady", zeitBusStr);
		jsonObject.put("zeitBus", mehrZeitStr);
		jsonObject.put("mehrZeit", mehrZeitStr);
		FileWriter file = new FileWriter("file.json");
		file.write(jsonObject.toJSONString());
		file.close();
	}

	static void option1() throws InterruptedException, IOException, ParseException, FileNotFoundException {
		JSONParser parser = new JSONParser();
		System.out.println("Daten werden aus der Datenbank geladen! \n\nBitte warten.");
		waitingLine();
		Object obj = parser.parse(new FileReader(fileName));
		JSONObject jsonObject = (JSONObject) obj;
		ankunftszeit = (String) jsonObject.get("ankunftszeit");
		String fahrtwegStr = (String) jsonObject.get("fahrtweg");
		fahrtweg = Float.parseFloat(fahrtwegStr);
		String zeitReadyStr = (String) jsonObject.get("zeitReady");
		zeitReady = Float.parseFloat(zeitReadyStr);
		String zeitBusStr = (String) jsonObject.get("zeitBus");
		zeitBus = Float.parseFloat(zeitBusStr);
		String mehrZeitStr = (String) jsonObject.get("mehrZeit");
		mehrZeit = Float.parseFloat(mehrZeitStr);
		System.out.println("\n\nDiese Daten wurden geladen:\nAnkunftszeit: " + ankunftszeit + " Uhr\nFahrtweg: "
				+ fahrtweg + " Minuten\nZeit zum Fertig machen: " + zeitReady + " Minuten\nWeg zum Bus/Bahn: " + zeitBus
				+ " Minuten\nWeitere Zeit: " + mehrZeit + " Minuten");
		System.out.println("Sind alle Daten soweit korrekt? Ja (1) Nein (4)");

	}

	static void waitingLine() throws InterruptedException {
		for (int i = 0; i <= 10; i++) {
			System.out.print(".");
			Thread.sleep(250);
		}
	}

	static void berechnung() throws InterruptedException {
		System.out.println("Berechne Weckzeit");
		waitingLine();
		gesamteZeit = fahrtweg + zeitReady + zeitBus + mehrZeit;
		LocalTime aTime = LocalTime.parse(ankunftszeit);
		LocalTime weckZeit = aTime.minus((long) gesamteZeit, ChronoUnit.MINUTES);
		System.out.println("\nDer Wecker wurde auf " + weckZeit + " Uhr gestellt.");
	}

	static void option2() throws InterruptedException {

		System.out.println("Neue Eingabe \nGeben Sie ihre Ankunftszeit ein in hh:mm Format:");
		ankunftszeit = in.next();
		System.out.println("Wie lange brauchen Sie für Ihren Fahrtweg? (in Minuten)");
		fahrtweg = in.nextFloat();
		System.out.println("Wie lange Brauchen Sie, um sich fertig zu machen? (in Minuten)");
		zeitReady = in.nextFloat();
		System.out.println("Wie lange ist der Weg zum Bahn oder dem Bus? (in Minuten)"
				+ "\nFalls Sie mit Auto fahren, dann den Weg zum Auto eintragen!");
		zeitBus = in.nextFloat();
		System.out.println("Benötigen Sie noch etwas Zeit, wenn ja, bitte hinschrieben (in Minuten)");
		mehrZeit = in.nextFloat();
		System.out.println("Zusammenfassung:\nAnkunftszeit: " + ankunftszeit + " Uhr\nFahrtweg: " + fahrtweg
				+ " Minuten\nZeit zum Fertig machen: " + zeitReady + " Minuten\nWeg zum Bus/Bahn: " + zeitBus
				+ " Minuten\nWeitere Zeit: " + mehrZeit + " Minuten\nIst das korrekt? Ja (1) Nein (2)");
	}
}
