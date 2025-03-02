package com.devjefster.backoffice.insumos.controller;

import com.devjefster.backoffice.insumos.controller.dto.GradeCadastradaDTO;
import com.devjefster.backoffice.insumos.service.GradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
@Slf4j
public class GradesController {

    private final GradeService gradeService;

    @PostMapping
    public ResponseEntity<GradeCadastradaDTO> criar(@RequestBody GradeCadastradaDTO dto) {
        log.info("Criando Grade: {}", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.criar(dto));
    }

    @GetMapping
    public PagedModel<GradeCadastradaDTO> listar(@RequestParam String textoBusca, Pageable pageable) {
        log.info("Listando todas as grades");
        return gradeService.listar(textoBusca, pageable);
    }
}

