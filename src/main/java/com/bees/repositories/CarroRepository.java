package com.bees.repositories;

import com.bees.domains.Carro;
import com.bees.domains.enums.StatusCarro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Integer> {

    @Transactional
    @Query("SELECT obj FROM Carro obj WHERE obj.status = :codeStatus ORDER BY obj.placa")
    public List<Carro> findAllCar(@Param("codeStatus") StatusCarro codeStatus);
}
