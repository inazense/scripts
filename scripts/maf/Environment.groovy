import com.movilizer.maf.scripting.MAFContext

/**
 * Clase para controlar los system IDs
 * desde el propio context de MAF
 */
public class Environment
{
    // Properties
    private MAFContext context

    // Constructor
    public Environment()
    {
        throw new Exception("No uses éste constructor: "+ this.getClass().getSimpleName() );
    }

    public Environment(MAFContext context)
    {
        this.context = context
    }

    // Métodos

    /**
     * Devuelve el systemID usado para MAF
     * @return
     */
    public long getScriptingSystemID()
    {
        return this.context.getSystemID()
    }

    /**
     * Devuelve el systemID para el webservice
     * @return
     */
    public long getWebserviceSystemID()
    {
        return this.context.getSystemID() + 1
    }

    /**
     * Devuelve el systemID usado para el EPCIS
     * @return
     */
    public getEpcisSystemID()
    {
        return this.context.getSystemID() + 2
    }
}