package com.carol.forum.service;

import com.carol.forum.controller.dto.RespostaGetDto;
import com.carol.forum.controller.dto.RespostaPostDto;
import com.carol.forum.exceptions.ResourceNotFoundException;
import com.carol.forum.modelo.Resposta;
import com.carol.forum.modelo.Topico;
import com.carol.forum.modelo.Usuario;
import com.carol.forum.repository.RespostaRepository;
import com.carol.forum.repository.TopicoRepository;
import com.carol.forum.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RespostaService {

    private final RespostaRepository respostaRepository;

    private final UsuarioRepository usuarioRepository;

    private final TopicoRepository topicoRepository;

    public RespostaGetDto findById(Long id) throws ResourceNotFoundException {
        Resposta resposta = respostaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resposta não encontrada."));
        return toRespostaGetDto(resposta);
    }

    public RespostaGetDto save(RespostaPostDto respostaPostDto) throws ResourceNotFoundException {
        respostaPostDto.setDataCriacao(LocalDateTime.now());
        Usuario usuario = usuarioRepository.findById(respostaPostDto.getUsuarioId()).orElseThrow(() -> new ResourceNotFoundException(("Usuário não encontrado")));
        Topico topico = topicoRepository.findById(respostaPostDto.getTopicoId()).orElseThrow(() -> new ResourceNotFoundException(("Tópico não encontrado")));
        Resposta resposta = respostaRepository.save(toResposta(respostaPostDto, usuario, topico));
        return toRespostaGetDto(resposta);
    }

    public static RespostaGetDto toRespostaGetDto(Resposta resposta) {
        return RespostaGetDto.builder()
                .id(resposta.getId())
                .dataCriacao(resposta.getDataCriacao())
                .mensagem(resposta.getMensagem())
                .usuarioId(resposta.getAutor().getId())
                .topicoId(resposta.getTopico().getId())
                .build();
    }

    public static Resposta toResposta(RespostaPostDto respostaPostDto, Usuario usuario, Topico topico) {
        return Resposta.builder()
                .autor(usuario)
                .dataCriacao(respostaPostDto.getDataCriacao())
                .mensagem(respostaPostDto.getMensagem())
                .topico(topico)
                .build();
    }

}
