package br.com.torfresma.ProcessoSeletivo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.torfresma.ProcessoSeletivo.model.Usuario;
import br.com.torfresma.ProcessoSeletivo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuarios", description = "Endpoints para gerenciar usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	@Operation(summary = "Lista todos os usuarios presentes no banco de dados")
	public List<Usuario> findAll(){
		return usuarioService.findAll();
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Procura no banco de dados um usuario com o ID fornecido")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id){
		return usuarioService.getUsuarioById(id);
	}
	@PostMapping
	@Transactional
	@Operation(summary = "Cadastra um novo usuario no banco de dados")
	public ResponseEntity<Usuario> cadastrar (@RequestBody @Valid Usuario usuario, UriComponentsBuilder uriBuilder){
		return usuarioService.save(usuario, uriBuilder);
	}
	
	@PutMapping("/{id}")
	@Transactional
	@Operation(summary = "Atualiza um usuario no banco de dados apartir do ID informado")
	public ResponseEntity<Usuario> atualizar (@PathVariable Long id, @RequestBody @Valid Usuario usuarioAtualizado){
		return usuarioService.atualizar(id, usuarioAtualizado);
	}
	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um usuario do banco dados com o ID fornecido")
	public ResponseEntity<Void> deletar (@PathVariable Long id){
		return usuarioService.deletar(id);
	}
}
