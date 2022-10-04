package api_v1.reactiveuser.Controller;

import api_v1.reactiveuser.Model.User;
import api_v1.reactiveuser.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(
        value = "/get/all",
        produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<User> getAll() {
        return userService.findAllUser();
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Mono<User>> getById(@PathVariable("id") String id) {
        Mono<User> user = userService.findById(id);

        return new ResponseEntity<>(user, user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/get/email/{email}")
    public ResponseEntity<Mono<User>> getByEmail(@PathVariable("email") String email) {
        Mono<User> user = userService.findByEmail(email);

        return new ResponseEntity<>(user, user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Mono<Void>> create(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>(null,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Mono<User>> update(@RequestBody User user) {
        Mono<User> out = userService.updateUser(user);
        return new ResponseEntity<>(out,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mono<Void>> deleteById(@PathVariable("id") String id) {
        Mono<Void> user = userService.deleteById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/delete/email/{email}")
    public ResponseEntity<Mono<Void>> deleteByEmail(@PathVariable("email") String email) {
        Mono<Void> user = userService.deleteByEmail(email);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
