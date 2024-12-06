package Model;

import java.util.*;

public class Grafo {
    private Map<Nodo, List<Arista>> adyacencia;

    // Constructor que inicializa el grafo con un mapa vacío de adyacencia
    public Grafo() {
        adyacencia = new HashMap<>();
    }

    // Método para agregar un nodo al grafo
    public void agregarNodo(Nodo nodo) {
        adyacencia.putIfAbsent(nodo, new ArrayList<>());
    }

    // Método para agregar una arista entre dos nodos
    public void agregarArista(Arista arista) {
        agregarNodo(arista.getNodoInicio());
        agregarNodo(arista.getNodoFin());
        adyacencia.get(arista.getNodoInicio()).add(arista);
    }

    // Método que implementa el algoritmo de Edmonds-Karp para el cálculo del flujo máximo
    public double edmondsKarp(Nodo fuente, Nodo sumidero) {
        Map<Arista, Double> capacidadResidual = new HashMap<>();
        Map<Nodo, Arista> padres = new HashMap<>();
        double flujoMaximo = 0;

        // Inicializa las capacidades residuales con las capacidades de las aristas
        for (Nodo nodo : adyacencia.keySet()) {
            for (Arista arista : adyacencia.get(nodo)) {
                capacidadResidual.put(arista, arista.getCapacidad());
            }
        }

        // Ejecuta el algoritmo de búsqueda de caminos aumentantes
        while (bfs(fuente, sumidero, capacidadResidual, padres)) {
            double flujoAumentante = Double.MAX_VALUE;
            Nodo nodoActual = sumidero;

            // Determina el flujo máximo posible en el camino encontrado
            while (!nodoActual.equals(fuente)) {
                Arista arista = padres.get(nodoActual);
                flujoAumentante = Math.min(flujoAumentante, capacidadResidual.get(arista));
                nodoActual = arista.getNodoInicio();
            }

            // Actualiza las capacidades residuales de las aristas
            nodoActual = sumidero;
            while (!nodoActual.equals(fuente)) {
                Arista arista = padres.get(nodoActual);
                capacidadResidual.put(arista, capacidadResidual.get(arista) - flujoAumentante);
                Nodo nodoAnterior = arista.getNodoInicio();
                Arista aristaInversa = new Arista(arista.getNodoFin(), nodoAnterior, 0);
                capacidadResidual.put(aristaInversa, capacidadResidual.getOrDefault(aristaInversa, 0.0) + flujoAumentante);
                nodoActual = arista.getNodoInicio();
            }

            flujoMaximo += flujoAumentante;
        }

        return flujoMaximo;
    }

    // Método auxiliar para realizar la búsqueda en amplitud (BFS)
    private boolean bfs(Nodo fuente, Nodo sumidero, Map<Arista, Double> capacidadResidual, Map<Nodo, Arista> padres) {
        Set<Nodo> visitados = new HashSet<>();
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(fuente);
        visitados.add(fuente);

        // Recorrido por los nodos adyacentes usando BFS
        while (!queue.isEmpty()) {
            Nodo nodoActual = queue.poll();

            for (Arista arista : adyacencia.getOrDefault(nodoActual, Collections.emptyList())) {
                if (!visitados.contains(arista.getNodoFin()) && capacidadResidual.get(arista) > 0) {
                    queue.add(arista.getNodoFin());
                    visitados.add(arista.getNodoFin());
                    padres.put(arista.getNodoFin(), arista);
                    if (arista.getNodoFin().equals(sumidero)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Algoritmo de Prim para encontrar el árbol de expansión mínima (MST)
    public List<Arista> prim(Nodo nodoInicial) {
        Set<Nodo> visitados = new HashSet<>();
        PriorityQueue<Arista> pq = new PriorityQueue<>(Comparator.comparingDouble(Arista::getCapacidad));
        List<Arista> mst = new ArrayList<>();

        visitados.add(nodoInicial);
        pq.addAll(adyacencia.get(nodoInicial));

        // Ejecución del algoritmo de Prim
        while (!pq.isEmpty()) {
            Arista arista = pq.poll();
            Nodo nodoDestino = arista.getNodoFin();

            if (!visitados.contains(nodoDestino)) {
                visitados.add(nodoDestino);
                mst.add(arista);

                for (Arista adyacente : adyacencia.get(nodoDestino)) {
                    if (!visitados.contains(adyacente.getNodoFin())) {
                        pq.add(adyacente);
                    }
                }
            }
        }

        return mst;
    }

    public void setNodos(ArrayList<Nodo> nodos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNodos'");
    }

    public void setAristas(ArrayList<Arista> aristas) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAristas'");
    }
}

