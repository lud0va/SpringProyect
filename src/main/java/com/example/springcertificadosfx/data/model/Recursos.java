package com.example.springcertificadosfx.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recursos")
public class Recursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recursos", nullable = false)
    private int id_recursos;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name="password")
    private String password;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "firma")
    private String firma;
    public Recursos(String nombre, String password,String firma) {
        this.nombre = nombre;
        this.password = password;
        this.firma=firma;
    }


}
