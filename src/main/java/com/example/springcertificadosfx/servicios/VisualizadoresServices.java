package com.example.springcertificadosfx.servicios;

import com.example.springcertificadosfx.UserCacheo;
import com.example.springcertificadosfx.common.Configuration;
import com.example.springcertificadosfx.data.dao.RecursosDao;
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
    private final RecursosDao recDao;

    private final DaoClavesCert claves;
    private final UserCacheo cacheo;

    public VisualizadoresServices(VisualizadoresDao visDao, Configuration co, Encriptacion encriptacion, RecursosDao recDao, DaoClavesCert claves, UserCacheo cacheo) {
        this.visDao = visDao;
        this.co = co;
        this.encriptacion = encriptacion;
        this.recDao = recDao;
        this.claves = claves;
        this.cacheo = cacheo;
    }

    public boolean comprobarRecurso(String nombreVis, String recurso) {
        if (visDao.existsByNombre(nombreVis)) {
            Recursos rec = recDao.findByNombre(recurso).get();
            Visualizadores visComprobar = visDao.findByNombreAndAndRecursos(cacheo.getName(),rec).get();

            String claveRandom = encriptacion.desencriptarAsim(cacheo.getName(), visComprobar.getClaveAsim(), cacheo.getPassw());
            String passw = encriptacion.desencriptar(rec.getPassword(), claveRandom);
            return claves.comprobarFirma(nombreVis, passw, rec.getFirma());

        }
        return false;
    }

    public void compartirRecurso(String nombreVis, int idrec) {
        if (visDao.existsByNombre(nombreVis)) {
            Recursos rec=new Recursos(idrec);
            Visualizadores visTienePassw = visDao.findByNombreAndAndRecursos(cacheo.getName(),rec).get();
            String claveRandom = encriptacion.desencriptarAsim(cacheo.getName(), visTienePassw.getClaveAsim(), cacheo.getPassw());
            String claveEncriptada = encriptacion.encriptarAsim(nombreVis, claveRandom);
            Visualizadores nuevoVis = visDao.findByNombreAndAndRecursos(nombreVis,rec).get();
            nuevoVis.setClaveAsim(claveEncriptada);
            nuevoVis.setRecursos(rec);
            visDao.save(nuevoVis);
        }
    }
}
