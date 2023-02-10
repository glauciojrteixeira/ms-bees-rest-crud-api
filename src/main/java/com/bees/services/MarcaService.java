package com.bees.services;

import com.bees.domains.Marca;
import com.bees.domains.dtos.MarcaDTO;
import com.bees.repositories.MarcaRepository;
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
public class MarcaService {
    private static final String MSG_API_NAO_ENCONTRADA = "Versão da API informada não encontrada!";

    @Autowired
    private MarcaRepository marcaRepo;

    @Value("${api.version.default}")
    private String versionAPIDefault;

    public Marca buscarId(String version, Integer id) {
        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {
            Optional<Marca> obj = marcaRepo.findById(id);

            return obj.orElseThrow(
                    () -> new ObjetoNaoEncontradoException("Objeto não encontrado! "
                            + "[ " + Marca.class.getSimpleName() + " | Código: " + id + " ]"));
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }

    public List<Marca> buscarTodas(String version) {
        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {
            return marcaRepo.findAll();
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }

    public Page<Marca> buscarTodasPaginado(String version,
                                           Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {
            return marcaRepo.findAll(pageRequest);
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }

    @Transactional
    public Marca guardar(String version, Marca entity) {

        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {
            entity.setId(null);

            return marcaRepo.save(entity);
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }

    /*
     * Metodo auxiliar para instanciar uma classe de dominio a partir de um DTO
     */
    public Marca fromDTO(String version, MarcaDTO objetoDTO) {

        version = version.equals("0") ? versionAPIDefault : version;

        if (version.equals("1.0")) {
            return new Marca(objetoDTO.getId(), objetoDTO.getNomeMarca());
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }
}
