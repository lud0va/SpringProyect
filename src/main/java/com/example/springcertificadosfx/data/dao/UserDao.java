package com.example.springcertificadosfx.data.dao;

import com.example.springcertificadosfx.data.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends ListCrudRepository<Users,Long> {


    Optional<Users> findByUsername(String userName);

    boolean existsByUsername(String username);
}
