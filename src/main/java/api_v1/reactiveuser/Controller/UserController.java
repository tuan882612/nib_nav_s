package api_v1.reactiveuser.Controller;

import api_v1.reactiveuser.Model.User;
import api_v1.reactiveuser.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
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

    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }
}
