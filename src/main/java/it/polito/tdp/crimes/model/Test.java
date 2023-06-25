package it.polito.tdp.crimes.model;

public class Test {

	public static void main(String[] args) {
		
		Model model = new Model();
		model.creaGrafo(2015);
		System.out.println(model.Sim(2015, 12, 10, 10));
		
	}

}
