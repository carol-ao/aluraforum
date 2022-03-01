package com.carol.forum.controller;

import com.carol.forum.controller.dto.TopicoDto;
import com.carol.forum.modelo.Topico;
import com.carol.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
