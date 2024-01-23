package com.example.springcertificadosfx.servicios;

import com.example.springcertificadosfx.data.dao.UserDao;
import com.example.springcertificadosfx.data.dao.crearClave.DaoClaveAsimetrica;
import com.example.springcertificadosfx.data.model.Users;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    private final UserDao dao;
    private final DaoClaveAsimetrica daoAsim;

    public UserServices(UserDao dao, DaoClaveAsimetrica daoAsim) {
        this.dao = dao;
        this.daoAsim = daoAsim;
    }

    public void register(String name,String passw){
        dao.save(new Users(name,passw));
        daoAsim.crearClaveAsim();
    }
    public boolean login(){
        return false;
    }
}
