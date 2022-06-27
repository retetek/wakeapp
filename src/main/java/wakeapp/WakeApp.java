package wakeapp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WakeApp {

	public static void main(String[] args) {

		// Variablen & Deklarationen
		Scanner in = new Scanner(System.in);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		int option;
		float fahrtweg, zeitReady, zeitBus, mehrZeit;
		double gesamteZeit;
		String ankunftszeit;
		String line = "----------------------------";
		String fileName = "file.json";

		// Einleitung
		System.out.println(
				"Uhrzeit: " + format.format(Calendar.getInstance().getTime()) + "\nWillkommen in Ihrer WakeApp!\n"
						+ line + "\nHaben Sie gespeicherte Daten und wollen diese laden?\nJa (1) | Nein (2)");
		option = in.nextInt();

		// case2
		if (option == 2) {
			do {
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

				option = in.nextInt();
			} while (option == 2);
			
			System.out.println("Berechne Weckzeit");
			waitingLine();
			gesamteZeit = fahrtweg + zeitReady + zeitBus + mehrZeit;
			LocalTime aTime = LocalTime.parse(ankunftszeit);
			LocalTime weckZeit = aTime.minus((long) gesamteZeit, ChronoUnit.MINUTES);
			System.out.println("\\nDer Wecker wurde auf " + weckZeit + " Uhr gestellt.");

		// case1
		} else if (option == 1) {
			JSONParser parser = new JSONParser();
			System.out.println("Daten werden aus der Datenbank geladen! \n\nBitte warten.");
			waitingLine();
			try {
				Object obj = parser.parse(new FileReader(fileName));
				JSONObject jsonObject = (JSONObject) obj;
				do {
					ankunftszeit = (String) jsonObject.get("ankunftszeit");
					String fahrtwegStr = (String) jsonObject.get("fahrtweg");
					fahrtweg = Float.parseFloat(fahrtwegStr);
					String zeitReadyStr = (String) jsonObject.get("zeitReady");
					zeitReady = Float.parseFloat(zeitReadyStr);
					String zeitBusStr = (String) jsonObject.get("zeitBus");
					zeitBus = Float.parseFloat(zeitBusStr);
					String mehrZeitStr = (String) jsonObject.get("mehrZeit");
					mehrZeit = Float.parseFloat(mehrZeitStr);
					System.out.println("\n\nDiese Daten wurden geladen:\nAnkunftszeit: " + ankunftszeit
							+ " Uhr\nFahrtweg: " + fahrtweg + " Minuten\nZeit zum Fertig machen: " + zeitReady
							+ " Minuten\nWeg zum Bus/Bahn: " + zeitBus + " Minuten\nWeitere Zeit: " + mehrZeit
							+ " Minuten");
					System.out.println("Sind alle Daten soweit korrekt? Ja (1) Nein (2)");
					option = in.nextInt();
				} while (option == 2);
				System.out.println("Berechne Weckzeit");
				waitingLine();
				gesamteZeit = fahrtweg + zeitReady + zeitBus + mehrZeit;
				LocalTime aTime = LocalTime.parse(ankunftszeit);
				LocalTime weckZeit = aTime.minus((long) gesamteZeit, ChronoUnit.MINUTES);
				System.out.println("\nDer Wecker wurde auf " + weckZeit + " Uhr gestellt.");

			} catch (FileNotFoundException e) {e.printStackTrace();
			} catch (IOException e) {e.printStackTrace();
			} catch (Exception e) {e.printStackTrace();}

		}
		in.close();
	}

	static void waitingLine() {
		for (int i = 0; i <= 10; i++) {
			System.out.print(".");
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {e.printStackTrace();}
		}

	}
}
