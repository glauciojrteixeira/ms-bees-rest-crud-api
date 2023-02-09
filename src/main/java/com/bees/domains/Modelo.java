package com.bees.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "modelo", indexes = {
        @Index(columnList = "id", unique = true),
        @Index(columnList = "nomeModelo ASC", unique = true)
})
@NoArgsConstructor
@ToString
public class Modelo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Atributos */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "modelo_sequence")
    @SequenceGenerator(name = "modelo_sequence",
            sequenceName = "seq_modelo",
            initialValue = 1,
            allocationSize = 50)
    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private Integer id;

    @Getter @Setter @Column(nullable = false, unique = true)
    private String nomeModelo;

    /** Mapeamentos & Cardinalidades **/
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "MODELO_MARCA",
            joinColumns = @JoinColumn(name = "modelo_id"),
            inverseJoinColumns = @JoinColumn(name = "marca_id")
    )
    @Getter @Setter private List<Marca> marcas = new ArrayList<>();

    /** Construtores **/
    public Modelo(Integer id, String nomeModelo) {
        this.id = id;
        this.nomeModelo = nomeModelo;
    }

    /** Equals and Hash **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Modelo modelo = (Modelo) o;
        return id != null && Objects.equals(id, modelo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
