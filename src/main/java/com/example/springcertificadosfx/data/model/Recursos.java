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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_recursos", nullable = false)
    private int id_recursos;
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "recursos")
    private List<Visualizadores> visualizadores;
}
