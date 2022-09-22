package com.carol.forum.controller.dto;

import com.carol.forum.modelo.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RespostaGetDto {
    Long id;
    String mensagem;
    LocalDateTime dataCriacao;
    Long usuarioId;
    Long topicoId;
}
