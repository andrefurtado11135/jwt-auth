package com.andre.jwtauth.resources;

import com.andre.jwtauth.model.dto.LoginDto;
import com.andre.jwtauth.model.exception.CustomAuthException;
import com.andre.jwtauth.model.exception.InvalidLoginException;
import com.andre.jwtauth.security.model.Sessao;
import com.andre.jwtauth.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/login")
public class LoginResource {

    @Autowired
    private SecurityUserService securityUserService;

    @PostMapping
    public ResponseEntity logar(@RequestBody LoginDto login){
        try{
            Sessao sessao = securityUserService.fazerLogin(login);
            return ResponseEntity.status(200).body(sessao);
        }catch (CustomAuthException customAuthException){
            return ResponseEntity.status(customAuthException.getHttpCode()).body(customAuthException.getResponseErrorMessage());
        }catch (Throwable throwable){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage(), throwable);
        }
    }

}
