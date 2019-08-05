MAFContext context = mafContext

String pkKey = "PK_TEST"
String counterKey = "COUNTER_TEST"
Long counterValue = 0 // Solo admite valores Long

MAFCounterBO counterBO = new MAFCounterBO()
PartitionKey pk = new PartitionKey(pkKey) // Divide el counter en distintas "porciones" a las que luego hay que acceder

// Se crea el objeto CounterBO definiendo systemID, key, valor y partition key
counterBO.setSystemID(context.getSystemID())
counterBO.setKey(counterKey)
counterBO.setValue(counterValue)
counterBO.setPartition(pk)

// Guarda el counter en la nube
context.getDataAccessManager().saveCounter(counterBO) 