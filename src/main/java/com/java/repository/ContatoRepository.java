package com.java.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Contato obj WHERE obj.email LIKE %:email% ")
	Page<Contato> buscar(@Param("email") String nome, Pageable PageRequest);
}