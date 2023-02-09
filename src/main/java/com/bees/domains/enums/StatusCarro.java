package com.bees.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum StatusCarro {
    DISPONIVEL(1, "Disponivel"),
    INDISPONIVEL(2, "Indisponivel");

    @Getter private int codigo;
    @Getter private String descricao;

    public static StatusCarro toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (StatusCarro item : StatusCarro.values()) {
            if (codigo.equals(item.getCodigo())) {
                return item;
            }
        }

        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}
