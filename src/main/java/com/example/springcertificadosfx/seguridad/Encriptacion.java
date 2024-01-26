package com.example.springcertificadosfx.seguridad;

public interface Encriptacion {
    String encriptar(String texto,String clave);

    String desencriptar(String texto,String clave);

    String encriptarAsim(String passw,String clave);

    String desencriptarAsim(String passw,String clave);
}
