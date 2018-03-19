package Movicoders;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.movilizer.AnswerColumnSizeType;
import com.movilizer.MoveletType;
import com.movilizer.MovilizerAnswer;
import com.movilizer.MovilizerGenericUploadDataContainer;
import com.movilizer.MovilizerMovelet;
import com.movilizer.MovilizerMoveletDelete;
import com.movilizer.MovilizerMoveletDocument;
import com.movilizer.MovilizerMoveletMasterdata;
import com.movilizer.MovilizerMoveletSet;
import com.movilizer.MovilizerParticipant;
import com.movilizer.MovilizerQuestion;
import com.movilizer.MovilizerRequest;
import com.movilizer.MovilizerResponse;
import com.movilizer.MovilizerRestriction;
import com.movilizer.MovilizerUploadDataContainer;
import com.movilizer.MovilizerValidation;
import com.movilizer.MovilizerWebServiceV15;
import com.movilizer.MovilizerWebServiceV15Service;
import com.movilizer.ValidationType;

/**
 * Clase usada para generar objetos Movilizer en versión 2.5
 * @author Inazio
 *
 */
public class ManejadorMovilizer {

	////////////////////////////
	//   Creación de objetos  //
	////////////////////////////
	
	/**
	 * Genera la respuesta de realizar un callback a la nube de Movilizer
	 * @param solicitud MovilizerRequest a enviar
	 * @return MovilizerResponse capturado de la operación de callback
	 */
	public MovilizerResponse crearResponse(MovilizerRequest solicitud) {
		MovilizerWebServiceV15Service servicio = new MovilizerWebServiceV15Service();
		MovilizerWebServiceV15 puerto = servicio.getMovilizerWebServiceV15Soap11();
		MovilizerResponse respuesta = puerto.movilizer(solicitud);
		
		return respuesta;
	}
	/**
	 * Crea un request para realizar una petición al cloud de Movilizer con los parámetros básicos ya configurados
	 * @param systemID Atributo systemID
	 * @param password Atributo systemPassword
	 * @param numRespuestas Atributo numResponses
	 * @param autoKnowledge Atributo autoKnowledge
	 * @param respuestaSincrona Atributo syncResponse
	 * @param listaDeletes Lista de MovilizerMoveletDelete
	 * @param listaMoveletSet Lista de MovilizerMoveletSet
	 * @return MovilizerRequest con los parámetros básicos ya configurados
	 */
	public MovilizerRequest crearMovilizerRequest(Long systemID, String password, int numRespuestas, boolean autoKnowledge, boolean respuestaSincrona, List<MovilizerMoveletDelete> listaDeletes, List<MovilizerMoveletSet> listaMoveletSet) {
		MovilizerRequest solicitud = new MovilizerRequest();
		
		solicitud.setSystemId(systemID);
		solicitud.setSystemPassword(password);
		solicitud.setNumResponses(numRespuestas);
		solicitud.setUseAutoAcknowledge(autoKnowledge);
		solicitud.setSynchronousResponse(respuestaSincrona);
		
		for (MovilizerMoveletDelete moveletDelete : listaDeletes) {
			solicitud.getMoveletDelete().add(moveletDelete);
		}
		
		for (MovilizerMoveletSet moveletSet : listaMoveletSet) {
			solicitud.getMoveletSet().add(moveletSet);
		}
		
		return solicitud;
	}
	
	/**
	 * Crear un moveletDelete con todos sus parámetros
	 * @param moveletKey Atributo moveletKey
	 * @param ignorarExtensionKey Atributo ignoreExtensionKey
	 * @param keyExtension Atributo moveletKeyExtension
	 * @return MovilizerMoveletDelete con todos sus parámetros
	 */
	public MovilizerMoveletDelete crearMoveletDelete(String moveletKey, boolean ignorarExtensionKey, String keyExtension) {
		MovilizerMoveletDelete moveletDelete = new MovilizerMoveletDelete();
		
		moveletDelete.setMoveletKey(moveletKey);
		moveletDelete.setIgnoreExtensionKey(ignorarExtensionKey);
		moveletDelete.setMoveletKeyExtension(keyExtension);
		return moveletDelete;
	}
	
