package com.carol.forum.controller.dto;
import lombok.Data;

@Data
public class TopicoPostDto {

    private String titulo;
    private String mensagem;
    private Long cursoId;
    private Long autorId;

}
