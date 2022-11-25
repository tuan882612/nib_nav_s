package api_v1.reactiveuser.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SessionServiceImp implements SessionService {
    private final SessionRepository repository;

    @Autowired
    public SessionServiceImp(SessionRepository sessionRepository) {
        repository = sessionRepository;
    }

    @Override
    public Mono<Session> createSession(Session session) {
        return repository.save(session);
    }

    @Override
    public Mono<Session> findSession(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Void> deleteSession(String id) {
        return repository.deleteById(id);
    }
}
