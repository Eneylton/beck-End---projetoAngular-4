package com.java.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Aluno obj WHERE obj.nome LIKE %:nome% ")
	Page<Aluno> buscar(@Param("nome") String nome, Pageable PageRequest);
}