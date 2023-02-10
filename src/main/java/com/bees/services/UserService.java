package com.bees.services;

import com.bees.securities.UserSpringSecurity;
import com.bees.services.exceptions.ClasseUtilitariaException;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {
    private UserService() {
        throw new ClasseUtilitariaException("Classe Utilitária.");
    }

    public static UserSpringSecurity authenticated() {
        try {
            // Retorna o usuario logado
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
