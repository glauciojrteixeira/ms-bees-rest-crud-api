package com.bees.services;

import com.bees.domains.Marca;
import com.bees.domains.Modelo;
import com.bees.repositories.MarcaRepository;
import com.bees.repositories.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
public class DatabaseService {
    @Autowired
    private MarcaRepository marcaRepo;

    @Autowired
    private ModeloRepository modeloRepo;

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

    }
}
