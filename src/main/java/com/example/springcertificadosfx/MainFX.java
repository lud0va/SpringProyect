package com.example.springcertificadosfx;


import com.example.springcertificadosfx.common.Constantes;
import com.example.springcertificadosfx.ui.PrincipalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class MainFX implements ApplicationListener<DIJavafx.StageReadyEvent> {


    private final FXMLLoader fxmlLoader;

    public MainFX(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }


    @Override
    public void onApplicationEvent(DIJavafx.StageReadyEvent event) {
        try {
            Stage stage = event.getStage();

            Parent fxmlParent = fxmlLoader.load(getClass().getResourceAsStream(Constantes.FXML_PRINCIPALSCREEN_FXML));
            PrincipalController controller = fxmlLoader.getController();
            stage.setScene(new Scene(fxmlParent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
