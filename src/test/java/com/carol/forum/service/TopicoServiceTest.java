package com.carol.forum.service;

import com.carol.forum.controller.dto.TopicoGetDto;
import com.carol.forum.controller.dto.TopicoPostDto;
import com.carol.forum.exceptions.ResourceNotFoundException;
import com.carol.forum.modelo.*;
import com.carol.forum.repository.CursoRepository;
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
public class TopicoServiceTest {

    private TopicoRepository topicoRepository;

    private UsuarioRepository usuarioRepository;

    private CursoRepository cursoRepository;

    private TopicoService topicoService;

    @BeforeAll
    void init() {
        topicoRepository = Mockito.mock(TopicoRepository.class);
        usuarioRepository = Mockito.mock(UsuarioRepository.class);
        cursoRepository = Mockito.mock(CursoRepository.class);
        topicoService = new TopicoService(topicoRepository, usuarioRepository, cursoRepository); // se usar autowired eu nao sei
    }

    @Test
    public void shouldSaveAndReturnSavedTopicWhenSuccessful() throws ResourceNotFoundException {
        TopicoPostDto topicoPostDto = getValidTopicoPostDto();
        Usuario usuario = getValidUsuario1();
        Curso curso = getValidCurso();
        Mockito.when(usuarioRepository.findById(topicoPostDto.getAutorId())).thenReturn(Optional.of(usuario));
        Mockito.when(cursoRepository.findById(topicoPostDto.getCursoId())).thenReturn(Optional.of(curso));
        Mockito.when(topicoRepository.save(Mockito.any())).thenReturn(getValidTopico());

        TopicoGetDto topicoGetDto = topicoService.saveTopico(topicoPostDto);

        Assertions.assertNotNull(topicoGetDto.getId());
        Assertions.assertEquals(topicoPostDto.getAutorId(), topicoGetDto.getAutorId());
        Assertions.assertEquals(topicoPostDto.getMensagem(), topicoGetDto.getMensagem());
        Assertions.assertEquals(topicoPostDto.getTitulo(), topicoGetDto.getTitulo());
        Assertions.assertEquals(topicoPostDto.getCursoId(), topicoGetDto.getCursoId());

    }

    private Topico getValidTopico(){
        return Topico.builder()
                .id(1L)
                .titulo("Unsolvable Calc Problem")
                .mensagem("I can't seem to solve this problem. Can I get some help over here?")
                .dataCriacao(LocalDateTime.now())
                .status(StatusTopico.NAO_SOLUCIONADO)
                .curso(getValidCurso())
                .autor(getValidUsuario1())
                .respostas(new ArrayList<>())
                .build();
    }


    private TopicoPostDto getValidTopicoPostDto(){
        return TopicoPostDto.builder()
                .titulo("Unsolvable Calc Problem")
                .mensagem("I can't seem to solve this problem. Can I get some help over here?")
                .cursoId(1L)
                .autorId(1L)
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

    private Curso getValidCurso(){
        return Curso.builder()
                .id(1L)
                .categoria("Exact Sciences")
                .nome("Calculus")
                .build();
    }
}
