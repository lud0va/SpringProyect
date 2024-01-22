package com.example.springcertificadosfx.data.dao;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

    private final JdbcClient jdbcClient;

    public UserDaoImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Boolean login(String username, String password) {
        return null;
    }

    @Override
    public void register(String username, String password) {
        jdbcClient.sql("insert into Users (username, password) values (?, ?)")
                .param(1,username)
                .param(2, password)
                .update();
    }

    @Override
    public void crearClavesUsuario() {

    }

    @Override
    public String getPrivateKey(String password) {
        return null;
    }

    @Override
    public String getCertificado(String clavePublica) {
        return null;
    }
}
