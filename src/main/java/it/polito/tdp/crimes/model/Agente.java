package it.polito.tdp.crimes.model;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Agente {

	Distretto d;
	int id;
	boolean libero;
	
	public Agente(int id, Distretto d) {
		this.id = id;
		this.d = d;
		this.libero = true;
	}

	public Distretto getD() {
		return d;
	}

	public void setD(Distretto d) {
		this.d = d;
	}

	public int getId() {
		return id;
	}
	
	public void occupato() {
		this.libero = false;
	}
	
	public void libero() {
		this.libero = true;
	}

	public boolean isLibero() {
		return libero;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public double distanza(Distretto a) {
		return LatLngTool.distance(this.d.getCentro(), a.getCentro(), LengthUnit.KILOMETER);
	}
	
}
