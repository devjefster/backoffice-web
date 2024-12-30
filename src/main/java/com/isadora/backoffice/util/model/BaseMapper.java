package com.isadora.backoffice.util.model;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import java.util.List;
import java.util.stream.Collectors;

public interface BaseMapper<ENTIDADE, DTO> {

    DTO toDto(ENTIDADE E);

    ENTIDADE toEntity(DTO D);

    List<ENTIDADE> toEntity(List<DTO> D);

    List<DTO> toDto(List<ENTIDADE> E);

    @Mapping(target = "id",  ignore = true)
    void update(@MappingTarget ENTIDADE entity, DTO dto);


    default PagedModel<DTO> toDto(Page<ENTIDADE> entities) {
        List<DTO> dtos = entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        Pageable pageable = entities.getPageable();
        return new PagedModel<>(new PageImpl<>(dtos, pageable, entities.getTotalElements()));
    }
}
