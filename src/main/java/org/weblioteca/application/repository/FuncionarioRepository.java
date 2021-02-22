package org.weblioteca.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.weblioteca.application.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
