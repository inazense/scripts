MAFContext context = mafContext
MAFNotificationGateway notify = context.getNotificationManager()

String pkKey = "PK_TEST"
String counterKey = "COUNTER_TEST"

MAFCounterBO counterBO = new MAFCounterBO()
PartitionKey pk = new PartitionKey(pkKey)

// Lista todos los counter con el PK y el systemID indicados
List<MAFCounterBO> list = context.getDataAccessManager().loadCounters(context.getSystemID(), pk)

// Bucle hasta encontrar el que coincida con nuestra key
for (MAFCounterBO counter : list)
{
    if (counter.getKey().equals(counterKey))
    {
        // Ralizamos acción con el counter recuperado
        notify.writeDebug("Counter encontrado. Valor actual: " + counter.getValue(), null)
    }
}