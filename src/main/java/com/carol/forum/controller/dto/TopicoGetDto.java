package com.carol.forum.controller.dto;

import com.carol.forum.modelo.Resposta;
import com.carol.forum.modelo.StatusTopico;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicoGetDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataDeCriacao;
    private StatusTopico statusTopico;
    private String autor;
    private String curso;
    private List<RespostaDto> respostaDtos;

}
