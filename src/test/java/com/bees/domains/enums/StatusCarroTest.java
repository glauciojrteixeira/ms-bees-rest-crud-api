package com.bees.domains.enums;

import com.bees.Main;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class StatusCarroTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusCarroTest.class);

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
        Assertions.assertEquals(expected, StatusCarro.DISPONIVEL.getCodigo());
        expected = 2;
        Assertions.assertEquals(expected, StatusCarro.INDISPONIVEL.getCodigo());
    }

    @Test
    @DisplayName("Deve retornar equalsTrue quando obter por descricao.")
    void should_Return_Equals_When_getDescricao() {
        String expected = "Disponivel";
        Assertions.assertEquals(expected, StatusCarro.DISPONIVEL.getDescricao());

        expected = "Indisponivel";
        Assertions.assertEquals(expected, StatusCarro.INDISPONIVEL.getDescricao());
    }

    @Test
    @DisplayName("Deve retornar equalsTrue quando obter objeto.")
    void should_Return_Equals_When_toEnum() {
        StatusCarro expected = StatusCarro.DISPONIVEL;
        Assertions.assertEquals(expected, StatusCarro.toEnum(1));

        expected = StatusCarro.INDISPONIVEL;
        Assertions.assertEquals(expected, StatusCarro.toEnum(2));

        expected = null;
        Assertions.assertEquals(expected, StatusCarro.toEnum(null));
    }

    @Test
    @DisplayName("Deve lançar exceção IllegalArgumentException quando código não for encontrado na range de enums.")
    void should_Return_IllegalArgumentException_When_CodigoNotFound() {
        int codigo = 555;

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> StatusCarro.toEnum(codigo),
                "Esperava-se que IllegalArgumentException fosse lançado, mas não foi."
        );
        Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());
        Assertions.assertTrue(exception.getMessage().contains("Código inválido: " + codigo));
    }
}
