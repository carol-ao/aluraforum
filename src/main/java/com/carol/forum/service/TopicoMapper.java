package com.carol.forum.service;

import com.carol.forum.controller.dto.RespostaGetDto;
import com.carol.forum.controller.dto.TopicoGetDto;
import com.carol.forum.controller.dto.TopicoPostDto;
import com.carol.forum.exceptions.ResourceNotFoundException;
import com.carol.forum.modelo.Resposta;
import com.carol.forum.modelo.StatusTopico;
import com.carol.forum.modelo.Topico;
import com.carol.forum.repository.CursoRepository;
import com.carol.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TopicoMapper {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CursoRepository cursoRepository;


    public TopicoGetDto toGetDto(Topico topico) {
        return TopicoGetDto.builder()
                .id(topico.getId())
                .autor(topico.getAutor().getNome())
                .dataDeCriacao(topico.getDataCriacao())
                .titulo(topico.getTitulo())
                .mensagem(topico.getMensagem())
                .curso(topico.getCurso().getNome())
                .statusTopico(topico.getStatus())
                .respostaDtos(toRespostaDtos(topico.getRespostas()))
                .build();
    }

    private List<RespostaGetDto> toRespostaDtos(List<Resposta> respostas) {
        return respostas != null ? respostas.stream().map(resposta -> RespostaGetDto.builder()
        .id(resposta.getId())
        .dataCriacao(resposta.getDataCriacao())
        .nomeAutor(resposta.getAutor().getNome())
        .mensagem(resposta.getMensagem())
        .build()).collect(Collectors.toList()) : null;
    }

    public Topico toEntity(TopicoPostDto topicoPostDto) throws ResourceNotFoundException {

        return Topico.builder()
                .autor(usuarioRepository.findById(topicoPostDto.getAutorId()).
                        orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado.")
                ))
                .dataCriacao(LocalDateTime.now())
                .titulo(topicoPostDto.getTitulo())
                .mensagem(topicoPostDto.getMensagem())
                .curso(cursoRepository.findById(topicoPostDto.getCursoId()).
                        orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado.")))
                .status(StatusTopico.NAO_RESPONDIDO)
                .build();
    }

    public List<TopicoGetDto> toGetDtos(List<Topico> topicos) {
        return topicos.stream().map(this::toGetDto).collect(Collectors.toList());
    }
}