	/**
	 * Crear un moveletDelete con todos sus parámetros
	 * @param moveletKey Movelet a la que aplicar el moveletDelete
	 * @param ignorarExtensionKey Atributo ignoreExtensionKey
	 * @param keyExtension Atributo moveletKeyExtension
	 * @return MovilizerMoveletDelete con todos sus parámetros
	 */
	public MovilizerMoveletDelete crearMoveletDelete(MovilizerMovelet moveletKey, boolean ignorarExtensionKey, String keyExtension) {
		return this.crearMoveletDelete(moveletKey.getMoveletKey(), ignorarExtensionKey, keyExtension);
	}
	
	/**
	 * Crea un moveletSet con todas sus movelets y participantes correspondientes
	 * @param movelets Listado de movelets a incluir
	 * @param participantes Listado de participantes a incluir
	 * @return MoveletSet con movelets y participantes
	 */
	public MovilizerMoveletSet crearMoveletSet(List<MovilizerMovelet> movelets, List<MovilizerParticipant> participantes) {
		MovilizerMoveletSet moveletSet = new MovilizerMoveletSet();
		
		for (MovilizerMovelet movelet : movelets) {
			moveletSet.getMovelet().add(movelet);
		}
		
		for (MovilizerParticipant participante : participantes) {
			moveletSet.getParticipant().add(participante);
		}

		return moveletSet;
	}
	
	/**
	 * Crea una movelet con todos los parámetros básicos
	 * @param moveletKey Atributo moveletKey
	 * @param nombre Atributo name
	 * @param questionInicial Atributo initialQuestionKey
	 * @param visible Atributo visible
	 * @param tipo Atributo type
	 * @param fechaValidez Atributo validTillDate
	 * @param icono Atributo icon
	 * @param espacioDeNombre Atributo namespace
	 * @param prioridad Atributo priority
	 * @return MovilizerMovelet con los parámetros básicos creados
	 */
	public MovilizerMovelet crearMovelet(String moveletKey, String nombre, String questionInicial, boolean visible, MoveletType tipo, String fechaValidez, int icono, String espacioDeNombre, Long prioridad) {
		MovilizerMovelet movelet = new MovilizerMovelet();
		
		movelet.setMoveletKey(moveletKey);
		movelet.setName(nombre);
		movelet.setPriority(prioridad);
		movelet.setMoveletType(tipo);
		movelet.setInitialQuestionKey(questionInicial);
		movelet.setVisible(visible);
		movelet.setValidTillDate(this.stringACalendarioGregoriano(fechaValidez));
		movelet.setIcon(Byte.parseByte(String.valueOf(icono)));
		movelet.setNamespace(espacioDeNombre);
		
		return movelet;
	}
	
	/**
	 * Crea una movelet con todos los parámetros básicos
	 * @param moveletKey Atributo moveletKey
	 * @param nombre Atributo name
	 * @param questionInicial Question con la que inicia la movelet
	 * @param visible Atributo visible
	 * @param tipo Atributo type
	 * @param fechaValidez Atributo validTillDate
	 * @param icono Atributo icon
	 * @param espacioDeNombre Atributo namespace
	 * @param prioridad Atributo priority
	 * @return MovilizerMovelet con los parámetros básicos creados
	 */
	public MovilizerMovelet crearMovelet(String moveletKey, String nombre, MovilizerQuestion questionInicial, boolean visible, MoveletType tipo, String fechaValidez, int icono, String espacioDeNombre, Long prioridad) {
		return crearMovelet(moveletKey, espacioDeNombre, questionInicial.getKey(), visible, tipo, fechaValidez, icono, espacioDeNombre, prioridad);
	}
	
	/**
	 * Genera un elemento que sirva para suscribir una movelet a un masterdata
	 * @param pool Atributo pool
	 * @param systemID Atributo systemId del masterdata a suscribir
	 * @param grupo Atributo group
	 * @return MovilizerMoveletMasterdata con los parámetros ya completados
	 */
	public MovilizerMoveletMasterdata crearSuscripcionMasterdata(String pool, Long systemID, String grupo) {
		MovilizerMoveletMasterdata masterdata = new MovilizerMoveletMasterdata();
		
		masterdata.setPool(pool);
		masterdata.setSystemId(systemID);
		masterdata.setGroup(grupo);
		
		return masterdata;
	}
	
