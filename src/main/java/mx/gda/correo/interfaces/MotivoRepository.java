package mx.gda.correo.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.gda.correo.entities.Motivo;

public interface MotivoRepository extends JpaRepository<Motivo, Long> {

}
