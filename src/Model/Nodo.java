package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa un nodo dentro de un sistema eléctrico o energético, como generadores, consumidores, y transformadores.
 */
public class Nodo {
    private String id; // Identificador único del nodo (Ej. generador1, consumo2)
    private TipoNodo tipo; // Tipo de nodo: Generador, Consumo, Transformador
    private double capacidadMaxima; // Capacidad máxima de transmisión o generación
    private double cargaActual; // Carga actual de energía (solo para consumidores)
    private double generacionActual; // Generación actual de energía (solo para generadores)
    private List<Arista> aristasConectadas; // Lista de aristas conectadas al nodo

    /**
     * Enum que define los tipos posibles de nodos.
     */
    public enum TipoNodo {
        GENERADOR, CONSUMO, TRANSFORMADOR
    }

    /**
     * Constructor para Nodo (por ejemplo, Generador, Transformador, Consumo)
     */
    public Nodo(String id, TipoNodo tipo, double capacidadMaxima) {
        setId(id);
        setTipo(tipo);
        setCapacidadMaxima(capacidadMaxima);
        this.aristasConectadas = new ArrayList<>();
    }

    // Métodos Getter y Setter con validación
    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID no puede ser nulo o vacío.");
        }
        this.id = id;
    }

    public TipoNodo getTipo() {
        return tipo;
    }

    public void setTipo(TipoNodo tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de nodo no puede ser nulo.");
        }
        this.tipo = tipo;
    }

    public double getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(double capacidadMaxima) {
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad máxima debe ser mayor a cero.");
        }
        this.capacidadMaxima = capacidadMaxima;
    }

    public double getCargaActual() {
        return cargaActual;
    }

    public void setCargaActual(double cargaActual) {
        if (tipo == TipoNodo.CONSUMO) {
            if (cargaActual < 0 || cargaActual > capacidadMaxima) {
                throw new IllegalArgumentException("La carga actual no puede ser menor que 0 o mayor que la capacidad máxima.");
            }
            this.cargaActual = cargaActual;
        } else {
            throw new IllegalStateException("El nodo no es de tipo 'Consumo', no se puede asignar carga.");
        }
    }

    public double getGeneracionActual() {
        return generacionActual;
    }

    public void setGeneracionActual(double generacionActual) {
        if (tipo == TipoNodo.GENERADOR) {
            if (generacionActual < 0 || generacionActual > capacidadMaxima) {
                throw new IllegalArgumentException("La generación actual no puede ser menor que 0 o mayor que la capacidad máxima.");
            }
            this.generacionActual = generacionActual;
        } else {
            throw new IllegalStateException("El nodo no es de tipo 'Generador', no se puede asignar generación.");
        }
    }

    public List<Arista> getAristasConectadas() {
        return new ArrayList<>(aristasConectadas); // Retornamos una copia para evitar modificaciones externas
    }

    // Métodos para agregar y quitar aristas
    public void agregarArista(Arista arista) {
        if (arista == null) {
            throw new IllegalArgumentException("La arista no puede ser nula.");
        }
        aristasConectadas.add(arista);
    }

    public void quitarArista(Arista arista) {
        if (arista == null) {
            throw new IllegalArgumentException("La arista no puede ser nula.");
        }
        aristasConectadas.remove(arista);
    }

    // Método para mostrar los detalles del nodo
    public void mostrarDetalles() {
        System.out.println("Nodo ID: " + id);
        System.out.println("Tipo: " + tipo);
        System.out.println("Capacidad Máxima: " + capacidadMaxima);
        if (tipo == TipoNodo.CONSUMO) {
            System.out.println("Carga Actual: " + cargaActual);
        } else if (tipo == TipoNodo.GENERADOR) {
            System.out.println("Generación Actual: " + generacionActual);
        }
    }

    @Override
    public String toString() {
        return "Nodo{id='" + id + "', tipo='" + tipo + "', capacidadMaxima=" + capacidadMaxima + "}"; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodo nodo = (Nodo) o;
        return Double.compare(nodo.capacidadMaxima, capacidadMaxima) == 0 &&
               Double.compare(nodo.cargaActual, cargaActual) == 0 &&
               Double.compare(nodo.generacionActual, generacionActual) == 0 &&
               id.equals(nodo.id) &&
               tipo == nodo.tipo && // Aquí comparamos los tipos de nodo correctamente
               aristasConectadas.equals(nodo.aristasConectadas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, capacidadMaxima, cargaActual, generacionActual, aristasConectadas);
    }
}
