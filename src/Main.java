import java.util.Scanner;
import Diccionario.Diccionario;
import clases.Clase_Perfil;
import Cola.Cola;
import clases.Postulacion;
import arbol.ArbolHabilidades;
import arbol.Lista;

public class Main {
    public static void main(String[] args) {
        Diccionario plataforma = new Diccionario();
        Cola colaJava = new Cola();
        Cola colaSistemas = new Cola();
        Cola colaLinux = new Cola();
        ArbolHabilidades arbolHabilidades = inicializarArbolHabilidades();
        Scanner teclado = new Scanner(System.in);

        //Usuarios ya creados

        Clase_Perfil user1 = new Clase_Perfil("rami@mail.com", "Ramiro", "Desarrollador Java");
        Clase_Perfil user2 = new Clase_Perfil("nacho@mail.com", "Ignacio", "Analista de Sistemas");
        Clase_Perfil user3 = new Clase_Perfil("facu@mail.com", "Facundo", "Administrador Linux");

        plataforma.insertar(user1.getId(), user1);
        plataforma.insertar(user2.getId(), user2);
        plataforma.insertar(user3.getId(), user3);

        arbolHabilidades.asociarProfesional("Java", user1.getId());
        arbolHabilidades.asociarProfesional("Analisis de Sistemas", user2.getId());
        arbolHabilidades.asociarProfesional("Linux", user3.getId());

        //Usuarios ya encolados


        colaJava.encolar(new Postulacion(user1.getId(), "Desarrollador de Java Backend"));
        colaSistemas.encolar(new Postulacion(user2.getId(), "Analista de Sistemas / Funcional"));



        int opcionPrincipal;

        do {
            System.out.println("\n-----------------------------------------------");
            System.out.println("          PLATAFORMA LABORAL - MENÚ PRINCIPAL    ");
            System.out.println("-------------------------------------------------");
            System.out.println("1- Registro/Inicio de sesion");
            System.out.println("2- Bajas");
            System.out.println("3- Consultas y Reportes del Sistema");
            System.out.println("4- Salir");
            System.out.println("-------------------------------------------------");
            System.out.print("Elija una opción del menú principal: ");
            opcionPrincipal = teclado.nextInt();
            teclado.nextLine();

            if (opcionPrincipal == 1) {
                int opcionAltas;
                System.out.println("\n SUBMENU -USUARIOS");
                System.out.println(" 1- Registrar usuario");
                System.out.println(" 2- Iniciar sesion de usuario");
                System.out.print("Seleccione una opcion (1 o 2): ");
                opcionAltas = teclado.nextInt();
                teclado.nextLine();

                if (opcionAltas == 1) {         // Se piden Datos de registro al usuario
                    System.out.println("Ingrese Email (ID único):");
                    String id = teclado.nextLine();
                    System.out.println("Ingrese Nombre completo:");
                    String nombre = teclado.nextLine();
                    System.out.println("Ingrese Profesion actual:");
                    String prof = teclado.nextLine();

                    Clase_Perfil nuevo = new Clase_Perfil(id, nombre, prof);
                    plataforma.insertar(id, nuevo);             // Ingresamos los datos al diccionario
                    System.out.println("-> ¡Usuario registrado con éxito!");

                } else if (opcionAltas == 2) {        // Se piden datos de ingreso al usuario
                    System.out.println("Ingrese su Email para ingresar:");
                    String loginId = teclado.nextLine();

                    Clase_Perfil UsuarioLogueado = plataforma.recuperar(loginId);

                    if (UsuarioLogueado != null) {
                        System.out.println("-> ¡Bienvenido " + UsuarioLogueado.getNombre() + "!");   // Usuario ingresa correctamente
                        menuUsuario(UsuarioLogueado, colaJava, colaSistemas, colaLinux, arbolHabilidades, teclado);  // Llamamos a la funcion del menu usuario
                    } else {
                        System.out.println("-> Error: El email no coincide con ningún usuario registrado.");
                    }
                }
            } else if (opcionPrincipal == 2) {      // Llamamos a la funcion de bajas
                menuBajas(plataforma, teclado);
            } else if (opcionPrincipal == 3) {
                menuConsultas(plataforma, colaJava, colaSistemas, colaLinux, arbolHabilidades, teclado);
            }
        } while (opcionPrincipal != 4);

        teclado.close();
    }

