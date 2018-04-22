# -*- coding: utf-8 -*-
# Script para arrancar o detener los servicios de XAMPP en Ubuntu 16.04
# Parámetros esperados:
# - start: Iniciar servicios
# - stop: Parar servicios

import os
import sys


opcion 		= sys.argv[1]
servicios 	= "\nServidores Apache y MariaDB "
opcionServicios = {'start': 'iniciados\n', 'stop': 'detenidos\n'}

os.system("clear")
if opcion == 'start':
	os.system("sudo /opt/lampp/lampp start")
else:
	os.system("sudo /opt/lampp/lampp stop")

print(servicios, opcionServicios[opcion])
