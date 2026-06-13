import java.util.Scanner;
import Diccionario.Diccionario;
import clases.Clase_Perfil;
import Cola.Cola;
import clases.Postulacion;

public class Main {
    public static void main(String[] args) {
        Diccionario plataforma = new Diccionario();
        Cola colaJava = new Cola();
        Cola colaSistemas = new Cola();
        Cola colaLinux = new Cola();
        Scanner teclado = new Scanner(System.in);

        //Usuarios ya creados

        Clase_Perfil user1 = new Clase_Perfil("rami@mail.com", "Ramiro", "Desarrollador Java");
        Clase_Perfil user2 = new Clase_Perfil("nacho@mail.com", "Ignacio", "Analista de Sistemas");
        Clase_Perfil user3 = new Clase_Perfil("facu@mail.com", "Facundo", "Administrador Linux");

        plataforma.insertar(user1.getId(), user1);
        plataforma.insertar(user2.getId(), user2);
        plataforma.insertar(user3.getId(), user3);

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
