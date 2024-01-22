package com.example.springcertificadosfx.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Visualizadores")
public class Visualizadores {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "firma", nullable = false)
    private String firma;
    @Column(name = "claveAsimetrica")
    private String claveAsimetrica;
    @ManyToOne
    @JoinColumn(name = "id")
    private Recursos recursos;




}
