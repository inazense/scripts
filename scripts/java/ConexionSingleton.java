import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* Clase usada para generar un objeto Connection a base de datos MySQL siguiendo el patrón Singleton
*/
public class ConexionSingleton {
	
	// Propiedades
	private static Connection CONEXION = null;
	
	// Constructor
	private ConexionSingleton() {
		String url = "jdbc:mysql://localhost:3306/test";
		String driver = "com.mysql.jdbc.Driver";
		String usuario = "root";
		String password = "";
		
		try {
			Class.forName(driver);
			CONEXION = DriverManager.getConnection(url, usuario, password);
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Métodos
	
	/**
	 * Devuelve la conexión a la base de datos
	 * @return Un objeto Connection único para toda la aplicación
	 */
	public static Connection devolverConexion() {
		if (CONEXION == null) {
			new ConexionSingleton();
		}
		
		return CONEXION;
	}
}