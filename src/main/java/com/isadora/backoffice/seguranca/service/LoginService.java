package com.isadora.backoffice.seguranca.service;

import com.isadora.backoffice.seguranca.model.entidades.Login;
import com.isadora.backoffice.seguranca.model.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.auth.AuthenticationException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final LoginRepository loginRepository;

    @Cacheable(value = "cacheLogin", key = "#username")
    public Login getLogin(String username) throws AuthenticationException {
        return loginRepository.findByUsername(username)
                .orElseThrow(() -> new AuthenticationException("Usuario não encontrado com o login: " + username));
    }

    @CacheEvict(value = "cacheLogin", key = "#login.username")
    public void evictCacheOnUpdate(Login login) {
        log.info("Cache evicted for login: {}", login.getUsername());
    }
}
