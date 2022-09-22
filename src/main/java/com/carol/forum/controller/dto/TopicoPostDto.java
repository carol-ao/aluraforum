package com.carol.forum.controller.dto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
