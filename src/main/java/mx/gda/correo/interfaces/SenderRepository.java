package mx.gda.correo.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.gda.correo.entities.Sender;

public interface SenderRepository extends JpaRepository<Sender, Long> {

}
