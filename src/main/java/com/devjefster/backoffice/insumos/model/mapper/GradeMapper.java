package com.devjefster.backoffice.insumos.model.mapper;

import com.devjefster.backoffice.estoque.model.entidades.GradeCadastrada;
import com.devjefster.backoffice.insumos.controller.dto.GradeCadastradaDTO;
import com.devjefster.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GradeMapper extends BaseMapper<GradeCadastrada, GradeCadastradaDTO> {
}
