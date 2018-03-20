<?php
/**
 * Librería encargada de trabajar con ficheros
 *
 * @author Inazio
 */
class Ficheros {

    /**
     * Carga en un array los ficheros de un directorio pasado por parámetro.
     * @param string $ruta directorio de la carpeta donde extraer los ficheros
     * @return array con la ruta de los ficheros o NULL si directorio inválido
     */
    public function cargarFicheros($ruta){

        // Compruebo si el parámetro pasado es una ruta
        if (is_dir($ruta)){

            // Abro un gestor de directorios
            $gestor = opendir($ruta);

            $ficheros = array();

            // Recorro archivos del directorio
            while(($archivo = readdir($gestor)) !== false){
                if (is_file($ruta . "/" . $archivo)){
                    $ficheros = $ruta . "/" . $archivo;
                    array_push($ficheros, $imagen);
                }
            }

            closedir($gestor);

            return $ficheros;
        }
        else{
            return NULL;
        }
    }
}
