package api_v1.reactiveuser.Utilities;

import org.springframework.mail.SimpleMailMessage;

import java.util.Random;

public class AuthUtilities {
    public static int generateKey() {
        return new Random().nextInt(900000) + 100000;
    }

    public static SimpleMailMessage generateEmail(String email, int key) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("NibNav");
        message.setTo(email);
        message.setSubject("Authentication Key | Expires in 3 minutes");
        message.setText("key : " + key);
        return message;
    }
}
