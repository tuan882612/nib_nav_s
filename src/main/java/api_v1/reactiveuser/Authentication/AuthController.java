package api_v1.reactiveuser.Authentication;

import api_v1.reactiveuser.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private UserService userService;

    @Autowired
    public AuthController(
            AuthService authService,
            UserService userService,
            JavaMailSender sender) {
        this.authService = authService;
        this.userService = userService;
        this.sender = sender;
    }

    @PostMapping("/retrieve/{id}")
    public Mono<ResponseEntity<Object>> retrieveAuthInfo(@PathVariable("id") String id) {
        return userService.findById(id)
            .map(user -> ResponseEntity.status(409).build())
            .switchIfEmpty(
                authService.save(new Auth(id, generateKey()))
                    .map(auth -> {
                        sender.send(generateEmail(auth.getEmail(),auth.getKey()));
                        return new ResponseEntity<>(auth, HttpStatus.CREATED);
                    }));
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