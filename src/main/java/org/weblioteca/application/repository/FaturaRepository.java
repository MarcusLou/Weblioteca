package org.weblioteca.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.weblioteca.application.model.Fatura;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {
	
}
