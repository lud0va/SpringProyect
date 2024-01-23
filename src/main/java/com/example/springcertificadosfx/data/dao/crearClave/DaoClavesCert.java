package com.example.springcertificadosfx.data.dao.crearClave;

import com.example.springcertificadosfx.Utils;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Date;

@Repository
public class DaoClavesCert {
    public void crearClaveAsim(String username,String passwrd) {
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance(Utils.randomBytes());
            KeyPair claveBase64 = gen.generateKeyPair();
            PrivateKey clavePriv = claveBase64.getPrivate();
            char[] keystorePassword = passwrd.toCharArray();
            String alias = username+"mykey"; // Alias para identificar la clave en el KeyStore

            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(null, keystorePassword);

            // Agregar la clave privada y la clave pública al KeyStore
            keyStore.setKeyEntry(alias, clavePriv, keystorePassword, new java.security.cert.Certificate[]{});

            // Guardar el KeyStore en un archivo
            try (FileOutputStream fos = new FileOutputStream(username+"privateKey.jks")) {
                keyStore.store(fos, keystorePassword);
            }
        } catch (NoSuchAlgorithmException | CertificateException | KeyStoreException | IOException e) {
            throw new RuntimeException(e);
        }


    }
    public String getClaveAsim(String username,String password){
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            try (FileInputStream fis = new FileInputStream(username+"privateKey.jks")) {
                keyStore.load(fis, password.toCharArray());
            }
            Key key = keyStore.getKey(username, password.toCharArray());
            return key.toString();
        }catch (NoSuchAlgorithmException | CertificateException | KeyStoreException | IOException e) {
            throw new RuntimeException(e);
        } catch (UnrecoverableKeyException e) {
            throw new RuntimeException(e);
        }



    }

    public void crearCertificadoPublic(String username,String password){

        try {
            Security.addProvider(new BouncyCastleProvider());
            KeyPairGenerator keyGen = null; // Hace uso del provider BC
            keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048,new SecureRandom());  // tamano clave 512 bits
            KeyPair clavesRSA = keyGen.generateKeyPair();
            PrivateKey clavePrivada = clavesRSA.getPrivate();
            PublicKey clavePublica = clavesRSA.getPublic();

            // crear un certificado con CErtificateBuilder
            X500Name owner = new X500Name("CN=Oscar");
            X500Name issuer = new X500Name("CN=Servidor");
            X509v3CertificateBuilder certGen = new X509v3CertificateBuilder(
                    issuer,
                    BigInteger.valueOf(1),
                    new Date(),
                    new Date(System.currentTimeMillis()+1000000),
                    owner, SubjectPublicKeyInfo.getInstance(
                    ASN1Sequence.getInstance(clavePublica.getEncoded()))

            );
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


    }
}
