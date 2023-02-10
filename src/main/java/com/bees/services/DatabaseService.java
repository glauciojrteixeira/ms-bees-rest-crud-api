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
        Marca mac01 = new Marca(null, "Jeep");
        Marca mac02 = new Marca(null, "Ford");


        Modelo mod01 = new Modelo(null, "Renegade");
        Modelo mod02 = new Modelo(null, "Compass");
        Modelo mod03 = new Modelo(null, "Fiesta");

        mod01.getMarcas().addAll(Arrays.asList(mac01));
        mod02.getMarcas().addAll(Arrays.asList(mac01));
        mod03.getMarcas().addAll(Arrays.asList(mac02));

        marcaRepo.saveAll(Arrays.asList(mac01, mac02));
        modeloRepo.saveAll(Arrays.asList(mod01, mod02, mod03));

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
