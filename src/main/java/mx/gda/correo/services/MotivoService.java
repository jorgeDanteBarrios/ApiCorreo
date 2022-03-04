package mx.gda.correo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import mx.gda.correo.entities.Motivo;
import mx.gda.correo.interfaces.MotivoRepository;

@Service
public class MotivoService {

	Logger logger= LoggerFactory.getLogger(MotivoService.class);
	
	@Autowired
	private MotivoRepository motivoRepository;
	
	/* Guarda el registro en base */
	public Motivo saveMotivo(Motivo motivo) {
		// default settings
		motivo.setSdescripcion(motivo.getSdescripcion().toUpperCase().trim());
		return motivoRepository.saveAndFlush(motivo);
	}
	
	/* Actualiza el registro */
	public Motivo updateMotivo(Motivo motivo) {
		if(!motivoRepository.findById(motivo.getKmotivo()).isPresent()) {
			logger.error("Error en updateMotivo: El kmotivo  no existe {}",motivo.getKmotivo());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El kmotivo  no existe");
		}
		// default settings
		motivo.setSdescripcion(motivo.getSdescripcion().toUpperCase().trim());
		return motivoRepository.saveAndFlush(motivo);
	}
}
