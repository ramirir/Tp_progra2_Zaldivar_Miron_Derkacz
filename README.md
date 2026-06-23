Nombre del proyecto: Lankedon

Integrantes del grupo:
Ramiro Zaldivar
Facundo Miron
Ignacio Derkacz

Alternativa elegida: ecosistema de Red Social Profesional

Estructuras de datos utilizadas. 
Diccionario: Implementado mediante una lista enlazada ordenada por clave para la identificación inmediata y almacenamiento de los perfiles de usuario.
Pila : Utilizada en cada perfil de usuario para gestionar el historial de cambios, permitiendo deshacer la última actualización de la profesión
Cola : Implementada para la gestión de postulaciones a ofertas de empleo, procesando las solicitudes estrictamente por orden de llegada .
Grafo : Implementado mediante vértices y nodos adyacentes para modelar la red de contactos entre los profesionales.
arbol binario: utilizado para representar y organizar correctamente la taxonomia de habilidades laborales.

Funcionalidades implementadas en esta segunda etapa. 
Registrar usuario, iniciar sesion, postularse a una Oferta de Empleo, deshacer última modificación,identificación de perfiles, 
gestión de usuarios, panel de autogestión, historial de cambios, gestión de postulaciones, procesamiento de candidatos, 
sugerencia de contactos conectar con alguien y ver recomendaciones

Link del repositorio. 
https://github.com/ramirir/Tp_progra2_Zaldivar_Miron_Derkacz

Actividades realizadas por cada integrantes
Ramiro Zaldivar:Grafo
Facundo Miron:cola pila diccionario
Ignacio Derkacz: Arbol binario


PASOS PARA LA EJECUCION DEL CODIGO:

1- Registro de Usuario + asociación de habilidad

-Funcionalidades evaluadas: Diccionario = Alta de usuario + Login + ver perfil

Paso 1- En el menú principal seleccionar la opción 1 (Altas)
Paso 2- En el submenú de Altas seleccionar 1 (Registro de usuario)
Paso 3- Registro de usuario
 Email: facu@mail.com 
 Nombre: Facundo Miron
 Profesión:Tester 
Habilidad: Sistemas

Paso 4- Volver al submenú de altas y seleccionar 2 (Inicio de sesión)
Paso 5- Ingresar los datos de la cuenta
Paso 6- Seleccionar opción 1 -Ver mis datos

Resultado esperado: 
Email: facu@mail.com
Nombre: Facundo Miron
Carrera: Tester


2- Ver contactos + sugerencias + agregar contacto
Funcionalidades evaluadas: Grafo (contactos + recomendaciones + conexión bidireccional)

Paso 1- Ingresar al sistema con usuario Juan Perez  (juan@mail.com
Paso 2- Seleccionar opción 5 (Ver mis contactos)

Paso 3- Seleccionar opción 6 (Ver contactos recomendados)

Sistema muestra sugerencias:

Lucas Diaz

Paso 4- Seleccionar opción S (Agregar contacto)
Paso 5- Elegir opción 1 (Lucas Diaz)

Resultado esperado:

Se muestra contacto recomendado
Se agrega contacto correctamente


4- Postulación a empleo (Cola FIFO)

Funcionalidades evaluadas: Inserción en cola de postulaciones

Paso 1- Ingresar al sistema con usuario Juan Perez
Paso 2- Seleccionar opción 3 (Postularse a una oferta de empleo)
Paso 3- Seleccionar opción 1 (Desarrollador Java Backend)

Resultado esperado:
Postulación exitosa. Usuario agregado a la cola Java Backend

Verificación: 
Paso 1- Salir de la cuenta de usuario
Paso 1- Ingreso a la opción 3- de Consultas y Reportes del sistema
Paso 2- Seleccionar opción 2 --> Procesar postulante de la cola

Resultado esperado:
Postulante a entrevista: Juan Perez
Email de contacto: juan@mail.com



5- Modificar profesión + deshacer (Pila)
Funcionalidades evaluadas: (historial de cambios)

Paso 1- Ingresar al sistema con usuario Juan Perez
Paso 2- Seleccionar opción 1  -- Ver perfil
Paso 3- Seleccionar opción 2 (Modificar carrera / profesión)
Paso 4- Ingresar nueva profesión: Arquitecto de Software
Paso 5- Seleccionar opción 4 (Deshacer última modificación)

Verificación: 
Paso 1- Desde perfil Juan seleccionar opción 1-- Ver perfil

Resultado esperado:  
Valor inicial del perfil




6- Calcular Grado de Separación

Grafo: cálculo de grado de separación entre usuarios mediante recorrido BFS.

Pasos

Paso 1: Ingresar al sistema con el usuario Juan Pérez (juan@mail.com).

Paso 2: Seleccionar opción 7 – Calcular grado de separación.

Paso 3: Ingresar email de destino: lucas@mail.com.

Resultado esperado

El sistema debe encontrar el camino más corto entre ambos usuarios y mostrar:

Grado de separación: 2 saltos.

Como  existe la conexión:

Juan Perez - Ana Gomez - Lucas Diaz


7- Buscar Profesionales por Habilidad
Funcionalidad evaluada : Árbol de habilidades: búsqueda de profesionales asociados a una habilidad.

Pasos

Paso 1: Volver al menú principal.

Paso 2: Seleccionar opción 3 – Consultas y Reportes.

Paso 3: Seleccionar opción 3 – Buscar profesionales por habilidad.

Paso 4: Ingresar la habilidad Java.

Resultado esperado

El sistema debe mostrar todos los profesionales asociados a la habilidad Java.

Ejemplo:

Juan Perez - Desarrollador Java


8-Buscar Perfil

Funcionalidad evaluada: Diccionario: búsqueda inmediata de perfiles por clave (email).

Pasos

Paso 1: Desde el menú principal ingresar a Consultas y Reportes.

Paso 2: Seleccionar opción 1 – Buscar Perfil.

Paso 3: Ingresar email: ana@mail.com.

Resultado esperado

El sistema debe recuperar y mostrar la información almacenada del perfil.

Email: ana@mail.com
Nombre: Ana Gomez
Carrera: Analista de Sistemas

9 – Inicio de Sesión con Usuario Inexistente

Funcionalidad evaluada:
Validación de acceso al sistema mediante Diccionario.

Pasos

Paso 1: Ingresar al menú principal.

Paso 2: Seleccionar opción 1 - Altas.

Paso 3: Seleccionar opción 2 - Iniciar sesión.

Paso 4: Ingresar email inexistente@mail.com.

Resultado esperado

El sistema debe impedir el acceso y mostrar:

Error: El email no coincide con ningún usuario registrado.

11– Deshacer Sin Cambios Previos
Funcionalidad evaluada

Control de pila vacía.

Pasos

Paso 1: Iniciar sesión con un usuario.

Paso 2: Sin modificar la profesión.

Paso 3: Seleccionar opción 4 - Deshacer última modificación.

Resultado esperado

El sistema debe informar que no existen cambios guardados.


