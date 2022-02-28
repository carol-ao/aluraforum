package com.carol.forum.controller.dto;

import com.carol.forum.modelo.Topico;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TopicoDto {

    private Long id;
    private String titulo;
    private String autor;
    private LocalDateTime dataDeCriacao;

    public TopicoDto(Topico topico){
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.autor = (topico.getAutor() != null) ? topico.getAutor().getNome() : null ;
        this.dataDeCriacao = topico.getDataCriacao();
    }

    public static List<TopicoDto> converter(List<Topico> lista) {
        return lista.stream().map(TopicoDto::new).collect(Collectors.toList());
    }
}
