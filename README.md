Estructura de Paquetes
La aplicación sigue el patrón Modelo-Vista-Controlador (MVC) y está organizada en los siguientes paquetes:

1. model (Modelo)
Este paquete contiene las clases que representan la lógica de negocio de la aplicación. Cada clase dentro de este paquete corresponde a una entidad o un concepto del dominio de la aplicación (por ejemplo, usuarios, productos, etc.). Estas clases encapsulan los datos y las reglas de negocio.

Qué almacena: Clases que representan las entidades y la lógica de negocio.
Ejemplo: Usuario.java, Producto.java.
2. view (Vista)
Este paquete contiene las clases relacionadas con la interfaz gráfica de la aplicación (GUI). Aquí se manejan las ventanas, paneles y componentes visuales que interactúan con el usuario. Dependiendo de la tecnología utilizada (Java Swing o JavaFX), las clases en este paquete se encargan de renderizar la interfaz y mostrar la información proveniente del modelo.

Qué almacena: Clases que definen las interfaces gráficas que interactúan con el usuario.
Ejemplo: VentanaPrincipal.java, VentanaUsuario.java.
3. controller (Controlador)
Este paquete contiene las clases que actúan como intermediarios entre el modelo y la vista. Los controladores procesan las interacciones del usuario (por ejemplo, clics en botones) y coordinan las respuestas correspondientes, como actualizar los datos o modificar la interfaz gráfica. También son responsables de llamar a las clases del DAO para gestionar la interacción con la base de datos.

Qué almacena: Clases que gestionan la lógica de control, coordinando la vista y el modelo.
Ejemplo: UsuarioController.java, ProductoController.java.
4. dao (Data Access Object)
Este paquete contiene las clases encargadas de la comunicación con la base de datos. El patrón DAO se utiliza para separar la lógica de persistencia de la lógica de negocio. Aquí se implementan las operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre las entidades del modelo.

Qué almacena: Clases que gestionan la conexión y las operaciones con la base de datos.
Ejemplo: UsuarioDAO.java, ProductoDAO.java.
5. utils (Utilidades)
Este paquete contiene clases auxiliares y utilitarias que no pertenecen directamente a las otras capas pero que son necesarias para el funcionamiento general de la aplicación. Por ejemplo, la clase de conexión a la base de datos, funciones comunes, validaciones, entre otras.

Qué almacena: Clases de apoyo, como la gestión de la conexión a la base de datos y otras utilidades.
Ejemplo: ConexionBD.java.
6. assets (Recursos)
Esta carpeta almacena recursos estáticos como imágenes, íconos, archivos de configuración, y cualquier otro archivo multimedia que la aplicación necesite para funcionar correctamente. Estos recursos pueden ser cargados en la vista para su uso en la interfaz gráfica.

Qué almacena: Archivos multimedia y otros recursos estáticos.
Ejemplo: logo.png, iconoUsuario.png.