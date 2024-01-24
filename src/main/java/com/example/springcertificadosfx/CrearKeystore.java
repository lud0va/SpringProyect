package com.example.springcertificadosfx;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

   /*public class CrearKeystore {
  public static void main(String[] args) {
     try {
            Configuration c = new Configuration();
            String s= c.getClave();

            KeyStore keyStore =KeyStore.getInstance("PKCS12");
            keyStore.load(null,null );
            keyStore.store(new FileOutputStream("keystore.pfx"),s.toCharArray());

        } catch (KeyStoreException | CertificateException | IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}*/
