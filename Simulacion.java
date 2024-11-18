import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Simulacion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Grafo grafo = new Grafo();

        System.out.println("Bienvenido a la simulación de red empresarial.");

        // Agregar nodos
        System.out.println("Ingrese los servidores (nodos) de la red. Escriba 'fin' para terminar:");
        List<String> servidores = new ArrayList<>();
        while (true) {
            System.out.print("Servidor: ");
            String servidor = scanner.nextLine();
            if (servidor.equalsIgnoreCase("fin")) break;
            grafo.agregarNodo(servidor);
            servidores.add(servidor);
        }

        // Generar conexiones aleatorias
        System.out.println("\nGenerando conexiones aleatorias entre servidores...");
        for (String origen : servidores) {
            for (String destino : servidores) {
                if (!origen.equals(destino) && random.nextBoolean()) {
                    // Verificar si ya existe una conexión en el sentido inverso
                    if (grafo.getGrafo().containsEdge(destino, origen)) {
                        continue; // Si existe en el sentido inverso, no agregar esta conexión
                    }

                    double peso = 1 + random.nextInt(10);
                    grafo.agregarArista(origen, destino, peso);
                    System.out.println("Conexión creada: " + origen + " -> " + destino + " [Peso: " + peso + "]");
                }
            }
        }

        // Visualizar el grafo
        GrafoVisualizador visualizador = new GrafoVisualizador(grafo.getGrafo());
        visualizador.mostrarGrafo();

        System.out.println("\nRed configurada con éxito.");
        System.out.print("Ingrese el servidor fuente: ");
        String nodoFuente = scanner.nextLine();

        System.out.print("Ingrese el servidor destino: ");
        String nodoDestino = scanner.nextLine();

        // Ejecutar Dijkstra y resaltar ruta
        Dijkstra dijkstra = new Dijkstra(grafo.getGrafo());
        System.out.println("\nCalculando la ruta más corta...");
        dijkstra.encontrarRutaMasCortaDesdeHasta(nodoFuente, nodoDestino, visualizador);

        scanner.close();
    }
}
