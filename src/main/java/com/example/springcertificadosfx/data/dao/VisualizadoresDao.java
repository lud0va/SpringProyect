package com.example.springcertificadosfx.data.dao;

import com.example.springcertificadosfx.data.model.Recursos;
import com.example.springcertificadosfx.data.model.Users;
import com.example.springcertificadosfx.data.model.Visualizadores;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisualizadoresDao extends ListCrudRepository<Visualizadores,Long> {

    Optional<Visualizadores> findByNombre(String nombre);
    Optional<Visualizadores> findById(int id);
    Optional<Visualizadores> findByNombreAndAndRecursos(String nombre,Recursos rec);

    boolean existsByNombre(String nombre);

    Optional<List<Visualizadores>> findAllByRecursos(Recursos recursos);

}
