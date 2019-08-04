MAFContext context = mafContext
MAFNotificationGateway notify = context.getNotificationManager()

String titulo = "Mi primer log"
String body = "Esto es el cuerpo del mensaje"

// Hay distintos tipos de log
notify.writeDebug(titulo, body)
notify.writeInfo(titulo, body)
notify.writeWarning(titulo, body)
notify.writeError(titulo, body)

// Multilínea
notify.writeDebug(titulo, 
'''Esto es un debug multilínea.
¿A que no te lo esperabas?''')

// Imprimiendo variables en el log
notify.writeDebug("Variable titulo: {$titulo}")