package api_v1.reactiveuser.Controller;

import api_v1.reactiveuser.Model.Favorite;
import api_v1.reactiveuser.Model.Order;
import api_v1.reactiveuser.Model.User;
import api_v1.reactiveuser.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        return userService.findById("cool@gmail.com")
            .map(User::getOrder)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/get/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getAll() {
        return userService.findAllUser();
    }

    @GetMapping(value = "/get/{id}")
    public Mono<ResponseEntity<User>> getById(@PathVariable("id") String id) {
        return userService.findById(id)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/get/order/{id}")
    public Mono<ResponseEntity<List<Order>>> getOrder(@PathVariable("id") String id) {
        return userService.findById(id)
            .map(User::getOrder)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/get/favorite/{id}")
    public Mono<ResponseEntity<List<Favorite>>> getFavorite(@PathVariable("id") String id) {
        return userService.findById(id)
            .map(User::getFavorite)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<Object>> create(@RequestBody User user) {
        return userService.findById(user.getEmail())
            .map(u -> ResponseEntity.badRequest().build())
            .defaultIfEmpty(new ResponseEntity<>(userService.createUser(user),HttpStatus.CREATED));
    }

    @PutMapping("/update")
    public ResponseEntity<Mono<User>> update(@RequestBody User user) {
        Mono<User> out = userService.updateUser(user);
        return new ResponseEntity<>(out,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Object>> deleteById(@PathVariable("id") String id) {
        return userService.findById(id)
            .flatMap(user ->
                userService.deleteById(user.getEmail())
                    .then(Mono.just(ResponseEntity.ok().build()))
            ).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
