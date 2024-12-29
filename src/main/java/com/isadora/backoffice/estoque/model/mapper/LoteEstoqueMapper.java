package com.isadora.backoffice.estoque.model.mapper;

import com.isadora.backoffice.estoque.controller.dto.LoteEstoqueDTO;
import com.isadora.backoffice.estoque.model.entidades.LoteEstoque;
import com.isadora.backoffice.estoque.model.entidades.LoteInsumo;
import com.isadora.backoffice.estoque.model.entidades.LoteProdutoAcabado;
import com.isadora.backoffice.estoque.model.entidades.LoteProdutoFinal;
import com.isadora.backoffice.estoque.model.enums.TipoEstoque;
import com.isadora.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoteEstoqueMapper extends BaseMapper<LoteEstoque, LoteEstoqueDTO> {

    @Override
    default LoteEstoque toEntity(LoteEstoqueDTO dto) {
        return switch (dto.getTipo()) {
            case TipoEstoque.INSUMOS -> new LoteInsumo();
            case TipoEstoque.PRODUTO_ACABADO -> new LoteProdutoAcabado();
            case TipoEstoque.PRODUTO_FINAL -> new LoteProdutoFinal();
            default -> throw new IllegalArgumentException("Unknown Tipo de estoque: " + dto.getTipo());
        };
    }
}
