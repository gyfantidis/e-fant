package com.efant.efant.model.dtos;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityDTO implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return null;
    }
}
