package com.bees.domains.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
public class CredencialDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    /** Atributos */
    @Getter @Setter private String email;
    @Getter @Setter private String senha;
}
