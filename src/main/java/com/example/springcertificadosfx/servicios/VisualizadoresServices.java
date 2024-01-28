package com.example.springcertificadosfx.servicios;

import com.example.springcertificadosfx.UserCacheo;
import com.example.springcertificadosfx.common.Configuration;
import com.example.springcertificadosfx.data.dao.VisualizadoresDao;
import com.example.springcertificadosfx.data.dao.crearClave.DaoClavesCert;
import com.example.springcertificadosfx.data.model.Recursos;
import com.example.springcertificadosfx.data.model.Visualizadores;
import com.example.springcertificadosfx.seguridad.Encriptacion;
import org.springframework.stereotype.Service;

@Service
public class VisualizadoresServices {
    private final VisualizadoresDao visDao;
    private final Configuration co;
    private final Encriptacion encriptacion;

    private final DaoClavesCert claves;
    private final UserCacheo cacheo;

    public VisualizadoresServices(VisualizadoresDao visDao, Configuration co, Encriptacion encriptacion, DaoClavesCert claves, UserCacheo cacheo) {
        this.visDao = visDao;
        this.co = co;
        this.encriptacion = encriptacion;
        this.claves = claves;
        this.cacheo = cacheo;
    }

    public void compartirRecurso(String nombreVis,int idrec) {
        if (visDao.existsByNombre(nombreVis)) {
            Visualizadores visTienePassw = visDao.findByNombre(cacheo.getName()).get();
            String claveRandom = encriptacion.desencriptarAsim(cacheo.getName(), visTienePassw.getClaveAsim(), cacheo.getPassw());
            String claveEncriptada = encriptacion.encriptarAsim(nombreVis, claveRandom);
            Visualizadores nuevoVis = visDao.findByNombre(nombreVis).get();
            nuevoVis.setClaveAsim(claveEncriptada);
            nuevoVis.setRecursos(new Recursos(idrec));
            visDao.save(nuevoVis);
        }
    }
}
