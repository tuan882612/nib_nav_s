package api_v1.reactiveuser.User.Order;

import api_v1.reactiveuser.User.User;
import api_v1.reactiveuser.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user/")
public class OrderController {

    private final UserService userService;

    @Autowired
    public OrderController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(value = "/update/order")
    public Mono<ResponseEntity<Mono<User>>> update(@RequestBody User user) {
        return userService.findById(user.getEmail())
            .map(u -> new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK));
    }
}