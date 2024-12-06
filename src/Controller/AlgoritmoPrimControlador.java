package Controller;

import Model.Arista;
import Model.Grafo;
import Model.Nodo;

import java.util.List;

public class AlgoritmoPrimControlador {

    private Grafo grafo;
 // Constructor que recibe un grafo para ser usado por el controlador
 public AlgoritmoPrimControlador(Grafo grafo) {
    this.grafo = grafo;
}

// Método que ejecuta el algoritmo de Prim para encontrar el árbol de expansión mínima
public List<Arista> ejecutarAlgoritmoPrim(Nodo nodoInicial) {
    // Llama al método prim del grafo y retorna las aristas del MST
    return grafo.prim(nodoInicial);
    }
}
