package com.devjefster.backoffice.seguranca.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum RoleEnum implements GrantedAuthority {
    USUARIO("USUARIO"), USUARIO_PREMIUM("USUARIO_PREMIUM"), ADMIN("ADMIN"), DONO("DONO");

    private String nome;

    RoleEnum(String nome) {
    }

    @Override
    public String getAuthority() {
        return getNome();
    }
}
