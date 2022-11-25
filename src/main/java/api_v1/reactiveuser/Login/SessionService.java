package api_v1.reactiveuser.Login;

import reactor.core.publisher.Mono;

public interface SessionService {
    Mono<Session> createSession(Session session);
    Mono<Session> findSession(String id);
    Mono<Void> deleteSession(String id);
}
