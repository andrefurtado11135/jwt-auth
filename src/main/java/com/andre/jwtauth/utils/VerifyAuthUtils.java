package com.andre.jwtauth.utils;

import com.andre.jwtauth.model.exception.CustomAuthException;
import com.andre.jwtauth.model.exception.GeneralAuthException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class VerifyAuthUtils {

    public Boolean hasAuthority(String role) throws Throwable {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equalsIgnoreCase(role));
        }else{
            throw new GeneralAuthException(403, "ERRO - Usuário não autenticado");
        }
    }

}
