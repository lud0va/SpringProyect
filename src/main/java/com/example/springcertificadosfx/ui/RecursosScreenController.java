package com.example.springcertificadosfx.ui;

import com.example.springcertificadosfx.data.model.Recursos;
import com.example.springcertificadosfx.servicios.RecursosServices;
import com.example.springcertificadosfx.servicios.VisualizadoresServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RecursosScreenController {


    private final ApplicationContext context;
    @FXML
    private TextField nombreVis;
    @FXML
    private TextField passwRec;
    @FXML
    private TextField nombreRec;
    @FXML
    private TableView<Recursos> recursoTabla;
    @FXML
    private TableColumn<Recursos, String> idRec;
    @FXML
    private TableColumn<Recursos, String> nameRec;

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

    private final RecursosServices serv;
    private final VisualizadoresServices servVis;

    public RecursosScreenController(ApplicationContext context, RecursosServices serv, VisualizadoresServices servVis) {
        this.context = context;


        this.serv = serv;
        this.servVis = servVis;
    }

    public void initialize() {
        cargarCosas();
    }

    public void cargarCosas() {
        idRec.setCellValueFactory(new PropertyValueFactory<>("id_recursos"));
        nameRec.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        recursoTabla.getItems().addAll(serv.getAll());
    }

    public void crearRecurso(ActionEvent event) {
        serv.crearRecurso(nombreRec.getText(), passwRec.getText());
    }

    public void cambiarPassw(ActionEvent event) {
        serv.cambiarPasswRecurso(recursoTabla.getSelectionModel().getSelectedItem().getNombre(),passwRec.getText());
    }

    public void comprobarRec(ActionEvent event) {
    }

    public void compartirRecurso(ActionEvent event) {

        try {
            servVis.compartirRecurso(nombreVis.getText(),recursoTabla.getSelectionModel().getSelectedItem().getId_recursos());

        }catch (NullPointerException n){
            comprobarRecAviso.setText("selecciona un recurso");
        }


    }
}
