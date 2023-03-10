package mx.gda.correo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import mx.gda.correo.entities.Motivo;
import mx.gda.correo.entities.Origen;
import mx.gda.correo.objects.Email;
import mx.gda.correo.objects.EnviaResCovid;
import mx.gda.correo.objects.Webhook;
import mx.gda.correo.services.CorreoResultadosService;
import mx.gda.correo.services.CorreoService;
import mx.gda.correo.services.MotivoService;
import mx.gda.correo.services.OrigenService;
import sibModel.GetSendersListSenders;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/ApiCorreo")
public class ApiCorreoController {

	Logger logger= LoggerFactory.getLogger(ApiCorreoController.class);
	
	@Autowired
	private CorreoService correoService;
	@Autowired
	private OrigenService origenService;
	@Autowired
	private MotivoService motivoService;
	@Autowired
	private CorreoResultadosService correoResultadosService;
	
	
	@GetMapping("/getSenders")
	@Operation(description = "Mpara listar los remitentes (senders) registrados", tags = {"Catálogos"})
	public List<GetSendersListSenders> getSenders() {
		logger.info("Se consume método getSenders");
		return correoService.getSenders();
	}
	
	@PostMapping("/sendEmail")
	@Operation(description = "Método para enviar un correo electrónico", tags = {"Envio Correo"})
	public ResponseEntity<Boolean> sendEmail(@Valid @RequestBody Email email) {
		logger.info("Se consume método sendEmail");
		logger.debug("{}",email.toString());
		return ResponseEntity.ok(correoService.sendEmail(email));
	}
	
	@PostMapping("/sendEmail/resultados/covid")
	@Operation(description = "Método para enviar los resultados de una prueba de covid por correo", tags = {"Envio Correo"})
	public ResponseEntity<Boolean> sendResultadoCovid(@Valid @RequestBody EnviaResCovid enviaResCovid) {
		logger.info("Se consume método sendEmail");
		logger.debug(enviaResCovid.toString());
		return ResponseEntity.ok(correoResultadosService.sendResultadoCovid(enviaResCovid));
	}
	
	@PostMapping("/webhook")
	@Operation(description = "Método para registrar la trazabilidad de un correo electrónico", tags = {"Trazabilidad"})
	public ResponseEntity<Boolean> saveEmailInfo(@RequestBody Webhook webhook) {
		logger.info("Se consume método saveEmailInfo-webhook");
		logger.debug(webhook.toString());
		return ResponseEntity.ok(correoService.registerTraceability(webhook));
	}
	
	@PostMapping("/origen")
	@Operation(description = "Método para guardar un registro de tipo origen", tags = {"Catálogos"})
	public ResponseEntity<Origen> saveOrigen(@Valid @RequestBody Origen origen){
		logger.info("Se consume método getEmailInfo");
		logger.debug(origen.toString());
		return ResponseEntity.ok(origenService.saveOrigen(origen));
	}
	
	@PutMapping("/origen")
	@Operation(description = "Método para actualizar un registro de tipo origen", tags = {"Catálogos"})
	public ResponseEntity<Origen> updateOrigen(@Valid @RequestBody Origen origen){
		logger.info("Se consume método updateOrigen");
		logger.debug(origen.toString());
		return ResponseEntity.ok(origenService.updateOrigen(origen));
	}
	
	@PostMapping("/motivo")
	@Operation(description = "Método para guardar un registro de tipo motivo (desencadenador del envio de un correo)", tags = {"Catálogos"})
	public ResponseEntity<Motivo> saveMotivo(@Valid @RequestBody Motivo motivo){
		logger.info("Se consume método saveMotivo");
		logger.debug(motivo.toString());
		return ResponseEntity.ok(motivoService.saveMotivo(motivo));
	}
	
	@PutMapping("/motivo")
	@Operation(description = "Método para actualizar un registro de tipo motivo (desencadenador del envio de un correo)", tags = {"Catálogos"})
	public ResponseEntity<Motivo> updateMotivo(@Valid @RequestBody Motivo motivo){
		logger.info("Se consume método updateMotivo");
		logger.debug(motivo.toString());
		return ResponseEntity.ok(motivoService.updateMotivo(motivo));
	}
	
	/* Manejo de Errores (@Valid) */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    logger.error(" Error al validar el request : {}",errors);
	    return errors;
	}
}
