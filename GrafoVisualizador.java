import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class GrafoVisualizador {

    private DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> grafo;
    private mxGraph jGraph;
    private Map<String, Object> nodos;
    private Map<DefaultWeightedEdge, Object> aristas;

    public GrafoVisualizador(DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> grafo) {
        this.grafo = grafo;
        this.nodos = new HashMap<>();
        this.aristas = new HashMap<>();
    }

    public void mostrarGrafo() {
        jGraph = new mxGraph();
        Object parent = jGraph.getDefaultParent();

        jGraph.getModel().beginUpdate();
        try {
            for (String vertice : grafo.vertexSet()) {
                Object nodo = jGraph.insertVertex(parent, null, vertice, 0, 0, 80, 30);
                nodos.put(vertice, nodo);
            }

            for (DefaultWeightedEdge edge : grafo.edgeSet()) {
                String origen = grafo.getEdgeSource(edge);
                String destino = grafo.getEdgeTarget(edge);
                double peso = grafo.getEdgeWeight(edge);

                Object arista = jGraph.insertEdge(parent, null, String.format("%.1f", peso), nodos.get(origen), nodos.get(destino));
                aristas.put(edge, arista);
            }
        } finally {
            jGraph.getModel().endUpdate();
        }


        mxCircleLayout layout = new mxCircleLayout(jGraph);
        layout.execute(jGraph.getDefaultParent());

        JFrame frame = new JFrame("Visualización de Grafo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mxGraphComponent graphComponent = new mxGraphComponent(jGraph);

        graphComponent.getGraph().setEdgeLabelsMovable(false);
        frame.getContentPane().add(graphComponent);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void resaltarRuta(List<DefaultWeightedEdge> ruta) {
        jGraph.getModel().beginUpdate();
        try {
            for (Object nodo : nodos.values()) {
                jGraph.setCellStyle("defaultVertex", new Object[]{nodo});
            }
            for (Object arista : aristas.values()) {
                jGraph.setCellStyle("strokeColor=black;labelBackgroundColor=white", new Object[]{arista});
            }

            for (DefaultWeightedEdge edge : ruta) {
                Object arista = aristas.get(edge);
                jGraph.setCellStyle("strokeColor=red;strokeWidth=2;labelBackgroundColor=yellow;fontColor=blue", new Object[]{arista});

                Object origen = nodos.get(grafo.getEdgeSource(edge));
                Object destino = nodos.get(grafo.getEdgeTarget(edge));
                jGraph.setCellStyle("fillColor=red;fontColor=white", new Object[]{origen, destino});
            }
        } finally {
            jGraph.getModel().endUpdate();
        }
    }
}
