package com.bees.domains.dtos;

import com.bees.domains.Carro;
import com.bees.domains.Marca;
import com.bees.domains.Modelo;
import com.bees.domains.PojoTest;
import com.bees.domains.enums.StatusCarro;
import com.bees.repositories.ModeloRepository;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class MarcaDTOTest implements PojoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarcaDTOTest.class);

    /*
    Atributos
     */
    private Integer id;
    private String nomeMarca;
    private Marca marca = new Marca(1, "Jeep");

    @BeforeAll
    static void setup() {
        LOGGER.info("@BeforeAll annotation needs to be static - executa uma vez antes de todos os métodos de teste nesta classe");

    }

    @Override
    @BeforeEach
    public void init() {
        LOGGER.info("@BeforeEach - executa antes de cada método de teste nesta classe");

        this.id = 1;
        this.nomeMarca = "Jeep";

    }

    @Override
    @Test
    @DisplayName("Deve ter sucesso depois de atribuir valor aos argumentos.")
    public void should_Return_Success_Then_OnSetter() {
        /* Montagem do cenário */
        MarcaDTO dto = new MarcaDTO();

        /* Execução */
        dto.setId(id);
        dto.setNomeMarca(nomeMarca);

        /* Verificações */
        check(dto);
    }

    @Override
    @Test
    @DisplayName("Deve ter sucesso depois de instanciar pelo construtor com argumentos.")
    public void should_Return_Success_Then_Constructor() {
        /* Montagem do cenário + execução */
        MarcaDTO dto = new MarcaDTO(marca);

        /* Verificações */
        check(dto);
    }

    public void check(MarcaDTO dto) {
        Assertions.assertNotNull(dto, createMsg("MarcaDTO"));

        Assertions.assertEquals(id, dto.getId(), createMsg("id"));
        Assertions.assertEquals(nomeMarca, dto.getNomeMarca(), createMsg("nomeMarca"));
    }

    private String createMsg(String field) {
        return String.format(MSG_ERRO, field);
    }
}
