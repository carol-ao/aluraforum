package com.carol.forum.controller.dto;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class TopicoPostDto {

    @NotBlank
    @Length(min = 5)
    private String titulo;
    @NotBlank
    @Length(min = 5)
    private String mensagem;
    private Long cursoId;
    private Long autorId;

}
