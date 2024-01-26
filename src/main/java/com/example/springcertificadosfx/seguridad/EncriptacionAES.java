package com.example.springcertificadosfx.seguridad;

import com.google.common.primitives.Bytes;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
@Component
public class EncriptacionAES implements Encriptacion {
    @Override
    public String encriptar(String textoToEncript, String clave) {
        try {

            byte[] iv = new byte[12];
            byte[] salt = new byte[16];
            SecureRandom sr = new SecureRandom();
            sr.nextBytes(iv);
            sr.nextBytes(salt);
            GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);


            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            // en el jdk8 esta limitado a 128 bits, desde el 9 puede ser de 256
            KeySpec spec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/GCM/noPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);
            return Base64.getUrlEncoder().encodeToString(Bytes.concat(iv, salt,
                    cipher.doFinal(textoToEncript.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public String desencriptar(String textoToDeEncript, String clave) {
        try {
            byte[] decoded = Base64.getUrlDecoder().decode(textoToDeEncript);

            byte[] iv = Arrays.copyOf(decoded, 12);
            byte[] salt = Arrays.copyOfRange(decoded, 12, 28);

            GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/GCM/noPADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);
            return new String(cipher.doFinal(Arrays.copyOfRange(decoded, 28, decoded.length)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String encriptarAsim(String passw, String clave) {
        return null;
    }

    @Override
    public String desencriptarAsim(String passw, String clave) {
        return null;
    }


}
