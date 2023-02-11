package com.bees.domains.dtos;

import com.bees.domains.Marca;
import com.bees.domains.Modelo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
public class ModeloNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter @Setter private String nomeModelo;
    @Getter @Setter private Integer idMarca;

    public ModeloNewDTO(Modelo modelo) {
        this.nomeModelo = modelo.getNomeModelo();
        this.idMarca = modelo.getMarca().getId();
    }
}
