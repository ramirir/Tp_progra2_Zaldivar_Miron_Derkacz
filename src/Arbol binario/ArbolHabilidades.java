package arbol;

import java.util.ArrayList;

/**
 * TDA Árbol General de Habilidades.
 *
 * Modela la jerarquía de competencias laborales:
 *   Tecnología → Desarrollo → Java
 *              → Frontend → React
 *   Marketing  → Digital  → SEO
 *
 * Permite buscar profesionales por habilidad o categoría completa (subárbol).
 * Implementado como árbol n-ario (cada nodo puede tener N hijos).
 *
 * TPO — Alternativa A: Ecosistema de Red Social Profesional
 * Programación 2 · 2026
 */
public class ArbolHabilidades {

    private NodoArbol raiz;

    // ─────────────────────────────────────────────
    //  Constructor
    // ─────────────────────────────────────────────

    public ArbolHabilidades() {
        this.raiz = null;
    }

    // ─────────────────────────────────────────────
    //  Operaciones básicas
    // ─────────────────────────────────────────────

    /**
     * Crea la raíz del árbol. Solo se puede llamar una vez.
     * Ejemplo: agregarRaiz("Habilidades")
     */
    public void agregarRaiz(String habilidad) {
        if (raiz != null) {
            System.out.println("El árbol ya tiene raíz: " + raiz.habilidad);
            return;
        }
        raiz = new NodoArbol(habilidad);
        System.out.println("Raíz creada: " + habilidad);
    }

    /**
     * Agrega una sub-habilidad como hijo de un nodo padre existente.
     * Ejemplo: agregarHabilidad("Tecnología", "Java")
     * → busca "Tecnología" y le agrega "Java" como hijo
     */
    public void agregarHabilidad(String nombrePadre, String nuevaHabilidad) {
        NodoArbol padre = buscarNodo(raiz, nombrePadre);

        if (padre == null) {
            System.out.println("No se encontró la habilidad padre: " + nombrePadre);
            return;
        }

        // Verificar que no exista ya esa habilidad en el árbol
        if (buscarNodo(raiz, nuevaHabilidad) != null) {
            System.out.println("La habilidad '" + nuevaHabilidad + "' ya existe en el árbol.");
            return;
        }

        NodoArbol nuevo = new NodoArbol(nuevaHabilidad, padre);
        padre.hijos.add(nuevo);
        System.out.println("Habilidad '" + nuevaHabilidad + "' agregada bajo '" + nombrePadre + "'.");
    }

    /**
     * Asocia un profesional (por ID) a una habilidad específica.
     * Ejemplo: asociarProfesional("Java", "USR001")
     */
    public void asociarProfesional(String habilidad, String idProfesional) {
        NodoArbol nodo = buscarNodo(raiz, habilidad);

        if (nodo == null) {
            System.out.println("Habilidad no encontrada: " + habilidad);
            return;
        }

        if (nodo.profesionales.contains(idProfesional)) {
            System.out.println("El profesional " + idProfesional + " ya está asociado a '" + habilidad + "'.");
            return;
        }

        nodo.profesionales.add(idProfesional);
        System.out.println("Profesional " + idProfesional + " asociado a '" + habilidad + "'.");
    }

    /**
     * Desasocia un profesional de una habilidad específica.
     */
    public void desasociarProfesional(String habilidad, String idProfesional) {
        NodoArbol nodo = buscarNodo(raiz, habilidad);

        if (nodo == null) {
            System.out.println("Habilidad no encontrada: " + habilidad);
            return;
        }

        boolean eliminado = nodo.profesionales.remove(idProfesional);

        if (eliminado) {
            System.out.println("Profesional " + idProfesional + " desasociado de '" + habilidad + "'.");
        } else {
            System.out.println("El profesional " + idProfesional + " no estaba asociado a '" + habilidad + "'.");
        }
    }

    // ─────────────────────────────────────────────
    //  Búsqueda de nodo (uso interno)
    // ─────────────────────────────────────────────

