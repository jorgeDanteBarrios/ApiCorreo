package mx.gda.correo.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import mx.gda.correo.entities.Sender;
import mx.gda.correo.interfaces.SenderRepository;

@Service
public class SenderService {
	
	Logger logger= LoggerFactory.getLogger(SenderService.class);
	
	@Autowired
	private SenderRepository senderRepository;
	
	public Sender saveSender(Sender sender) {
		//default values
		sender.setNcmarca((sender.getNcmarca()==null)?new Long(0):sender.getNcmarca());
		return senderRepository.saveAndFlush(sender);
		
	}
	
	public Sender updateSender(Sender sender) {
		Optional<Sender> tmpSender=null;
		tmpSender=senderRepository.findById(sender.getKsender());
		if(!tmpSender.isPresent()) {
			logger.error("Error en método updateSender: {}","ksender no valido");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ksender no valido");
		}
		//default values
		sender.setNcmarca((sender.getNcmarca()==null)?new Long(0):sender.getNcmarca());
		return senderRepository.saveAndFlush(sender);		
	}
	
	public Optional<Sender> getSenderById(Long ksender){
		return senderRepository.findById(ksender);
	}
	

}
