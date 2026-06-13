package grafo2;

public class GrafoLista<T> implements Igrafo<T> {

    private NodoVertice<T> primero;
    private boolean dirigido;

    public GrafoLista(boolean dirigido) {
        this.primero = null;
        this.dirigido = dirigido;
    }

    @Override
    public void agregarUsuario(T email) {
        if (existeUsuario(email)) {
            return;
        }
        NodoVertice<T> nuevo = new NodoVertice<>(email);
        if (primero == null) {
            primero = nuevo;
        } else {
            NodoVertice<T> aux = primero;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
    }

    @Override
    public boolean existeUsuario(T email) {
        return buscarNodo(email) != null;
    }

    private NodoVertice<T> buscarNodo(T email) {
        NodoVertice<T> aux = primero;
        while (aux != null) {
            if (aux.dato.equals(email)) {
                return aux;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    @Override
    public void crearConexion(T email1, T email2) {
        agregarUsuario(email1);
        agregarUsuario(email2);
        conectar(email1, email2);
        if (!dirigido) {
            conectar(email2, email1);
        }
    }

    private void conectar(T origen, T destino) {
        NodoVertice<T> nodoOrigen = buscarNodo(origen);
        if (nodoOrigen == null || sonContactos(origen, destino)) {
            return;
        }
        NodoAdyacente<T> nuevo = new NodoAdyacente<>(destino);
        if (nodoOrigen.adyacentes == null) {
            nodoOrigen.adyacentes = nuevo;
        } else {
            NodoAdyacente<T> aux = nodoOrigen.adyacentes;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
    }

    @Override
    public boolean sonContactos(T email1, T email2) {
        NodoVertice<T> nodo = buscarNodo(email1);
        if (nodo == null) {
            return false;
        }
        NodoAdyacente<T> aux = nodo.adyacentes;
        while (aux != null) {
            if (aux.dato.equals(email2)) {
                return true;
            }
            aux = aux.siguiente;
        }
        return false;
    }

    @Override
    public void eliminarConexion(T email1, T email2) {
        desconectar(email1, email2);
        if (!dirigido) {
            desconectar(email2, email1);
        }
    }

    private void desconectar(T origen, T destino) {
        NodoVertice<T> nodo = buscarNodo(origen);
        if (nodo == null || nodo.adyacentes == null) {
            return;
        }
        NodoAdyacente<T> actual = nodo.adyacentes;
        NodoAdyacente<T> anterior = null;
        while (actual != null) {
            if (actual.dato.equals(destino)) {
                if (anterior == null) {
                    nodo.adyacentes = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
    }

    @Override
    public void eliminarUsuario(T email) {
        if (primero == null) {
            return;
        }
        NodoVertice<T> actual = primero;
        NodoVertice<T> anterior = null;
        while (actual != null) {
            if (actual.dato.equals(email)) {
                if (anterior == null) {
                    primero = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
                eliminarReferencias(email);
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
    }

    private void eliminarReferencias(T email) {
        NodoVertice<T> aux = primero;
        while (aux != null) {
            desconectar(aux.dato, email);
            aux = aux.siguiente;
        }
    }

    @Override
    public void mostrarRed() {
        NodoVertice<T> aux = primero;
        while (aux != null) {
            System.out.print("Usuario " + aux.dato + " conectado con: ");
            NodoAdyacente<T> ady = aux.adyacentes;
            while (ady != null) {
                System.out.print(ady.dato + " | ");
                ady = ady.siguiente;
            }
            System.out.println();
            aux = aux.siguiente;
        }
    }

    private void limpiarVisitados() {
        NodoVertice<T> aux = primero;
        while (aux != null) {
            aux.visitado = false;
            aux = aux.siguiente;
        }
    }

    @Override
    public void recorridoDFS(T inicio) {
        limpiarVisitados();
        NodoVertice<T> nodoInicio = buscarNodo(inicio);
        if (nodoInicio == null) return;
        System.out.println("DFS:");
        dfsRecursivo(nodoInicio);
        System.out.println();
    }

    private void dfsRecursivo(NodoVertice<T> vertice) {
        vertice.visitado = true;
        System.out.print(vertice.dato + " ");
        NodoAdyacente<T> ady = vertice.adyacentes;
        while (ady != null) {
            NodoVertice<T> vecino = buscarNodo(ady.dato);
            if (vecino != null && !vecino.visitado) {
                dfsRecursivo(vecino);
            }
            ady = ady.siguiente;
        }
    }

    @Override
    public void recorridoBFS(T inicio) {
        limpiarVisitados();
        NodoVertice<T> nodoInicio = buscarNodo(inicio);
        if (nodoInicio == null) return;

        ColaPropia<NodoVertice<T>> cola = new ColaPropia<>();
        nodoInicio.visitado = true;
        cola.encolar(nodoInicio);

        System.out.println("BFS:");
        while (!cola.estaVacia()) {
            NodoVertice<T> actual = cola.desencolar();
            System.out.print(actual.dato + " ");
            NodoAdyacente<T> ady = actual.adyacentes;
            while (ady != null) {
                NodoVertice<T> vecino = buscarNodo(ady.dato);
                if (vecino != null && !vecino.visitado) {
                    vecino.visitado = true;
                    cola.encolar(vecino);
                }
                ady = ady.siguiente;
            }
        }
        System.out.println();
    }

    @Override
    public int calcularGradoSeparacion(T origen, T destino) {
        if (origen.equals(destino)) return 0;
        limpiarVisitados(); 
        NodoVertice<T> n1 = buscarNodo(origen);
        NodoVertice<T> n2 = buscarNodo(destino);
        if (n1 == null || n2 == null) return -1;

        ColaPropia<NodoVertice<T>> c1 = new ColaPropia<>();
        ColaPropia<Integer> c2 = new ColaPropia<>();

        n1.visitado = true;
        c1.encolar(n1);
        c2.encolar(0); 
        int res = -1;

        while (c1.estaVacia() == false) {
            NodoVertice<T> act = c1.desencolar();
            int dist = c2.desencolar();
            if (act.dato.equals(destino)) {
                res = dist;
                break;
            }
            NodoAdyacente<T> ady = act.adyacentes;
            while (ady != null) {
                NodoVertice<T> v = buscarNodo(ady.dato);
                if (v != null && v.visitado == false) {
                    v.visitado = true;
                    c1.encolar(v);
                    c2.encolar(dist + 1);
                }
                ady = ady.siguiente;
            }
        }
        return res;
    }

    @Override
    public void sugerirContactos(T usuario) {
        NodoVertice<T> u = buscarNodo(usuario);
        if (u == null) {
            System.out.println("el usuario no esta en la red");
            return;
        }

        limpiarVisitados();
        u.visitado = true; 

        NodoAdyacente<T> ady = u.adyacentes;
        while (ady != null) {
            NodoVertice<T> aux = buscarNodo(ady.dato);
            if (aux != null) {
                aux.visitado = true;
            }
            ady = ady.siguiente;
        }

        NodoAdyacente<T> ady2 = u.adyacentes;
        boolean hay = false;
        System.out.println("Sugerencias de conexion:");
        
        while (ady2 != null) {
            NodoVertice<T> aux2 = buscarNodo(ady2.dato);
            if (aux2 != null) {
                NodoAdyacente<T> ady3 = aux2.adyacentes;
                while (ady3 != null) {
                    NodoVertice<T> sug = buscarNodo(ady3.dato);
                    if (sug != null && sug.visitado == false) {
                        System.out.println("- " + sug.dato);
                        sug.visitado = true; 
                        hay = true;
                    }
                    ady3 = ady3.siguiente;
                }
            }
            ady2 = ady2.siguiente;
        }

        if (hay == false) {
            System.out.println("no hay sugerencias por ahora");
        }
    }
}