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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mx.gda.correo.entities.Motivo;
import mx.gda.correo.entities.Origen;
import mx.gda.correo.objects.Email;
import mx.gda.correo.objects.Webhook;
import mx.gda.correo.services.CorreoService;
import mx.gda.correo.services.MotivoService;
import mx.gda.correo.services.OrigenService;
import sibModel.GetSendersListSenders;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/ApiCorreo")
@Api
public class ApiCorreoController {

	Logger logger= LoggerFactory.getLogger(ApiCorreoController.class);
	
	@Autowired
	private CorreoService correoService;
	@Autowired
	private OrigenService origenService;
	@Autowired
	private MotivoService motivoService;
	
	
	@GetMapping("/getSenders")
	@ApiOperation(value = "Listado de remitentes (senders) registrados",notes = "Método para listar los remitentes (senders) / o registrados, por default el valor 1 usa el mismo correo que la cuenta registrada")
	public List<GetSendersListSenders> getSenders() {
		return correoService.getSenders();
	}
	
	@PostMapping("/sendEmail")
	//@Transactional(timeout = 60)
	@ApiOperation(value = "Envia Correo",notes = "Método para enviar un correo electrónico")
	public Boolean sendEmail(@Valid @RequestBody Email email) {
		return correoService.sendEmail(email);
	}	
	
	@PostMapping("/webhook")
	@ApiOperation(value = "Registra trazabilidad",notes = "Método para registrar la trazabilidad de un correo electrónico")
	public ResponseEntity<Boolean> getEmailInfo(@RequestBody Webhook webhook) {
		return ResponseEntity.ok(correoService.registerTraceability(webhook));
	}
	
	@PostMapping("/origen")
	@ApiOperation(value = "Guarda origen",notes = "Método para guardar un registro de tipo origen al catálogo")
	public ResponseEntity<Origen> saveOrigen(@RequestBody @Valid Origen origen){
		return ResponseEntity.ok(origenService.saveOrigen(origen));
	}
	
	@PutMapping("/origen")
	@ApiOperation(value = "Actualiza origen",notes = "Método para actualizar un registro de tipo origen al catálogo")
	public ResponseEntity<Origen> updateOrigen(@RequestBody @Valid Origen origen){
		return ResponseEntity.ok(origenService.updateOrigen(origen));
	}
	
	@PostMapping("/motivo")
	@ApiOperation(value = "Guarda motivo",notes = "Método para actualizar un registro de tipo motivo al catálogo")
	public ResponseEntity<Motivo> saveMotivo(@RequestBody @Valid Motivo motivo){
		return ResponseEntity.ok(motivoService.saveMotivo(motivo));
	}
	
	@PutMapping("/motivo")
	@ApiOperation(value = "Actualiza motivo",notes = "Método para actualizar un registro de tipo motivo al catálogo")
	public ResponseEntity<Motivo> updateMotivo(@RequestBody @Valid Motivo motivo){
		return ResponseEntity.ok(motivoService.saveMotivo(motivo));
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
