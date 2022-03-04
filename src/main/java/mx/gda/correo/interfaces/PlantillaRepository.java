package mx.gda.correo.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.gda.correo.entities.Plantilla;

public interface PlantillaRepository extends JpaRepository<Plantilla,Long>{

	public Plantilla findByNcmarcaAndSclave(Long ncmarca,String sclave);
	
}
