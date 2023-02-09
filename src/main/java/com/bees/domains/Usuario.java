package com.bees.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuario", indexes = {
        @Index(columnList = "id", unique = true),
        @Index(columnList = "nomeUsuario ASC", unique = true),
        @Index(columnList = "email ASC", unique = true)
})
@ToString
@EqualsAndHashCode(of={"id"})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;


    /** Atributos */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "usuario_sequence")
    @SequenceGenerator(name = "usuario_sequence",
            sequenceName = "seq_usuario",
            initialValue = 1,
            allocationSize = 50)
    @Getter @Setter @Column(nullable = false, unique = true)
    private Integer id;

    @Getter @Setter @Column(nullable = false, unique = true)
    private String nomeUsuario;
    @Getter @Setter @Column(nullable = false, unique = true)
    private String email;
    @Getter @Setter @Column(nullable = true, unique = false) @JsonIgnore
    private String senha;

}
