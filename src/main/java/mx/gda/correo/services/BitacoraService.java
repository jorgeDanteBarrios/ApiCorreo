package mx.gda.correo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gda.correo.entities.Bitacora;
import mx.gda.correo.interfaces.BitacoraRepository;

@Service
public class BitacoraService {

	Logger logger= LoggerFactory.getLogger(BitacoraService.class);
	
	@Autowired
	private BitacoraRepository bitacoraRepository;
	
	/* Guarda la bitacora en base */
	public Bitacora saveBitacora(Bitacora bitacora) {		
		return bitacoraRepository.saveAndFlush(bitacora);		
	}
	
}
