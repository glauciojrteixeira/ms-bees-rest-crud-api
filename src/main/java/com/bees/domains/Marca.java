package com.bees.domains;

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
@Table(name = "marca", indexes = {
        @Index(columnList = "id", unique = true),
        @Index(columnList = "nomeMarca ASC", unique = true)
})
@NoArgsConstructor
@ToString
public class Marca implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Atributos */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "marca_sequence")
    @SequenceGenerator(name = "marca_sequence",
            sequenceName = "seq_marca",
            initialValue = 1,
            allocationSize = 50)
    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private Integer id;
    @Getter @Setter @Column(nullable = false, unique = true)
    private String nomeMarca;

    /** Mapeamentos & Cardinalidades **/
    @ManyToMany(mappedBy = "marcas")
    @Getter @Setter private List<Modelo> modelos = new ArrayList<>();


    /** Construtores **/
    public Marca(Integer id, String nomeMarca) {
        this.id = id;
        this.nomeMarca = nomeMarca;
    }

    /** Equals and Hash **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Marca marca = (Marca) o;
        return id != null && Objects.equals(id, marca.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
