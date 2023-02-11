package com.bees.services;

import com.bees.domains.Marca;
import com.bees.domains.Modelo;
import com.bees.domains.dtos.MarcaDTO;
import com.bees.domains.dtos.ModeloNewDTO;
import com.bees.repositories.MarcaRepository;
import com.bees.repositories.ModeloRepository;
import com.bees.services.exceptions.ObjetoNaoEncontradoException;
import com.bees.services.exceptions.VersionAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ModeloService {
    private static final String MSG_API_NAO_ENCONTRADA = "Versão da API informada não encontrada!";

    @Autowired
    private ModeloRepository modeloRepo;

    @Autowired
    private MarcaRepository marcaRepo;

    @Value("${api.version.default}")
    private String versionAPIDefault;

    public Modelo buscarId(String version, Integer id) {
        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {
            Optional<Modelo> obj = modeloRepo.findById(id);

            return obj.orElseThrow(
                    () -> new ObjetoNaoEncontradoException("Objeto não encontrado! "
                            + "[ " + Modelo.class.getSimpleName() + " | Código: " + id + " ]"));
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }

    public List<Modelo> buscarTodas(String version) {
        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {
            return modeloRepo.findAll();
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }

    public Page<Modelo> buscarTodasPaginado(String version,
                                           Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {
            return modeloRepo.findAll(pageRequest);
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }

    @Transactional
    public Modelo guardar(String version, Modelo entity) {

        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {
            entity.setId(null);

            return modeloRepo.save(entity);
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }

    /*
     * Metodo auxiliar para instanciar uma classe de dominio a partir de um DTO
     */
    public Modelo fromDTO(String version, ModeloNewDTO objetoDTO) {

        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {
            Optional<Marca> marca = marcaRepo.findById(objetoDTO.getIdMarca());
            return new Modelo(objetoDTO.getNomeModelo(), marca.orElseThrow());
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }
}
