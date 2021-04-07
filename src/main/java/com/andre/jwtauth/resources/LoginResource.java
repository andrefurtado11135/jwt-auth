package com.andre.jwtauth.resources;

import com.andre.jwtauth.model.dto.LoginDto;
import com.andre.jwtauth.security.model.Sessao;
import com.andre.jwtauth.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginResource {

    @Autowired
    private SecurityUserService securityUserService;

    @PostMapping
    public Sessao logar(@RequestBody LoginDto login) throws Exception {
        return securityUserService.fazerLogin(login);
    }

}
