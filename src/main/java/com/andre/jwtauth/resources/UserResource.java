package com.andre.jwtauth.resources;

import com.andre.jwtauth.model.dto.CriarUsuarioDto;
import com.andre.jwtauth.service.SecurityUserService;
import com.andre.jwtauth.utils.VerifyAuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            securityUserService.CriarUsuario(criarUsuarioDto);
            return ResponseEntity.status(201).body("Usuário criado com sucesso");
        }catch (Exception ex){
            return ResponseEntity.status(500).body("Não foi possível criar o usuário " + ex.getMessage());
        }
    }
}
