package com.example.springcertificadosfx.common;

import javafx.fxml.FXMLLoader;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.util.Properties;

@org.springframework.context.annotation.Configuration
@Getter
public class Configuration {
    private String clave;
    private String nombreKeystore;
    private String serverName;


    public Configuration() {
        try {
            Properties p = new Properties();
            p.loadFromXML(Configuration.class.getClassLoader().getResourceAsStream("config/claveKeystore.xml"));
            this.clave = p.getProperty("clave");
            this.nombreKeystore=p.getProperty("keystoreName");
            this.serverName=p.getProperty("serverName");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    @Bean
    public FXMLLoader createLoader(ApplicationContext context) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(context::getBean);
        return loader;
    }

    @Bean
    public PasswordEncoder createPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
