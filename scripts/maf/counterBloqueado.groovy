/*
    Sirve para almacenar un contador en la nbue MAF con persistencia
    permitiendo ser bloqueado y liberado según necesidad para evitar
    que se dupliquen resultados.
    Ejemplo: Un script que generará SSCC iterativos y que las peticiones
    van a ser realizadas por más de un dispositivo. Aquí se corre el riesgo
    de que puedan duplicarse los barcodes y eso sería un desastre.
 */
MAFContext context = mafContext
MAFNotificationGateway notify = context.getNotificationManager()

String pkKey = "PK_TEST"
String counterKey = "COUNTER_TEST"
String lockName = "LOCK_TEST"
Long counterValue = 10

// Prerrequesitos: MAFCounterBO ya creado
MAFCounterBO counterBO = new MAFCounterBO()
PartitionKey pk = new PartitionKey(pkKey)
counterBO.setSystemID(context.getSystemID())
counterBO.setKey(counterKey)
counterBO.setValue(counterValue)
counterBO.setPartition(pk)
context.getDataAccessManager().saveCounter(counter) // Guarda el counter en la nube

// 1. Bloquear transacciones en System ID concreto
context.getMDSLifecycleManager().releaseLock(context.getSystemID(), lockName)

// 2. Incrementar valor del contador
// Aquí hace la sumatoria del valor que le pasemos por parámetro.
// Si nuestro contador valía anteriormente 10, con un parámetro de 5 pasará a valer 15
counterBO.setValue(5)

// 3. Guardar de nuevo el contador
context.getDataAccessManager().saveCounter(counterBO)

// 4. Libera el bloqueo establecido una vez que se termina de operar con el counter
context.getMDSLifecycleManager().releaseLock(context.getSystemID(), lockName)
context.getMDSLifecycleManager().tryAcquireLock(context.getSystemID(), lockName)