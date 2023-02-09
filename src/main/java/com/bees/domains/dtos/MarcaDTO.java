package com.bees.domains.dtos;

import com.bees.domains.Marca;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
public class MarcaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer id;

    @Getter @Setter private String nomeMarca;

    /** Construtores **/
    public MarcaDTO(Marca marca) {
        this.id = marca.getId();
        this.nomeMarca = marca.getNomeMarca();
    }
}
