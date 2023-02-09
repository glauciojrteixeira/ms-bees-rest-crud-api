package com.bees.services;

import com.bees.domains.Marca;
import com.bees.repositories.MarcaRepository;
import com.bees.services.exceptions.ObjetoNaoEncontradoException;
import com.bees.services.exceptions.VersionAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
}
