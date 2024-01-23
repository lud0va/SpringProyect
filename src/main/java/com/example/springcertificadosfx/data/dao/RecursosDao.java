package com.example.springcertificadosfx.data.dao;

import com.example.springcertificadosfx.data.model.Recursos;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursosDao extends ListCrudRepository<Recursos,Long> {

    @Override
    Recursos save(Recursos entity);
}
