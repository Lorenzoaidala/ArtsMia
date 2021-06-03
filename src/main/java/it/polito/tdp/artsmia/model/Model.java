package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {

	private Graph<ArtObject, DefaultWeightedEdge> grafo; // ArtObject è il tipo dei vertici del grafo, DefaultWeightedEdge
	private ArtsmiaDAO dao;													 //	significa che gli archi saranno semplici e pesati
	private Map<Integer, ArtObject> idMap; //pattern della identity map, per avere tutti gli oggetti disponibili e crearli una sola volta
	public Model() {	
		//grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		dao = new ArtsmiaDAO();
		idMap = new HashMap<Integer, ArtObject>();
	}

	public void creaGrafo() {
		//conviene creare qua il grafo e non nel costruttore
		//perchè chiamando il metodo il grafo è distrutto e ricreato da zero
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

		//Aggiunta dei vertici
		//1) recupero tutti gli SrtObject dal DB
		//2) Li inserisco come oggetti
		//List<ArtObject> vertici = dao.listObjects();
		dao.listObjects(idMap);
		Graphs.addAllVertices(grafo, idMap.values()); 

		//Aggiungere gli archi


		for(Adiacenza a : dao.getAdiacenze()) {
			Graphs.addEdge(this.grafo, idMap.get(a.getId1()),idMap.get(a.getId2()), a.getPeso());
		}


	}
}
