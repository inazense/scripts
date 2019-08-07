import com.movilizer.maf.bo.MAFIndexIterator
import com.movilizer.maf.bo.data.MAFTimeseriesIndexEntryBO
import com.movilizer.maf.scripting.MAFEventContext
import com.movilizer.maf.scripting.access.MAFDataGateway
import com.movilizer.spring3utils.util.PartitionKey

public class ProcessingQueue
{
	// Propiedades
	private final String 	PK_QUEUE_DELAY 		= "PK_QUEUE_DELAY"
	private final Long 		BUCKET_SIZE 		= 10000L
	private final Long 		NEXT_DAY_IN_MILLS 	= (24 * 3600 * 1000)
	private final Long 		INBOX_TTL 			= 60 * 60 * 24 * 60 // TTL en segundos

	private MAFEventContext context
	private MAFDataGateway dataGateway
	private PartitionKey partitionKey

	// Constructor
	public ProcessingQueue() {}

	public ProcessingQueue(MAFEventContext context)
	{
		this.context = context
		this.dataGateway = context.getDataAccessManager()
		this.partitionKey = new PartitionKey(PK_QUEUE_DELAY)
	}

	// Métodos
	/**
	 * Agrega un elemento a la cola MAF que se almacenará en los nodos de Cassandra
	 * @param value Cualquier objeto posible
	 * @param id Long que actue de clave única (timestamp, por ejemplo)
	 * @return 
	 */
	void addElement(Object value, Long id)
	{
		MAFTimeseriesIndexEntryBO element = new MAFTimeseriesIndexEntryBO()
		element.setKey(id)
		element.setSystemID(Environment.getWSSystemID()) // Consultar clase Environment.groovy en la carpeta codigo
		element.setValue(value) // Admite cualquier objeto
		element.setBucketSize(this.BUCKET_SIZE)
		element.setPartition(this.partitionKey)
		this.dataGateway.addTimeseriesIndex(element, this.INBOX_TTL.toInteger())
	}
	
	/**
	 * Borra un elemento de la cola especificando su key
	 * @param key Clave del elemento a borrar
	 */
	private void removeElementFromQueue(Long key)
	{
		this.dataGateway.deleteTimeseriesIndexEntry(Environment.getWSSystemID(), this.partitionKey, key, this.BUCKET_SIZE)
	}
	
	/**
	 * Recupera todos los elementos entre dos periodos de tiempo
	 * @param startTime Tiempo de inicio. Ha de ser menor al endTime
	 * @param endTime Tiempo final. Ha de ser mayor a startTime
	 * @return Lista de MAFTimeseriesIndexEntryBO
	 */
	private MAFIndexIterator<MAFTimeseriesIndexEntryBO> getElementsFromPeriodOfTime(Long startTime, Long endTime)
	{
		return this.dataGateway.loadTimeseriesIndex(Environment.getWSSystemID(), this.partitionKey, this.BUCKET_SIZE, startTime, endTime)
	}
	
	/**
	 * 
	 * @return
	 */
	public void readElementsFromQueue()
	{
		Long startTime = System.currentTimeMillis() // Hoy
		Long endTime = startTime + this.NEXT_DAY_IN_MILLS // Mañana
		MAFIndexIterator<MAFTimeseriesIndexEntryBO> mafEvents = this.getElementsFromPeriodOfTime(startTime, endTime)
		
		while (mafEvents.hasNext())
		{
			MAFTimeseriesIndexEntryBO event = mafEvents.next()
			Long key = event.getKey() // Extraigo la clave
			Object value = event.getValue() // Extraigo el valor
			// Aquí puedo operar de la manera que desee con estos elementos
		}
	}
}