package api_v1.reactiveuser.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Random;

import static api_v1.reactiveuser.Utilities.AuthUtilities.generateEmail;
import static api_v1.reactiveuser.Utilities.AuthUtilities.generateKey;

@RestController
@RequestMapping("/auth/")
public class AuthController {
    private final JavaMailSender sender;
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService, JavaMailSender sender) {
        this.authService = authService;
        this.sender = sender;
    }

    @PostMapping("/retrieve/{id}")
    public ResponseEntity<Mono<Auth>> retrieveAuthInfo(@PathVariable("id") String id) {
        int key = generateKey();
        sender.send(generateEmail(id,key));
        return new ResponseEntity<>(authService.save(new Auth(id,key)), HttpStatus.OK);
    }

//    @GetMapping("verify/{id}")
//    public void verifyAuthInfo(@PathVariable("id") int id) {
//        Mono<Auth> entity = authService.find(id);
//    }
}
/*
    verifyAuth arg1=id -> key
        entity = repo.find(key)
        repo.delete(entity)
        String body = (entity is NULL)? "{auth:false}":"{auth:true}"
        return new response-body(body, status 200)
*/