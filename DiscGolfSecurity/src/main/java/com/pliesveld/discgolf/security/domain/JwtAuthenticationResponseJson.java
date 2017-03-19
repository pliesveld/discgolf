package com.pliesveld.discgolf.security.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class JwtAuthenticationResponseJson extends ModelBase {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponseJson(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
