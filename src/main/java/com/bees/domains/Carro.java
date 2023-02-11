package com.bees.domains;

import com.bees.domains.enums.StatusCarro;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "carro", indexes = {
        @Index(columnList = "id", unique = true),
        @Index(columnList = "placa", unique = true)
})
@NoArgsConstructor @ToString
public class Carro implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Atributos */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "carro_sequence")
    @SequenceGenerator(name = "carro_sequence",
            sequenceName = "seq_carro",
            initialValue = 1,
            allocationSize = 50)
    @Getter @Setter @Column(nullable = false, unique = true)
    private Integer id;

    @Getter @Setter @Column(nullable = false, unique = true)
    private String placa;

    @Getter @Setter @Column(nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private StatusCarro status;

    /** Mapeamentos & Cardinalidades **/
    @ManyToOne
    @JoinColumn(name = "modelo_id")
    @Getter @Setter private Modelo modelo;

    /** Construtores **/
    public Carro(Integer id, String placa, Modelo modelo, StatusCarro status) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.status = status;
    }

    /** Equals and Hash **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return Objects.equals(id, carro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
