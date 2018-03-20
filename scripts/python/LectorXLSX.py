# -*- coding: utf-8 -*-

from openpyxl import load_workbook  # Requiere instalar openpyxl
import os.path

rutaXLSX = "fichero.xlsx"

if os.path.isfile(rutaXLSX):

    libro = load_workbook(rutaXLSX)  # Abro el excel para extraer los campos
    hoja = libro.get_sheet_by_name(libro.get_sheet_names()[0])  # Cojo la primera hoja

    for fila in hoja.rows:
        for celda in fila:
            print(celda.value)
else:
    print("No se ha reconocido el fichero")
