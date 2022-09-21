package com.carol.forum.repository;

import com.carol.forum.modelo.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta,Long> {
    List<Resposta> findByTopicoId(Long topicoId);
}
