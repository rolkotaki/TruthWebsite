package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestPassword {

    public static void main(String[] args) {
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String password = "alma";
            String ecryptedPwd = "";
            
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            ecryptedPwd = sb.toString();
            
            System.out.println(password);
            System.out.println(ecryptedPwd);
            
        } catch (NoSuchAlgorithmException nsa) {
            
        }
    }
    
}
