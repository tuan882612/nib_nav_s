package api_v1.reactiveuser.Authentication;

import api_v1.reactiveuser.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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
    public Mono<ResponseEntity<Object>> generateAuthInfo(@PathVariable("id") String id) {
        return userService.findById(id)
            .map(user -> ResponseEntity.status(409).build())
            .defaultIfEmpty(
                new ResponseEntity<>(
                    authService.save(new Auth(id, generateKey(), false))
                    .map(auth -> {
                        SimpleMailMessage message = generateEmail(auth.getEmail(),auth.getKey());
                        sender.send(message);
                        return auth;
                    }), HttpStatus.CREATED));
    }

    @GetMapping("/verify/")
    public Mono<ResponseEntity<Auth>> verifyAuthInfo(@RequestBody Auth auth) {
        return authService.findByKey(auth.getKey())
            .map(body -> {
                body.setFound(true);
                return new ResponseEntity<>(body, HttpStatus.OK);
            }).defaultIfEmpty(new ResponseEntity<>(auth, HttpStatus.OK));
    }

    @DeleteMapping("/clean/")
    public Mono<Void> cleanAuth(@RequestBody Auth auth) {
        return authService.delete(auth);
    }
}