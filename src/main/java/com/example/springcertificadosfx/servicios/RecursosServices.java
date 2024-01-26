package com.example.springcertificadosfx.servicios;

import com.example.springcertificadosfx.UserCacheo;
import com.example.springcertificadosfx.Utils;
import com.example.springcertificadosfx.common.Configuration;
import com.example.springcertificadosfx.data.dao.RecursosDao;
import com.example.springcertificadosfx.data.dao.UserDao;
import com.example.springcertificadosfx.data.dao.VisualizadoresDao;
import com.example.springcertificadosfx.data.dao.crearClave.DaoClavesCert;
import com.example.springcertificadosfx.data.model.Recursos;
import com.example.springcertificadosfx.data.model.Visualizadores;
import com.example.springcertificadosfx.seguridad.Encriptacion;
import org.springframework.stereotype.Service;

@Service
public class RecursosServices {
    private final RecursosDao dao;
    private final UserDao usDao;

    private final VisualizadoresDao visDao;

    private final DaoClavesCert claves;

    private final Configuration co;
    private final Encriptacion encriptacion;


    private final UserCacheo cacheo;

    public RecursosServices(RecursosDao dao, UserDao usDao, VisualizadoresDao visDao, DaoClavesCert claves, Configuration co, Encriptacion encriptacion, UserCacheo cacheo) {
        this.dao = dao;
        this.usDao = usDao;
        this.visDao = visDao;
        this.claves = claves;
        this.co = co;
        this.encriptacion = encriptacion;
        this.cacheo = cacheo;
    }

    public void crearRecurso(String nameRec, String passwRec) {
        //encriptar contraseña
        String randomKey = Utils.randomBytes();
        String firma = claves.firmar(passwRec);
        String passwEncript = encriptacion.encriptar(passwRec, randomKey);



        dao.save(new Recursos(nameRec, passwEncript, firma));
       String claveVis= encriptacion.encriptarAsim(cacheo.getName(),randomKey);
       visDao.save(new Visualizadores(cacheo.getName(),claveVis,dao.findByNombre(nameRec).get()));


    }
}
