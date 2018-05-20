# -*- coding: utf-8 -*-

# Script que controla XAMPP en Ubuntu 16.04
# Parámetros admitidos:
# | start | stop | restart |

import sys
import os

# Ruta y configuración de los parámetros aceptados por LAMPP
rutaLampp = "/opt/lampp/lampp"
listaParametrosPermitidos = {"start", "stop", "restart"}

# Frases de error preestablecidas
errNoParametros = "Debes introducir uno de los siguientes parámetros:"
errParametroIncorrecto = "El parámetro %s no se encuentra entre los aceptados"


def imprimirListaParametros():
	print(errNoParametros)
	for i in listaParametrosPermitidos:
		print(i)


os.system("clear")
if len(sys.argv) >= 2:
	parametro = sys.argv[1]
	if parametro in listaParametrosPermitidos:
		os.system("sudo " + rutaLampp + " " + parametro)
	else:
		print(errParametroIncorrecto % (parametro))
		imprimirListaParametros()
else:
	imprimirListaParametros()
