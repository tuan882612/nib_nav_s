package api_v1.reactiveuser.Authentication;

import api_v1.reactiveuser.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Date;

import static api_v1.reactiveuser.Utilities.AuthUtilities.generateEmail;
import static api_v1.reactiveuser.Utilities.AuthUtilities.generateKey;

@RestController
@RequestMapping("/auth/")
public class AuthController {
    private final JavaMailSender sender;
    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(
            AuthService authService,
            UserService userService,
            JavaMailSender sender) {
        this.authService = authService;
        this.userService = userService;
        this.sender = sender;
    }

    @PostMapping("/generate/{id}")
    public Mono<ResponseEntity<Mono<Auth>>> generateAuthInfo(@PathVariable("id") String id) {
        return userService.findById(id)
            .map(user -> new ResponseEntity<>(
                authService.save(new Auth(id, generateKey(), false, new Date()))
                .map(auth -> {
                    SimpleMailMessage message = generateEmail(auth.getEmail(),auth.getKey());
                    sender.send(message);
                    return auth;
                }), HttpStatus.CONFLICT))
            .defaultIfEmpty(
                new ResponseEntity<>(
                    authService.save(new Auth(id, generateKey(), false, new Date()))
                    .map(auth -> {
                        SimpleMailMessage message = generateEmail(auth.getEmail(),auth.getKey());
                        sender.send(message);
                        return auth;
                    }), HttpStatus.CREATED));
    }

    @GetMapping("/verify/{key}")
    public Mono<ResponseEntity<Mono<Auth>>> verifyAuthInfo(@PathVariable("key") int key) {
        return authService.findByKey(key)
            .map(body -> {
                body.setFound(true);
                return new ResponseEntity<>(Mono.just(body), HttpStatus.OK);
            }).defaultIfEmpty(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}