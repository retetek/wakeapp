package wakeapp;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class WakeApp {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		char nein = 'n';
		char ja = 'j';
		char korrekt;
		String ankunftszeit;
		float fahrtweg;
		float zeitReady;
		float zeitBus;
		float mehrZeit;
		double verzögerungen;
		double gesamteZeit;
		String line = "----------------------------";
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

		// Einleitung
		System.out.println("Uhrzeit: " + format.format(Calendar.getInstance().getTime()));
		System.out.println("Willkommen in Ihrer WakeApp!");
		System.out.println(line);
		System.out.println("Haben Sie gespeicherte Daten und wollen diese laden? J/N?");
		System.out.println("Ja (1) | Nein (2)");
		int option = in.nextInt();

		// neue eingabe sowie Berrechnung
		if (option == 2) {

			System.out.println("Neue Eingabe \nGeben Sie ihre Ankunftszeit ein in hh:mm Format:");
			ankunftszeit = in.next();
			System.out.println("Wie lange brauchen Sie für Ihren Fahrtweg? (in Minuten)");
			fahrtweg = in.nextFloat();
			System.out.println("Wie lange Brauchen Sie, um sich fertig zu machen? (in Minuten)");
			zeitReady = in.nextFloat();
			System.out.println("Wie lange ist der Weg zum Bahn oder dem Bus? (in Minuten)");
			System.out.println("Falls Sie mit Auto fahren, dann den Weg zum Auto eintragen!");
			zeitBus = in.nextFloat();
			System.out.println("Benötigen Sie noch etwas Zeit, wenn ja, bitte hinschrieben (in Minuten)");
			mehrZeit = in.nextFloat();
			System.out.println("Zusammenfassung:");
			System.out.println("Ankunftszeit: " + ankunftszeit + " Uhr");
			System.out.println("Fahrtweg: " + fahrtweg + " Minuten");
			System.out.println("Zeit zum Fertig machen: " + zeitReady + " Minuten");
			System.out.println("Weg zum Bus/Bahn: " + zeitBus + " Minuten");
			System.out.println("Weitere Zeit: " + mehrZeit + " Minuten");
			System.out.println("Ist das korrekt? j/n");
			korrekt = in.next().charAt(0);

			gesamteZeit = fahrtweg + zeitReady + zeitBus + mehrZeit;
			LocalTime aTime = LocalTime.parse(ankunftszeit);
			System.out.println(gesamteZeit);
			System.out.println(aTime);
			LocalTime value = aTime.minus((long) gesamteZeit, ChronoUnit.MINUTES);
			System.out.println(value);

		}
		else if (option == 1) 
		{
			System.out.println("Daten laden!");
		}
	}

}
