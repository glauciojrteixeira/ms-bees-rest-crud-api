package com.bees.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoPerfil {
    ADMIN(1, "ROLE_ADMIN"),
    USUARIO(2, "ROLE_USUARIO");

    @Getter
    private int codigo;
    @Getter private String descricao;


    /** Metodos **/

    public static TipoPerfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (TipoPerfil x : TipoPerfil.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Código inválido: " + cod);
    }
}
