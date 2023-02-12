package com.bees.domains.dtos;

import com.bees.domains.Carro;
import com.bees.domains.Modelo;
import com.bees.domains.enums.StatusCarro;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
public class CarroNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter @Setter private Integer id;
    @Getter @Setter private String placa;
    @Getter @Setter private Integer idModelo;
    @Getter @Setter private Integer codeStatus;

}
