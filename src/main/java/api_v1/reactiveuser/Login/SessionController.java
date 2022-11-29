package api_v1.reactiveuser.Login;

import api_v1.reactiveuser.Login.SubModels.Login;
import api_v1.reactiveuser.Login.SubModels.PreLogin;
import api_v1.reactiveuser.User.User;
import api_v1.reactiveuser.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
public class SessionController {
    private final SessionService sessionService;
    private final UserService userService;

    @Autowired
    public SessionController(SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @GetMapping("/verify/{id}")
    public Mono<ResponseEntity<Object>> verifySession(@PathVariable("id") String id) {
        return sessionService.findSession(id)
            .map(session -> new ResponseEntity<>(null, HttpStatus.OK))
            .defaultIfEmpty(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/register/")
    public Mono<ResponseEntity<Object>> createSession(@RequestBody PreLogin preLogin) {
        return userService.findById(preLogin.getEmail())
            .map(user -> ResponseEntity.status(409).build())
            .defaultIfEmpty(new ResponseEntity<>(
                sessionService.createSession(new Session(preLogin.getEmail(), new Date()))
                    .flatMap(temp -> {
                        User user = new User(preLogin.getEmail(), preLogin.getName(), preLogin.getPassword());
                        return userService.createUser(user);
                    }), HttpStatus.OK));
    }

    @PostMapping("/login/")
    public Mono<ResponseEntity<Mono<User>>> createLoginSession(@RequestBody Login login){
        return userService.findByEmailPassword(login.getEmail(), login.getPassword())
            .map(user -> {
                Session session = new Session(user.getEmail(), new Date());
                return new ResponseEntity<>(
                sessionService.createSession(session)
                    .flatMap(temp -> userService.findById(user.getEmail())), HttpStatus.OK);
            }).defaultIfEmpty(new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Mono<Void>>> deleteSession(@PathVariable("id") String id) {
        return sessionService.findSession(id)
        .map(s -> new ResponseEntity<>(
            sessionService.deleteSession(id), HttpStatus.ACCEPTED))
        .defaultIfEmpty(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}