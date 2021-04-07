package com.andre.jwtauth.security.model;

import org.springframework.security.core.GrantedAuthority;

public class AuthorityDto implements GrantedAuthority {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
