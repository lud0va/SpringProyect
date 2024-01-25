package com.example.springcertificadosfx.data.dao;

import com.example.springcertificadosfx.data.model.Visualizadores;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisualizadoresDao extends ListCrudRepository<Visualizadores,Long> {


}
