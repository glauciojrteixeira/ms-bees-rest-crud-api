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
public class CarroNewDTOTest implements PojoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarroNewDTOTest.class);

    /*
    Atributos
     */
    private Integer id;
    private String placa;
    private Integer idModelo;
    private Integer codeStatus;

    @Autowired
    private ModeloRepository modeloRepository;

    @BeforeAll
    static void setup() {
        LOGGER.info("@BeforeAll annotation needs to be static - executa uma vez antes de todos os métodos de teste nesta classe");

    }

    @Override
    @BeforeEach
    public void init() {
        LOGGER.info("@BeforeEach - executa antes de cada método de teste nesta classe");

        this.id = 1;
        this.placa = "QNH-3272";
        this.idModelo = 1;
        this.codeStatus = 1;

    }

    @Override
    @Test
    @DisplayName("Deve ter sucesso depois de atribuir valor aos argumentos.")
    public void should_Return_Success_Then_OnSetter() {
        /* Montagem do cenário */
        CarroNewDTO dto = new CarroNewDTO();

        /* Execução */
        dto.setId(id);
        dto.setPlaca(placa);
        dto.setIdModelo(idModelo);
        dto.setCodeStatus(codeStatus);

        /* Verificações */
        check(dto);
    }

    @Override
    @Test
    @DisplayName("Deve ter sucesso depois de instanciar pelo construtor sem argumentos.")
    public void should_Return_Success_Then_Constructor() {
        /* Montagem do cenário + execução */
        CarroNewDTO dto = new CarroNewDTO();

        /* Verificações */
        noArgs(dto);
    }

    public void check(CarroNewDTO dto) {
        Assertions.assertNotNull(dto, createMsg("CarroNewDTO"));

        Assertions.assertEquals(id, dto.getId(), createMsg("id"));
        Assertions.assertEquals(placa, dto.getPlaca(), createMsg("placa"));
        Assertions.assertEquals(idModelo, dto.getIdModelo(), createMsg("idModelo"));
        Assertions.assertEquals(codeStatus, dto.getCodeStatus(), createMsg("codeStatus"));
    }

    public void noArgs(CarroNewDTO dto) {
        Assertions.assertNotNull(dto, createMsg("CarroNewDTO"));
    }

    private String createMsg(String field) {
        return String.format(MSG_ERRO, field);
    }
}
