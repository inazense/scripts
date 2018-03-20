# -*- coding: utf-8 -*-

# Clono un repositorio online a mi disco duro local.
# Genero una carpeta en la que almacenaré, en diferentes subcarpetas, todas las ramas que vaya a necesitar clonar
# Posteriormente comprimo toda la clonación en un archivo .zip con la fecha actual del sistema.
# Si ya existiese dicho archivo lo sobrescribiré.
# Posteriormente borro las carpetas creadas para dejar solo el .zip

import git # Requiere librería GitPython
import time
import zipfile
import os
import shutil
import sys

# Info del repositorio
usuario = "user"
password = "pass"
# En los repositorios privados es necesario usuario y contraseña
# después del protocolo https.
# En repos publicos no es necesario
# repositorioPublico = "https://@bitbucket.org/repoPrivado/repoPrivado.git"
repositorioPrivado = "https://" + usuario + ":" + password + "@bitbucket.org/repoPrivado/repoPrivado.git"

carpetaDescarga = "repositorio" # Carpeta principal donde se guardaran las ramas
ramas = ["master", "release", "dev"] # Ramas que queremos clonar

# Detectar Sistema Operativo
if sys.platform == "win32":
    cmd = "rmdir " + carpetaDescarga + "/s /q"
else:
    cmd = "rm -rf " + carpetaDescarga

# Creo la estructura de carpetas
if os.path.exists(carpetaDescarga):
    os.system(cmd)
os.makedirs(carpetaDescarga)

for i in ramas:
    if not os.path.exists(carpetaDescarga + "/" + i):
        os.makedirs(carpetaDescarga + "/" + i)

# Clonar repositorios
print("##################################")
print("     CLONACIÓN DE REPOSITORIO")
print("##################################")
print("\n")

for i in ramas:
    print("Clonando rama " + i)
    nuevoDirectorio = carpetaDescarga + "/" + i
    repo = git.Repo.clone_from(repositorio, nuevoDirectorio, branch = i)
    print("Rama " + i + " clonada")

# Generación del archivo .zip
nombreArchivoZip = time.strftime("%Y_%m_%d")
if os.path.exists(nombreArchivoZip + ".zip"):
    os.remove(nombreArchivoZip + ".zip")

archivoZip = zipfile.ZipFile(nombreArchivoZip + ".zip", "w")
for carpeta, subcarpetas, archivos in os.walk(carpetaDescarga):
    for archivo in archivos:
        archivoZip.write(os.path.join(carpeta, archivo), os.path.relpath(os.path.join(carpeta, archivo), carpetaDescarga), compress_type = zipfile.ZIP_DEFLATED)

archivoZip.close()

# Elimino carpeta clonación repositorio
shutil.rmtree(carpetaDescarga, True)
os.system(cmd) # Fuerzo a la eliminación del directorio porque shutil no me borra la carpeta raíz

# Mensaje de final de ejecución
print("\nClonación finalizada. Hasta mañana!")
time.sleep(3)
