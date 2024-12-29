package com.isadora.backoffice.seguranca.service;

import com.isadora.backoffice.seguranca.config.LoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AutenticacaoService implements UserDetailsService {

    private final LoginService loginService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return LoginDTO.map(loginService.getLogin(username));
    }
}
