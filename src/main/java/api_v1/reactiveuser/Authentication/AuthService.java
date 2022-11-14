package api_v1.reactiveuser.Authentication;


import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<Auth> save(Auth auth);
    Mono<Auth> find(int key);
    Mono<Auth> findByEmail(String email);
    Mono<Void> delete(Auth auth);
}
