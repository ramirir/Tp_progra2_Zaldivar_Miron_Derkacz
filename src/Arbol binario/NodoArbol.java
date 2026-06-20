package arbol;

public class NodoArbol {

    String habilidad;
    NodoArbol padre;

    public NodoArbol(String habilidad) {
        this.habilidad = habilidad;
        this.padre = null;
    }

    public NodoArbol(String habilidad, NodoArbol padre) {
        this.habilidad = habilidad;
        this.padre = padre;
    }
}
