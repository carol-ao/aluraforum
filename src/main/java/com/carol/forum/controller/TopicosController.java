package com.carol.forum.controller;

import com.carol.forum.controller.dto.TopicoDto;
import com.carol.forum.modelo.Topico;
import com.carol.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    TopicoRepository topicoRepository;

    @GetMapping
    ResponseEntity<List<TopicoDto>> findAll(){

        List<Topico> topicos = topicoRepository.findAll();
        return ResponseEntity.ok(TopicoDto.converter(topicos));
    }

    @GetMapping(params = {"nomeDoCurso"})
    ResponseEntity<List<TopicoDto>> findByCursoNome(@RequestParam("nomeDoCurso") String nomeDoCurso){

        List<Topico> topicos = topicoRepository.findByCursoNome(nomeDoCurso);
        return ResponseEntity.ok(TopicoDto.converter(topicos));
    }

    @GetMapping(params = {"categoriaDoCurso"})
    ResponseEntity<List<TopicoDto>> findByCategoriaDoCurso(@RequestParam("categoriaDoCurso") String categoriaDoCurso){

        List<Topico> topicos = topicoRepository.findByCategoriaDoCurso(categoriaDoCurso);
        return ResponseEntity.ok(TopicoDto.converter(topicos));
    }
}
