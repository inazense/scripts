import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Clase usada para leer archivos properteis usando un 
 * patrón Singleton
 * @author Inazio
 *
 */
public class ManejadorProperties {

	// Propiedades
	private String rutaFichero = "fichero.properties";
	private static Properties propiedades = null;
	
	// Constructor
	private ManejadorProperties() throws FileNotFoundException, IOException {
		propiedades = new Properties();
		propiedades.load(new FileReader(rutaFichero));
	}
	
	// Métodos
	
	/**
	 * Lee una propiedad
	 * @param miClave
	 * @return Valor de la propiedad
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String leerPropiedad(String miClave) throws FileNotFoundException, IOException {
		
		if (propiedades == null) {
			new ManejadorProperties();
		}
		
		String resultado = "";
		resultado = propiedades.getProperty(miClave);
		
		return resultado;
	}
	
	/**
	 * Devuelve todas las propiedades del archivo de configuración
	 * @return Mapa de clave valor con las propiedades correspondientes
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Map<String, String> leerTodasLasPropiedades() throws FileNotFoundException, IOException {
		
		if (propiedades == null) {
			new ManejadorProperties();
		}
		
		Map<String, String> listadoPropiedades = new HashMap<String, String>();
		
		Enumeration<Object> claves = propiedades.keys();
		
		while (claves.hasMoreElements()) {
			Object clave = claves.nextElement();
			listadoPropiedades.put(clave.toString(), propiedades.get(clave).toString());
		}
		
		return listadoPropiedades;
	}
}