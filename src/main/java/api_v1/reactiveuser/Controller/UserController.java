package api_v1.reactiveuser.Controller;

import api_v1.reactiveuser.Model.Order;
import api_v1.reactiveuser.Model.User;
import api_v1.reactiveuser.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public Mono<ResponseEntity<List<Order>>> test() {
        return userService.findById("test@gmail.om")
                .map(User::getOrder)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @GetMapping(value = "/get/all")
    public Flux<User> getAll() {
        return userService.findAllUser();
    }

    @GetMapping(value = "/get/{id}")
    public Mono<ResponseEntity<User>> getById(@PathVariable("id") String id) {
        return userService.findById(id)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
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
}
