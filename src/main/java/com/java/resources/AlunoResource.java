package com.java.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.java.dto.AlunoDTO;
import com.java.model.Aluno;
import com.java.services.AlunoService;
import com.java.util.exceptions.URL;

@RestController
@RequestMapping(value="/alunos")
public class AlunoResource {
	
	@Autowired
	private AlunoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Aluno> find(@PathVariable Long id) {
		Aluno obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Aluno>> findAll() {
		List<Aluno> list = service.findAll();
	/*	List<AlunoDTO> listDto = list.stream().map(obj -> new AlunoDTO(obj)).collect(Collectors.toList());  */
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/alunoPaginacao", method=RequestMethod.GET)
	public ResponseEntity<Page<AlunoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="7") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="id") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		Page<Aluno> list = service.findPage(nomeDecoded,page, linesPerPage, orderBy, direction);
		Page<AlunoDTO> listDto = list.map(obj -> new AlunoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody AlunoDTO objDto) {
        Aluno obj = service.fromDTO(objDto);
		obj = service.salvar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Aluno obj, @PathVariable Long id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
