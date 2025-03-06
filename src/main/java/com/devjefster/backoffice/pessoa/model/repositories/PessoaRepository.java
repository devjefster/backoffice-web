package com.devjefster.backoffice.pessoa.model.repositories;

import com.devjefster.backoffice.pessoa.model.entidades.Pessoa;
import com.devjefster.backoffice.pessoa.model.enums.TipoCadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa> {
    boolean existsPessoaByCpfCnpjAndTipo(String cpfCnpj, TipoCadastro tipoCadastro);

    boolean existsPessoaByEmailAndTipo(String email,TipoCadastro tipoCadastro);
}
