public class ManejadorExcepciones
{

	// Métodos
	
	/**
	 * Devuelve un string con la excepción formateada
	 * @param e Exception
	 * @return String formateado
	 */
	public static String createErrorMessage(Exception e)
	{
		return e.getMessage() + "\n" + e.getStackTrace();
	}
	
	/**
	 * Devuelve String con mensaje de error personalizado
	 * @param e Exception
 	 * @param header Cabecera de la excepción
	 * @return String con formato "cabecera" + \n + Mensaje + \n + Traza
	 */
	public static String createCustomErrorMessage(Exception e, String header)
	{
		return header + "\n" + createErrorMessage(e);
	}
}
