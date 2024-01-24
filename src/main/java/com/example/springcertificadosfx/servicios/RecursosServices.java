package com.example.springcertificadosfx.servicios;

import com.example.springcertificadosfx.Configuration;
import com.example.springcertificadosfx.data.dao.RecursosDao;
import com.example.springcertificadosfx.data.dao.crearClave.DaoClavesCert;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class RecursosServices {
    private final RecursosDao dao;

    private final DaoClavesCert claves;

    private final Configuration co;

    @Inject
    public RecursosServices(RecursosDao dao, DaoClavesCert claves, Configuration co) {
        this.dao = dao;
        this.claves = claves;
        this.co = co;
    }

    public void crearRecurso(String nameRec,String username,String passwUser){

    }
}
