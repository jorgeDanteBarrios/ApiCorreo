package mx.gda.correo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import mx.gda.correo.entities.Origen;
import mx.gda.correo.interfaces.OrigenRepository;

@Service
public class OrigenService {

	Logger logger= LoggerFactory.getLogger(OrigenService.class);
	@Autowired
	private OrigenRepository origenRepository;
	
	/* Guarda el registro en base */
	public Origen saveOrigen(Origen  origen) {
		Origen salida;
		if(getOrigenBySclave(origen.getSclave())!=null) {
			logger.error("Error en saveOrigen: Clave ya existente {}",origen.getSclave().toUpperCase().trim());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Clave ya existente");
		}
		// default settings
		origen.setSclave(origen.getSclave().toUpperCase().trim());
		origen.setSdescripcion(origen.getSdescripcion().toUpperCase().trim());
		salida=origenRepository.saveAndFlush(origen);
		return salida;
	}
	
	/* Busca el Origen por Sclave */
	public Origen getOrigenBySclave(String sclave) {
		return origenRepository.findBySclave(sclave.toUpperCase().trim());
	}
	
	/* Actualiza el registro */
	public Origen updateOrigen(Origen origen) {
		Origen salida;
		if(!origenRepository.findById(origen.getKorigen()).isPresent()) {
			logger.error("Error en updateOrigen: El korigen  no existe {}",origen.getKorigen());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El korigen  no existe");
		}
		if(getOrigenBySclave(origen.getSclave())!=null) {
			logger.error("Error en updateOrigen: Clave ya existente {}",origen.getSclave().toUpperCase().trim());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Clave ya existente");
		}
		// default settings
		origen.setSclave(origen.getSclave().toUpperCase().trim());
		origen.setSdescripcion(origen.getSdescripcion().toUpperCase().trim());
		salida=origenRepository.saveAndFlush(origen);
		return salida;
	}
	
}
