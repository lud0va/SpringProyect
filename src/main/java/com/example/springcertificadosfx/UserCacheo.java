package com.example.springcertificadosfx;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.annotation.ApplicationScope;

@Data
@ApplicationScope
public class UserCacheo {
    String name;
    String passw;



}