    //-------------------------
    //    Menu de usuario
    //-------------------------

    public static void menuUsuario(Clase_Perfil usuario, Cola colaJava, Cola colaSistemas, Cola colaLinux, ArbolHabilidades arbolHabilidades, Scanner teclado) {
        int opcionUsuario;
        do {
            System.out.println("\n    -------------------------------------------------");
            System.out.println("    PANEL DE USUARIO: " + usuario.getNombre().toUpperCase());
            System.out.println("    -------------------------------------------------");
            System.out.println("    1- Ver mis Datos del Perfil");
            System.out.println("    2- Modificar Carrera / Profesión");
            System.out.println("    3- Postularse a una Oferta de Empleo");
            System.out.println("    4- Deshacer última modificación");
            System.out.println("    5- Asociar una habilidad a mi perfil");
            System.out.println("    6- Cerrar Sesion");
            System.out.print("    Elija una opción de su cuenta: ");
            opcionUsuario = teclado.nextInt();
            teclado.nextLine();

            if (opcionUsuario == 1) {
                System.out.println("\n    [ Tus Datos Actuales ]");
                System.out.println("    Email/ID: " + usuario.getId());
                System.out.println("    Nombre:   " + usuario.getNombre());
                System.out.println("    Carrera:  " + usuario.getProfesion());

            } else if (opcionUsuario == 2) {
                System.out.println("    Carrera actual: " + usuario.getProfesion());
                System.out.print("    Ingrese su NUEVA carrera: ");
                String nuevaProf = teclado.nextLine();

                usuario.actualizarProfesion(nuevaProf);
                System.out.println("    Carrera actualizada. Cambio guardado en tu Pila.");

            } else if (opcionUsuario == 3) {
                System.out.println("\n    [ OFERTAS DE TRABAJO DISPONIBLES ]");
                System.out.println("    1- Desarrollador de Java Backend");
                System.out.println("    2- Analista de Sistemas / Funcional");
                System.out.println("    3- Administrador de Servidores Linux");
                System.out.print("    Seleccione el puesto al que desea aplicar (1-3): ");

                int puestoElegido = teclado.nextInt();
                teclado.nextLine();

                String nombrePuesto = "";
                if (puestoElegido == 1) {
                    Postulacion nuevaSolicitud = new Postulacion(usuario.getId(), nombrePuesto);
                    colaJava.encolar(nuevaSolicitud);
                    System.out.println(" ¡Postulación exitosa a la oferta de Desarrollador de Java.");
                } else if (puestoElegido == 2) {
                    Postulacion nuevaSolicitud = new Postulacion(usuario.getId(), nombrePuesto);
                    colaSistemas.encolar(nuevaSolicitud);
                    System.out.println(" Postulación exitosa a la oferta de Analista de Sistemas.");
                } else if (puestoElegido == 3) {
                    Postulacion nuevaSolicitud = new Postulacion(usuario.getId(), nombrePuesto);
                    colaLinux.encolar(nuevaSolicitud);
                    System.out.println(" ¡Postulación exitosa a la oferta de Servidores de Linux.");
                } else {
                    System.out.println(" Opción de puesto inválida.");
                }


            } else if (opcionUsuario == 4) {
                System.out.println("    Carrera actual: " + usuario.getProfesion());
                if (usuario.deshacerUltimoCambio()) {
                    System.out.println("  ¡Cambio deshecho!.");
                    System.out.println("    Carrera actual: " + usuario.getProfesion());
                } else {
                    System.out.println("  No hay modificaciones guardadas en el historial.");
                }

            } else if (opcionUsuario == 5) {
                asociarHabilidadAUsuario(usuario, arbolHabilidades, teclado);

            } else if (opcionUsuario == 6) {
                System.out.println("    Cerrando sesión de " + usuario.getNombre() + "...");
            } else {
                System.out.println("    Opción inválida dentro del usuario.");
            }

        } while (opcionUsuario != 6); //  Cerrar Sesion
    }
    // -----------------------------------------
    //              Menu de bajas
    // -----------------------------------------

