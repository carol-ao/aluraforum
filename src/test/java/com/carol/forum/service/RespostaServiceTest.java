package com.carol.forum.service;

import com.carol.forum.controller.dto.RespostaGetDto;
import com.carol.forum.controller.dto.RespostaPostDto;
import com.carol.forum.exceptions.ResourceNotFoundException;
import com.carol.forum.modelo.*;
import com.carol.forum.repository.RespostaRepository;
import com.carol.forum.repository.TopicoRepository;
import com.carol.forum.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RespostaServiceTest {

    private UsuarioRepository usuarioRepository;

    private TopicoRepository topicoRepository;

    private RespostaRepository respostaRepository;

    private RespostaService respostaService;

    @BeforeAll
    void init(){
        usuarioRepository = Mockito.mock(UsuarioRepository.class);
        topicoRepository = Mockito.mock(TopicoRepository.class);
        respostaRepository = Mockito.mock(RespostaRepository.class);
        respostaService = new RespostaService(respostaRepository, usuarioRepository, topicoRepository);
    }

    @Test
    public void shouldReturnSavedRespostaWhenSuccessful() throws ResourceNotFoundException {

        RespostaPostDto respostaPostDto = getValidRespostaPostDto();
        Mockito.when(usuarioRepository.findById(respostaPostDto.getUsuarioId())).thenReturn(Optional.of(getValidUsuario2()));
        Mockito.when(topicoRepository.findById(respostaPostDto.getTopicoId())).thenReturn(Optional.of(getValidTopico()));
        Mockito.when(respostaRepository.save(Mockito.any(Resposta.class))).thenReturn(getValidResposta());
        RespostaGetDto respostaGetDto = respostaService.save(respostaPostDto);

        Assertions.assertNotNull(respostaGetDto.getId());
        Assertions.assertEquals(respostaPostDto.getMensagem(), respostaGetDto.getMensagem());
        Assertions.assertEquals(respostaPostDto.getUsuarioId(), respostaGetDto.getUsuarioId());
        Assertions.assertEquals(respostaPostDto.getTopicoId(), respostaGetDto.getTopicoId());
        Assertions.assertNotNull(respostaGetDto.getDataCriacao());
    }

    @Test
    public void  shouldThrowResourceNotFoundExceptionWhenUserIsInvalid() {
        Long invalidUserId = 200L;
        RespostaPostDto respostaPostDto = getValidRespostaPostDto();
        respostaPostDto.setTopicoId(invalidUserId);

        Mockito.when(usuarioRepository.findById(respostaPostDto.getUsuarioId())).thenReturn(Optional.empty());
        Mockito.when(topicoRepository.findById(respostaPostDto.getTopicoId())).thenReturn(Optional.of(getValidTopico()));
        Mockito.when(respostaRepository.save(Mockito.any(Resposta.class))).thenReturn(getValidResposta());

        Throwable exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> respostaService.save(respostaPostDto));
        Assertions.assertEquals("Usuário não encontrado", exception.getMessage());
    }

    @Test
    public void  shouldThrowResourceNotFoundExceptionWhenTopicIsInvalid() {
        Long invalidUserId = 200L;
        RespostaPostDto respostaPostDto = getValidRespostaPostDto();
        respostaPostDto.setTopicoId(invalidUserId);

        Mockito.when(usuarioRepository.findById(respostaPostDto.getUsuarioId())).thenReturn(Optional.of(getValidUsuario2()));
        Mockito.when(topicoRepository.findById(respostaPostDto.getTopicoId())).thenReturn(Optional.empty());
        Mockito.when(respostaRepository.save(Mockito.any(Resposta.class))).thenReturn(getValidResposta());

        Throwable exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> respostaService.save(respostaPostDto));
        Assertions.assertEquals("Tópico não encontrado", exception.getMessage());
    }

    private Resposta getValidResposta() {
        return Resposta.builder()
                .id(1L)
                .topico(getValidTopico())
                .mensagem("DM me and I'll send you the answer to your question.")
                .dataCriacao(LocalDateTime.now())
                .autor(getValidUsuario2())
                .build();
    }

    private Curso getValidCurso(){
        return Curso.builder()
                .id(1L)
                .categoria("Exact Sciences")
                .nome("Calculus")
                .build();
    }

    private Usuario getValidUsuario1(){
        return Usuario.builder()
                .id(1L)
                .email("some.guy@gmail.com")
                .senha("123")
                .nome("Guy Richards")
                .build();
    }

    private Usuario getValidUsuario2(){
        return Usuario.builder()
                .id(2L)
                .email("another.guy@gmail.com")
                .senha("456")
                .nome("Guy Peters")
                .build();
    }

    private Topico getValidTopico(){
        return Topico.builder()
                .id(1L)
                .autor(getValidUsuario1())
                .mensagem("I can't seem to solve this problem. Can I get some help over here?")
                .dataCriacao(LocalDateTime.now())
                .status(StatusTopico.NAO_SOLUCIONADO)
                .titulo("Unsolvable Calc Problem")
                .curso(getValidCurso())
                .respostas(new ArrayList<>())
                .build();
    }

    private RespostaPostDto getValidRespostaPostDto(){
        return RespostaPostDto.builder()
                .dataCriacao(LocalDateTime.now())
                .mensagem("DM me and I'll send you the answer to your question.")
                .topicoId(1L)
                .usuarioId(2L).build();
    }
}
