package com.example.springcertificadosfx.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Recursos")
public class Recursos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firma", nullable = false)
    private String firma;
    @OneToMany(mappedBy = "recursos")
    private List<Visualizadores> visualizadores;
}
