package mx.gda.correo.sequences;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomIDGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		SimpleDateFormat dt = new SimpleDateFormat("ddMMyyyy_HHmmssSSSSS"); 		
		return dt.format(new Date());	
	}

}
