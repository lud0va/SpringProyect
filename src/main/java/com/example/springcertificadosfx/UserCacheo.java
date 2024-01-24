package com.example.springcertificadosfx;

import jakarta.inject.Singleton;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Singleton
public class UserCacheo {
    String name;
    String passw;



}
