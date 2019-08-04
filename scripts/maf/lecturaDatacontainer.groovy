MAFContext context = mafContext
MAFNotificationGateway notify = context.getNotificationManager()

MAFUploadDataContainer dc = context.getUploadContainer()
MAFGenericUploadDataContainer uData = dc.getData()

String clave = "CLAVE" // La clave del campo de array que quiera leer
String campo = uData.getObject(clave)
notify.writeDebug("Campo leído: ${campo}", null)

/*
	Para descubrir el tipo de objeto
	que estamos leyendo, debemos hacer
	lo siguiente
*/
def object = context.getUploadContainer() // Recibir el datacontainer
notify.writeDebug("Object", object) // Conocer el tipo de objeto
notify.writeDebug("Nombre de la clase en concreto", object.getClass().getSimpleName())
