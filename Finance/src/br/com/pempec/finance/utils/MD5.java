/**
 *
 */
package br.com.pempec.finance.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Luis Alexandre
 *
 */
public final class MD5 {

    private static MessageDigest messageDigest;
    private static String MD5 = "MD5";

    static {

        try {

            messageDigest = MessageDigest.getInstance(MD5);

        } catch (NoSuchAlgorithmException ex) {

            ex.printStackTrace();

        }

    }

    private static char[] hexCodes(byte[] text) {

        char[] hexOutput = new char[text.length * 2];

        String hexString;

        for (int i = 0; i < text.length; i++) {

            hexString = "00" + Integer.toHexString(text[i]);

            hexString.toUpperCase().getChars(hexString.length() - 2,
                    hexString.length(), hexOutput, i * 2);

        }

        return hexOutput;

    }

    public static String criptografar(String pwd) {

        if (messageDigest != null) {

            return new String(hexCodes(messageDigest.digest(pwd.getBytes())));

        }

        return null;

    }
}
