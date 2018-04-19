package com.java.dto;

import java.io.Serializable;
import java.util.List;

import com.java.model.Aluno;
import com.java.model.Contato;

public class AlunoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;
	
	private String endereco;

	private List<Contato> contatos;

	public AlunoDTO() {
		super();
	}

	public AlunoDTO(Aluno obj) {
		id = obj.getId();
		nome = obj.getNome();
		endereco = obj.getEndereco();
		contatos = obj.getContatos();

	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	

}
