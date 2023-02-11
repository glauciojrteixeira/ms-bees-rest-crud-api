package com.bees.domains.dtos;

import com.bees.domains.Carro;
import com.bees.domains.Marca;
import com.bees.domains.Modelo;
import com.bees.domains.enums.StatusCarro;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
public class CarroDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter @Setter private Integer id;
    @Getter @Setter private String placa;
    @Getter @Setter private Modelo modelo;
    @Getter @Setter private StatusCarro status;

    public CarroDTO(Carro carro) {
        this.id = carro.getId();
        this.placa = carro.getPlaca();
        this.modelo = carro.getModelo();
        this.status = carro.getStatus();
    }
}
