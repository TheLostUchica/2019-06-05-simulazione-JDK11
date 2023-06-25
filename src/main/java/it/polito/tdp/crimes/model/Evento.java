package it.polito.tdp.crimes.model;

import java.time.LocalDateTime;

public class Evento implements Comparable<Evento>{
	
	enum EventType{
		EVENTO,
		AGENTE,
		FINE
	}
	
	EventType tipo;
	Distretto d;
	LocalDateTime time;
	Agente a1;
	Event e1;
	
	public Evento(EventType tipo, Distretto d, LocalDateTime time, Agente a1, Event e1) {
		super();
		this.tipo = tipo;
		this.d = d;
		this.time = time;
		this.a1 = a1;
		this.e1 = e1;
	}
	
	
	public EventType getTipo() {
		return tipo;
	}


	public Distretto getD() {
		return d;
	}
	
	public LocalDateTime getTime() {
		return time;
	} 
	
	public Agente getA1() {
		return a1;
	}

	public Event getE1() {
		return e1;
	}
	
	@Override
	public int compareTo(Evento o) {
		return this.time.compareTo(o.time);
	}


	@Override
	public String toString() {
		return "Evento [tipo=" + tipo + ", d=" + d + ", time=" + time + ", e1=" + e1.getIncident_id() + "]";
	}
	
	

}
