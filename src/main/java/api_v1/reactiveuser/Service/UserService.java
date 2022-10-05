package api_v1.reactiveuser.Service;

import api_v1.reactiveuser.Model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Flux<User> findAllUser();
    Mono<User> findById(String id);
    Mono<User> createUser(User user);
    Mono<User> updateUser(User user);
    Mono<Void> deleteById(String id);
    String testGet();
}
