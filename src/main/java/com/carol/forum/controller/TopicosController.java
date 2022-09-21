package com.carol.forum.controller;

import com.carol.forum.TopicoService;
import com.carol.forum.controller.dto.TopicoGetDto;
import com.carol.forum.controller.dto.TopicoPostDto;
import com.carol.forum.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    TopicoService topicoService;

    @GetMapping
    ResponseEntity<List<TopicoGetDto>> findAll() {
        return ResponseEntity.ok().body(topicoService.findAllTopicos());

    }

    @GetMapping("{id}")
    ResponseEntity<TopicoGetDto> findById(@PathVariable Long id) throws ResourceNotFoundException {

        TopicoGetDto topicoGetDto = topicoService.findTopicoById(id);
        return ResponseEntity.ok().body(topicoGetDto);
    }

    @PostMapping
    ResponseEntity<TopicoGetDto> save(@RequestBody @Valid TopicoPostDto topicoPostDto, UriComponentsBuilder uriComponentsBuilder) throws ResourceNotFoundException {
        TopicoGetDto topicoGetDto = topicoService.saveTopico(topicoPostDto);
        URI uri = uriComponentsBuilder.path("/topicos/{id").buildAndExpand(topicoGetDto.getId()).toUri();
        return ResponseEntity.created(uri).body(topicoGetDto);
    }

    @DeleteMapping({"id"})
    ResponseEntity<Void> delete(@PathVariable Long id) throws ResourceNotFoundException {
        topicoService.deleteTopico(id);
        return ResponseEntity.ok().build();
    }
}
