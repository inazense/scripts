MAFContext context = mafContext
MAFNotificationGateway notify = context.getNotificationManager()

Long systemID = 0000000
String pool = "POOL-TEST"

MAFLifecycleGateway gateway = context.getMDSLifecycleManager()

// Extraigo todos los MDKeys de un pool concreto
Iterator<MAFMasterdataKey> masterdatas = gateway.listMasterdataKeys(systemID, pool)

// Itero los masterdata para procesarlos uno a uno
while (masterdatas.hasNext())
{
	MAFMasterdataEntry md = gateway.loadMasterdata(masterdatas.next())
	notify.writeDebug(md.getKey(), null)
}