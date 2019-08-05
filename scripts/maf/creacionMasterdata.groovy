MAFContext context = mafContext

Long systemID = 0000000
String pool = "POOL"
String group = "GROUP"
String mdKey = "MD-KEY"

// Creamos un objeto MAFGenericDataContainer que se usará
// para la request del masterdata
MAFGenericDataContainer dc = new MAFGenericDataContainer()

// Generamos las entradas que queramos agregarle al dc
for (int i = 0; i < 5; i++)
{
    MAFGenericDataContainerEntry row = new MAFGenericDataContainerEntry()
    row.setName("ENTRADA_" + i)
    row.setValstr("VALOR_" + i)
    //raw.setValb64(byte[]) nos permite subir información en base64 también

    // Agregamos la entrada al objeto padre
    dc.addEntry(row)
}

// Extraemos del context un objeto MAFLifecycleGateway
MAFLifecycleGateway gateway = context.getMDSLifecycleManager()

/*
    Usamos una de las sobreescrituras de sendMasterdata disponibles
    La parametrización disponible es la siguiente:
        1. Long systemID, String pool, String group, String key, String description, String filter1, String filter2, String filter3, Long filter4, Long filter5, Long filter6,
        String language, MAFGenericDataContainer dataContainer)
        2. Long systemID, String pool, String group, String key, String description, String filter1, String filter2, String filter3, Long filter4, Long filter5, Long filter6,
        String language, Date validTill, MAFGenericDataContainer dataContainer)
        3. Long systemID, String pool, String group, String key, String description, String filter1, String filter2, String filter3, Long filter4, Long filter5, Long filter6,
        Date validTill, MAFGenericDataContainer dataContainer)
        4. Long systemID, String pool, String group, String key, String description, String filter1, String filter2, String filter3, Long filter4, Long filter5, Long filter6,
        String language, Hashtable dataContainer)
        5. Long systemID, String pool, String group, String key, String description, String filter1, String filter2, String filter3, Long filter4, Long filter5, Long filter6,
        String language, Date validTill, Hashtable dataContainer)
        6. Long systemID, String pool, String group, String key, String description, String filter1, String filter2, String filter3, Long filter4, Long filter5, Long filter6,
        Date validTill, MAFGenericDataContainer dataContainer)
 */
gateway.sendMasterdata(systemID, pool, group, mdKey, "Description", "", "", "", null, null, null, "ES", dc)