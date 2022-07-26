package dd.projects.ddshop.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
    public static String getMD5EncryptedValue(final String password) {

        try {
            final MessageDigest md5 = MessageDigest.getInstance("MD5");
            final byte messageDigest[] = md5.digest(password.getBytes());
            final BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashText = no.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;


        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException();

        }
    }
}