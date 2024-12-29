package com.isadora.backoffice.insumos.model.mapper;

import com.isadora.backoffice.estoque.model.entidades.GradeCadastrada;
import com.isadora.backoffice.insumos.controller.dto.GradeCadastradaDTO;
import com.isadora.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GradeMapper extends BaseMapper<GradeCadastrada, GradeCadastradaDTO> {
}
