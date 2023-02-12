package com.bees.domains.enums;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class TipoPerfilTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TipoPerfilTest.class);

    @BeforeAll
    static void setup() {
        LOGGER.info("@BeforeAll annotation needs to be static - executa uma vez antes de todos os métodos de teste nesta classe");

    }

    @BeforeEach
    public void init() {
        LOGGER.info("@BeforeEach - executa antes de cada método de teste nesta classe");

    }

    @Test
    @DisplayName("Deve retornar equalsTrue quando obter por código.")
    void should_Return_Equals_When_getCodigo() {
        int expected = 1;
        Assertions.assertEquals(expected, TipoPerfil.ADMIN.getCodigo());
        expected = 2;
        Assertions.assertEquals(expected, TipoPerfil.USUARIO.getCodigo());
    }

    @Test
    @DisplayName("Deve retornar equalsTrue quando obter por descricao.")
    void should_Return_Equals_When_getDescricao() {
        String expected = "ROLE_ADMIN";
        Assertions.assertEquals(expected, TipoPerfil.ADMIN.getDescricao());

        expected = "ROLE_USUARIO";
        Assertions.assertEquals(expected, TipoPerfil.USUARIO.getDescricao());
    }

    @Test
    @DisplayName("Deve retornar equalsTrue quando obter objeto.")
    void should_Return_Equals_When_toEnum() {
        TipoPerfil expected = TipoPerfil.ADMIN;
        Assertions.assertEquals(expected, TipoPerfil.toEnum(1));

        expected = TipoPerfil.USUARIO;
        Assertions.assertEquals(expected, TipoPerfil.toEnum(2));

        expected = null;
        Assertions.assertEquals(expected, TipoPerfil.toEnum(null));
    }

    @Test
    @DisplayName("Deve lançar exceção IllegalArgumentException quando código não for encontrado na range de enums.")
    void should_Return_IllegalArgumentException_When_CodigoNotFound() {
        int codigo = 555;

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> TipoPerfil.toEnum(codigo),
                "Esperava-se que IllegalArgumentException fosse lançado, mas não foi."
        );
        Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());
        Assertions.assertTrue(exception.getMessage().contains("Código inválido: " + codigo));
    }
}
