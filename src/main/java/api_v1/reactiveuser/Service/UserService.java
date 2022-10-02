package api_v1.reactiveuser.Service;

import api_v1.reactiveuser.Model.User;
import reactor.core.publisher.Flux;

public interface UserService {
    Flux<User> findAllUser();
    void createUser(User user);
}
