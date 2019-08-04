MAFContext context = mafContext
MAFNotificationGateway notify = context.getNotificationManager()

Hashtable reply = new Hashtable() // Respuesta que se devolverá a movilizer
MAFGenericUploadDatacontainer uData = dc.getData()

// Obtengo la clave del datacontainer
String contKey = dc.getKey() 
String campo = uData.getObject("CLAVE") 

// Creo un par clave - valor para devolver el campo leído
reply.put("Campo", campo)

// Agrego al contexto la respuesta con la misma key del datacontainer enviado
context.addOnlineContainerReply(contKey, reply) 