package com.carol.forum.controller.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RespostaPostDto {

    @NotBlank
    @Length(min = 5)
    private String mensagem;
    @NotNull
    private Long topicoId;
    private LocalDateTime dataCriacao;
    @NotNull
    private Long usuarioId;
}
