package it.polito.tdp.crimes.model;
import com.javadocmd.simplelatlng.*;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Distanze implements Comparable<Distanze>{
	
	Distretto d1;
	Distretto d2;
	double distanza;
	
	public Distanze(Distretto d1, Distretto d2) {
		super();
		this.d1 = d1;
		this.d2 = d2;
		this.calcoladistanza();
	}
	
	private void calcoladistanza() {
		distanza = LatLngTool.distance(d1.getCentro(), d2.getCentro(), LengthUnit.KILOMETER);
	}
	
	public double getDistanza() {
		return distanza;
	}

	@Override
	public int compareTo(Distanze o) {
		return (int) (this.distanza-o.distanza);
	}

	@Override
	public String toString() {
		return "[d1=" + d1.getId() + ", d2=" + d2.getId() + ", distanza=" + distanza + "]";
	}
	
	
	

}
