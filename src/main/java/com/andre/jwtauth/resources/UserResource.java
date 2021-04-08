package com.andre.jwtauth.resources;

import com.andre.jwtauth.model.dto.CriarUsuarioDto;
import com.andre.jwtauth.model.exception.CustomAuthException;
import com.andre.jwtauth.security.model.SecurityUser;
import com.andre.jwtauth.service.SecurityUserService;
import com.andre.jwtauth.utils.VerifyAuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private SecurityUserService securityUserService;

    @Autowired
    private VerifyAuthUtils verifyAuthUtils;

    @PostMapping("/cadastro")
    public ResponseEntity criarUsuario(@RequestBody CriarUsuarioDto criarUsuarioDto){
        try {
            SecurityUser newUser = securityUserService.CriarUsuario(criarUsuarioDto);
            return ResponseEntity.status(201).body(newUser);
        }catch (CustomAuthException customAuthException){
            return ResponseEntity.status(customAuthException.getHttpCode()).body(customAuthException.getResponseErrorMessage());
        }catch (Throwable throwable){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage(), throwable);
        }
    }
}
