package arbol;

import java.util.ArrayList;

/**
 * Nodo del Árbol General de Habilidades.
 * Cada nodo representa una categoría o habilidad (ej: "Tecnología", "Java").
 * Puede tener múltiples hijos (árbol n-ario).
 */
public class NodoArbol {

    String habilidad;
    NodoArbol padre;
    ArrayList<NodoArbol> hijos;

    // IDs de profesionales que poseen esta habilidad exacta
    ArrayList<String> profesionales;

    public NodoArbol(String habilidad) {
        this.habilidad = habilidad;
        this.padre = null;
        this.hijos = new ArrayList<>();
        this.profesionales = new ArrayList<>();
    }

    public NodoArbol(String habilidad, NodoArbol padre) {
        this.habilidad = habilidad;
        this.padre = padre;
        this.hijos = new ArrayList<>();
        this.profesionales = new ArrayList<>();
    }
}
