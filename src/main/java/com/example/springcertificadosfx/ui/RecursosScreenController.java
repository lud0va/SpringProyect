package com.example.springcertificadosfx.ui;

import com.example.springcertificadosfx.data.model.Recursos;
import com.example.springcertificadosfx.servicios.UserServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RecursosScreenController {


    private final ApplicationContext context;
    @FXML
    private TableView<Recursos> recursoTabla;
    @FXML
    private TableColumn<Recursos,String> idRec;
    @FXML
    private TableColumn<Recursos,String> nameRec;

    @FXML
    private Button crearRecurso;
    @FXML
    private Button cambiarPasswRec;

    @FXML
    private Button comprobarRec;
    @FXML
    private Label recCreated;

    @FXML
    private Label cambiarRecAviso;
    @FXML
    private Label comprobarRecAviso;


    public RecursosScreenController(ApplicationContext context) {
        this.context = context;

    }


    public void crearRecurso(ActionEvent event) {
    }

    public void cambiarPassw(ActionEvent event) {
    }

    public void comprobarRec(ActionEvent event) {
    }
}
