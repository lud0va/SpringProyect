package com.example.springcertificadosfx.servicios;

import com.example.springcertificadosfx.Configuration;
import com.example.springcertificadosfx.UserCacheo;
import com.example.springcertificadosfx.data.dao.UserDao;
import com.example.springcertificadosfx.data.dao.crearClave.DaoClavesCert;
import com.example.springcertificadosfx.data.model.Users;
import jakarta.inject.Inject;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    private final UserDao dao;
    private final DaoClavesCert daoAsim;
    private final Configuration co;

    private UserCacheo uc;

    @Inject
    public UserServices(UserDao dao, DaoClavesCert daoAsim, Configuration co) {
        this.dao = dao;
        this.daoAsim = daoAsim;
        this.co = co;
    }

    public void register(String name, String passw) {
        String str = co.createPasswordEncoder().encode(passw);
        dao.save(new Users(name, str));
        daoAsim.crearClaveAsim(name, passw);
    }

    public boolean login(String name, String passw) {
        Users user = dao.findByUsername(name).get();

        if (co.createPasswordEncoder().matches(passw, user.getPassword())) {
            uc.setName(user.getUsername());
            uc.setPassw(user.getPassword());
            return true;

        }
        return false;
    }
}
