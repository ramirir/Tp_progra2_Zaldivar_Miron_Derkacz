package Diccionario;

import clases.Clase_Perfil; // No olvides importar Perfil si está en otro paquete

public interface Idiccionario {

    void insertar(String clave, Clase_Perfil valor);

    void eliminar(String clave);

    Clase_Perfil recuperar(String clave);
}