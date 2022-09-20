package com.carol.forum.repository;

import com.carol.forum.modelo.Curso;
import com.carol.forum.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    List<Topico> findByCursoNome(String nomeDoCurso);

    @Query("SELECT t FROM Topico t where t.curso.categoria = :categoriaDoCurso")
    List<Topico> findByCategoriaDoCurso(@Param("categoriaDoCurso") String categoriaDoCurso);

    Optional<Topico> findByTituloAndMensagemAndCurso(String titulo, String mensagem, Curso curso);

}
