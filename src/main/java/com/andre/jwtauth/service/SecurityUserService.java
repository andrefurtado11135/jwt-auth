package com.andre.jwtauth.service;

import com.andre.jwtauth.model.dto.CriarUsuarioDto;
import com.andre.jwtauth.model.dto.LoginDto;
import com.andre.jwtauth.repository.AuthorityRepository;
import com.andre.jwtauth.repository.SecurityUserRepository;
import com.andre.jwtauth.security.model.SecurityUser;
import com.andre.jwtauth.security.model.Sessao;
import com.andre.jwtauth.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SecurityUserService {

    @Autowired
    private SecurityUserRepository securityUserRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void CriarUsuario(CriarUsuarioDto criarUsuarioDto) throws Exception{
        if (securityUserRepository.findByUsername(criarUsuarioDto.getUsername()) != null){
            throw new Exception("Esse usuário já está cadastrado");
        }else if (authorityRepository.findByName(criarUsuarioDto.getPerfil()) == null){
            throw new Exception("O perfil informado não existe");
        }else{
            SecurityUser securityUser = new SecurityUser();
            securityUser.setUsername(criarUsuarioDto.getUsername());
            securityUser.setPassword(passwordEncoder.encode(criarUsuarioDto.getPassword()));
            securityUser.setAuthorities(List.of(authorityRepository.findByName(criarUsuarioDto.getPerfil())));
            securityUser.setAccountNonExpired(true);
            securityUser.setAccountNonLocked(true);
            securityUser.setCredentialsNonExpired(true);
            securityUser.setDisableDate(null);
            securityUserRepository.save(securityUser);
        }
    }

    public Sessao fazerLogin(LoginDto login) throws Exception{
        SecurityUser securityUser = securityUserRepository.findByUsername(login.getUsername());

        if (securityUser != null) {
            boolean passwordMatches = passwordEncoder.matches(login.getPassword(), securityUser.getPassword());

            if (!passwordMatches) {
                throw new Exception("Senha inválida para o login: " + login.getUsername());
            }

            Sessao sessao = new Sessao();
            Date issuedAt = new Date(System.currentTimeMillis());
            Date expriration = (new Date(System.currentTimeMillis() + JwtUtils.TOKEN_EXPIRATION));

            sessao.setUsername(login.getUsername());

            sessao.setJwtToken(JwtUtils.create(sessao.getUsername(), securityUser.getAuthorities(), issuedAt, expriration));

            return sessao;
        }else{
            throw new Exception("Nome de usuário informado não existe");
        }
    }

    public List<SecurityUser> getAllUsuarios(){
        return securityUserRepository.findAll();
    }

}
