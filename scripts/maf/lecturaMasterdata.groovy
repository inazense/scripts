MAFContext context = mafContext
MAFNotificationGateway notify = context.getNofiticationManager()

Long systemID = 0000000
String mdPool = "TEST-POOL"
String key = "KEY-TEST"

MAFLifecycleGateway mafLifecycleGateway = context.getMDSLifecycleManager()
MAFMasterdataKey mafMDKey = new MAFMasterdataKey(systemID, mdPool, key)

MAFGenericDataContainer data = md.getData()
String value = data.getObject("CAMPO") // Campo del masterdata a leer

notify.writeDebug("Campo leído: {$value}", null)