package it.polito.tdp.crimes.model;
import java.util.*;


import com.javadocmd.simplelatlng.LatLng;

public class Distretto {

	int id;
	List<Event> eventi;
	LatLng centro;
	List<Distanze> distanze;
	
	public Distretto(int id) {
		super();
		this.id = id;
		eventi = new LinkedList<Event>();
		distanze = new LinkedList<Distanze>();
	}
	
	public void addEvent(Event e) {
		eventi.add(e);
	}
	
	private void calcolacentro() {
		double lat = 0;
		double lng = 0;
		for(Event e : eventi) {
			lat += e.getGeo_lat();
			lng += e.getGeo_lon();
		}
		centro = new LatLng(lat/eventi.size(), lng/eventi.size());
	}
	
	public LatLng getCentro() {
		this.calcolacentro();
		return centro;
	}

	public int getId() {
		return id;
	}

	public void add(Distanze d) {
		distanze.add(d);
	}
	
	public List<Distanze> getSortedList(){
		Collections.sort(distanze);
		return distanze;
	}
	
	@Override
	public String toString() {
		return "Distretto " + id;
	}
	
	
	
	
	
	
	
}
