package com.bees.domains.dtos;

import com.bees.Main;
import com.bees.domains.Carro;
import com.bees.domains.Marca;
import com.bees.domains.Modelo;
import com.bees.domains.PojoTest;
import com.bees.domains.enums.StatusCarro;
import com.bees.repositories.ModeloRepository;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class CarroDTOTest implements PojoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarroDTOTest.class);

    /*
    Atributos
     */
    private Integer id;
    private String placa;
    private Modelo modelo;
    private StatusCarro status;
    private Carro carro;

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

        this.modelo = new Modelo();
        this.modelo.setId(id);
        this.modelo.setNomeModelo("Renegade");
        this.modelo.setMarca(new Marca(1, "Jeep"));

        this.status = StatusCarro.DISPONIVEL;

        this.carro = new Carro();
        this.carro.setId(id);
        this.carro.setPlaca(placa);
        this.carro.setModelo(modelo);
        this.carro.setStatus(status);
    }

    @Override
    @Test
    @DisplayName("Deve ter sucesso depois de atribuir valor aos argumentos.")
    public void should_Return_Success_Then_OnSetter() {
        /* Montagem do cenário */
        CarroDTO dto = new CarroDTO();

        /* Execução */
        dto.setId(id);
        dto.setPlaca(placa);
        dto.setModelo(modelo);
        dto.setStatus(status);

        /* Verificações */
        check(dto);
    }

    @Override
    @Test
    @DisplayName("Deve ter sucesso depois de instanciar pelo construtor com argumentos.")
    public void should_Return_Success_Then_Constructor() {
        /* Montagem do cenário + execução */
        CarroDTO dto = new CarroDTO(carro);

        /* Verificações */
        check(dto);
    }

    public void check(CarroDTO dto) {
        Assertions.assertNotNull(dto, createMsg("CarroDTO"));

        Assertions.assertEquals(id, dto.getId(), createMsg("id"));
        Assertions.assertEquals(placa, dto.getPlaca(), createMsg("placa"));
        Assertions.assertEquals(modelo, dto.getModelo(), createMsg("modelo"));
        Assertions.assertEquals(status, dto.getStatus(), createMsg("status"));
    }

    private String createMsg(String field) {
        return String.format(MSG_ERRO, field);
    }
}
