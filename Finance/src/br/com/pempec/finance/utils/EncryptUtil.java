package br.com.pempec.finance.utils;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtil {

    private final String key = "a393kl129403llpwsdf";
    private final byte[] salt = {
        (byte) 0xc2, (byte) 0x71, (byte) 0x31, (byte) 0x8b,
        (byte) 0x1e, (byte) 0x08, (byte) 0xcc, (byte) 0xbb
    };
    private final int count = 76;
    private PBEKeySpec pbeKeySpec;
    private PBEParameterSpec pbeParamSpec;
    private SecretKeyFactory keyFactory;
    private SecretKey pbeKey;
    private Cipher pbeCipher;

    public EncryptUtil() throws Exception {
        pbeKeySpec = new PBEKeySpec(key.toCharArray());
        pbeParamSpec = new PBEParameterSpec(salt, count);
        try {
            keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new Exception("Error finding the cipher algorithm");
        }
        pbeKey = keyFactory.generateSecret(pbeKeySpec);
        pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");

    }

    private void initEncryptMode() throws InvalidKeyException, InvalidAlgorithmParameterException {
        pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);
    }

    private void initDecryptMode() throws InvalidKeyException, InvalidAlgorithmParameterException {
        pbeCipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);
    }

    public String encrypt(String texto) {
        if (texto == null) {
            return null;
        }
        if (texto.equals("")) {
            return "";
        }
        byte[] ciphertext;
        try {
            initEncryptMode();
            ciphertext = pbeCipher.doFinal(texto.getBytes());
        } catch (InvalidKeyException e1) {
            e1.printStackTrace();
            return null;
        } catch (InvalidAlgorithmParameterException e1) {
            e1.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e1) {
            e1.printStackTrace();
            return null;
        } catch (BadPaddingException e1) {
            e1.printStackTrace();
            return null;
        }
        String resp = null;
        try {
            BASE64Encoder base64 = new BASE64Encoder();
            resp = base64.encode(ciphertext);
        } catch (Exception e) {
            System.out.println("Problema com a codifica��o: encripta��o " + e.getMessage());
            //e.printStackTrace();
        }
        return resp;
    }

    public String encrypt(byte[] texto) {
        if (texto == null) {
            return null;
        }
        if (texto.equals("")) {
            return "";
        }
        try {
            byte[] ciphertext;
            initEncryptMode();
            ciphertext = pbeCipher.doFinal(texto);
            String resp = null;
            BASE64Encoder base64 = new BASE64Encoder();
            resp = base64.encode(ciphertext);
            return resp;
        } catch (InvalidKeyException e1) {
            e1.printStackTrace();
        } catch (InvalidAlgorithmParameterException e1) {
            e1.printStackTrace();
        } catch (IllegalBlockSizeException e1) {
            e1.printStackTrace();
        } catch (BadPaddingException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            System.out.println("Problema com a codifica��o: encripta��o " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public String decrypt(String texto) {
        if (texto == null) {
            return null;
        }
        System.out.println(texto.length());
        System.out.println(texto.length() % 12);
        if (texto.length() % 12 > 0) {
            int falta = 12 - (texto.length() % 12);
            StringBuffer sb = new StringBuffer();
            sb.append(texto);
            for (int i = 0; i < falta; i++) {
                sb.append("=");
            }
            texto = sb.toString();
        }
        try {
            initDecryptMode();
        } catch (InvalidKeyException e1) {
            e1.printStackTrace();
            return null;
        } catch (InvalidAlgorithmParameterException e1) {
            e1.printStackTrace();
            return null;
        }

        byte[] entrada = null;
        try {
            BASE64Decoder base64 = new BASE64Decoder();
            entrada = base64.decodeBuffer(texto);
        } catch (RuntimeException e) {
            System.out.println("Problema com a codifica��o: desencripta��o " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Problema com a decodifica��o: desencripta��o (io???)" + e.getMessage());
            e.printStackTrace();
        }
        if (entrada != null) {
            byte[] cleartext = null;
            try {
                cleartext = pbeCipher.doFinal(entrada);
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
                return null;
            } catch (BadPaddingException e) {
                e.printStackTrace();
                return null;
            }
            return new String(cleartext);
        }
        return null;
    }

    public byte[] decryptToByte(String texto) {
        if (texto == null) {
            return null;
        }
        try {
            initDecryptMode();
            byte[] entrada = null;
            BASE64Decoder base64 = new BASE64Decoder();
            entrada = base64.decodeBuffer(texto);
            byte[] cleartext = null;
            if (entrada != null) {
                cleartext = pbeCipher.doFinal(entrada);
            }
            return cleartext;
        } catch (InvalidKeyException e1) {
            e1.printStackTrace();
            return null;
        } catch (InvalidAlgorithmParameterException e1) {
            e1.printStackTrace();
            return null;
        } catch (RuntimeException e) {
            System.out.println("Problema com a codifica��o: desencripta��o " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Problema com a decodifica��o: desencripta��o (io???)" + e.getMessage());
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
