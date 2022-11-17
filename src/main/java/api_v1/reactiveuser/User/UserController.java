package api_v1.reactiveuser.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/get/{id}")
    public Mono<ResponseEntity<User>> getById(@PathVariable("id") String id) {
        return userService.findById(id)
            .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
            .defaultIfEmpty(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<Object>> create(@RequestBody User user) {
        return userService.findById(user.getEmail())
            .map(u -> new ResponseEntity<>(null, HttpStatus.CONFLICT))
            .defaultIfEmpty(new ResponseEntity<>(
                userService.createUser(user), HttpStatus.CREATED));
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<Mono<User>>> update(@RequestBody User user) {
        return userService.findById(user.getEmail())
            .map(u -> new ResponseEntity<>(
                userService.updateUser(user), HttpStatus.OK))
            .defaultIfEmpty(new ResponseEntity<>(
                userService.createUser(user), HttpStatus.CREATED));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Mono<Void>>> delete(@PathVariable("id") String id) {
        return userService.findById(id)
            .map(user -> new ResponseEntity<>(
                userService.deleteById(user.getEmail()), HttpStatus.ACCEPTED))
            .defaultIfEmpty(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
