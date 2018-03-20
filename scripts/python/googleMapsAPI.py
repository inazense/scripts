# -*- coding: utf-8 -*-

import requests
import re # Usado para eliminar las etiquetas HTML de las response

class googleMapsAPI:
    """ Clase para trabajar con la API de Google Maps """

    # Propiedades
    _clave = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" # Escribe aquí to clave (puedes generarlas desde https://console.developers.google.com/apis/credentials)

    # Constructor
    def __init__(self):
        pass

    # Métodos
    def geolocalizar(self, direccion):
        '''
        Devuelve la latitud y longitud de una dirección si es que existe
        :param direccion. String. Dirección a calcular latitud y longitud
        :returns: Diccionario con los siguientes pares clave : valor
            estado      -> String. Status de la request a la API. OK si está todo correcto
            direccion   -> String. Direccion completa que se devuelve. No necesariamente el mismo formato que la dirección pasada por parámetro
            latitud     -> Long. Latitud de la dirección
            longitud    -> Long. longitud de la dirección
        '''
        url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + direccion + "&key=" + self._clave

        respuesta = requests.get(url).json()
        resultado = {}
        resultado["estado"] = respuesta["status"]

        if resultado["estado"] == "OK":
            resultado["direccion"]  = respuesta["results"][0]["formatted_address"]
            resultado["latitud"]    = respuesta["results"][0]["geometry"]["location"]["lat"]
            resultado["longitud"]   = respuesta["results"][0]["geometry"]["location"]["lng"]

        return resultado


    def geolocalizarInversa(self, latitud, longitud):
        '''
        Devuelve la dirección en cadena de texto de una latitud y longitud concreta
        :param latitud. Long. Latitud de la dirección a extrar
        :param longitud. Long. Longitud de la dirección a extraer
        :returns: Diccionario con los siguientes pares clave : valor
            estado      -> String. Status de la request a la API. OK si está todo correcto
            direccion   -> String. Direccion completa que se devuelve. No necesariamente el mismo formato que la dirección pasada por parámetro
            latitud     -> Long. Latitud de la dirección
            longitud    -> Long. longitud de la dirección
        '''
        url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + str(latitud) + "," + str(longitud) + "&key=" + self._clave
        respuesta = requests.get(url).json()
        resultado = {}
        resultado["estado"] = respuesta["status"]

        if resultado["estado"] == "OK":
            resultado["direccion"]  = respuesta["results"][0]["formatted_address"]
            resultado["latitud"]    = respuesta["results"][0]["geometry"]["location"]["lat"]
            resultado["longitud"]   = respuesta["results"][0]["geometry"]["location"]["lng"]

        return resultado


    def calcularRuta(self, origen, destino, modoViaje):
        '''
        Calcula la ruta desde una dirección de origen a una de destino, eligiendo el modo de viaje (drivin, walking, bicycling o transit)
        :param origen. String. Dirección desde la que partir
        :param destino. String. Dirección a la que llegar
        :param modoViaje. String. Como viajar. Opciones:
                                                - driving: Red de carreteras
                                                - walking: caminando
                                                - bicycling: En bicicleta
                                                - transit: transporte público
        :returns: Diccionario con los siguientes pares clave : valor
            estado      -> String. Status de la request a la API. OK si está todo correcto
            origen      -> String. Dirección completa de origen
            destino     -> String. Dirección completa de destino
            distancia   -> String. Distancia total a recorrer (encadenada con la unidad de medida)
            duracion    -> String. Duración total del recorrido (encadenada con la unidad de medida)
            pasos       -> Diccionario. Contiene las indicaciones, paso a paso, para ir de origen a destino
                                        - distancia. String. Distancia a recorrer en ese tramo (encadenada con la unidad de medida)
                                        - duracion. String. Duración de ese tramo (encadenada con la unidad de medida)
                                        - instrucciones. String. Instrucciones a seguir en ese tramo
                                        - modo. Modo de viaje elegido en ese tramo
        '''
        etiquetaHTML = re.compile(r'<[^>]+>')
        url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + origen + "&destination= " + destino + "&mode=" + modoViaje + "&key=" + self._clave
        respuesta = requests.get(url).json()
        resultado = {}
        resultado["estado"] = respuesta["status"]

        if resultado["estado"] == "OK":
            resultado["origen"]     = respuesta["routes"][0]["legs"][0]["end_address"]
            resultado["destino"]    = respuesta["routes"][0]["legs"][0]["start_address"]
            resultado["distancia"]  = respuesta["routes"][0]["legs"][0]["distance"]["text"]
            resultado["duracion"]   = respuesta["routes"][0]["legs"][0]["duration"]["text"]

            counter = 0
            resultado["pasos"] = {}

            for i in respuesta["routes"][0]["legs"][0]["steps"]:
                resultado["pasos"][counter] = {}
                resultado["pasos"][counter]["distancia"]        = i["distance"]["text"]
                resultado["pasos"][counter]["duracion"]         = i["duration"]["text"]
                resultado["pasos"][counter]["instrucciones"]    = self._eliminarEtiquetas(repr(i["html_instructions"]))
                resultado["pasos"][counter]["modo"]             = i["travel_mode"]

                counter += 1

        return resultado


    def _eliminarEtiquetas(self, textoConEtiquetas):
        '''
        Devuelve un texto limpio de todas las etiquetas que contenga
        :param textoConEtiquetas. String. Texto del que eliminar las etiquetas
        :returns String. Texto del parámetro sin etiquetas
        '''
        etiquetaHTML = re.compile(r'<[^>]+>')
        return etiquetaHTML.sub('', textoConEtiquetas)
