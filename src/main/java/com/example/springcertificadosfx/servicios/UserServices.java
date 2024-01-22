package com.example.springcertificadosfx.servicios;

import com.example.springcertificadosfx.data.dao.UserDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    private final UserDao dao;


    public UserServices(@Qualifier("userDao") UserDao dao) {
        this.dao = dao;
    }

    public void register(String name,String passw){
        dao.register(name,passw);
        dao.crearClavesUsuario();
    }
}
