package api_v1.reactiveuser.Authentication;


import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<Auth> save(Auth auth);
    Mono<Auth> findByKey(int key);
}
