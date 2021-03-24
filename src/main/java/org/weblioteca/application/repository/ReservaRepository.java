package org.weblioteca.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.weblioteca.application.model.Reserva;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{

}
