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
		dao.listObjects(idMap);
	}

	public void creaGrafo() {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, idMap.values());
		
		
	}
}
