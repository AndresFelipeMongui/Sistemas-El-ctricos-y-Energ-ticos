package Controller;

import Model.Grafo;
import Model.Nodo;
import View.VistaConsola;

public class FlujoMaximoControlador {

    private Grafo grafo;
    private VistaConsola vista;

    // Constructor que recibe el grafo y la vista para la visualización de resultados
    public FlujoMaximoControlador(Grafo grafo, VistaConsola vista) {
        this.grafo = grafo;
        this.vista = vista;
    }

    // Método que calcula el flujo máximo utilizando el algoritmo de Edmonds-Karp
    public void calcularFlujoMaximo(Nodo nodo, Nodo nodo1) {

        // Llama al método edmondsKarp para calcular el flujo máximo entre dos nodos
        double flujoMaximo = grafo.edmondsKarp(nodo, nodo1);

        // Muestra el flujo máximo usando la vista
        vista.mostrarFlujoMaximo(flujoMaximo);
    }
}
