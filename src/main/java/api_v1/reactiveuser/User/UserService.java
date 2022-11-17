package api_v1.reactiveuser.User;


import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> findById(String id);
    Mono<User> findByEmailPassword(String email, String password);
    Mono<User> createUser(User user);
    Mono<User> updateUser(User user);
    Mono<Void> deleteById(String id);
    String testGet();
}
