package com.example.springcertificadosfx;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Data
@Component
@ApplicationScope
@Scope("singleton")
public class UserCacheo {
    int id;
    String name;
    String passw;



}