    public static void menuBajas(Diccionario plataforma, Scanner teclado) {
        System.out.println("\n  [ SUBMENÚ 2 - BAJAS DEL SISTEMA ]");
        System.out.print("  Ingrese el Email del usuario que desea eliminar: ");
        String bajaId = teclado.nextLine();

        Clase_Perfil pBaja = plataforma.recuperar(bajaId);

        if (pBaja != null) {
            System.out.println("\n Usuario encontrado!");
            System.out.println("  Nombre: " + pBaja.getNombre());
            System.out.println("  Carrera: " + pBaja.getProfesion());
            System.out.print("\n  ¿Está seguro de que desea eliminar este perfil permanentemente? (S/N): ");
            String confirmacion = teclado.nextLine();

            if (confirmacion.equalsIgnoreCase("S")) {
                plataforma.eliminar(bajaId); // Desenlaza el nodo
                System.out.println("  Usuario \"" + pBaja.getNombre() + "\" dado de baja correctamente!");
            } else {
                System.out.println(" Operación cancelada. El usuario no fue eliminado.");
            }
        } else {
            System.out.println("  Error: No se encontró ningún usuario con el email: " + bajaId);
        }
    }

    //Menu de consultas


    public static void menuConsultas(Diccionario plataforma, Cola colaJava, Cola colaSistemas, Cola colaLinux, ArbolHabilidades arbolHabilidades, Scanner teclado) {
        int opcionConsultas;

        System.out.println("\n  [ SUBMENÚ 3 - CONSULTAS Y REPORTES ]");
        System.out.println("  1- Buscar Perfil ");
        System.out.println("  2- Ver postulantes en la oferta de trabajo (Orden de llegada)");
        System.out.println("  3- Buscar profesionales por habilidad");
        System.out.print("  Seleccione una opcion (1 a 3): ");
        opcionConsultas = teclado.nextInt();
        teclado.nextLine(); // Limpiar el buffer

        if (opcionConsultas == 1) {
            System.out.print("\n  Ingrese el Email del usuario a consultar: ");
            String buscarId = teclado.nextLine();

            Clase_Perfil enc = plataforma.recuperar(buscarId);

            if (enc != null) {
                System.out.println("\n  [ Datos del Perfil Encontrado ]");
                System.out.println("  ID/Email: " + enc.getId());
                System.out.println("  Nombre:   " + enc.getNombre());
                System.out.println("  Carrera:  " + enc.getProfesion());
            } else {
                System.out.println(" El perfil no existe en el sistema.");
            }

        } else if (opcionConsultas == 2) {
            System.out.println("\n  [ Seleccione qué cola de puesto desea procesar ]");
            System.out.println("  1- Cola Desarrollador Java Backend");
            System.out.println("  2- Cola Analista de Sistemas / Funcional");
            System.out.println("  3- Cola Administrador de Servidores Linux");
            System.out.print("  Elija una opción (1-3): ");
            int seleccion = teclado.nextInt();
            teclado.nextLine();

            Cola colaElegida = null;
            if (seleccion == 1) colaElegida = colaJava;
            else if (seleccion == 2) colaElegida = colaSistemas;
            else if (seleccion == 3) colaElegida = colaLinux;

            if (colaElegida == null) {
                System.out.println(" Opcion de cola invalida.");
                return;
            }

            if (!colaElegida.esta_vacia()) {

                //  Capturamos la postulación del frente
                Postulacion solicitudActual = colaElegida.ver_primero();


                String emailPostulante = solicitudActual.getEmailUsuario();
                String puestoPostulado = solicitudActual.getPuestoTrabajo();

                //  Buscamos a la persona real en el Diccionario
                Clase_Perfil pPrimero = plataforma.recuperar(emailPostulante);


                if (pPrimero != null) {
                    System.out.println("\n  ---Evaluando Siguiente Solicitud por Orden de Llegada---");
                    System.out.println("  PUESTO APLICADO:         " + puestoPostulado.toUpperCase());
                    System.out.println("  ------------------------------------------------------------");
                    System.out.println("  Postulante a entrevista: " + pPrimero.getNombre());
                    System.out.println("  Especialidad/Carrera:    " + pPrimero.getProfesion());
                    System.out.println("  Email de contacto:       " + emailPostulante);
                } else {

                    // Si el perfil dio null es porque el usuario fue eliminado del diccionario
                    System.out.println("\n  -Alerta-");
                    System.out.println("  La postulación para el puesto " + puestoPostulado + "' pertenece al email (" + emailPostulante + "), pero el usuario ya no existe en el sistema (fue dado de baja).");
                }

                // desencolamos para que la fila no se quede trabada para siempre
                colaElegida.desencolar();
                System.out.println("\n Solicitud removida de la Cola de espera con éxito.");

            } else {
                System.out.println(" No hay solicitudes pendientes en la cola de postulaciones.");
            }
        } else if (opcionConsultas == 3) {
            buscarProfesionalesPorHabilidad(plataforma, arbolHabilidades, teclado);
        } else {
            System.out.println(" Opcion invalida.");
        }
    }