	/**
	 * Genera un elemento que sirva para suscribir una movelet a un document
	 * @param systemID Atributo systemId del masterdata a suscribir
	 * @param pool Atributo pool
	 * @return MovilizerMoveletDocument con los parámetros ya completados
	 */
	public MovilizerMoveletDocument crearSuscripcionDocumento(Long systemID, String pool) {
		MovilizerMoveletDocument documento = new MovilizerMoveletDocument();
		
		documento.setPool(pool);
		documento.setSystemId(systemID);
		
		return documento;
	}
	
	/**
	 * Devuelve una question con los parámetros básicos ya configurados
	 * @param key Movelet key
	 * @param tipo Tipo de question
	 * @param titulo Titulo de la cuestión
	 * @param backNavigationAllowed Si permite o no retroceder la pantalla
	 * @return MoveletQuestion con los parámetros de key, type, title y backNavigationAllowed ya rellenos
	 */
	public MovilizerQuestion crearQuestion(String key, int tipo, String titulo, boolean backNavigationAllowed) {
		MovilizerQuestion question = new MovilizerQuestion();
		
		question.setKey(key);
		question.setType(Byte.parseByte(String.valueOf(tipo)));
		question.setTitle(titulo);
		question.setBackNavigationAllowed(backNavigationAllowed);
		
		return question;
	}
	
	/**
	 * Devuelve una answer con los parámetros básicos ya configurados
	 * @param key Movelet key
	 * @param clientKey Client key
	 * @param tipo attributeType
	 * @param siguienteQuestion nextQuestionKey
	 * @param posicion Posicion de la answer
	 * @param dummyAnswer Si es dummy o no
	 * @param tipoColumna Formato de la answer al ser mostrada
	 * @param texto Texto predefinido de la answer
	 * @return MoveletAnswer con los parámetros de key, clientKey, attributeType, nextQuestionKey, position, dummyAnswer, columnSizeType y text
	 */
	public MovilizerAnswer crearAnswer(String key, String clientKey, int tipo, String siguienteQuestion, int posicion, boolean dummyAnswer, AnswerColumnSizeType tipoColumna, String texto) {
		MovilizerAnswer answer = new MovilizerAnswer();
		
		answer.setKey(key);
		answer.setAttributeType(Byte.parseByte(String.valueOf(tipo)));
		answer.setNextQuestionKey(siguienteQuestion);
		answer.setClientKey(clientKey);
		answer.setPosition(Short.parseShort(String.valueOf(posicion)));
		answer.setDummyAnswer(dummyAnswer);
		answer.setColumnSizeType(tipoColumna);
		answer.setText(texto);
		
		return answer;
	}
	
	/**
	 * Devuelve una answer con los parámetros básicos ya configurados
	 * @param key Movelet key
	 * @param clientKey Client key
	 * @param tipo attributeType
	 * @param siguienteQuestion MovilizerQuestion a la que apuntar
	 * @param posicion Posicion de la answer
	 * @param dummyAnswer Si es dummy o no
	 * @param tipoColumna Formato de la answer al ser mostrada
	 * @param texto Texto predefinido de la answer
	 * @return MovelizerAnswer con los parámetros de key, clientKey, attributeType, nextQuestionKey, position, dummyAnswer, columnSizeType y text
	 */
	public MovilizerAnswer crearAnswer(String key, String clientKey, int tipo, MovilizerQuestion siguienteQuestion, int posicion, boolean dummyAnswer, AnswerColumnSizeType tipoColumna, String texto) {
		return this.crearAnswer(key, clientKey, tipo, siguienteQuestion.getKey(), posicion, dummyAnswer, tipoColumna, texto);
	}
	
