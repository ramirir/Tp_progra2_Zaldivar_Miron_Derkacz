package grafo2;

public class ColaPropia<T> {

    private NodoCola<T> frente;
    private NodoCola<T> fin;

    public ColaPropia() {
        this.frente = null;
        this.fin = null;
    }

    public void encolar(T dato) {
        NodoCola<T> nuevo = new NodoCola<>(dato);

        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    public T desencolar() {
        if (estaVacia()) {
            return null;
        }

        T dato = frente.dato;
        frente = frente.siguiente;

        if (frente == null) {
            fin = null;
        }

        return dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }
}