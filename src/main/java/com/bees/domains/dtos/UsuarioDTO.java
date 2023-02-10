package com.bees.domains.dtos;

import com.bees.domains.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Atributos **/
    @Getter
    @Setter
    private Integer id;
    @Getter @Setter private String nomeUsuario;
    @Getter @Setter private String email;

    /** Construtores **/
    public UsuarioDTO(Usuario usuario) {
        super();

        this.id = usuario.getId();
        this.nomeUsuario = usuario.getNomeUsuario();
        this.email = usuario.getEmail();
    }
}
