package api_v1.reactiveuser.Controller;

import api_v1.reactiveuser.Model.User;
import api_v1.reactiveuser.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(
        value = "/get/all",
        produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<User> findAll() {
        return userService.findAllUser();
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Mono<User>> findUserById(@PathVariable String id) {
        Mono<User> user = userService.findById(id);
        HttpStatus status = (user == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK;

        return new ResponseEntity<>(user, status);
    }

    @GetMapping(value = "/get/{email}")
    public ResponseEntity<Mono<User>> findUserByEmail(@PathVariable String email) {
        Mono<User> user = userService.findByEmail(email);
        HttpStatus status = (user == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK;

        return new ResponseEntity<>(user, status);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }
}
