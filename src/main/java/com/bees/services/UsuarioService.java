package com.bees.services;

import com.bees.domains.Usuario;
import com.bees.domains.enums.TipoPerfil;
import com.bees.repositories.UsuarioRepository;
import com.bees.securities.UserSpringSecurity;
import com.bees.services.exceptions.AutorizacaoException;
import com.bees.services.exceptions.ObjetoNaoEncontradoException;
import com.bees.services.exceptions.VersionAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

@Service
public class UsuarioService {
    private static final String MSG_API_NAO_ENCONTRADA = "Versão da API informada não encontrada!";
    private static final String MSG_ACESSO_NEGADO = "Acesso negado!";

    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${api.version.default}")
    private String apiVersionDefault;

    public Usuario buscarId(String version, Integer id) {

        version = version.equals("0") ? apiVersionDefault : version;

        if (version.equals("1.0")) {
            UserSpringSecurity user = UserService.authenticated();
            if (user == null || !user.hasRole(TipoPerfil.ADMIN) && !id.equals(user.getId())) {
                throw new AutorizacaoException(MSG_ACESSO_NEGADO);
            }

            Optional<Usuario> obj = usuarioRepo.findById(id);

            return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado! Código: " + id
                    + ", Tipo: " + Usuario.class.getName()));
        } else {
            throw new VersionAPIException(MSG_API_NAO_ENCONTRADA);
        }
    }
}
