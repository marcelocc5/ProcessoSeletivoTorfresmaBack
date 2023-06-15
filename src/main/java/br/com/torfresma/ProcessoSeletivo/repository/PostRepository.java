package br.com.torfresma.ProcessoSeletivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.torfresma.ProcessoSeletivo.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
