package com.bees.services;

import com.bees.domains.Carro;
import com.bees.domains.Marca;
import com.bees.domains.Modelo;
import com.bees.domains.dtos.CarroDTO;
import com.bees.domains.dtos.CarroNewDTO;
import com.bees.domains.dtos.MarcaDTO;
import com.bees.domains.enums.StatusCarro;
import com.bees.repositories.CarroRepository;
import com.bees.repositories.ModeloRepository;
import com.bees.services.exceptions.ObjetoNaoEncontradoException;
import com.bees.services.exceptions.VersionAPIException;
import com.bees.services.exceptions.ViolacaoIntegridadeDadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
    private static final String MSG_API_NAO_ENCONTRADA = "Versão da API informada não encontrada!";

    @Autowired
    private CarroRepository carroRepo;

    @Autowired
    private ModeloRepository modeloRepo;

    @Value("${api.version.default}")
    private String versionAPIDefault;

    public Carro buscarId(String version, Integer id) {
        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {
            Optional<Carro> obj = carroRepo.findById(id);

            return obj.orElseThrow(
                    () -> new ObjetoNaoEncontradoException("Objeto não encontrado! "
                            + "[ " + Marca.class.getSimpleName() + " | Código: " + id + " ]"));
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }

    public List<Carro> buscarTodas(Integer codeStatus, String version) {
        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {
            if (codeStatus == 0) {
                return carroRepo.findAll();
            } else {
                StatusCarro status = StatusCarro.toEnum(codeStatus);
                return carroRepo.findAllCar(status);
            }

        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }

    public Page<Carro> buscarTodasPaginado(String version,
                                           Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {
            return carroRepo.findAll(pageRequest);
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }

    @Transactional
    public Carro guardar(String version, Carro entity) {

        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {
            entity.setId(null);

            return carroRepo.save(entity);
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }

    /*
     * Metodo auxiliar para instanciar uma classe de dominio a partir de um DTO
     */
    public Carro fromDTO(String version, CarroNewDTO objetoDTO) {

        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {
            Optional<Modelo> modelo = modeloRepo.findById(objetoDTO.getIdModelo());

            return new Carro(objetoDTO.getId(), objetoDTO.getPlaca(), modelo.orElseThrow(),
                    StatusCarro.toEnum(objetoDTO.getCodeStatus()));
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }

    @Transactional
    public Carro atualizar(String version, Carro entity) {

        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {

            return carroRepo.save(entity);
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }

    @Transactional
    public void remover(String version, Integer id) {

        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {
            buscarId(version, id);

            try {
                carroRepo.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new ViolacaoIntegridadeDadoException("Não é possível excluir Carro que possui entidades relacionadas!");
            }
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }

}
