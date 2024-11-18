import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

class Dijkstra {
    private DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> grafo;

    public Dijkstra(DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> grafo) {
        this.grafo = grafo;
    }

    public void encontrarRutaMasCortaDesdeHasta(String nodoFuente, String nodoDestino, GrafoVisualizador visualizador) {
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(grafo);

        var ruta = dijkstra.getPath(nodoFuente, nodoDestino);
        if (ruta == null) {
            System.out.println("No existe una ruta entre " + nodoFuente + " y " + nodoDestino + ".");
        } else {
            System.out.println("Ruta más corta de " + nodoFuente + " a " + nodoDestino + ":");
            ruta.getEdgeList().forEach(edge -> System.out.println(grafo.getEdgeSource(edge) + " -> " + grafo.getEdgeTarget(edge)));
            System.out.println("Costo total: " + ruta.getWeight());

            visualizador.resaltarRuta(ruta.getEdgeList());
        }
    }

}
