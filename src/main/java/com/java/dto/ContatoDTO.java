package com.java.dto;

import java.io.Serializable;

import com.java.model.Aluno;
import com.java.model.Contato;

public class ContatoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String email;

	private String telefone ;
	
	private Aluno aluno;

	public ContatoDTO() {
		super();
	}

	public ContatoDTO(Contato obj) {
		id = obj.getId();
		email = obj.getEmail();
		telefone = obj.getTelefone();
		aluno = obj.getAluno();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	
}
