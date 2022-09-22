package com.carol.forum.service;

import com.carol.forum.controller.dto.RespostaGetDto;
import com.carol.forum.controller.dto.TopicoGetDto;
import com.carol.forum.controller.dto.TopicoPostDto;
import com.carol.forum.exceptions.ResourceNotFoundException;
import com.carol.forum.modelo.*;
import com.carol.forum.repository.CursoRepository;
import com.carol.forum.repository.TopicoRepository;
import com.carol.forum.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicoService {

    private final TopicoRepository topicoRepository;

    private final UsuarioRepository usuarioRepository;

    private final CursoRepository cursoRepository;

    public TopicoGetDto saveTopico(TopicoPostDto topicoPostDto) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(topicoPostDto.getAutorId()).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontratdo"));
        Curso curso = cursoRepository.findById( topicoPostDto.getCursoId()).orElseThrow(() -> new ResourceNotFoundException("Curso não encontratdo"));
        Topico topico = toEntity(topicoPostDto, usuario, curso);
        topico = topicoRepository.save(topico);
        return toGetDto(topico);
    }

    public TopicoGetDto findTopicoById(Long id) throws ResourceNotFoundException {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado."));
        return toGetDto(topico);
    }

    public List<TopicoGetDto> findAllTopicos() {
        List<Topico> topicos = topicoRepository.findAll();
        return toGetDtos(topicos);
    }

    public void deleteTopico(Long id) throws ResourceNotFoundException {
        try {
            topicoRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Tópico não encontrado.");
        }
    }

    public static TopicoGetDto toGetDto(Topico topico) {
        return TopicoGetDto.builder()
                .id(topico.getId())
                .autorId(topico.getAutor().getId())
                .dataDeCriacao(topico.getDataCriacao())
                .titulo(topico.getTitulo())
                .mensagem(topico.getMensagem())
                .cursoId(topico.getCurso().getId())
                .statusTopico(topico.getStatus())
                .respostaDtos(toRespostaDtos(topico.getRespostas()))
                .build();
    }

    public static List<RespostaGetDto> toRespostaDtos(List<Resposta> respostas) {
        return respostas != null ? respostas.stream().map(resposta -> RespostaGetDto.builder()
                .id(resposta.getId())
                .dataCriacao(resposta.getDataCriacao())
                .usuarioId(resposta.getAutor().getId())
                .mensagem(resposta.getMensagem())
                .build()).collect(Collectors.toList()) : null;
    }

    public static Topico toEntity(TopicoPostDto topicoPostDto,Usuario usuario, Curso curso) {

        return Topico.builder()
                .autor(usuario)
                .dataCriacao(LocalDateTime.now())
                .titulo(topicoPostDto.getTitulo())
                .mensagem(topicoPostDto.getMensagem())
                .curso(curso)
                .status(StatusTopico.NAO_RESPONDIDO)
                .build();
    }

    public List<TopicoGetDto> toGetDtos(List<Topico> topicos) {
        return topicos.stream().map(TopicoService::toGetDto).collect(Collectors.toList());
    }


}
