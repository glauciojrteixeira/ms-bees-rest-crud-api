package com.bees.domains.dtos;

import com.bees.domains.PojoTest;
import com.bees.repositories.ModeloRepository;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioNewDTOTest implements PojoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioNewDTOTest.class);

    /*
    Atributos
     */
    private String nomeUsuario;
    private String email;
    private String senha;


    @BeforeAll
    static void setup() {
        LOGGER.info("@BeforeAll annotation needs to be static - executa uma vez antes de todos os métodos de teste nesta classe");

    }

    @Override
    @BeforeEach
    public void init() {
        LOGGER.info("@BeforeEach - executa antes de cada método de teste nesta classe");

        this.nomeUsuario = "XPTO";
        this.email = "user01@bees.com";
        this.senha = "123";

    }

    @Override
    @Test
    @DisplayName("Deve ter sucesso depois de atribuir valor aos argumentos.")
    public void should_Return_Success_Then_OnSetter() {
        /* Montagem do cenário */
        UsuarioNewDTO dto = new UsuarioNewDTO();

        /* Execução */
        dto.setNomeUsuario(nomeUsuario);
        dto.setEmail(email);
        dto.setSenha(senha);

        /* Verificações */
        check(dto);
    }

    @Override
    @Test
    @DisplayName("Deve ter sucesso depois de instanciar pelo construtor sem argumentos.")
    public void should_Return_Success_Then_Constructor() {
        /* Montagem do cenário + execução */
        UsuarioNewDTO dto = new UsuarioNewDTO();

        /* Verificações */
        noArgs(dto);
    }

    public void check(UsuarioNewDTO dto) {
        Assertions.assertNotNull(dto, createMsg("UsuarioNewDTO"));

        Assertions.assertEquals(nomeUsuario, dto.getNomeUsuario(), createMsg("nomeUsuario"));
        Assertions.assertEquals(email, dto.getEmail(), createMsg("email"));
        Assertions.assertEquals(senha, dto.getSenha(), createMsg("senha"));
    }

    public void noArgs(UsuarioNewDTO dto) {
        Assertions.assertNotNull(dto, createMsg("UsuarioNewDTO"));
    }

    private String createMsg(String field) {
        return String.format(MSG_ERRO, field);
    }
}
