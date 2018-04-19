package com.java.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.java.dto.ContatoDTO;
import com.java.model.Contato;
import com.java.repository.ContatoRepository;
import com.java.util.exceptions.DataIntegrityException;
import com.java.util.exceptions.ObjectNotFoundException;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository repo;

	public Contato find(Long id) {
		Contato obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Contato.class.getName());
		}
		return obj;
	}

	public Contato insert(Contato obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Contato update(Contato obj) {
		Contato newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	
	public List<Contato> findAll() {
		return repo.findAll();
	}

	/*public Page<Contato> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}*/
	
	public Page<Contato> findPage(String nome,Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.buscar(nome,pageRequest);
	}

	public Contato fromDTO(ContatoDTO objDto) {
		return new Contato(objDto.getId(), objDto.getEmail(), objDto.getTelefone(),objDto.getAluno());
	}

	private void updateData(Contato newObj, Contato obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setTelefone(obj.getTelefone());
		newObj.setAluno(obj.getAluno());
		
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Contato que possui produtos");
		}
	}
	

}
