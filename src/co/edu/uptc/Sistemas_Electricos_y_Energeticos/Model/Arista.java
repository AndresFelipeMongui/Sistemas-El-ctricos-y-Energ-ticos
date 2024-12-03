package co.edu.uptc.Sistemas_Electricos_y_Energeticos.Model;

import java.util.Objects;

/**
 * Representa una arista (conexión) entre dos nodos en una red eléctrica o energética.
 * Cada arista tiene una capacidad de transmisión.
 */
public class Arista {
    private Nodo nodoInicio;  // Nodo de inicio de la arista
    private Nodo nodoFin;     // Nodo de fin de la arista
    private double capacidad; // Capacidad de la arista (por ejemplo, capacidad de transmisión)

    /***9
     * Constructor para crear una nueva arista.
     * 
     * @param nodoInicio El nodo de inicio de la arista.
     * @param nodoFin El nodo de fin de la arista.
     * @param capacidad La capacidad de la arista.
     */
    public Arista(Nodo nodoInicio, Nodo nodoFin, double capacidad) {
        setNodoInicio(nodoInicio);
        setNodoFin(nodoFin);
        setCapacidad(capacidad);
    }

    // Métodos Getter y Setter con validación
    public Nodo getNodoInicio() {
        return nodoInicio;
    }

    public void setNodoInicio(Nodo nodoInicio) {
        if (nodoInicio == null) {
            throw new IllegalArgumentException("El nodo de inicio no puede ser nulo.");
        }
        this.nodoInicio = nodoInicio;
    }

    public Nodo getNodoFin() {
        return nodoFin;
    }

    public void setNodoFin(Nodo nodoFin) {
        if (nodoFin == null) {
            throw new IllegalArgumentException("El nodo de fin no puede ser nulo.");
        }
        this.nodoFin = nodoFin;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad de la arista debe ser mayor a cero.");
        }
        this.capacidad = capacidad;
    }

    /**
     * Muestra los detalles de la arista, incluyendo los nodos de inicio y fin, y su capacidad.
     */
    public void mostrarDetalles() {
        System.out.println(String.format("Arista entre %s y %s", nodoInicio.getId(), nodoFin.getId()));
        System.out.println("Capacidad: " + capacidad);
    }

    /**
     * Invierte la dirección de la arista, es decir, cambia el nodo de inicio y el nodo de fin.
     */
    public void invertir() {
        Nodo temp = this.nodoInicio;
        this.nodoInicio = this.nodoFin;
        this.nodoFin = temp;
    }

    @Override
    public String toString() {
        return String.format("Arista{nodoInicio=%s, nodoFin=%s, capacidad=%f}", 
                             nodoInicio.getId(), nodoFin.getId(), capacidad);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arista arista = (Arista) o;
        return Double.compare(arista.capacidad, capacidad) == 0 &&
               nodoInicio.equals(arista.nodoInicio) &&
               nodoFin.equals(arista.nodoFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodoInicio, nodoFin, capacidad);
    }
}
