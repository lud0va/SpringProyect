package com.example.springcertificadosfx.ui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PrincipalController {


    private final ApplicationContext context;
    @FXML
    public BorderPane root;

    public PrincipalController( ApplicationContext context) {

        this.context = context;
    }


    public void initialize() {

        cargarPantalla("/fxml/registerScreen.fxml");
    }

    private Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(context::getBean);
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            root.setCenter(panePantalla);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return panePantalla;
    }

}
