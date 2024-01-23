package com.example.springcertificadosfx;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCertificadosFxApplication {

    public static void main(String[] args) {
        Application.launch(DIJavafx.class, args);
    }

}
