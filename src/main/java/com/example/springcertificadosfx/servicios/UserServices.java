package com.example.springcertificadosfx.servicios;

import com.example.springcertificadosfx.common.Configuration;
import com.example.springcertificadosfx.UserCacheo;
import com.example.springcertificadosfx.data.dao.UserDao;
import com.example.springcertificadosfx.data.dao.crearClave.DaoClavesCert;
import com.example.springcertificadosfx.data.model.Users;

import org.springframework.stereotype.Service;

@Service
public class UserServices {

    private final UserDao dao;
    private final DaoClavesCert daoAsim;
    private final Configuration co;

    private final UserCacheo uc;


    public UserServices(UserDao dao, DaoClavesCert daoAsim, Configuration co, UserCacheo uc) {
        this.dao = dao;
        this.daoAsim = daoAsim;
        this.co = co;
        this.uc = uc;
    }

    public void register(String name, String passw) {
        String str = co.createPasswordEncoder().encode(passw);
        dao.save(new Users(name, str));
        daoAsim.crearClavesDeUsuario(name, passw);
    }

    public boolean login(String name, String passw) {
        Users user = dao.findByUsername(name).get();

        if (co.createPasswordEncoder().matches(passw, user.getPassword())) {
            uc.setName(user.getUsername());
            uc.setPassw(passw);
            return true;

        }
        return false;
    }
}
