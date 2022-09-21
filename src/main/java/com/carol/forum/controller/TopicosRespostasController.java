package com.carol.forum.controller;

import com.carol.forum.controller.dto.RespostaGetDto;
import com.carol.forum.controller.dto.RespostaPostDto;
import com.carol.forum.exceptions.ResourceNotFoundException;
import com.carol.forum.service.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/topicos/respostas")
public class TopicosRespostasController {

    @Autowired
    RespostaService respostaService;

    @PostMapping
    public ResponseEntity<RespostaGetDto> save(@RequestBody RespostaPostDto respostaPostDto) throws ResourceNotFoundException {
        RespostaGetDto respostaGetDto = respostaService.save(respostaPostDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(respostaGetDto);
    }

}
