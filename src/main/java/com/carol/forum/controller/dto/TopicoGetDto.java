package com.carol.forum.controller.dto;

import com.carol.forum.modelo.StatusTopico;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class TopicoGetDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataDeCriacao;
    private StatusTopico statusTopico;
    private String autor;
    private String curso;


}
