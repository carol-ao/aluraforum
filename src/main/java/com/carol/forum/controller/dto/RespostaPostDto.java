package com.carol.forum.controller.dto;

import com.carol.forum.modelo.Topico;
import com.carol.forum.modelo.Usuario;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RespostaPostDto {

    private Long id;
    private String mensagem;
    private Long topicoId;
    private LocalDateTime dataCriacao;
    private Long usuarioId;
}
