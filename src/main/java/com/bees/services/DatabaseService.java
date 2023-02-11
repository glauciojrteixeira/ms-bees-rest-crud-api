package com.bees.services;

import com.bees.domains.Carro;
import com.bees.domains.Marca;
import com.bees.domains.Modelo;
import com.bees.domains.Usuario;
import com.bees.domains.enums.StatusCarro;
import com.bees.domains.enums.TipoPerfil;
import com.bees.repositories.CarroRepository;
import com.bees.repositories.MarcaRepository;
import com.bees.repositories.ModeloRepository;
import com.bees.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.UUID;

@Service
public class DatabaseService {
    @Autowired
    private MarcaRepository marcaRepo;

    @Autowired
    private ModeloRepository modeloRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CarroRepository carroRepo;

    public void instantiateDatabaseLocal() throws ParseException {
        Marca marca01 = new Marca(null, "Jeep");
        Marca marca02 = new Marca(null, "Ford");

        Modelo modelo01 = new Modelo(null, "Renegade", marca01);
        Modelo modelo02 = new Modelo(null, "Compass", marca01);
        Modelo modelo03 = new Modelo(null, "Fiesta", marca02);

        marcaRepo.saveAll(Arrays.asList(marca01, marca02));
        modeloRepo.saveAll(Arrays.asList(modelo01, modelo02, modelo03));

        Usuario usuario01 = new Usuario(null,
                "Gláucio Júnior Teixeira",
                "glaucio.teixeira@outlook.com",
                passwordEncoder.encode("123"));
        usuario01.addTipoPerfil(TipoPerfil.ADMIN);

        Usuario usuario02 = new Usuario(null,
                "Alexsandra Silva da Costa",
                "alexsandra.costa1702@gmail.com",
                passwordEncoder.encode("123"));

        usuarioRepo.saveAll(Arrays.asList(usuario01, usuario02));

        Carro carro01 = new Carro(null, "QNH-3272", StatusCarro.DISPONIVEL);

        carroRepo.saveAll(Arrays.asList(carro01));
    }
}
