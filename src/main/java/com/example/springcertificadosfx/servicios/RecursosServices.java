package com.example.springcertificadosfx.servicios;

import com.example.springcertificadosfx.UserCacheo;
import com.example.springcertificadosfx.common.Configuration;
import com.example.springcertificadosfx.data.dao.RecursosDao;
import com.example.springcertificadosfx.data.dao.crearClave.DaoClavesCert;

import com.example.springcertificadosfx.data.model.Recursos;
import com.example.springcertificadosfx.seguridad.Encriptacion;
import org.springframework.stereotype.Service;

@Service
public class RecursosServices {
    private final RecursosDao dao;

    private final DaoClavesCert claves;

    private final Configuration co;
    private final Encriptacion encriptacion;


    private final UserCacheo cacheo;
    public RecursosServices(RecursosDao dao, DaoClavesCert claves, Configuration co, Encriptacion encriptacion, UserCacheo cacheo) {
        this.dao = dao;
        this.claves = claves;
        this.co = co;
        this.encriptacion = encriptacion;
        this.cacheo = cacheo;
    }

    public void crearRecurso(String nameRec,String passwRec){
         //encriptar contraseña
        ;
        dao.save(new Recursos(nameRec,encriptacion.encriptar(passwRec,cacheo.getName())));
    }
}
