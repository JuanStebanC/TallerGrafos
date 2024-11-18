public class Nodo {
    private String id; // Identificador único del servidor

    public Nodo(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
}
