package com.movilizer.tpd.serialization

import com.movilizer.maf.bo.MAFMasterdataKey
import com.movilizer.maf.bo.data.MAFCounterBO
import com.movilizer.maf.bo.mappings.container.MAFGenericDataContainer
import com.movilizer.maf.bo.mappings.container.MAFGenericDataContainerEntry
import com.movilizer.maf.bo.mappings.container.MAFMasterdataEntry
import com.movilizer.maf.scripting.MAFContext
import com.movilizer.maf.scripting.MAFEventContext
import com.movilizer.maf.scripting.access.MAFDataGateway
import com.movilizer.maf.scripting.access.MAFLifecycleGateway
import com.movilizer.spring3utils.util.PartitionKey
import com.movilizer.tpd.Constants
import com.movilizer.tpd.Environment
import com.movilizer.tpd.util.Logger

class AggregationIdManager {

    MAFEventContext context
    Logger logger
    MAFDataGateway mafDataGateway
    MAFLifecycleGateway lifecycleGateway
    Environment environment
    String exceptionMessage
    String COUNTER_CURRENT_SSCC_VALUE
    
    static final String LOCK_NAME = "AGGREGATIONIDMANAGER_SSCC_GENERATOR_LOCK"

    public AggregationIdManager() {}

    AggregationIdManager(MAFContext context, Logger logger) {
        this.context = (MAFEventContext)context
        this.logger = logger
        this.environment = new Environment(context)
        this.mafDataGateway = this.context.getDataAccessManager()
        this.lifecycleGateway = this.context.getMDSLifecycleManager()
        this.exceptionMessage = "AggregationIdManager. SSCC limit out of range"
        this.COUNTER_CURRENT_SSCC_VALUE = "COUNTER_SSCC_CURRENT"
    }


    Map<String, Hashtable<String, String>> getCodesForUser(String user, long limit) {
        context.getMDSLifecycleManager().tryAcquireLock(context.getSystemID(), LOCK_NAME)
        logger.info("MAFCounterBO blocked with LOCK_NAME: " + LOCK_NAME)
        MAFCounterBO cbo = this.getMAFCounterBO()
        Long startRange = this.getStartRangeLimit()
        Long endRange = this.getEndRangeLimit()

        if (startRange + cbo.getValue() + limit > endRange) {
            context.getMDSLifecycleManager().releaseLock(context.getSystemID(), LOCK_NAME)
            throw new Exception(this.exceptionMessage + ". Start range: " + startRange + ". End range: " + endRange + ". CBO: " + cbo.getValue() + ". Limit: " + limit)
        } else {
            long cboCurrentValue = cbo.getValue()
            cbo.setValue(limit)
            this.mafDataGateway.saveCounter(cbo)
            context.getMDSLifecycleManager().releaseLock(context.getSystemID(), LOCK_NAME)
            logger.info("MAFCounterBO released with LOCK_NAME: " + LOCK_NAME)
            Hashtable result = new Hashtable()
            for (def i = 0; i < limit; i++) {
                result.put(i.toString(), generateCode(getMasterdataInfo(), i, cboCurrentValue, startRange))
            }

            logger.info("Distributed aggregation level UIs for user: " + user, result.toString())

            return result
        }
    }

    Hashtable generateCode(Map<String, String> map, int i, long currentValue, long startRange){

        String PREFIX = map.get("prefix")
        long SUFIX = startRange + currentValue + Long.parseLong(String.valueOf(i))
        int prefixNum = PREFIX.length()
        int sufixNum = String.valueOf(SUFIX).length()
        String code = ""

        if (prefixNum + sufixNum == 17) {
            code = PREFIX + String.valueOf(SUFIX)
        } else {
            int addedZeros = 17 - sufixNum - prefixNum
            String newSufix = String.valueOf(SUFIX)
            for (int k = 0; k < addedZeros; k++) {
                newSufix = "0" + newSufix
            }

            code = PREFIX + newSufix
            logger.info("AggregationIdManager. New code generated: " + code)
        }

        Hashtable response = new Hashtable()
        String newCode = code + this.getControlDigit(code)
        response.put("raw", "00" + newCode)
        response.put("hr", "(00)" + newCode)
        this.saveSSCCIntoMasterdata(newCode)
        return response
    }

