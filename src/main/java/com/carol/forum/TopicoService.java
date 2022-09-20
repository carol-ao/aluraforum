package com.carol.forum;

import com.carol.forum.controller.dto.TopicoGetDto;
import com.carol.forum.controller.dto.TopicoPostDto;
import com.carol.forum.exceptions.ResourceNotFoundException;
import com.carol.forum.modelo.Topico;
import com.carol.forum.repository.TopicoRepository;
import com.carol.forum.service.TopicoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoMapper topicoMapper;

    public TopicoGetDto saveTopico(TopicoPostDto topicoPostDto) throws ResourceNotFoundException {
        Topico topico = topicoMapper.toEntity(topicoPostDto);
        topico = topicoRepository.save(topico);
        return topicoMapper.toGetDto(topico);
    }

    public TopicoGetDto findTopicoById(Long id) throws ResourceNotFoundException {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado."));
        return topicoMapper.toGetDto(topico);
    }

    public List<TopicoGetDto> findAllTopicos() {
        List<Topico> topicos = topicoRepository.findAll();
        return topicoMapper.toGetDtos(topicos);
    }

    public void deleteTopico(Long id) throws ResourceNotFoundException {
        try {
            topicoRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Tópico não encontrado.");
        }
    }


}
