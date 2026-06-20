package Grafo;

import Diccionario.Diccionario;
import clases.Nodo_Diccionario;

public class GrafoLista {
    private Diccionario usuarios;

    public GrafoLista(Diccionario usuarios) {
        this.usuarios = usuarios;
    }

    public void conectar(String email1, String email2) {         //Crea vinculo entre personas
        Nodo_Diccionario n1 = usuarios.obtenerNodo(email1);
        Nodo_Diccionario n2 = usuarios.obtenerNodo(email2);

        if (n1 != null && n2 != null) {
            agregarAdyacente(n1, email2);
            agregarAdyacente(n2, email1);
        }
    }

    private void agregarAdyacente(Nodo_Diccionario origen, String idDestino) {
        Nodo_Adyacente nuevo = new Nodo_Adyacente(idDestino);
        if (origen.getAdyacentes() == null) {
            origen.setAdyacentes(nuevo);
        } else {
            Nodo_Adyacente actual = origen.getAdyacentes();
            while (actual.getSiguiente() != null) {
                if (actual.getIdDestino().equals(idDestino)) return;    //Evita duplicados
                actual = actual.getSiguiente();
            }
            if (!actual.getIdDestino().equals(idDestino)) {
                actual.setSiguiente(nuevo);
            }
        }
    }

    public int calcularGradoSeparacion(String origen, String destino) {
        if (origen.equals(destino)) return 0;
        usuarios.limpiarVisitados();

        Nodo_Diccionario nodoOrigen = usuarios.obtenerNodo(origen);
        if (nodoOrigen == null) return -1;

        ColaPropia cola = new ColaPropia(); // <--- Usando tu nombre
        nodoOrigen.setVisitado(true);
        cola.encolar(nodoOrigen, 0);

        while (!cola.estaVacia()) {
            NodoCola actual = cola.desencolar(); // <--- Usando tu nombre

            if (actual.nodo.getClave().equals(destino)) {
                return actual.distancia;
            }

            Nodo_Adyacente ady = actual.nodo.getAdyacentes();
            while (ady != null) {
                Nodo_Diccionario vecino = usuarios.obtenerNodo(ady.getIdDestino());
                if (vecino != null && !vecino.isVisitado()) {
                    vecino.setVisitado(true);
                    cola.encolar(vecino, actual.distancia + 1);
                }
                ady = ady.getSiguiente();
            }
        }
        return -1;
    }

    public void sugerirContactos(String email) {
        Nodo_Diccionario origen = usuarios.obtenerNodo(email);
        if (origen == null) return;

        usuarios.limpiarVisitados();
        origen.setVisitado(true);

        Nodo_Adyacente ady = origen.getAdyacentes();
        while (ady != null) {
            Nodo_Diccionario amigoDirecto = usuarios.obtenerNodo(ady.getIdDestino());
            if (amigoDirecto != null) amigoDirecto.setVisitado(true);
            ady = ady.getSiguiente();
        }

        System.out.println("Sugerencias para " + origen.getValor().getNombre() + ":");
        boolean haySugerencias = false;

        ady = origen.getAdyacentes();
        while (ady != null) {
            Nodo_Diccionario amigoDirecto = usuarios.obtenerNodo(ady.getIdDestino());
            if (amigoDirecto != null) {
                Nodo_Adyacente ady2 = amigoDirecto.getAdyacentes();
                while (ady2 != null) {
                    Nodo_Diccionario sugerencia = usuarios.obtenerNodo(ady2.getIdDestino());
                    if (sugerencia != null && !sugerencia.isVisitado()) {
                        System.out.println(" -> " + sugerencia.getValor().getNombre());
                        sugerencia.setVisitado(true);
                        haySugerencias = true;
                    }
                    ady2 = ady2.getSiguiente();
                }
            }
            ady = ady.getSiguiente();
        }
        if (!haySugerencias) System.out.println(" No hay sugerencias por ahora.");
    }

    public void mostrarContactos(String email) {

        Nodo_Diccionario usuario = usuarios.obtenerNodo(email);

        if (usuario == null) {
            return;
        }

        Nodo_Adyacente ady = usuario.getAdyacentes();

        System.out.println("\nMIS CONTACTOS:");

        while (ady != null) {

            Nodo_Diccionario contacto =
                    usuarios.obtenerNodo(ady.getIdDestino());

            if (contacto != null) {
                System.out.println("- " +
                        contacto.getValor().getNombre());
            }

            ady = ady.getSiguiente();
        }
    }

    private class NodoCola {
        Nodo_Diccionario nodo;
        int distancia;
        NodoCola siguiente;
        public NodoCola(Nodo_Diccionario n, int d) { this.nodo = n; this.distancia = d; }
    }

    private class ColaPropia {
        NodoCola frente, fin;
        public void encolar(Nodo_Diccionario n, int dist) {
            NodoCola nuevo = new NodoCola(n, dist);
            if (frente == null) frente = fin = nuevo;
            else { fin.siguiente = nuevo; fin = nuevo; }
        }
        public NodoCola desencolar() {
            if (frente == null) return null;
            NodoCola aux = frente;
            frente = frente.siguiente;
            if (frente == null) fin = null;
            return aux;
        }
        public boolean estaVacia() { return frente == null; }
    }
}