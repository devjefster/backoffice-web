package com.devjefster.backoffice.pessoa.facade;

import com.devjefster.backoffice.pessoa.controller.dto.FiltrosPessoaDTO;
import com.devjefster.backoffice.pessoa.controller.dto.PessoaDTO;
import com.devjefster.backoffice.pessoa.model.entidades.Endereco;
import com.devjefster.backoffice.pessoa.model.entidades.Pessoa;
import com.devjefster.backoffice.pessoa.model.enums.TipoCadastro;
import com.devjefster.backoffice.pessoa.model.enums.TipoPessoa;
import com.devjefster.backoffice.pessoa.model.mapper.PessoaMapper;
import com.devjefster.backoffice.pessoa.service.EnderecoService;
import com.devjefster.backoffice.pessoa.service.PessoaService;
import com.devjefster.backoffice.seguranca.config.ValidationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PessoaFacade {

    private final PessoaService pessoaService;
    private final EnderecoService enderecoService;
    private final PessoaMapper mapper;

    public PagedModel<PessoaDTO> listarComFiltros(FiltrosPessoaDTO filtros, Pageable pageable) {
        return mapper.toDto(pessoaService.listarComFiltros(filtros, pageable));
    }

    @Transactional
    public PessoaDTO criar(PessoaDTO dto) {
        validarPessoa(dto);
        Pessoa pessoa = mapper.toEntity(dto);
        pessoaService.salvar(pessoa);
        List<Endereco> enderecos = enderecoService.criarOuAtualizarEnderecos(dto.getEnderecos(),pessoa);
        pessoa.setEnderecos(enderecos);
        return mapper.toDto(pessoa);
    }

    @Transactional
    public PessoaDTO atualizar(Long id, PessoaDTO dto) {
        validarPessoa(dto);
        Pessoa pessoa = pessoaService.buscarPeloId(id);
        pessoaService.salvar(pessoa);
        List<Endereco> enderecos = enderecoService.criarOuAtualizarEnderecos(dto.getEnderecos(),pessoa);
        mapper.update(pessoa, dto);
        pessoa.setEnderecos(enderecos);
        return mapper.toDto(pessoa);
    }

    public void deletar(Long id) {
        pessoaService.deletar(id);
    }

    public void deletarEndereco(Long enderecoId) {
        enderecoService.deletarEndereco(enderecoId);
    }


    private void validarPessoa(PessoaDTO dto) {
        if (dto.getTipo() == TipoCadastro.FABRICANTE) {
            if (dto.getNome() == null || dto.getNomeFantasia() == null || dto.getRazaoSocial() == null) {
                throw new ValidationException("Nome é obritório para Pessoa", "cpf");
            }
        } else {
            if (dto.getTipoPessoa().equals(TipoPessoa.PESSOA_FISICA)) {
                if (dto.getCpfCnpj() == null) {
                    throw new ValidationException("CPF é obritório para Pessoa Física", "cpf");
                }
                if (dto.getNome() == null || dto.getNome().isBlank()) {
                    throw new ValidationException("Nome é obritório para Pessoa Física", "cpf");
                }
            }
        }
        if (dto.getTipoPessoa().equals(TipoPessoa.PESSOA_JURIDICA)) {
            if (dto.getCpfCnpj() == null) {
                throw new ValidationException("CNPJ é obritório para Pessoa Jurídica", "cpf");
            }
            if (dto.getRazaoSocial() == null || dto.getNomeFantasia() == null) {
                throw new ValidationException("Razao Social ou Nome Fantasia são obritórios para Pessoa Jurídica", "cpf");
            }
        }
    }

    public Map<String, String> validarUnicidade(PessoaDTO pessoa) {
        Map<String, String> erros = new HashMap<>();
        if (pessoaService.existeCpfCnpj(pessoa.getCpfCnpj(), pessoa.getTipo())) {
            erros.put("cpfCnpj", "CPF/CNPJ já cadastrado.");
        }
        if (pessoaService.existeEmail(pessoa.getEmail(), pessoa.getTipo())) {
            erros.put("email", "E-mail já cadastrado.");
        }

        return  erros.isEmpty() ? Map.of("unico", "true") : erros;
    }
}
