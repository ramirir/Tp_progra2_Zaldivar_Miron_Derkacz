package grafo2;

public interface Igrafo <T> {
    void agregarUsuario(T email);
    void eliminarUsuario(T email);
    void crearConexion(T email1, T email2);
    void eliminarConexion(T email1, T email2);
    boolean existeUsuario(T email);
    boolean sonContactos(T email1, T email2);
    void mostrarRed();
    void recorridoDFS(T inicio);
    void recorridoBFS(T inicio);
    int calcularGradoSeparacion(T origen, T destino);
    void sugerirContactos(T usuario);
}