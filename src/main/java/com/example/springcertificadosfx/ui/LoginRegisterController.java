package com.example.springcertificadosfx.ui;

import com.example.springcertificadosfx.servicios.UserServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class LoginRegisterController {


    private final ApplicationContext context;
    private final UserServices services;
    @FXML
    public TextField txtUser;
    @FXML
    public TextField txtPassword;

    public LoginRegisterController(ApplicationContext context, UserServices services) {
        this.context = context;
        this.services = services;
    }


    public void login(ActionEvent event) {
    }

    public void register(ActionEvent event) {
        services.register(txtUser.getText(),txtPassword.getText());
    }
}
