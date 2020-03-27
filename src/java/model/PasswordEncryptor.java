package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {
    
    public static String encryptPassword(String pwd) {
        String ecryptedPwd = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(pwd.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            ecryptedPwd = sb.toString();
        } catch (NoSuchAlgorithmException nsa) {
            return "";
        }
        return ecryptedPwd;
    }
    
}
