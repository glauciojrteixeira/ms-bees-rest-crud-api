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
public class ModeloNewDTOTest implements PojoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModeloNewDTOTest.class);

    /*
    Atributos
     */
    private String nomeModelo;
    private Integer idMarca;
    private Modelo modelo;

    @BeforeAll
    static void setup() {
        LOGGER.info("@BeforeAll annotation needs to be static - executa uma vez antes de todos os métodos de teste nesta classe");

    }

    @Override
    @BeforeEach
    public void init() {
        LOGGER.info("@BeforeEach - executa antes de cada método de teste nesta classe");

        this.nomeModelo = "Renegade";
        this.idMarca = 1;
        this.modelo = new Modelo(1, "Renegade", new Marca(1, "Jeep"));

    }

    @Override
    @Test
    @DisplayName("Deve ter sucesso depois de atribuir valor aos argumentos.")
    public void should_Return_Success_Then_OnSetter() {
        /* Montagem do cenário */
        ModeloNewDTO dto = new ModeloNewDTO();

        /* Execução */
        dto.setNomeModelo(nomeModelo);
        dto.setIdMarca(idMarca);

        /* Verificações */
        check(dto);
    }

    @Override
    @Test
    @DisplayName("Deve ter sucesso depois de instanciar pelo construtor com argumentos.")
    public void should_Return_Success_Then_Constructor() {
        /* Montagem do cenário + execução */
        ModeloNewDTO dto = new ModeloNewDTO(modelo);

        /* Verificações */
        check(dto);
    }

    public void check(ModeloNewDTO dto) {
        Assertions.assertNotNull(dto, createMsg("ModeloNewDTO"));

        Assertions.assertEquals(nomeModelo, dto.getNomeModelo(), createMsg("nomeModelo"));
        Assertions.assertEquals(idMarca, dto.getIdMarca(), createMsg("idMarca"));
    }

    private String createMsg(String field) {
        return String.format(MSG_ERRO, field);
    }
}
