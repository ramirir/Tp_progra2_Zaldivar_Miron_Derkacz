package arbol;

/**
 * Casos de prueba del TDA Árbol de Habilidades.
 * TPO — Alternativa A: Ecosistema de Red Social Profesional
 */
public class MainArbol {

    public static void main(String[] args) {

        ArbolHabilidades arbol = new ArbolHabilidades();

        // ── Construcción de la jerarquía ──────────────────────────────
        System.out.println("=== Construcción del árbol ===");
        arbol.agregarRaiz("Habilidades");

        arbol.agregarHabilidad("Habilidades", "Tecnología");
        arbol.agregarHabilidad("Habilidades", "Marketing");
        arbol.agregarHabilidad("Habilidades", "Diseño");

        arbol.agregarHabilidad("Tecnología", "Desarrollo");
        arbol.agregarHabilidad("Tecnología", "Infraestructura");

        arbol.agregarHabilidad("Desarrollo", "Java");
        arbol.agregarHabilidad("Desarrollo", "Python");
        arbol.agregarHabilidad("Desarrollo", "Frontend");

        arbol.agregarHabilidad("Frontend", "React");
        arbol.agregarHabilidad("Frontend", "Angular");

        arbol.agregarHabilidad("Infraestructura", "Docker");
        arbol.agregarHabilidad("Infraestructura", "AWS");

        arbol.agregarHabilidad("Marketing", "Digital");
        arbol.agregarHabilidad("Digital", "SEO");
        arbol.agregarHabilidad("Digital", "SEM");

        // ── Asociar profesionales ─────────────────────────────────────
        System.out.println("\n=== Asociar profesionales ===");
        arbol.asociarProfesional("Java", "USR001");
        arbol.asociarProfesional("Java", "USR002");
        arbol.asociarProfesional("React", "USR003");
        arbol.asociarProfesional("Python", "USR004");
        arbol.asociarProfesional("AWS", "USR005");
        arbol.asociarProfesional("SEO", "USR006");
        arbol.asociarProfesional("Diseño", "USR007");
        // USR001 también sabe Python
        arbol.asociarProfesional("Python", "USR001");

        // ── Recorridos ────────────────────────────────────────────────
        System.out.println("\n=== Recorridos ===");
        arbol.mostrarPreOrden();
        System.out.println();
        arbol.mostrarPostOrden();
        System.out.println();
        arbol.mostrarPorNiveles();

        // ── Búsqueda por habilidad (funcionalidad TPO) ────────────────
        System.out.println("\n=== Buscar profesionales por habilidad ===");

        // Buscar en "Tecnología" debe traer todos los profes de Java, Python, React, Angular, Docker, AWS
        arbol.buscarProfesionalesPorHabilidad("Tecnología");

        // Buscar en "Desarrollo" trae Java, Python, React, Angular
        arbol.buscarProfesionalesPorHabilidad("Desarrollo");

        // Buscar en "Java" trae solo los de Java
        arbol.buscarProfesionalesPorHabilidad("Java");

        // Habilidad inexistente
        arbol.buscarProfesionalesPorHabilidad("Blockchain");

        // ── Información general ───────────────────────────────────────
        System.out.println("\n=== Información del árbol ===");
        System.out.println("Total de nodos: " + arbol.contarNodos());
        System.out.println("Altura del árbol: " + arbol.altura());

        // ── Mostrar camino ────────────────────────────────────────────
        System.out.println("\n=== Camino hasta la habilidad ===");
        arbol.mostrarCamino("React");
        arbol.mostrarCamino("SEO");
        arbol.mostrarCamino("Blockchain");

        // ── Hojas ─────────────────────────────────────────────────────
        System.out.println("\n=== Habilidades hoja ===");
        arbol.mostrarHojas();

        // ── Desasociar ────────────────────────────────────────────────
        System.out.println("\n=== Desasociar profesional ===");
        arbol.desasociarProfesional("Java", "USR001");
        arbol.buscarProfesionalesPorHabilidad("Java");

        // ── Casos límite ──────────────────────────────────────────────
        System.out.println("\n=== Casos límite ===");
        // Habilidad duplicada
        arbol.agregarHabilidad("Desarrollo", "Java");
        // Padre inexistente
        arbol.agregarHabilidad("Medicina", "Cardiología");
        // Asociar a habilidad inexistente
        arbol.asociarProfesional("Kotlin", "USR001");
    }
}
