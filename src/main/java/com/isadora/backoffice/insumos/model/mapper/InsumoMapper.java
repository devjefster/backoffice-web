package com.isadora.backoffice.insumos.model.mapper;

import com.isadora.backoffice.insumos.controller.dto.InsumoDTO;
import com.isadora.backoffice.insumos.model.entidades.Consumivel;
import com.isadora.backoffice.insumos.model.entidades.Embalagem;
import com.isadora.backoffice.insumos.model.entidades.Insumo;
import com.isadora.backoffice.insumos.model.entidades.MateriaPrima;
import com.isadora.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InsumoMapper extends BaseMapper<Insumo, InsumoDTO> {

    @Override
    default Insumo toEntity(InsumoDTO dto) {
        return switch (dto.getTipoInsumo()) {
            case "CONSUMIVEL" -> new Consumivel();
            case "EMBALAGEM" -> new Embalagem();
            case "MATERIA_PRIMA" -> new MateriaPrima();
            default -> throw new IllegalArgumentException("Unknown tipoInsumo: " + dto.getTipoInsumo());
        };
    }
}
