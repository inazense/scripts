# Scripts
Porque necesito un sitio para organizarme las porciones de código

## Categorías
- [Java](#java)
- [MAF](#maf)
- [Movilizer](#movilizer)
- [PHP](#php)
- [Python](#python)
- [Varios](#varios)

### Java
__[ConexionSingleton](/scripts/java/ConexionSingleton.java) :__ Clase usada para devolver objetos Connection a una base de datos MySQL implementando el patrón Singleton

__[LectorCSV](/scripts/java/LectorCSV.java) :__ Clase para leer ficheros CSV simples y complejos (requiere OpenCSV para estos últimos)

__[Manejador Log4j](/scripts/java/ManejadorLog4j) :__ Clase y fichero log4j.properties para manejar un logger por consola y ficheros con Log4j

__[ManejadorMovilizer](/scripts/java/ManejadorMovilizer.java) :__ Clase usada para generar objetos Movilizer. Actualmente en la versión 2.5

__[ManejadorProperties](/scripts/java/ManejadorProperties.java) :__ Clase para leer una propiedad concreta de archivos properties usando el patrón Singleton

### MAF
__[CounterBO. Creación](/scripts/maf/creacionCounterBO.groovy) :__ Creación de CounterBO y persistencia en la nube

__[CounterBO. Lectura de Counter de la nube](/scripts/maf/lecturaCounterBO.groovy) :__ 

__[CounterBO. Bloquear en transacción](/scripts/maf/counterBloqueado.groovy) :__ Bloqueo del counter en una transacción para evitar iteraciones no deseadas

__[Datacontainer. Lectura](/scripts/maf/lecturaDatacontainer.groovy)__ 

__[Datacontainer online. Lectura](/scripts/maf/lecturaDatacontainerOnline.groovy) :__ Lectura de datacontainer online y respuesta a la movelet

__[Environment](/scripts/Environment.groovy) :__ Clase para manejar el acceso a los distintos systems ID del proyecto

__[Log](/scripts/maf/log.groovy) :__ Creación de logs en el portal

__[Masterdata. Lectura](/scripts/maf/lecturaMasterdata.groovy) :__ Lectura de masterdata conociendo previamente la clave

__[Masterdata. Query](/scripts/maf/queryMasterdata.groovy)__

__[Masterdata. Creación](/scripts/maf/creacionMasterdata.groovy)__

### Movilizer
__[CheckAndUncheckElements](/scripts/movilizer/CheckAndUncheckElements.mxml) :__ Pantalla de selección de checkboxes en tablas Movilizer

__[ConnectToBluetoothPrinter](/scripts/movilizer/ConnectToBluetoothPrinter.mxml) :__ Cómo conectar una impresora Bluetooth desde Movilizer

__[ConnectWebBrowser](/scripts/movilizer/ConnectWebBrowser.mxml) :__ Abre una página web en el navegador por defecto de Windows desde Movilizer

__[Emmet snippets - Eclipse](/scripts/movilizer/movilizerEmmetSnippets.xml) :__ Snippets para atajos de teclado con Emmet en Eclipse

__[Emmet snippets - IntelliJ](/scripts/movilizer/movilizerEmmetSnippetsIntelliJ.xml) :__ Snippets para atajos de teclado con Emmet en IntelliJ

__[GoogleMapsRequests](/scripts/movilizer/GoogleMapsRequests.mxml) :__ Funciones MEL para trabajar con la API de Google Maps

__[ScanItems](/scripts/movilizer/ScanItems.mxml)__ Pantalla de escaneo de elementos usada en dispositivos Windows CE

__[TimeUtils](/scripts/movilizer/TimeUtils.mxml) :__ Librería MEL para trabajar con tiempos

### PHP
__[Ficheros](/scripts/php/Ficheros.php) :__ Clase para trabajar con ficheros. Actualmente solo lee ficheros de un directorio

__[GoogleMapsGeo](/scripts/php/GoogleMapsGeo.php) :__ Clase para trabajar con la geocodificación con la API de Google Maps

__[MailSender](/scripts/php/MailSender.php) :__ Clase para realizar envíos de correos electrónicos. Requiere phpmailer para envíos complejos

### Python
__[barraProgresoTerminal](/scripts/python/barraProgresoTerminal.py) :__ Genera una barra de progreso en la terminal

__[clonarRepositorio](/scripts/python/clonarRepositorio.py) :__ Clonación de todas las ramas de un repositorio git, tanto público como privado

__[googleMapsAPI](/scripts/python/googleMapsAPI.py) :__ Clase para trabajar con la API de Google Maps

__[lamppController](/scripts/python/lamppController.py) :__ Script para controlar el servidor XAMPP en Ubuntu

__[LectorXLSX](/scripts/python/LectorXLSX.py) :__ Lector de ficheros XLSX con Python3

__[ManejadorXamppUbuntu](/scripts/python/manejadorXamppUbuntu.py)__ Arranca / detiene los servicios de XAMPP en Ubuntu

### Varios
__[Anclar Papelera al dock de Ubuntu](/scripts/varios/PapeleraDockUbuntu.sh) :__ Fichero .sh para permitir anclar la papelera al dock de Ubuntu

__[Atajos de teclado para IntelliJ](/scripts/varios/intellij-shortcuts.md)__

__[Atajos de teclado para Notepad++](/scripts/varios/atajosNotepad++.md)__

__[Cheatsheet comandos GIT](/scripts/varios/cheatsheetGit.md)__

__[EliminarOneDrive](/scripts/varios/eliminarOneDrive.cmd) :__ Fichero .cmd para forzar la desinstalación de OneDrive de Windows 10

__[RestaurarVisorFotos](/scripts/varios/restaurarVisorFotos.reg) :__ Habilita el uso del visor de fotos de Windows 7 en Windows 10. Requiere reinicio del sistema operativo
