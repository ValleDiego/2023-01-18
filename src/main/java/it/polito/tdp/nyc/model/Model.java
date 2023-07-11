package it.polito.tdp.nyc.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
    
    private List<Citta> vertici;
    private NYCDao dao = new NYCDao();
    private Graph<Citta, DefaultWeightedEdge> grafo;
    
    
    public String creaGrafo(String p, double x) {
   	 this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
   	 vertici = new ArrayList();
   	 this.vertici.addAll(this.dao.getAllCitta(p));
   	 Graphs.addAllVertices(this.grafo, this.dao.getAllCitta(p));
   	 
   	 for(Citta v1 : vertici) {
   		 for(Citta v2 : vertici) {
   			 if(!v1.equals(v2)) {
   				 double peso = LatLngTool.distance(v1.getPosizione(), v2.getPosizione(), LengthUnit.KILOMETER);
   				 if(peso <= x ) {
   					 Graphs.addEdge(this.grafo,  v1,  v2, peso);
   				 }
   			 }
   		 }
   	 }
   	 
   	 
   	 return "Grafo creato!\n # Vertici: "+ this.grafo.vertexSet().size()+"\n # Archi: "+ this.grafo.edgeSet().size();
    }


    public List<String> getProviders() {
   	 // TODO Auto-generated method stubdddd
   	 
   	 return this.dao.getAllProviders();
    }
}