    public static ArbolHabilidades inicializarArbolHabilidades() {
        ArbolHabilidades arbolHabilidades = new ArbolHabilidades();

        arbolHabilidades.agregarRaiz("Habilidades");
        arbolHabilidades.agregarHabilidad("Habilidades", "Programacion");
        arbolHabilidades.agregarHabilidad("Habilidades", "Sistemas");
        arbolHabilidades.agregarHabilidad("Habilidades", "Infraestructura");
        arbolHabilidades.agregarHabilidad("Programacion", "Java");
        arbolHabilidades.agregarHabilidad("Programacion", "Backend");
        arbolHabilidades.agregarHabilidad("Sistemas", "Analisis de Sistemas");
        arbolHabilidades.agregarHabilidad("Sistemas", "Analisis Funcional");
        arbolHabilidades.agregarHabilidad("Infraestructura", "Linux");
        arbolHabilidades.agregarHabilidad("Infraestructura", "Servidores");

        return arbolHabilidades;
    }

    public static void asociarHabilidadAUsuario(Clase_Perfil usuario, ArbolHabilidades arbolHabilidades, Scanner teclado) {
        System.out.println("\n    [ ARBOL DE HABILIDADES DISPONIBLES ]");
        arbolHabilidades.mostrarPreOrden();
        System.out.print("    Ingrese la habilidad que desea asociar a su perfil: ");
        String habilidad = teclado.nextLine();

        if (arbolHabilidades.existeHabilidad(habilidad)) {
            arbolHabilidades.asociarProfesional(habilidad, usuario.getId());
        } else {
            System.out.println("    La habilidad ingresada no existe en el arbol.");
        }
    }

    public static void buscarProfesionalesPorHabilidad(Diccionario plataforma, ArbolHabilidades arbolHabilidades, Scanner teclado) {
        System.out.print("\n  Ingrese la habilidad a consultar: ");
        String habilidad = teclado.nextLine();

        Lista<String> profesionales = arbolHabilidades.buscarProfesionalesPorHabilidad(habilidad);

        if (!profesionales.isEmpty()) {
            System.out.println("\n  [ Profesionales encontrados ]");
            for (int i = 0; i < profesionales.size(); i++) {
                String idProfesional = profesionales.get(i);
                Clase_Perfil perfil = plataforma.recuperar(idProfesional);

                if (perfil != null) {
                    System.out.println("  - " + perfil.getNombre() + " | " + perfil.getProfesion() + " | " + perfil.getId());
                } else {
                    System.out.println("  - " + idProfesional + " (perfil dado de baja)");
                }
            }
        }
    }

}

