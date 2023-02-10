package com.bees.domains;

import com.bees.domains.enums.TipoPerfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    /** Mapeamentos & Cardinalidades */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "USUARIOPERFIL")
    private Set<Integer> perfis = new HashSet<>();

    /** Construtores **/
    public Usuario(Integer id, String nomeUsuario, String email, String senha) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;

        addTipoPerfil(TipoPerfil.USUARIO);
    }
    public Usuario() {
        addTipoPerfil(TipoPerfil.USUARIO);
    }

    /** Metodos */
    public Set<TipoPerfil> getPerfis() {
        return perfis.stream().map(x -> TipoPerfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addTipoPerfil(TipoPerfil tipoPerfil) {
        perfis.add(tipoPerfil.getCodigo());
    }
}
