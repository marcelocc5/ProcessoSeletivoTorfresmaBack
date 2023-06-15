package br.com.torfresma.ProcessoSeletivo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.torfresma.ProcessoSeletivo.model.Post;
import br.com.torfresma.ProcessoSeletivo.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public List<Post> findAll(){
		return postRepository.findAll();
	}
	
	
	public ResponseEntity<Post> getPostById(Long id) {
		Optional<Post> optionalPost = postRepository.findById(id);
		if(optionalPost.isPresent()) {
			return ResponseEntity.ok(optionalPost.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}


	public ResponseEntity<Post> save(Post post, UriComponentsBuilder uriBuilder) {
		postRepository.save(post);
		var uri = uriBuilder.path("/posts/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).body(post);
	}


	public ResponseEntity<Post> atualizar(Long id, Post postAtualizado) {
		
		Optional<Post> optionalPost = postRepository.findById(id);
		if(optionalPost.isPresent()) {
			Post post = optionalPost.get();
			post.setAutor(postAtualizado.getAutor());
			post.setConteudo(postAtualizado.getConteudo());
			post.setDataCriacao(postAtualizado.getDataCriacao());
			post.setTitulo(postAtualizado.getTitulo());
			return ResponseEntity.ok(postRepository.save(post));
		}else {
			return ResponseEntity.notFound().build();
		}
	}


	public ResponseEntity<Void> deletar(Long id) {
		Optional<Post> optionalPost = postRepository.findById(id);
		if (optionalPost.isPresent()) {
			postRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	
	}
}
