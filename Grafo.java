import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Grafo {
    private DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> grafo;

    public Grafo() {
        grafo = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
    }

    public void agregarNodo(String nodo) {
        grafo.addVertex(nodo);
    }

    public void agregarArista(String origen, String destino, double peso) {
        DefaultWeightedEdge edge = grafo.addEdge(origen, destino);
        if (edge != null) {
            grafo.setEdgeWeight(edge, peso);
        }
    }

    public DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> getGrafo() {
        return grafo;
    }
}
