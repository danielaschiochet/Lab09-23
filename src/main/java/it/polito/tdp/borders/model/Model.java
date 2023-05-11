package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private BordersDAO dao;
	private Graph<Country, DefaultEdge> grafo;
	private List<Country> paesi;
	private Map<Integer, Country> paesiIdMap;


	public Model() {
		dao = new BordersDAO();
	}

	public void creaGrafo(int anno) {

		paesiIdMap = new HashMap<>();
		
		this.grafo = new SimpleGraph<>(DefaultEdge.class);

		paesi = dao.loadAllCountries();
		
		for(Country c: paesi) {
			this.paesiIdMap.put(c.getcCode(),c);
		}
		
		List<Border> confini = dao.getCountryPairs(anno, paesiIdMap);
		
		for(Border b: confini) {
			grafo.addVertex(b.getC1());
			grafo.addVertex(b.getC2());
			grafo.addEdge(b.getC1(), b.getC2());
		}
		
		System.out.println("Grafo creato correttamente con "+grafo.vertexSet().size()+" vertici e "+grafo.edgeSet().size()+" archi.\n");
		
		paesi = new ArrayList<>(grafo.vertexSet());
	}
	
	public boolean isGrafoLoaded() {
		return this.grafo.vertexSet().size()>0;
	}

	public List<Country> getCountries() {
	
		return paesi;
	}

	public Integer getNumberOfConnectedComponents() {

		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<Country, DefaultEdge>(grafo);
		
		return ci.connectedSets().size();
	}

	public Map<Country, Integer> getCountryCounts() {
		Map<Country, Integer> stats = new HashMap<Country, Integer>();
		
		for (Country country : grafo.vertexSet()) {
			stats.put(country, grafo.degreeOf(country));
		}
		return stats;
	}

}