	/**
	 * Devuelve una restricción con los parámetros ya configurados
	 * @param question Atributo nextQuestionKey
	 * @param posicion Atributo position
	 * @param condicion Atributo condition
	 * @param matchingAssignment Atribuot matchingAssignment
	 * @return MovilizerRestriction con los parámetros de nextQuestionKey, position, condition y matchingAssingment configurados con los valores pasados por parámetro
	 */
	public MovilizerRestriction crearRestriccion(String question, int posicion, String condicion, String matchingAssignment) {
		MovilizerRestriction restriccion = new MovilizerRestriction();
		
		restriccion.setNextQuestionKey(question);
		restriccion.setPosition(Short.parseShort(String.valueOf(posicion)));
		restriccion.setCondition(condicion);
		restriccion.setMatchingAssignment(matchingAssignment);
		
		return restriccion;
	}
	
	/**
	 * Devuelve una restricción con los parámetros ya configurados
	 * @param question Question a la que apuntará el nextQuestionKey
	 * @param posicion Atributo position
	 * @param condicion Atributo condition
	 * @param matchingAssignment Atribuot matchingAssignment
	 * @return MovilizerRestriction con los parámetros de nextQuestionKey, position, condition y matchingAssingment configurados con los valores pasados por parámetro
	 */
	public MovilizerRestriction crearRestriccion(MovilizerQuestion question, int posicion, String condicion, String matchingAssignment) {
		return this.crearRestriccion(question.getKey(), posicion, condicion, matchingAssignment);
	}
	
	/**
	 * Devuelve una validación con los parámetros ya configurados
	 * @param condicion Atributo condition
	 * @param texto Atributo text
	 * @param tipo Atributo type
	 * @param posicion Atributo position
	 * @param matchingAssignment Atributo matchingAssignment
	 * @return MovilizerValidation con los parámetros de condition, text, type, position y matchingAssingment ya configurados con los valores pasados por parámetro
	 */
	public MovilizerValidation crearValidacion(String condicion, String texto, ValidationType tipo, int posicion, String matchingAssignment) {
		MovilizerValidation validacion = new MovilizerValidation();
		
		validacion.setCondition(condicion);
		validacion.setText(texto);
		validacion.setType(tipo);
		validacion.setPosition(Short.parseShort(String.valueOf(posicion)));
		validacion.setMatchingAssignment(matchingAssignment);
		
		return validacion;
	}
	
	////////////////////////////
	//   Agregar elementos    //
	////////////////////////////
	
	/**
	 * Agrega una lista de answers a una question concreta
	 * @param question Question a la que agregar answers
	 * @param lista Lista de answers que agregar
	 */
	public void agregarAnswersAQuestion(MovilizerQuestion question, List<MovilizerAnswer> lista) {
		for (MovilizerAnswer answer : lista) {
			question.getAnswer().add(answer);
		}
	}
	
	/**
	 * Agrega una lista de restricciones a una question concreta
	 * @param question Question a la que agregar answers
	 * @param lista Lista de restricciones que agregar
	 */
	public void agregarRestriccionesAQuestion(MovilizerQuestion question, List<MovilizerRestriction> lista) {
		for (MovilizerRestriction restriccion : lista) {
			question.getRestriction().add(restriccion);
		}
	}
	
	/**
	 * Agrega una lista de validaciones a una question concreta
	 * @param question Question a la que agregar answers
	 * @param lista Lista de validaciones que agregar
	 */
	public void agregarValidacionesAQuestion(MovilizerQuestion question, List<MovilizerValidation> lista) {
		for (MovilizerValidation validacion : lista) {
			question.getValidation().add(validacion);
		}
	}
	
	/**
	 * Agrega una lista de questions a una movelet concreta
	 * @param movelet Movelet a la que agregar las questions
	 * @param lista Lista de questions que agregar
	 */
	public void agregarQuestionsAMovelet(MovilizerMovelet movelet, List<MovilizerQuestion> lista) {
		for (MovilizerQuestion question : lista) {
			movelet.getQuestion().add(question);
		}
	}
	
