package com.isadora.backoffice.pessoa.service;


import com.isadora.backoffice.pessoa.controller.dto.EnderecoDTO;
import com.isadora.backoffice.pessoa.model.entidades.Endereco;
import com.isadora.backoffice.pessoa.model.entidades.Pessoa;
import com.isadora.backoffice.pessoa.model.mapper.EnderecoMapper;
import com.isadora.backoffice.pessoa.model.repositories.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    public void deletarEndereco(Long enderecoId) {
        enderecoRepository.findById(enderecoId).ifPresent(enderecoRepository::delete);
    }

    public List<Endereco> criarOuAtualizarEnderecos(List<EnderecoDTO> enderecoDTOS, Pessoa pessoa) {
        List<Endereco> enderecos = enderecoMapper.toEntity(enderecoDTOS);
        enderecos.replaceAll(endereco -> {
            if (endereco.getId() != null) {
                return enderecoRepository.findById(endereco.getId()).map(existing -> {
                    BeanUtils.copyProperties(endereco, existing, "id", "createdAt", "updatedAt");
                    return enderecoRepository.save(existing);
                }).orElse(endereco);
            }
            endereco.setPessoa(pessoa);
            return endereco;
        });
        return enderecoRepository.saveAll(enderecos);
    }


}
