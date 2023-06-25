package it.polito.tdp.crimes.model;
import org.jgrapht.*;
import org.jgrapht.graph.DefaultWeightedEdge;
import it.polito.tdp.crimes.model.Evento.EventType;
import java.util.*;

public class Simulator {

	int N;
	int mese;
	int anno;
	int giorno;
	Distretto d1;
	
	List<Event> eventi;
	Graph<Distretto, DefaultWeightedEdge> grafo;
	Model model;
	Queue<Evento> queue;
	HashMap<Integer, Distretto> DisMap;
	List<Agente> agenti;
	
	int casi = 0;
	int bad = 0;
	
	public Simulator(int n, int mese, int anno, int giorno, Model model) {
		super();
		N = n;
		this.mese = mese;
		this.anno = anno;
		this.giorno = giorno;
		this.model = model;
		grafo = model.getGrafo();
		eventi = model.getEventi(mese, giorno);
		d1 = model.getD();
		queue = new PriorityQueue<Evento>();
		DisMap = model.DisMap;
		agenti = new LinkedList<Agente>();
	}
	
	public void init() {
		for(Event e : eventi) {
			queue.add(new Evento(EventType.EVENTO, DisMap.get(e.getDistrict_id()), e.getReported_date(), null, e));
			casi++;
		}
		for(int i = 1; i<=N; i++) {
			Agente a = new Agente(i, d1);
			agenti.add(a);
		}
	}
	
	public void run() {
		while(!queue.isEmpty()) {
			process(queue.poll());
			
		}
	}
	
	private void process(Evento evento) {
		System.out.println(evento.toString());
		
		switch(evento.tipo) {
		
			case AGENTE:
				//inserire agente nel distretto OK
				//sommare tempo di elaborazione evento OK
				//capire quanto ritardo ha fatto OK
				//liberare l'agente OK
				
				if(evento.getTime().compareTo(evento.getE1().getReported_date().plusMinutes(15))>0) {
					bad++;
					System.out.println("mal gestito per ritardo.");
				}else {
					System.out.println("ben gestito.");
				}
				
				int durata = 0;
				if(evento.getE1().getOffense_category_id().compareTo("all_other_crimes")==0) {
					double d = (Math.random()*2);
					if(d<1) {
						durata = 1;
					}else {
						durata = 2;
					}
				}else {
					durata = 2;
				}
				
				queue.add(new Evento(EventType.FINE, evento.getD(), evento.getTime().plusHours(durata), evento.getA1(), evento.getE1()));
				
				break;
				
			case EVENTO:
				
				Distretto t = evento.getD();
				Agente doc = null;
				double distanza = Integer.MAX_VALUE;
				
				for(Agente a : agenti) {
					if(a.isLibero()) {
						if(a.distanza(t)<distanza) {
							doc = a;
							distanza = a.distanza(t);
						}
					}
				}
				
				if(doc!=null) {
					doc.occupato();
					
					queue.add(new Evento(EventType.AGENTE, t, evento.getTime().plusMinutes((long)doc.distanza(t)), doc, evento.getE1()));
				}else {
					bad++;
					System.out.println("mal gestito perchè nessun agente era libero.");
				}
				
				break;
			case FINE:
				evento.getA1().libero();
				evento.getA1().setD(evento.getD());
			
				break;
		}
		
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public int getMese() {
		return mese;
	}

	public void setMese(int mese) {
		this.mese = mese;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public int getGiorno() {
		return giorno;
	}

	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}

	public int getCasi() {
		return casi;
	}

	public void setCasi(int casi) {
		this.casi = casi;
	}

	public int getBad() {
		return bad;
	}

	public void setBad(int bad) {
		this.bad = bad;
	}
	
	
	
	
	
	
	
	
}
