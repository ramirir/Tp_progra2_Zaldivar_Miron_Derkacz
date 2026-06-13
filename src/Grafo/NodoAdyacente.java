package grafo2;

public class NodoAdyacente<T> {

    T dato;
    NodoAdyacente<T> siguiente;

    public NodoAdyacente(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}