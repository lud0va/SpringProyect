package com.example.springcertificadosfx.ui;


import com.example.springcertificadosfx.common.Constantes;
import com.example.springcertificadosfx.servicios.UserServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
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
    @FXML
    public TextField txtUser;
    @FXML
    public TextField txtPassword;
    private final UserServices services;

    public PrincipalController(ApplicationContext context, UserServices services) {

        this.context = context;
        this.services = services;
    }


    public void initialize() {


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

    public void login(ActionEvent event) {
        if (services.login(txtUser.getText(), txtPassword.getText())){
            cargarPantalla(Constantes.FXML_REGISTER_SCREEN_FXML);
        }
    }

    public void register(ActionEvent event) {
        services.register(txtUser.getText(), txtPassword.getText());

    }
}
