package com.movicoders.cloudsgaGDES.utiles;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.movicoders.cloudsgaGDES.enumeradores.TipoLog;

public class UtilesLog {

	private static Logger log = Logger.getLogger(UtilesLog.class);
	
	@SuppressWarnings("rawtypes")
	public static void registrarInfo(Class clase, TipoLog tipo, String mensaje)
	{
		log = LogManager.getLogger(clase);
		
		switch (tipo) 
		{
			case DEBUG:
				log.debug(mensaje);
				break;
			case ERROR:
				log.error(mensaje);
				break;
			case FATAL:
				log.fatal(mensaje);
				break;
			case INFO:
				log.info(mensaje);
				break;
			case WARNING:
				log.warn(mensaje);
		}
	}
}