    /**
     * Busca un nodo por nombre de habilidad usando DFS (PreOrden).
     * Retorna el nodo si lo encuentra, null si no existe.
     */
    private NodoArbol buscarNodo(NodoArbol actual, String habilidad) {
        if (actual == null) {
            return null;
        }

        if (actual.habilidad.equalsIgnoreCase(habilidad)) {
            return actual;
        }

        // Buscar recursivamente en cada hijo
        for (int i = 0; i < actual.hijos.size(); i++) {
            NodoArbol resultado = buscarNodo(actual.hijos.get(i), habilidad);
            if (resultado != null) {
                return resultado;
            }
        }

        return null;
    }

    /**
     * Verifica si una habilidad existe en el árbol.
     */
    public boolean existeHabilidad(String habilidad) {
        return buscarNodo(raiz, habilidad) != null;
    }

    // ─────────────────────────────────────────────
    //  Búsqueda de profesionales (funcionalidad TPO)
    // ─────────────────────────────────────────────

    /**
     * Retorna todos los profesionales asociados a una habilidad
     * y a TODAS sus sub-habilidades (recorre el subárbol completo).
     *
     * Ejemplo: buscarProfesionalesPorHabilidad("Tecnología")
     * → devuelve todos los profesionales de Tecnología, Desarrollo, Java, etc.
     */
    public ArrayList<String> buscarProfesionalesPorHabilidad(String habilidad) {
        NodoArbol nodo = buscarNodo(raiz, habilidad);

        if (nodo == null) {
            System.out.println("Habilidad no encontrada: " + habilidad);
            return new ArrayList<>();
        }

        ArrayList<String> resultado = new ArrayList<>();
        recolectarProfesionales(nodo, resultado);

        if (resultado.isEmpty()) {
            System.out.println("No hay profesionales asociados a '" + habilidad + "' ni sus subcategorías.");
        } else {
            System.out.println("Profesionales encontrados bajo '" + habilidad + "': " + resultado);
        }

        return resultado;
    }

    /**
     * Recorre el subárbol (DFS PostOrden) y acumula todos los profesionales.
     */
    private void recolectarProfesionales(NodoArbol nodo, ArrayList<String> acumulador) {
        if (nodo == null) {
            return;
        }

        // Primero los hijos (PostOrden)
        for (int i = 0; i < nodo.hijos.size(); i++) {
            recolectarProfesionales(nodo.hijos.get(i), acumulador);
        }

        // Luego los profesionales del nodo actual
        for (int i = 0; i < nodo.profesionales.size(); i++) {
            String id = nodo.profesionales.get(i);
            if (!acumulador.contains(id)) {
                acumulador.add(id);
            }
        }
    }

    // ─────────────────────────────────────────────
    //  Recorridos (DFS)
    // ─────────────────────────────────────────────

    /**
     * PreOrden: Raíz → Hijos (izquierda a derecha)
     * Muestra primero la categoría y luego sus subcategorías.
     */
    public void mostrarPreOrden() {
        System.out.print("PreOrden: ");
        preOrdenRecursivo(raiz, 0);
        System.out.println();
    }

    private void preOrdenRecursivo(NodoArbol nodo, int nivel) {
        if (nodo == null) {
            return;
        }

        // Indentación para visualizar niveles
        for (int i = 0; i < nivel; i++) {
            System.out.print("  ");
        }
        System.out.println(nodo.habilidad);

        for (int i = 0; i < nodo.hijos.size(); i++) {
            preOrdenRecursivo(nodo.hijos.get(i), nivel + 1);
        }
    }

    /**
     * PostOrden: Hijos → Raíz
     * Útil para liberar recursos o procesar desde las hojas hacia arriba.
     */
    public void mostrarPostOrden() {
        System.out.print("PostOrden: ");
        postOrdenRecursivo(raiz);
        System.out.println();
    }

