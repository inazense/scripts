<?php
/**
* Clase para trabajar con la geocodificación de Google Maps API
*
* @author Inazio Claver
*/
class GoogleMapsGeo{
	/**
	* Geocodifica una dirección
	* @param string $direccion
	* @return array con latitud, longitud y dirección, o FALSE si hubo algún error
	*/
	function geocode($direccion){
		// Convierte en URL la dirección
		$direccion = urlencode($direccion);
		// POST a la API de geolocalización de Google Maps
		$url = "http://maps.google.com/maps/api/geocode/json?address={$address}";
		// Recojo la respuesta JSON
		$respuestaJSON = file_get_contents($url);
		// Decodifico el JSON
		$respuesta = json_decode($respuestaJSON, true);
		// El parámetro de Status de la respuesta debe ser OK para permitir el geoposicionamiento
		if ($respuesta['status'] == 'OK'){
			// Capturo los datos (solo los que me interesan)
			$latitud = $respuesta['results'][0]['geometry']['location']['lat'];
			$longitud = $resputa['results'][0]['geometry']['location']['lng'];
			$direccionFormateada = $respuesta['results'][0]['formatted_address'];
			// Verifico si los datos están completos
			if ($latitud && $longitud && $direccionFormateada){
				// Meto los datos en un array
				$geoDatos = array($latitud, $longitud, $direccionFormateada);
				return $geoDatos;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	/**
	* Convierte latitud y longitud a dirección comprensible
	* @param double $lat Latitud
	* @param double $long Longitud
	* @return string con la dirección formateada, o FALSE si hubo algún error
	*/
	function inverseGeocoding($lat, $lng){
		// Convierte a URL las direcciones
		$lat = urlencode($lat);
		$lng = urlencode($lng);
		// POST a la API de geolocalización de Google Maps
		$url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=$lat,$lng";
		// Recojo la respuesta JSON
		$respuestaJSON = file_get_contents($url);
		// Decodifico la respuesta
		$respuesta = json_decode($respuestaJSON, true);
		// El parámetro de status de la respuesta debe ser OK para permitir la recogida de datos
		if ($respuesta['status'] == 'OK'){
				$direccionFormateada = $respuesta['results'][0]['formatted_address'];
				if ($direccionFormateada){
					return $direccionFormateada;
				}
				else{
					return false;
				}
		}
		else{
			return false;
		}
	}
	/**
	* Calcula el tiempo de viaje entre dos direcciones
	* @param string $dirInicio Dirección desde la que se parte
	* @param string $dirFin Dirección a la que se llega
	* @return array con tiempo de viaje en string y timestamp, direccion, latitud y longitud de dirección de inicio y dirección final, o FALSE si hubo algún error
	*/
	function travelTime($dirInicio, $dirFin){
		// Convierte en URL las direcciones
		$dirInicio = urlencode($dirInicio);
		$dirFin = urlencode($dirFin);
		// POST a la API de direcciones de Google Maps
		$url = "https://maps.googleapis.com/maps/api/directions/json?origin=$dirInicio&destination=$dirFin";
		// Recojo la respuesta JSON
		$respuestaJSON = file_get_contents($url);
		// Decodifico el JSON
		$respuesta = json_decode($respuestaJSON, true);
		// El parámetro de Status de la respuesta debe ser OK para permitir la recogide de datos
		if ($respuesta['status'] == 'OK'){
			// Tiempo (lenguaje humano)
			$tiempoViaje = $respuesta['routes'][0]['legs'][0]['duration']['text'];
			// Tiempo en timestamp
 			$tiempoViajeTimestamp = $respuesta['routes'][0]['legs'][0]['duration']['value'];
 			// Posición inicio
 			$direccionInicio = $respuesta['routes'][0]['legs'][0]['start_address'];
 			$latitudInicio = $respuesta['routes'][0]['legs'][0]['start_location']['lat'];
 			$longitudInicio = $respuesta['routes'][0]['legs'][0]['start_location']['lng'];
 			// Posición final
 			$direccionFinal = $respuesta['routes'][0]['legs'][0]['end_address'];
 			$latitudFin = $respuesta['routes'][0]['legs'][0]['end_location']['lat'];
 			$longitudFin = $respuesta['routes'][0]['legs'][0]['end_location']['lng'];
 			// Valido si los campos están completos
 			if ($tiempoViaje && $tiempoViajeTimestamp && $direccionInicio && $latitudInicio && $longitudInicio && $direccionFinal && $latitudFin && $longitudFin){
 					$geoDatos = array($tiempoViaje, $tiempoViajeTimestamp, $direccionInicio, $latitudInicio, $longitudInicio, $direccionFinal, $latitudFin, $longitudFin);
 					return $geoDatos;
 			}
 			else{
 				return false;
 			}
		}
		else{
			return false;
		}
	}
}
?>
