package Controller;

import java.util.ArrayList;

import Model.Arista;
import Model.Grafo;
import Model.Nodo;
import Util.FileManagement;

public class CargarDatos {

    Grafo grafo;
    FileManagement fileManagement;
    

    public CargarDatos(Grafo grafo) {
       this.grafo=grafo;
       this.fileManagement = new FileManagement();
    }

    public void cargarDatosDesdeJson(String pathNodos, String pathAristas){
        String leerAristas=fileManagement.readJsonFile(pathAristas);
        String leerNodos=fileManagement.readJsonFile(pathNodos);

        if (leerAristas == null || leerNodos == null) {
            System.out.println("Error al leer los archivos JSON.");
            return;
        }

        ArrayList<Nodo> nodos=fileManagement.deserealiseObjectCollectionFromJson2(leerNodos, Nodo.class);
        ArrayList<Arista> aristas=fileManagement.deserealiseObjectCollectionFromJson2(leerAristas,Arista.class);

        if (nodos == null || aristas == null) {
            System.out.println("Error al deserializar los datos.");
            return;
        }

        //Agregar nodos al grafo
        for(Nodo nodo: nodos){
            grafo.agregarNodo(nodo);
        }


        for (Arista arista : aristas) {
            grafo.agregarArista(arista);
            
        }


    }
    

}
