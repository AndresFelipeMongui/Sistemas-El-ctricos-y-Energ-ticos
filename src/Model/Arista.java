package Model;

public class Arista {
    private Nodo nodoInicio;
    private Nodo nodoFin;
    private double capacidad;
    private double flujo;

     // Constructor que crea una arista con un nodo de inicio, un nodo de fin y una capacidad
     public Arista(Nodo nodoInicio, Nodo nodoFin, double capacidad) {
        this.nodoInicio = nodoInicio;
        this.nodoFin = nodoFin;
        this.capacidad = capacidad;
        this.flujo = 0;
    }

    // Getters y setters para los atributos
    public Nodo getNodoInicio() { return nodoInicio; }
    public void setNodoInicio(Nodo nodoInicio) { this.nodoInicio = nodoInicio; }

    public Nodo getNodoFin() { return nodoFin; }
    public void setNodoFin(Nodo nodoFin) { this.nodoFin = nodoFin; }

    public double getCapacidad() { return capacidad; }
    public void setCapacidad(double capacidad) { this.capacidad = capacidad; }

    public double getFlujo() { return flujo; }
    public void setFlujo(double flujo) { this.flujo = flujo; }

    // Método que devuelve una representación en formato de cadena de la arista
    @Override
    public String toString() {
        return String.format("Arista{nodoInicio=%s, nodoFin=%s, capacidad=%f}", 
                             nodoInicio.getId(), nodoFin.getId(), capacidad);
    }
}
