package mx.gda.correo.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.gda.correo.entities.Origen;

public interface OrigenRepository extends JpaRepository<Origen, Long> {

	public Origen findBySclave(String sclave);
	
}
