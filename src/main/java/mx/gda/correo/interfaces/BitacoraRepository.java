package mx.gda.correo.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.gda.correo.entities.Bitacora;

public interface BitacoraRepository extends JpaRepository<Bitacora, String> {

}
