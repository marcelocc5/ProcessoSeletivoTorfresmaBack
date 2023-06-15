package br.com.torfresma.ProcessoSeletivo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.torfresma.ProcessoSeletivo.model.Usuario;
import br.com.torfresma.ProcessoSeletivo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}

	public ResponseEntity<Usuario> getUsuarioById(Long id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		if (optionalUsuario.isPresent()) {
			return ResponseEntity.ok(optionalUsuario.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}

	public ResponseEntity<Usuario> save(Usuario usuario, UriComponentsBuilder uriBuilder) {
		usuarioRepository.save(usuario);
		
		var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(usuario);
	}

	public ResponseEntity<Usuario> atualizar(Long id, Usuario usuarioAtualizado) {

		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		if (optionalUsuario.isPresent()){
			Usuario usuario = optionalUsuario.get();
			usuario.setNome(usuarioAtualizado.getNome());
			usuario.setEmail(usuarioAtualizado.getEmail());
			usuario.setSenha(usuarioAtualizado.getSenha());
			return ResponseEntity.ok(usuarioRepository.save(usuario));
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	public ResponseEntity<Void> deletar(Long id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		if (optionalUsuario.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	
}
