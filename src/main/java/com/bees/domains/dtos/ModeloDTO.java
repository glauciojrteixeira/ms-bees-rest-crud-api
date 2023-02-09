package com.bees.domains.dtos;

import com.bees.domains.Marca;
import com.bees.domains.Modelo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
public class ModeloDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter @Setter private Integer id;
    @Getter @Setter private String nomeModelo;

    public ModeloDTO(Modelo modelo) {
        this.id = modelo.getId();
        this.nomeModelo = modelo.getNomeModelo();
    }
}
