package com.devjefster.backoffice.insumos.service;

import com.devjefster.backoffice.estoque.model.entidades.GradeCadastrada;
import com.devjefster.backoffice.insumos.controller.dto.GradeCadastradaDTO;
import com.devjefster.backoffice.insumos.model.mapper.GradeMapper;
import com.devjefster.backoffice.insumos.model.repository.GradeRepository;
import com.devjefster.backoffice.insumos.model.specifications.GradeSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GradeService {

    private final GradeRepository gradeRepository;
    private final GradeMapper mapper;

    public GradeCadastradaDTO criar(GradeCadastradaDTO dto) {
        GradeCadastrada grade = mapper.toEntity(dto);
        return mapper.toDto(gradeRepository.save(grade));
    }

    public PagedModel<GradeCadastradaDTO> listar(String textoBusca, Pageable pageable) {
        return mapper.toDto(gradeRepository.findAll(GradeSpecifications.porTextoBusca(textoBusca), pageable));
    }
}

