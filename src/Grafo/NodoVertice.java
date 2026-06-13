package grafo2;

public class NodoVertice<T> {

    T dato;
    NodoVertice<T> siguiente;
    NodoAdyacente<T> adyacentes;
    boolean visitado;

    public NodoVertice(T dato) {
        this.dato = dato;
        this.siguiente = null;
        this.adyacentes = null;
        this.visitado = false;
    }
}