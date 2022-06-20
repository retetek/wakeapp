package wakeapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class WakeApp {

	public static void main(String[] args) {

		// Variablen & Deklarationen
		Scanner in = new Scanner(System.in);
		String ankunftszeit;
		float fahrtweg;
		float zeitReady;
		float zeitBus;
		float mehrZeit;
		double gesamteZeit;
		String line = "----------------------------";
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		int option;

		// Einleitung
		System.out.println(
				"Uhrzeit: " + format.format(Calendar.getInstance().getTime()) + "\nWillkommen in Ihrer WakeApp!\n"
						+ line + "\nHaben Sie gespeicherte Daten und wollen diese laden?\nJa (1) | Nein (2)");
		option = in.nextInt();

		// neue eingabe sowie Berrechnung
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

			gesamteZeit = fahrtweg + zeitReady + zeitBus + mehrZeit;
			LocalTime aTime = LocalTime.parse(ankunftszeit);
			LocalTime weckZeit = aTime.minus((long) gesamteZeit, ChronoUnit.MINUTES);
			System.out.println("Der Wecker wurde auf " + weckZeit + "Uhr gestellt.");

		} else if (option == 1) {
			System.out.println("Daten laden!");
		}
	}

}
