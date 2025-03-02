package com.devjefster.backoffice.insumos.controller;

import com.devjefster.backoffice.fabricacao.model.enums.UnidadeMedida;
import com.devjefster.backoffice.insumos.controller.dto.InsumoDTO;
import com.devjefster.backoffice.insumos.facade.InsumoFacade;
import com.devjefster.backoffice.insumos.model.enums.SubTipoInsumoEnum;
import com.devjefster.backoffice.insumos.model.enums.TipoInsumo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/insumos")
@RequiredArgsConstructor
@Slf4j
public class InsumosController {

    private final InsumoFacade facade;

    @PostMapping
    public ResponseEntity<InsumoDTO> create(@Valid @RequestBody InsumoDTO dto) {
        log.info("Criando Insumo, {}", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(facade.criar(dto));
    }

    @PutMapping("/{id}")
    public InsumoDTO update(@PathVariable Long id, @RequestBody InsumoDTO dto) {
        log.info("Alterando Insumo,{}, {}", id, dto);
        return facade.atualizar(id, dto);
    }

    @GetMapping("/{id}")
    public InsumoDTO findById(@PathVariable Long id) {
        log.info("Buscando Insumo pelo id:{}", id);
        return facade.buscarPeloId(id);
    }

    @GetMapping
    public Page<InsumoDTO> findAll(
            @RequestParam(value = "textoBusca", required = false) String textoBusca,
            @RequestParam(value = "tipoInsumo", required = false) TipoInsumo tipo,
            Pageable pageable) {
        log.info("Buscando Insumo - {},{}", textoBusca, pageable);
        return facade.findAll(textoBusca, tipo, pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deletando Insumo pelo id:{}", id);
        facade.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tipo-insumo")
    public List<Map<String, String>> getTipoInsumo() {
        log.info("Buscando enums de TipoInsumo");
        return
                Arrays.stream(TipoInsumo.values())
                        .map(enumValue -> Map.of(
                                "chave", enumValue.name(),
                                "valor", enumValue.getDescricao()
                        ))
                        .toList();

    }

    @GetMapping("/unidade-medida")
    public List<Map<String, String>> getUnidadeMedida() {
        log.info("Buscando enums de Unidade Medida");
        return
                Arrays.stream(UnidadeMedida.values())
                        .map(enumValue -> Map.of(
                                "chave", enumValue.name(),
                                "valor", enumValue.getDescricao()
                        ))
                        .toList();

    }

    @GetMapping("/subtipos-insumo/{tipoInsumo}")
    public ResponseEntity<List<Map<String, String>>> getSubtiposInsumo(@PathVariable String tipoInsumo) {
        log.info("Buscando subtipos de insumo para tipo: {}", tipoInsumo);

        try {
            TipoInsumo tipo = TipoInsumo.valueOf(tipoInsumo.toUpperCase());
            Class<? extends SubTipoInsumoEnum> subTipoClass = tipo.getSubTipo();
            return ResponseEntity.ok(
                    Arrays.stream(subTipoClass.getEnumConstants())
                            .map(enumValue -> Map.of(
                                    "chave", enumValue.toString(),
                                    "valor", enumValue.getDescricacao()
                            ))
                            .toList()
            );
        } catch (IllegalArgumentException e) {
            log.error("TipoInsumo inválido: {}", tipoInsumo);
            return ResponseEntity.badRequest().build();
        }
    }
}
