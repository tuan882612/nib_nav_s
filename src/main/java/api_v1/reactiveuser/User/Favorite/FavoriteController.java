package api_v1.reactiveuser.User.Favorite;

import api_v1.reactiveuser.User.User;
import api_v1.reactiveuser.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/user/")
@RestController
public class FavoriteController {
    private final UserService userService;

    @Autowired
    public FavoriteController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(value = "/update/favorite")
    public Mono<ResponseEntity<Mono<User>>> update(@RequestBody User user) {
        return userService.findById(user.getEmail())
            .map(u -> new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK));
    }
}
