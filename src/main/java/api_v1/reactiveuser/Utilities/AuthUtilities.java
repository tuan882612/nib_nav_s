package api_v1.reactiveuser.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Random;

public class AuthUtilities {
    public static int generateKey() {
        return new Random().nextInt(900000) + 100000;
    }

    public static SimpleMailMessage generateEmail(String email, int key) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nibnav0@gmail.com");
        message.setTo(email);
        message.setSubject("NibNav Authentication Key");
        message.setText("key : " + key);
        return message;
    }
}