    String getControlDigit(String code) {
        int subtotal = 0
        for (int i = 0; i < code.length(); i++) {
            if (i == 0 || i % 2 == 0) {
                String numString = code.substring(i, i+1)
                int num = Integer.parseInt(numString)
                subtotal = subtotal + (num * 3)
            }
            else {
                String numString = code.substring(i, i+1)
                int num = Integer.parseInt(numString)
                subtotal += num
            }
        }

        subtotal = subtotal % 10
        if (subtotal == 0) { return subtotal }
        else { return String.valueOf(10 - subtotal) }
    }
    //
    // COUNTER BUSINESS OBJECT METHODS
    //
    MAFCounterBO getMAFCounterBO() {
        PartitionKey pk = new PartitionKey(COUNTER_CURRENT_SSCC_VALUE)
        MAFCounterBO counter = this.mafDataGateway.loadCounter(this.environment.getMasterdataSystemId(), pk, "CURRENT_SSCC")

        if (counter != null){
            return counter
        }

        // IF not return any object in for loop, I will create one counter
        MAFCounterBO counterBO = new MAFCounterBO()
        counterBO.setSystemID(this.environment.getMasterdataSystemId())
        counterBO.setKey("CURRENT_SSCC")
        counterBO.setValue(0)
        counterBO.setPartition(pk)
        this.mafDataGateway.saveCounter(counterBO)
        logger.info("Created new MAFCounterBO object with key: " + counterBO.getKey())

        return counterBO
    }

    Map<String, String> getMasterdataInfo() {
        Map<String, String> result = new HashMap<String, String>()

        // Prefix
        MAFMasterdataKey mdKeyPrefix         = new MAFMasterdataKey(this.environment.getMasterdataSystemId(), Constants.GENERATION_MD_GCP_POOL, "PREFIX")
        MAFMasterdataEntry mdPrefix          = lifecycleGateway.loadMasterdata(mdKeyPrefix)

        if (mdPrefix == null){
            context.getMDSLifecycleManager().releaseLock(context.getSystemID(), LOCK_NAME)
            this.logger.error("Masterdata record for PREFIX is missing")
        }

        MAFGenericDataContainer dataPrefix   = mdPrefix.getData()
        result.put("prefix", dataPrefix.getObject("PREFIX_CODE") as String)
        logger.info("Readed prefix " + dataPrefix.getObject("PREFIX_CODE") as String)

        // Ranges
        MAFMasterdataKey mdKeyRanges             = new MAFMasterdataKey(this.environment.getMasterdataSystemId(), Constants.GENERATION_MD_GCP_POOL, "RANGES")
        MAFMasterdataEntry mdRanges              = lifecycleGateway.loadMasterdata(mdKeyRanges)

        if (mdRanges == null){
            context.getMDSLifecycleManager().releaseLock(context.getSystemID(), LOCK_NAME)
            this.logger.error("Masterdata record for RANGES is missing")
        }

        MAFGenericDataContainer  dataRanges      = mdRanges.getData()
        result.put("startRange", dataRanges.getObject("START_RANGE") as String)
        result.put("endRange", dataRanges.getObject("END_RANGE") as String)
        logger.info("Readed startRange " + dataRanges.getObject("START_RANGE") as String)
        logger.info("Readed endRange " + dataRanges.getObject("END_RANGE") as String)

        return result
    }

    long getStartRangeLimit() {
        return Long.parseLong(this.getMasterdataInfo().get("startRange"))
    }

    long getEndRangeLimit() {
        return Long.parseLong(this.getMasterdataInfo().get("endRange"))
    }

    void saveSSCCIntoMasterdata(String code)  {
        MAFGenericDataContainer dc = new MAFGenericDataContainer()
        MAFGenericDataContainerEntry raw = new MAFGenericDataContainerEntry()
        raw.setName("RAW")
        raw.setValstr("00" + code)

        MAFGenericDataContainerEntry hr = new MAFGenericDataContainerEntry()
        hr.setName("HR")
        hr.setValstr("(00)" + code)

        dc.addEntry(raw)
        dc.addEntry(hr)
        lifecycleGateway.sendMasterdata(this.environment.getMasterdataSystemId(), Constants.GENERATION_MD_IDS_POOL, Constants.GENERATION_MD_GROUP, "00" + code, code,
                code,null , "(00)" + code, null, null, null, "", dc)
        logger.info("Masterdata created. Key: 00" + code + ". SystemID: " + context.getSystemID())
    }
}