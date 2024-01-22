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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "id_recursos")
    private Recursos recursos;




}
