package it.polito.tdp.crimes.model;
import java.util.*;
import org.jgrapht.*;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	
	EventsDao dao;
	HashMap<Integer, Distretto> DisMap; 
	Graph<Distretto, DefaultWeightedEdge> grafo;
	List<Event> eventi;
	
	public Model() {
		dao = new EventsDao();
		DisMap = new HashMap<Integer, Distretto>(dao.DisMap());
		eventi = new LinkedList<>();
	}
	
	public List<Integer> setCombo(){
		return dao.getYears();
	}
	
	public Graph<Distretto, DefaultWeightedEdge> getGrafo(){
		return this.grafo;
	}
	
	public List<Event> getEventi(int mese, int giorno){
		List<Event> result = new LinkedList<Event>();
		for(Event e : eventi) {
			if(e.getReported_date().getMonth().getValue()==mese && e.getReported_date().getDayOfMonth()==giorno) {
				result.add(e);
			}
		}
		return result;		
	}
	
	public void creaGrafo(int year) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, DisMap.values());
		
		eventi = dao.events(year);
		
		for(Event e : eventi) {
			DisMap.get(e.getDistrict_id()).addEvent(e);
		}
		
		for(Distretto d1 : this.grafo.vertexSet()) {
			for(Distretto d2 : this.grafo.vertexSet()) {
				if(d1.getId()<d2.getId()) {
					Distanze d = new Distanze(d1, d2);
					d1.add(d);
					d2.add(d);
					Graphs.addEdgeWithVertices(this.grafo, d1, d2, d.getDistanza());
				}
			}
		}
	}
	
	public String Sim(int anno, int mese, int giorno, int N) {
		Simulator sim = new Simulator(N, mese, anno, giorno, this);
		sim.init();
		sim.run();
		return sim.getBad()+" casi mal gestiti su "+sim.getCasi()+" il giorno "+sim.getGiorno()+"-"+sim.getMese()+"-"+sim.getAnno()+ " da "+sim.getN()+ " agenti.";
	}
	
	public Distretto getD() {
		int k = 100000000;
		Distretto target = null;
		for (Distretto d : this.grafo.vertexSet()) {
			if(d.eventi.size()<k) {
				target = d;
			}
		}
		return target;
	}
}