	/**
	 * Agrega una lista de masterdatas a una movelet
	 * @param movelet Movelet a la que agregar MD
	 * @param lista Lista de masterdatas a agregar
	 */
	public void agregarMasterdataAMovelet(MovilizerMovelet movelet, List<MovilizerMoveletMasterdata> lista) {
		for (MovilizerMoveletMasterdata masterdata : lista) {
			movelet.getMasterdata().add(masterdata);
		}
	}
	
	/**
	 * Agrega una lista de documents a una movelet
	 * @param movelet Movelet a la que agregar documentos
	 * @param lista Lista de documentos a agregar
	 */
	public void agregarDocumentoAMovelet(MovilizerMovelet movelet, List<MovilizerMoveletDocument> lista) {
		for (MovilizerMoveletDocument documento : lista) {
			movelet.getDocument().add(documento);
		}
	}
	
	////////////////////////////
	//   Lectura de DC		  //
	////////////////////////////
	
	/**
	 * Devuelve todos los datacontainer que pertenezcan a una moveletKey en concreto
	 * @param respuesta Respuesta movilizer que analizar
	 * @param moveletKey Clave de la movelet desde la que se mandaron los DataContainer
	 * @return Lista de datacontainer
	 */
	public List<MovilizerGenericUploadDataContainer> leerDatacontainersDesdeMovelet(MovilizerResponse respuesta, String moveletKey) {
		
		List<MovilizerGenericUploadDataContainer> contenedores = new ArrayList<MovilizerGenericUploadDataContainer>();
		
		for (MovilizerUploadDataContainer mud : respuesta.getUploadContainer()) {
			if (mud.getContainer().getMoveletKey().equals(moveletKey)) {
				contenedores.add(mud.getContainer());
			}
		}
		
		return contenedores;
	}
	
	/**
	 * Devuelve todos los datacontainer que su clave sea exactamente como la pasada por parámetro
	 * @param respuesta Respuesta movilizer que analizar
	 * @param claveDatacontainer Clave del datacontainer
	 * @return Lista de datacontainer
	 */
	public List<MovilizerGenericUploadDataContainer> leerDatacontainersConClaveDatacontainerExacta(MovilizerResponse respuesta, String claveDatacontainer) {
		
		List<MovilizerGenericUploadDataContainer> contenedores = new ArrayList<MovilizerGenericUploadDataContainer>();
		
		for (MovilizerUploadDataContainer mud : respuesta.getUploadContainer()) {
			if (mud.getContainer().getKey().equals(claveDatacontainer)) {
				contenedores.add(mud.getContainer());
			}
		}
		
		return contenedores;
	}
	
	/**
	 * Devuelve todos los datacontainer que su clave empiece como la pasada por parámetro
	 * @param respuesta Respuesta movilizer que analizar
	 * @param claveDatacontainer Clave del datacontainer
	 * @return Lista de datacontainer
	 */
	public List<MovilizerGenericUploadDataContainer> leerDatacontainersConClaveDatacontainerInicio(MovilizerResponse respuesta, String claveDatacontainer) {
		
		List<MovilizerGenericUploadDataContainer> contenedores = new ArrayList<MovilizerGenericUploadDataContainer>();
		
		for (MovilizerUploadDataContainer mud : respuesta.getUploadContainer()) {
			if (mud.getContainer().getKey().startsWith(claveDatacontainer)) {
				contenedores.add(mud.getContainer());
			}
		}
		
		return contenedores;
	}
	
	////////////////////////////
	//   Útiles varios		  //
	////////////////////////////
	
	/**
	 * Convierte una fecha en String a un XMLGregorianCalendar con formato yyyy-MM-ddThh:mm:ss.000+00:00
	 * @param fecha String con formato yyyy-MM-ddThh:mm:ss.000+00:00
	 * @return Fecha parseada a XMLGregorianCalendar con formato yyyy-MM-ddThh:mm:ss.000+00:00
	 */
	private XMLGregorianCalendar stringACalendarioGregoriano(String fecha) {
		
		try {
			DateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date fechaParseada = formato.parse(fecha);
			GregorianCalendar calendario = new GregorianCalendar();
			calendario.setTime(fechaParseada);
			
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendario);
			
		} catch (ParseException | DatatypeConfigurationException e) {
			return null;
		}
	}
}