package com.example.springcertificadosfx.data.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    Boolean login(String username,String password);

    void register(String username,String password);

}
