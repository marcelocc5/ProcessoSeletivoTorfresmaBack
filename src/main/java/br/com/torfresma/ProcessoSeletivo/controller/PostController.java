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

import br.com.torfresma.ProcessoSeletivo.model.Post;
import br.com.torfresma.ProcessoSeletivo.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
@Tag(name = "Posts", description = "Endpoints para gerenciamento de posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping
	@Operation(summary = "Lista todos os posts cadastrados")
	public List<Post> findAll(){
		return postService.findAll();
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Busca um post atraves do seu ID")
	public ResponseEntity<Post> getPostById(@PathVariable Long id){
		return postService.getPostById(id);
	}
	@PostMapping
	@Transactional
	@Operation(summary = "Cadastra um novo post")
	public ResponseEntity<Post> cadastrar (@RequestBody @Valid Post post, UriComponentsBuilder uriBuilder){
		return postService.save(post, uriBuilder);
	}
	
	@PutMapping("/{id}")
	@Transactional
	@Operation(summary = "Atualiza um post existente")
	public ResponseEntity<Post> atualizar (@PathVariable Long id, @RequestBody @Valid Post postAtualizado){
		return postService.atualizar(id, postAtualizado);
	}
	@Operation(summary = "Deleta um post com o ID fornecido")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar (@PathVariable Long id){
		return postService.deletar(id);
	}
}
