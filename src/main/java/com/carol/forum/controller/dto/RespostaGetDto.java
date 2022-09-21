package com.carol.forum.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespostaGetDto {
    Long id;
    String mensagem;
    LocalDateTime dataCriacao;
    String nomeAutor;
}