    private void postOrdenRecursivo(NodoArbol nodo) {
        if (nodo == null) {
            return;
        }

        for (int i = 0; i < nodo.hijos.size(); i++) {
            postOrdenRecursivo(nodo.hijos.get(i));
        }

        System.out.print(nodo.habilidad + " ");
    }

    /**
     * BFS (por niveles): recorre habilidad por habilidad, nivel a nivel.
     * Usa una Cola interna (ArrayList como FIFO).
     */
    public void mostrarPorNiveles() {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
            return;
        }

        System.out.println("Recorrido por niveles (BFS):");

        ArrayList<NodoArbol> cola = new ArrayList<>();
        cola.add(raiz);

        int nivel = 0;

        while (!cola.isEmpty()) {
            int cantNivel = cola.size();
            System.out.print("  Nivel " + nivel + ": ");

            for (int i = 0; i < cantNivel; i++) {
                NodoArbol actual = cola.remove(0);
                System.out.print(actual.habilidad + " ");

                for (int j = 0; j < actual.hijos.size(); j++) {
                    cola.add(actual.hijos.get(j));
                }
            }

            System.out.println();
            nivel++;
        }
    }

    // ─────────────────────────────────────────────
    //  Información del árbol
    // ─────────────────────────────────────────────

    /**
     * Retorna la cantidad total de nodos (habilidades) en el árbol.
     */
    public int contarNodos() {
        return contarRecursivo(raiz);
    }

    private int contarRecursivo(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }

        int total = 1;
        for (int i = 0; i < nodo.hijos.size(); i++) {
            total += contarRecursivo(nodo.hijos.get(i));
        }
        return total;
    }

    /**
     * Retorna la altura del árbol (número máximo de niveles).
     */
    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }

        if (nodo.hijos.isEmpty()) {
            return 1;
        }

        int maxHijo = 0;
        for (int i = 0; i < nodo.hijos.size(); i++) {
            int h = alturaRecursiva(nodo.hijos.get(i));
            if (h > maxHijo) {
                maxHijo = h;
            }
        }

        return 1 + maxHijo;
    }

    /**
     * Muestra el camino (ruta) desde la raíz hasta una habilidad dada.
     * Ejemplo: "Habilidades > Tecnología > Desarrollo > Java"
     */
    public void mostrarCamino(String habilidad) {
        ArrayList<String> camino = new ArrayList<>();
        boolean encontrado = buscarCamino(raiz, habilidad, camino);

        if (!encontrado) {
            System.out.println("Habilidad no encontrada: " + habilidad);
            return;
        }

        System.out.print("Camino: ");
        for (int i = 0; i < camino.size(); i++) {
            System.out.print(camino.get(i));
            if (i < camino.size() - 1) {
                System.out.print(" > ");
            }
        }
        System.out.println();
    }

    private boolean buscarCamino(NodoArbol actual, String habilidad, ArrayList<String> camino) {
        if (actual == null) {
            return false;
        }

        camino.add(actual.habilidad);

        if (actual.habilidad.equalsIgnoreCase(habilidad)) {
            return true;
        }

        for (int i = 0; i < actual.hijos.size(); i++) {
            if (buscarCamino(actual.hijos.get(i), habilidad, camino)) {
                return true;
            }
        }

        // No estaba en este subárbol: backtrack
        camino.remove(camino.size() - 1);
        return false;
    }

    /**
     * Muestra todas las hojas del árbol (habilidades sin subcategorías).
     */
    public void mostrarHojas() {
        System.out.print("Habilidades hoja: ");
        mostrarHojasRecursivo(raiz);
        System.out.println();
    }

    private void mostrarHojasRecursivo(NodoArbol nodo) {
        if (nodo == null) {
            return;
        }

        if (nodo.hijos.isEmpty()) {
            System.out.print(nodo.habilidad + " ");
            return;
        }

        for (int i = 0; i < nodo.hijos.size(); i++) {
            mostrarHojasRecursivo(nodo.hijos.get(i));
        }
    }

    /**
     * Verifica si el árbol está vacío.
     */
    public boolean esVacio() {
        return raiz == null;
    }
}
