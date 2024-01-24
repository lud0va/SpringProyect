package com.example.springcertificadosfx.data.dao.crearClave;

import com.example.springcertificadosfx.Configuration;
import com.example.springcertificadosfx.Utils;
import jakarta.inject.Inject;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.springframework.stereotype.Repository;

import javax.security.auth.x500.X500Principal;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Repository
public class DaoClavesCert {
    private final Configuration co;

    @Inject
    public DaoClavesCert(Configuration co) {

        this.co = co;
    }

    public void crearClaveAsim(String username, String passwrd) {
        try {

            KeyPairGenerator gen = KeyPairGenerator.getInstance(Utils.randomBytes());
            KeyPair claveBase64 = gen.generateKeyPair();
            PrivateKey clavePriv = claveBase64.getPrivate();
            PublicKey clavePub = claveBase64.getPublic();

            X509Certificate cert = crearCertificado(username, clavePub,clavePriv);
            char[] keystorePassword = co.getClave().toCharArray();
            String alias = username + "mykey"; // Alias para identificar la clave en el KeyStore

            KeyStore keyStore = KeyStore.getInstance("PKCS12");

            // Agregar la clave privada y la clave pública al KeyStore
            keyStore.setKeyEntry(alias, clavePriv, keystorePassword, new java.security.cert.Certificate[]{cert});
            keyStore.load(new FileInputStream("keystore.pfx"), keystorePassword);

        } catch (NoSuchAlgorithmException | CertificateException | KeyStoreException | IOException e) {
            throw new RuntimeException(e);
        }


    }

    public String getClaveAsim(String username, String password) {
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            try (FileInputStream fis = new FileInputStream(username + "privateKey.jks")) {
                keyStore.load(fis, password.toCharArray());
            }
            Key key = keyStore.getKey(username, password.toCharArray());
            return key.toString();
        } catch (NoSuchAlgorithmException | CertificateException | KeyStoreException | IOException e) {
            throw new RuntimeException(e);
        } catch (UnrecoverableKeyException e) {
            throw new RuntimeException(e);
        }


    }

    public X509Certificate crearCertificado(String username, PublicKey clavePub,PrivateKey privateKey) {


        try {
            X509V3CertificateGenerator cert1 = new X509V3CertificateGenerator();
            cert1.setIssuerDN(new X500Principal(username));
            cert1.setSubjectDN(new X500Principal(username));
            cert1.setPublicKey(clavePub);
            cert1.setSignatureAlgorithm("SHA256WithRSAEncryption");
            return cert1.generateX509Certificate(privateKey);
        } catch (SignatureException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
