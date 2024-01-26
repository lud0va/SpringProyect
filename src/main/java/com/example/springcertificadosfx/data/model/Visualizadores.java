package com.example.springcertificadosfx.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "visualizadores")
@AllArgsConstructor
@NoArgsConstructor
public class Visualizadores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "clave_asim",nullable = false)
    private String claveAsim;

    @ManyToOne
    @JoinColumn(name = "id_recursos")
    private Recursos recursos;

    public Visualizadores(String nombre, String claveAsim, Recursos recursos) {
        this.nombre = nombre;
        this.claveAsim = claveAsim;
        this.recursos = recursos;
    }
}
