package com.example.springcertificadosfx.ui;

import com.example.springcertificadosfx.servicios.UserServices;
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
    public StackPane txtPassword;

    public LoginRegisterController(ApplicationContext context, UserServices services) {
        this.context = context;
        this.services = services;
    }










}
