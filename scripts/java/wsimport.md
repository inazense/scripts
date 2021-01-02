# Importar un WSDL Java por línea de comandos

Para nuestro supuesto, vamos a decir que el WSDL va a ser importado en un paquete de un proyecto Java ya existente.
Para ello tenemos que tener claros tres nombres, directorio donde descargar el WSDL, nombre del paquete y URL del WSDL a descargar. Ejemplo

__RUTA_SRC__ = C
<br>__WSDL_URL__ = http://www.webservicex.com/globalweather.asmx?wsdl
<br>__PACKAGE__ = com.inazense.globalweather

1. Inicio -> ejecutar -> cmd
2. Cambiar al directorio de wsimport si no está registrado como variable de entorno <br><code>cd C:\Program Files\Java\jdkX.X.X\bin</code>
3. Para importar usaremos el siguiente comando (recuerda reemplazar los valores en mayúsculas por los tuyos propios: <br><code>wsimport.exe -d "RUTA_SRC" -s "RUTA_SRC" -p "PACKAGE" -keep WSDL_URL</code>
