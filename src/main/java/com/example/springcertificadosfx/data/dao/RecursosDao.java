package com.example.springcertificadosfx.data.dao;

import com.example.springcertificadosfx.data.model.Recursos;
import com.example.springcertificadosfx.data.model.Users;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecursosDao extends ListCrudRepository<Recursos,Long> {

    Optional<Recursos> findByNombre(String nombre);
}
