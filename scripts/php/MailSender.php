<?php
require_once 'lib/class.phpmailer.php'; // Ruta de la librería class.phpmailer.php https://github.com/PHPMailer/PHPMailer
/**
 * Librería para trabajar con correos electrónicos.
 * Para usar las funciones de phpmailer requiere importar
 * la librería class.phpmailer.php
 *
 * @author Inazio
 *
 */
class MailSender{

	private $emisario = "xxx";
	private $mailEmisario = "xxx@xxx.com";
	private $miMail = "xxx@xxx";

	/**
	 * Envía un correo electrónico para usar en campos recogidos de un formulario.
	 * La idea es que la web tiene un correo asignado ($mailEmisario) y un nombre ($emisario).
	 * Tambien tengo un correo al que quiero que me lleguen ($miCorreo) y redirigir la respuesta automáticamente
	 * al correo del usuario que me escribe ($mailCliente).
	 *
	 * @param String $nombreCliente Nombre del usuario que escribe el mail
	 * @param String $mailCliente Correo del usuario
	 * @param String $mensajeCliente Contenido del correo
	 * @param String $asuntoMensaje Asunto del mail
	 * @return boolean true si envío correcto, false en caso contrario
	 */
	public function enviarFormularioPHPMailer($nombreCliente, $mailCliente, $mensajeCliente, $asuntoMensaje) {

		// Instancia de una clase en vez de función mail()
		$correo = new PHPMailer();

		// Quien envía el correo
		$correo->setFrom($mailEmisario, $emisario);

		// A quien tiene que responder el correo
		$correo->addReplyTo($mailCliente, $nombreCliente);

		// Destinatario
		$correo->addAddress($miMail);

		// Asunto del mensaje
		$correo->Subject = $asuntoMensaje;

		// Cuerpo del mensaje
		$cadena = 'Nombre: '
			. $nombreCliente
			. '\nMail: '
			. $mailCliente
			. '\nMensaje:\n'
			. $mensajeCliente;
		$cadena = wordwrap($cadena, 70, '\n', true);
		$correo->isHTML(false);
		$correo->Body = $cadena;

		return $correo->send();

	}

	/**
	 * Envía un correo electrónico con la clase mail()
	 *
	 * @param String $para receptor del mensaje
	 * @param String $asunto asunto de cabecera del mensaje
	 * @param String $mensaje cuerpo del mensaje
	 * @return boolean true si se ha enviado, false en caso contrario
	 */
	public function sendMail($para, $asunto, $mensaje) {

		$mensaje = wordwrap($mensaje, 70, "\r\n");
		return mail($para, $asunto, $mensaje);
	}
}
