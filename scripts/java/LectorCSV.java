package csvReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.opencsv.CSVReader;

/**
 * Clase para leer archivos CSV desde Java.
 * Para la lectura de csv complejos se debe usar la librería OpenCSV.
 * Leer más información en http://www.programandoapasitos.com/2017/04/como-leer-fichero-csv-con-java.html
 * @author Inazio
 *
 */
public class LectorCSV {

	// Propiedades
	private char separador;
	private char comillas;
	
	// Constructor
	
	/**
	 * Inicializa el constructor definiendo el separador de los campos y las comillas usadas
	 * @param separador
	 * @param comillas
	 */
	public LectorCSV(char separador, char comillas) {
		this.separador = separador;
		this.comillas = comillas;
	}
	
	// Métodos
	/**
	 * Lee un CSV que no contiene el mismo caracter que el separador en su texto
	 * y sin comillas que delimiten los campos
	 * @param path Ruta donde está el archivo
	 * @throws IOException 
	 */
	public void leerCSVSimple(String path) throws IOException {
		
		// Abro el .csv en buffer de lectura
		BufferedReader bufferLectura = new BufferedReader(new FileReader(path));
		
		// Leo una línea del archivo
		String linea = bufferLectura.readLine();
		
		while (linea != null) {
			// Separa la línea leída con el separador definido previamente
			String[] campos = linea.split(String.valueOf(this.separador));
			System.out.println(Arrays.toString(campos));
			
			// Vuelvo a leer del fichero
			linea = bufferLectura.readLine();
		}
		
		// CIerro el buffer de lectura
		if (bufferLectura != null) {
			bufferLectura.close();
		}
	}
	
	/**
	 * Lee csv complejo usando la librería OpenCSV
	 * @param path Ruta donde está el archivo
	 * @throws IOException
	 */
	public void leerCSVComplejo(String path) throws IOException {
		
		CSVReader lector = new CSVReader(new FileReader(path), separador, comillas);
		String[] linea = null;
		
		while ((linea = lector.readNext()) != null) {
			System.out.println(Arrays.toString(linea));
		}
		
		if (linea != null) {
			lector.close();
		}
	}
}