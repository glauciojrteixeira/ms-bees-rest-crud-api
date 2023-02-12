package com.bees.domains.dtos;

import com.bees.domains.Marca;
import com.bees.domains.Modelo;
import com.bees.domains.PojoTest;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class ModeloDTOTest implements PojoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModeloDTOTest.class);

    /*
    Atributos
     */
    private Integer id;
    private String nomeModelo;
    private Marca marca;
    private Modelo modelo;

    @BeforeAll
    static void setup() {
        LOGGER.info("@BeforeAll annotation needs to be static - executa uma vez antes de todos os métodos de teste nesta classe");

    }

    @Override
    @BeforeEach
    public void init() {
        LOGGER.info("@BeforeEach - executa antes de cada método de teste nesta classe");

        this.id = 1;
        this.nomeModelo = "Renegade";
        this.marca = new Marca(1, "Jeep");
        this.modelo = new Modelo(1, "Renegade", marca);

    }

    @Override
    @Test
    @DisplayName("Deve ter sucesso depois de atribuir valor aos argumentos.")
    public void should_Return_Success_Then_OnSetter() {
        /* Montagem do cenário */
        ModeloDTO dto = new ModeloDTO();

        /* Execução */
        dto.setId(id);
        dto.setNomeModelo(nomeModelo);
        dto.setMarca(marca);

        /* Verificações */
        check(dto);
    }

    @Override
    @Test
    @DisplayName("Deve ter sucesso depois de instanciar pelo construtor com argumentos.")
    public void should_Return_Success_Then_Constructor() {
        /* Montagem do cenário + execução */
        ModeloDTO dto = new ModeloDTO(modelo);

        /* Verificações */
        check(dto);
    }

    public void check(ModeloDTO dto) {
        Assertions.assertNotNull(dto, createMsg("ModeloDTO"));

        Assertions.assertEquals(id, dto.getId(), createMsg("id"));
        Assertions.assertEquals(nomeModelo, dto.getNomeModelo(), createMsg("nomeModelo"));
        Assertions.assertEquals(marca, dto.getMarca(), createMsg("marca"));
    }

    private String createMsg(String field) {
        return String.format(MSG_ERRO, field);
    }
}
