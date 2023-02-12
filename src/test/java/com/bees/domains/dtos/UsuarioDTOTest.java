package com.bees.domains.dtos;

import com.bees.domains.Marca;
import com.bees.domains.Modelo;
import com.bees.domains.PojoTest;
import com.bees.domains.Usuario;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioDTOTest implements PojoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioDTOTest.class);

    /*
    Atributos
     */
    private Integer id;
    private String nomeUsuario;
    private String email;
    private Usuario usuario;

    @BeforeAll
    static void setup() {
        LOGGER.info("@BeforeAll annotation needs to be static - executa uma vez antes de todos os métodos de teste nesta classe");

    }

    @Override
    @BeforeEach
    public void init() {
        LOGGER.info("@BeforeEach - executa antes de cada método de teste nesta classe");

        this.id = 1;
        this.nomeUsuario = "XPTO";
        this.email = "user01@bees.com";
        this.usuario = new Usuario(1, "XPTO", "user01@bees.com", "123");
    }

    @Override
    @Test
    @DisplayName("Deve ter sucesso depois de atribuir valor aos argumentos.")
    public void should_Return_Success_Then_OnSetter() {
        /* Montagem do cenário */
        UsuarioDTO dto = new UsuarioDTO();

        /* Execução */
        dto.setId(id);
        dto.setNomeUsuario(nomeUsuario);
        dto.setEmail(email);

        /* Verificações */
        check(dto);
    }

    @Override
    @Test
    @DisplayName("Deve ter sucesso depois de instanciar pelo construtor com argumentos.")
    public void should_Return_Success_Then_Constructor() {
        /* Montagem do cenário + execução */
        UsuarioDTO dto = new UsuarioDTO(usuario);

        /* Verificações */
        check(dto);
    }

    public void check(UsuarioDTO dto) {
        Assertions.assertNotNull(dto, createMsg("UsuarioDTO"));

        Assertions.assertEquals(id, dto.getId(), createMsg("id"));
        Assertions.assertEquals(nomeUsuario, dto.getNomeUsuario(), createMsg("nomeUsuario"));
        Assertions.assertEquals(email, dto.getEmail(), createMsg("email"));
    }

    private String createMsg(String field) {
        return String.format(MSG_ERRO, field);
    }
}
