package com.example.springcertificadosfx.data.dao.crearClave;

import com.example.springcertificadosfx.UserCacheo;
import com.example.springcertificadosfx.common.Configuration;
import com.example.springcertificadosfx.seguridad.Encriptacion;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.springframework.stereotype.Repository;

import javax.security.auth.x500.X500Principal;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Repository
public class DaoClavesCert {
    private final Configuration co;
    private final UserCacheo cacheo;
    private final Encriptacion en;

    public DaoClavesCert(Configuration co, UserCacheo cacheo, Encriptacion en) {

        this.co = co;
        this.cacheo = cacheo;
        this.en = en;
    }

    public void crearClavesDeUsuario(String username, String passwrd) {
        Security.addProvider(new BouncyCastleProvider());

        try {
            //generar claves privada y publica
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            KeyPair claveBase64 = gen.generateKeyPair();
            PrivateKey clavePriv = claveBase64.getPrivate();
            PublicKey clavePub = claveBase64.getPublic();
            //generar certificado
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            char[] keystorePassword = co.getClave().toCharArray();
            keyStore.load(new FileInputStream(co.getNombreKeystore()), keystorePassword);

            KeyStore.PasswordProtection pt = new KeyStore.PasswordProtection(co.getClave().toCharArray());
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(co.getServerName(), pt);
            PrivateKey privateKeyServidor = (PrivateKey) privateKeyEntry.getPrivateKey();

            X509Certificate cert = crearCertificado(username, clavePub, privateKeyServidor);


            // Agregar la clave privada y la clave pública al KeyStore
            keyStore.setCertificateEntry(username, cert);
            keyStore.setKeyEntry(username, clavePriv, passwrd.toCharArray(), new java.security.cert.Certificate[]{cert});

            //guardar keystore
            FileOutputStream fos = new FileOutputStream(co.getNombreKeystore());
            keyStore.store(fos, keystorePassword);


        } catch (NoSuchAlgorithmException | CertificateException | KeyStoreException | IOException e) {
            throw new RuntimeException(e);
        } catch (UnrecoverableEntryException e) {
            throw new RuntimeException(e);
        }


    }
    public  String firmar(String passw){

        try {
            KeyStore    keyStore = KeyStore.getInstance("PKCS12");
            char[] keystorePassword = co.getClave().toCharArray();
            keyStore.load(new FileInputStream(co.getNombreKeystore()), keystorePassword);
            KeyStore.PasswordProtection pt = new KeyStore.PasswordProtection(cacheo.getPassw().toCharArray());
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(cacheo.getName(), pt);
            PrivateKey privateKeyUsuario = (PrivateKey) privateKeyEntry.getPrivateKey();


            Signature sign = Signature.getInstance("SHA256WithRSA");
            sign.initSign(privateKeyUsuario);
            sign.update(passw.getBytes());
            byte[] firma = sign.sign();
            return firma.toString();
        } catch (KeyStoreException | UnrecoverableEntryException | CertificateException | IOException |
                 NoSuchAlgorithmException | SignatureException | InvalidKeyException e) {
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

    public X509Certificate crearCertificado(String username, PublicKey clavePub, PrivateKey privateKey) {


        try {
            X509V3CertificateGenerator cert1 = new X509V3CertificateGenerator();
            cert1.setSerialNumber(BigInteger.valueOf(1));
            cert1.setIssuerDN(new X500Principal("CN=" + username));
            cert1.setSubjectDN(new X500Principal("CN=" + username));
            cert1.setPublicKey(clavePub);
            cert1.setNotBefore(
                    Date.from(LocalDate.now().plus(365, ChronoUnit.DAYS).atStartOfDay().toInstant(ZoneOffset.UTC)));
            cert1.setNotAfter(new Date());
            cert1.setSignatureAlgorithm("SHA256WithRSAEncryption");
            return cert1.generateX509Certificate(privateKey);
        } catch (SignatureException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
