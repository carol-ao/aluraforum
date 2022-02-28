package com.carol.forum.controller;

import com.carol.forum.controller.dto.TopicoDto;
import com.carol.forum.modelo.Curso;
import com.carol.forum.modelo.Topico;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/topicos")
public class TopicosController {

    @GetMapping
    ResponseEntity<List<TopicoDto>> findAll(){

        Topico topico = new Topico("Título","mensagem", new Curso("Nome do curso","Categoria qualquer"));
        List<Topico> lista = Arrays.asList(topico,topico,topico);
        return ResponseEntity.ok(TopicoDto.converter(lista));
    }
}
