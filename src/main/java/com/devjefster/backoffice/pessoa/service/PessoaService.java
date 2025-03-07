package com.devjefster.backoffice.pessoa.service;

import com.devjefster.backoffice.pessoa.controller.dto.FiltrosPessoaDTO;
import com.devjefster.backoffice.pessoa.model.entidades.Pessoa;
import com.devjefster.backoffice.pessoa.model.enums.TipoCadastro;
import com.devjefster.backoffice.pessoa.model.repositories.PessoaRepository;
import com.devjefster.backoffice.pessoa.model.specification.PessoaSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PessoaService {

    private final PessoaRepository repository;

    public Page<Pessoa> listarComFiltros(FiltrosPessoaDTO filtros, Pageable pageable) {
        return repository.findAll(new PessoaSpecification(filtros), pageable);
    }

    public Pessoa buscarPeloId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
    }

    public Pessoa salvar(Pessoa pessoa) {
        pessoa.getEnderecos().forEach(endereco -> endereco.setPessoa(pessoa));
        return repository.save(pessoa);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public boolean existeCpfCnpj(String cpfCnpj, TipoCadastro tipoCadastro) {
        return repository.existsPessoaByCpfCnpjAndTipo(cpfCnpj, tipoCadastro);
    }

    public boolean existeEmail(String email, TipoCadastro tipo) {
        return repository.existsPessoaByEmailAndTipo(email, tipo);
    }
}
