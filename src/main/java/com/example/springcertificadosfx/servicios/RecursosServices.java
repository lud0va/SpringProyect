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

import java.util.List;

@Service
public class RecursosServices {
    private final RecursosDao dao;
    private final UserDao usDao;

    private final VisualizadoresDao visDao;

    private final DaoClavesCert claves;

    private final Configuration co;
    private final Encriptacion encriptacion;


    private final UserCacheo cacheo;

    private final VisualizadoresServices visServ;

    public RecursosServices(RecursosDao dao, UserDao usDao, VisualizadoresDao visDao, DaoClavesCert claves, Configuration co, Encriptacion encriptacion, UserCacheo cacheo, VisualizadoresServices visServ) {
        this.dao = dao;
        this.usDao = usDao;
        this.visDao = visDao;
        this.claves = claves;
        this.co = co;
        this.encriptacion = encriptacion;
        this.cacheo = cacheo;
        this.visServ = visServ;
    }

    public void cambiarPasswRecurso(String nombreRec, String newPassw) {
        Recursos recurso = dao.findByNombre(nombreRec).get();
        Visualizadores visTienePassw = visDao.findByNombreAndAndRecursos(cacheo.getName(), recurso).get();
        String claveRandom = encriptacion.desencriptarAsim(cacheo.getName(), visTienePassw.getClaveAsim(), cacheo.getPassw());

        String passw = encriptacion.encriptar(newPassw, claveRandom);
        recurso.setPassword(passw);
        dao.save(recurso);
        visDao.findAllByRecursos(recurso).get().forEach(vis -> {
            visServ.compartirRecurso(vis.getNombre(), recurso.getId_recursos());
        });


    }

    public void crearRecurso(String nameRec, String passwRec) {
        //encriptar contraseña
        String randomKey = Utils.randomBytes();
        String firma = claves.firmar(passwRec);
        String passwEncript = encriptacion.encriptar(passwRec, randomKey);


        dao.save(new Recursos(nameRec, passwEncript, firma));
        String claveVis = encriptacion.encriptarAsim(cacheo.getName(), randomKey);
        Recursos rec = dao.findByNombre(nameRec).get();
        visDao.save(new Visualizadores(cacheo.getName(), claveVis, rec));


    }

    public List<Recursos> getAll() {
        return dao.findAll();
    }


}
