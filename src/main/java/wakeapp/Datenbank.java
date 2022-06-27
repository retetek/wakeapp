package wakeapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Datenbank implements Serializable {

	private String ankunftszeit;
	private float fahrtweg, zeitReady, zeitBus, mehrZeit;
	private List<Object> zeiten;

	public Datenbank(String ankunftszeit, float fahrtweg, float zeitReady, float zeitBus, float mehrZeit) {
		this.ankunftszeit = ankunftszeit;
		this.zeitReady = zeitReady;
		this.fahrtweg = fahrtweg;
		this.zeitBus = zeitBus;
		this.mehrZeit = mehrZeit;
		zeiten = new ArrayList<>();
		
		zeiten.add("Zeit1");
	}

	public String getAnkunftszeit() {
		return ankunftszeit;
	}

	public void setAnkunftszeit(String ankunftszeit) {
		this.ankunftszeit = ankunftszeit;
	}

	public float getFahrtweg() {
		return fahrtweg;
	}

	public void setFahrtweg(float fahrtweg) {
		this.fahrtweg = fahrtweg;
	}

	public float getZeitReady() {
		return zeitReady;
	}

	public void setZeitReady(float zeitReady) {
		this.zeitReady = zeitReady;
	}

	public float getZeitBus() {
		return zeitBus;
	}

	public void setZeitBus(float zeitBus) {
		this.zeitBus = zeitBus;
	}

	public float getMehrZeit() {
		return mehrZeit;
	}

	public void setMehrZeit(float mehrZeit) {
		this.mehrZeit = mehrZeit;
	}

}
