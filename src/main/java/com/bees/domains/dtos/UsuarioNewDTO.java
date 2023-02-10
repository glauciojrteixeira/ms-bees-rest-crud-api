package com.bees.domains.dtos;

import com.bees.domains.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
public class UsuarioNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Atributos **/
    @Getter @Setter private String nomeUsuario;
    @Getter @Setter private String email;
    @Getter @Setter private String senha;

}
