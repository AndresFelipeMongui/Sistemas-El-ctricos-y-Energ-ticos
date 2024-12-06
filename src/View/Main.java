package View;

import java.util.List;

import Controller.AlgoritmoPrimControlador;
import Controller.FlujoMaximoControlador;
import Model.Arista;
import Model.Grafo;
import Model.Nodo;

public class Main {

    static FlujoMaximoControlador f;

    public static void main(String[] args) {
        // Crear nodos
        Nodo nodo1 = new Nodo("A", Nodo.TipoNodo.GENERADOR, 100);
        Nodo nodo2 = new Nodo("B", Nodo.TipoNodo.CONSUMO, 50);
        Nodo nodo3 = new Nodo("C", Nodo.TipoNodo.TRANSFORMADOR, 30);
        Nodo nodo4 = new Nodo("D", Nodo.TipoNodo.CONSUMO, 40);

        // Crear aristas
        Arista arista1 = new Arista(nodo1, nodo2, 50);
        Arista arista2 = new Arista(nodo1, nodo3, 30);
        Arista arista3 = new Arista(nodo2, nodo4, 40);
        Arista arista4 = new Arista(nodo3, nodo4, 20);

        // Crear grafo y agregar aristas
        Grafo grafo = new Grafo();
        grafo.agregarArista(arista1);
        grafo.agregarArista(arista2);
        grafo.agregarArista(arista3);
        grafo.agregarArista(arista4);

        // Algoritmo de flujo máximo (Edmonds-Karp)

        System.out.println("Ejecutando flujo máximo con Edmonds-Karp:");

        // Crear el controlador de Edmonds-Karp
        Nodo nodoFuente = nodo1;
        Nodo nodoSumidero = nodo4;
       // f.calcularFlujoMaximo(nodoFuente, nodoSumidero);
       double flujoMaximo = grafo.edmondsKarp(nodoFuente, nodoSumidero);

       System.out.println("El flujo máximo desde " + nodoFuente.getId() + " hasta " + nodoSumidero.getId() + " es: " + flujoMaximo);
       System.out.println();

        //  Algoritmo de Prim (Árbol de Expansión Mínima)

        System.out.println("Ejecutando árbol de expansión mínima con el algoritmo de Prim:");

        // Crear el controlador de Prim
        AlgoritmoPrimControlador controladorPrim = new AlgoritmoPrimControlador(grafo);

        // Ejecutar algoritmo de Prim desde el nodo "A"
        List<Arista> mst = controladorPrim.ejecutarAlgoritmoPrim(nodo1);

        // Mostrar las aristas del árbol de expansión mínima
        System.out.println("Árbol de expansión mínima (MST) utilizando Prim:");
        for (Arista arista : mst) {
            System.out.println(arista);
        }
    }

}