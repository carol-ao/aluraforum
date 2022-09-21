package com.carol.forum.service;

import com.carol.forum.controller.dto.RespostaGetDto;
import com.carol.forum.controller.dto.RespostaPostDto;
import com.carol.forum.exceptions.ResourceNotFoundException;
import com.carol.forum.modelo.Resposta;
import com.carol.forum.repository.RespostaRepository;
import com.carol.forum.repository.TopicoRepository;
import com.carol.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespostaService {

    @Autowired
    RespostaRepository respostaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TopicoRepository topicoRepository;

    public List<RespostaGetDto> findByTopico(Long topicoId) {
        List<Resposta> respostas = respostaRepository.findByTopicoId(topicoId);
        return toRespostaDtos(respostas);
    }

    public RespostaGetDto save(RespostaPostDto respostaPostDto) throws ResourceNotFoundException {
        respostaPostDto.setDataCriacao(LocalDateTime.now());
        Resposta resposta = respostaRepository.save(toResposta(respostaPostDto));
        return RespostaGetDto.builder()
                .id(resposta.getId())
                .dataCriacao(resposta.getDataCriacao())
                .mensagem(resposta.getMensagem())
                .nomeAutor(resposta.getAutor().getNome())
                .build();
    }

    private Resposta toResposta(RespostaPostDto respostaPostDto) throws ResourceNotFoundException {
        return Resposta.builder()
                .autor(usuarioRepository.findById(respostaPostDto.getUsuarioId()).orElseThrow(() -> new ResourceNotFoundException(("Usuário não encontrado"))))
                .dataCriacao(respostaPostDto.getDataCriacao())
                .mensagem(respostaPostDto.getMensagem())
                .topico(topicoRepository.findById(respostaPostDto.getTopicoId()).orElseThrow(() -> new ResourceNotFoundException(("Tópico não encontrado"))))
                .build();
    }

    private List<RespostaGetDto> toRespostaDtos(List<Resposta> respostas) {
        return respostas.stream().map(resposta ->
                RespostaGetDto.builder()
                        .id(resposta.getId())
                        .mensagem(resposta.getMensagem())
                        .dataCriacao(resposta.getDataCriacao())
                        .nomeAutor(resposta.getAutor().getNome())
                        .build()).collect(Collectors.toList());
    }
}
